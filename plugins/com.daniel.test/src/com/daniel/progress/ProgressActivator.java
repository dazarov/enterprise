package com.daniel.progress;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.daniel.installer.IProgress;

public class ProgressActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("activating ProgressActivator...");
	    Hashtable dict = new Hashtable();
	    //dict.put(SimpleShape.NAME_PROPERTY, "Trapezoid");
	    //dict.put(SimpleShape.ICON_PROPERTY, new ImageIcon(Trapezoid.class.getResource("trapezoid.png")));
	    context.registerService(IProgress.class.getName(), new SimpleProgress(), dict);
	    System.out.println("ProgressActivator activated");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

}
