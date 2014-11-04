package com.daniel.osgi;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.foo.paint.PaintFrame;
import org.foo.paint.ShapeTracker;
import org.foo.shape.SimpleShape;
import org.foo.shape.trapezoid.Trapezoid;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public class Main {
	private static Framework fwk;
	
	private static PaintFrame frame = null;
	private static ShapeTracker shapeTracker = null;
	
	public static void main(String[] args) throws Exception{
		addShutdownHook();
		fwk = createFramework();
		publishTrapezoidService();
		createPaintFrame();
	}
	
	/**
	   * Adds a shutdown hook to the JVM process to cleanly shutdown
	   * the embedded framework instance.
	   */
	  private static void addShutdownHook() {
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	      public void run() {
	        try {
	          if (fwk != null) {
	            fwk.stop();
	            fwk.waitForStop(0);
	          }
	        } catch (Exception ex) {
	          System.err.println("Error stopping framework: " + ex);
	        }
	      }
	    });
	  }
	  
	private static Framework createFramework() throws Exception{
		File[] files = new File("bundles").listFiles();
		Arrays.sort(files);
		List jars = new ArrayList();
		for(int i = 0; i < files.length; i++){
			if(files[i].getName().toLowerCase().endsWith(".jar")){
				jars.add(files[i]);
			}
		}
		try{
			List bundleList = new ArrayList();
			Map m = new HashMap();
			m.putAll(System.getProperties());
			m.put(Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);
			m.put(Constants.FRAMEWORK_SYSTEMCAPABILITIES_EXTRA, "org.foo.shape; version=\"4.0.0\"");
			fwk = getFrameworkFactory().newFramework(m);
			fwk.start();
			BundleContext ctxt = fwk.getBundleContext();
			for(int i = 0; i < jars.size(); i++){
				Bundle b = ctxt.installBundle(((File)jars.get(i)).toURI().toString());
				bundleList.add(b);
			}
			for(int i =0; i < bundleList.size(); i++){
				((Bundle)bundleList.get(i)).start();
			}
		}catch(Exception ex){
			System.err.println("Error starting framework: "+ex);
			ex.printStackTrace();
			System.exit(0);
		}
		return fwk;
	}
	
	private static void publishTrapezoidService(){
		Hashtable dict = new Hashtable();
		dict.put(SimpleShape.NAME_PROPERTY, "Trapezoid");
		dict.put(SimpleShape.ICON_PROPERTY, new ImageIcon(Trapezoid.class.getResource("trapezoid.png")));
		fwk.getBundleContext().registerService(SimpleShape.class.getName(), new Trapezoid(), dict);
	}
	
	private static void createPaintFrame() throws Exception {
	    SwingUtilities.invokeAndWait(new Runnable() {
	      public void run() {
	        frame = new PaintFrame();
	        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        frame.addWindowListener(new WindowAdapter() {
	          public void windowClosing(WindowEvent evt) {
	            try {
	              fwk.stop();
	              fwk.waitForStop(0);
	            } catch (Exception ex) {
	              System.err.println("Issue stopping framework: " + ex);
	            }
	            System.exit(0);
	          }
	        });
	        frame.setVisible(true);

	        shapeTracker = new ShapeTracker(fwk.getBundleContext(), frame);
	        shapeTracker.open();
	      }
	    });
	  }
	
	public static void old_main(String[] args) throws Exception{
		if(args.length < 1 || !new File(args[0]).isDirectory()){
			System.out.println("Usage: <bundle-derectory>");
		}else{
			File[] files = new File(args[0]).listFiles();
			Arrays.sort(files);
			List jars = new ArrayList();
			for(int i = 0; i < files.length; i++){
				if(files[i].getName().toLowerCase().endsWith(".jar"))
					jars.add(files[i]);
			}
			if(jars.isEmpty()){
				System.out.println("No bundles to install.");
			}else{
				Runtime.getRuntime().addShutdownHook(new Thread(){
					public void run(){
						try{
							if(fwk != null){
								fwk.stop();
								fwk.waitForStop(0);
							}
						}catch(Exception ex){
							System.err.println("Error stopping framework: "+ex);
						}
					}
				});
				Bundle mainBundle = null;
				try{
					List bundleList = new ArrayList();
					Map m = new HashMap();
					
					m.putAll(System.getProperties());
					m.put(Constants.FRAMEWORK_STORAGE_CLEAN, "onFirstInit");
					fwk = getFrameworkFactory().newFramework(m);
					fwk.start();
					
					BundleContext ctxt = fwk.getBundleContext();
					for(int i = 0; i < jars.size(); i++){
						Bundle b = ctxt.installBundle(((File) jars.get(i)).toURI().toString());
						bundleList.add(b);
						if(b.getHeaders().get("Main-Class") != null){
							mainBundle = b;
						}
					}
					
					for(int i = 0; i < bundleList.size(); i++){
						if(!isFragment((Bundle)bundleList.get(i))){
							((Bundle)bundleList.get(i)).start();
						}
					}
					
					if(mainBundle != null){
						final String className = (String) mainBundle.getHeaders().get("Main-Class");
						if(className != null){
							final Class mainClass = mainBundle.loadClass(className);
							try{
								Method method = mainClass.getMethod("main", new Class[]{String[].class});
								String[] mainArgs = new String[args.length-1];
								System.arraycopy(args, 1, mainArgs, 0, mainArgs.length);
								method.invoke(null, new Object[]{mainArgs});
							}catch(Exception ex){
								System.err.println("Error invoking main method: "+ex+" couse = "+ex.getCause());
							}
						}else{
							System.err.println("Main class not found: "+className);
						}
					}
					fwk.waitForStop(0);
					System.exit(0);
				}catch(Exception ex){
					System.err.println("Error starting framework: "+ex);
					ex.printStackTrace();
					System.exit(0);
				}
			}
		}
		
	}
	
	private static boolean isFragment(Bundle bundle){
		return bundle.getHeaders().get(Constants.FRAGMENT_HOST) != null;
	}
	
	private static FrameworkFactory getFrameworkFactory() throws Exception{
		URL url = Main.class.getClassLoader().getResource("META-INF/services/org.osgi.framework.launch.FrameworkFactory");
		
		if(url != null){
			BufferedReader br= new BufferedReader(new InputStreamReader(url.openStream()));
			try{
				for(String s = br.readLine(); s != null; s = br.readLine()){
					s = s.trim();
					if((s.length() > 0) && (s.charAt(0) != '#')){
						return (FrameworkFactory)Class.forName(s).newInstance();
					}
				}
			} finally{
				if(br != null) br.close();
			}
		}
		throw new Exception("Could not find framework factory.");
	}

}
