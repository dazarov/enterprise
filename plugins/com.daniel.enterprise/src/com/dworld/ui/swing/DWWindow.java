package com.dworld.ui.swing;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class DWWindow extends JFrame {
	public static final int ORIENTATION_TOP = 0;
	public static final int ORIENTATION_BOTTOM = 1;
	public static final int ORIENTATION_LEFT = 2;
	public static final int ORIENTATION_RIGHT = 3;
	
	private static final long serialVersionUID = 1L;
	private int orientation;
	
	public DWWindow(String title, int orientation){
		super(title);
		this.orientation = orientation;
	}

	public int getOrientation(){
		return orientation;
	}
	
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		dispose();
	}
}
