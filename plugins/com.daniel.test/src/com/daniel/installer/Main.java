package com.daniel.installer;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import org.foo.shape.SimpleShape;
import org.foo.shape.trapezoid.Trapezoid;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import com.daniel.progress.SimpleProgress;

public class Main {
  private static Framework fwk;
  private static InstallerFrame installer = null;
  private static ProgressTracker progressTracker = null;

  public static void main(String[] args) throws Exception {
    addShutdownHook();
    fwk = createFramework();
    //publishProgressService();
    createInstaller();
  }

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

  private static Framework createFramework() throws Exception {
    // Look in the "bundles" directory to create a list
    // of all JAR files to install.
    File[] files = new File("bundles").listFiles();
    Arrays.sort(files);
    List jars = new ArrayList();
    for (int i = 0; i < files.length; i++)
      if (files[i].getName().toLowerCase().endsWith(".jar"))
        jars.add(files[i]);

    try {
      // Create, configure, and start an OSGi framework instance
      // using the ServiceLoader to get a factory.
      List bundleList = new ArrayList();
      Map m = new HashMap();
      m.putAll(System.getProperties());
      m.put(Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);
      m.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "com.daniel.progress");
      fwk = getFrameworkFactory().newFramework(m);
      fwk.start();
      // Install bundle JAR files and remember the bundle objects.
      BundleContext ctxt = fwk.getBundleContext();
      for (int i = 0; i < jars.size(); i++) {
        Bundle b = ctxt.installBundle(((File) jars.get(i)).toURI().toString());
        bundleList.add(b);
      }
      // Start all installed bundles.
      for (int i = 0; i < bundleList.size(); i++) {
        ((Bundle) bundleList.get(i)).start();
      }
    } catch (Exception ex) {
      System.err.println("Error starting framework: " + ex);
      ex.printStackTrace();
      System.exit(0);
    }

    return fwk;
  }

  private static void publishProgressService() {
    Hashtable dict = new Hashtable();
    //dict.put(SimpleShape.NAME_PROPERTY, "Trapezoid");
    //dict.put(SimpleShape.ICON_PROPERTY, new ImageIcon(Trapezoid.class.getResource("trapezoid.png")));
    fwk.getBundleContext().registerService(IProgress.class.getName(), new SimpleProgress(), dict);
  }

  private static void createInstaller() throws Exception {
    SwingUtilities.invokeAndWait(new Runnable() {
      public void run() {
        installer = new InstallerFrame();
        installer.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        installer.addWindowListener(new WindowAdapter() {
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
        installer.setVisible(true);

        progressTracker = new ProgressTracker(fwk.getBundleContext(), installer);
        progressTracker.open();
      }
    });
  }

  private static FrameworkFactory getFrameworkFactory() throws Exception {
    URL url = Main.class.getClassLoader().getResource(
      "META-INF/services/org.osgi.framework.launch.FrameworkFactory");
    if (url != null) {
      BufferedReader br =
        new BufferedReader(new InputStreamReader(url.openStream()));
      try {
        for (String s = br.readLine(); s != null; s = br.readLine()) {
          s = s.trim();
          // Try to load first non-empty, non-commented line.
          if ((s.length() > 0) && (s.charAt(0) != '#')) {
            return (FrameworkFactory) Class.forName(s).newInstance();
          }
        }
      } finally {
        if (br != null) br.close();
      }
    }

    throw new Exception("Could not find framework factory.");
  }
}