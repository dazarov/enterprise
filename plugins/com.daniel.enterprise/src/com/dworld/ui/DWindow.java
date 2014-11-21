package com.dworld.ui;

import javax.swing.JFrame;

public class DWindow extends JFrame {
	public static final int ORIENTATION_TOP = 0;
	public static final int ORIENTATION_BOTTOM = 1;
	public static final int ORIENTATION_LEFT = 2;
	public static final int ORIENTATION_RIGHT = 3;
	
	private static final long serialVersionUID = 1L;
	private int orientation;
	
	public DWindow(String title, int orientation){
		super(title);
		this.orientation = orientation;
	}

	public int getOrientation(){
		return orientation;
	}
}
