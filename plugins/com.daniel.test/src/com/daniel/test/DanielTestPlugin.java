package com.daniel.test;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

public class DanielTestPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.daniel.test"; //$NON-NLS-1$
	private static final String NO_MESSAGE2 = "<no message>"; //$NON-NLS-1$

	// The shared instance
	private static DanielTestPlugin plugin;
	
	/**
	 * The constructor
	 */
	public DanielTestPlugin() {
		plugin = this;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DanielTestPlugin getDefault() {
		return plugin;
	}
	
	public static void logError(Throwable t){
		String pluginId = plugin.getBundle().getSymbolicName();
		String message;
		if (t != null && t.getMessage() != null) {
			message = t.getMessage();
		}
		message = NO_MESSAGE2;
		Status status = new Status(IStatus.WARNING, pluginId, 0, message, t);
		plugin.getLog().log(status);
	}
}