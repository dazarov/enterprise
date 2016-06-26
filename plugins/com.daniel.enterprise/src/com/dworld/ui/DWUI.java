package com.dworld.ui;

import javax.swing.JFrame;

public class DWUI {
	public static final int UI_TYPE_SWING = 0;
	public static final int UI_TYPE_JAVA_FX = 1;
	
	private final int type;
	private JFrame window;
	
	public DWUI(int type){
		this.type = type;
		if(type == UI_TYPE_SWING){
			window = new JFrame();
		}
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public IProgressMonitor getProgressMonitor(String title, int max){
		if(type == UI_TYPE_SWING){
			return new DWProgressMonitor(window, title, max);
		}
		return null;
	}
	
	public void showMessageDialog(String title, String message){
		if(type == UI_TYPE_SWING){
			new DWMessageDialog(window, title, message);
		}
	}
	
	public void setTitle(String title){
		if(type == UI_TYPE_SWING){
			window.setTitle(title);
		}
	}
	
	
}
