package com.dworld.ui.swing;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class DWSwingWindow extends JFrame {
	public static final int ORIENTATION_TOP = 0;
	public static final int ORIENTATION_BOTTOM = 1;
	public static final int ORIENTATION_LEFT = 2;
	public static final int ORIENTATION_RIGHT = 3;
	
	private static final long serialVersionUID = 1L;
	private int orientation;
	
	private static long counter = 1;
	
	private long windowID;
	
	public DWSwingWindow(String title, int orientation){
		super(title);
		this.orientation = orientation;
		this.windowID = counter++;
	}
	
	public String toString(){
		return "DWindow "+windowID;
	}

	public int getOrientation(){
		return orientation;
	}
	
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		dispose();
	}
}
