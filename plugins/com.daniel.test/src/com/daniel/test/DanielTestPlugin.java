package com.daniel.test;

import org.jboss.tools.common.log.BaseUIPlugin;

public class DanielTestPlugin extends BaseUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.daniel.test"; //$NON-NLS-1$

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
}