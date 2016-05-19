package com.dworld.core;

import javax.swing.JFrame;

public class DWConfiguration {
	private static volatile DWConfiguration instance;
	private final DWEngine engine;
	private final JFrame window;
	
	public static DWConfiguration getInstance(){
		if(instance == null){
			synchronized (DWConfiguration.class) {
				if(instance == null){
					instance = new DWConfiguration();
				}
			}
		}
		return instance;
	}
	
	private DWConfiguration(){
		window = new JFrame();
		engine = new DWEngine(window);
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public DWEngine getEngine(){
		return engine;
	}
}
