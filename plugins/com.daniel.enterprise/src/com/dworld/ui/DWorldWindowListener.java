/**
 * 
 */
package com.dworld.ui;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * @author daniel
 *
 */
public class DWorldWindowListener implements WindowListener{
	private static DWorldWindowListener instance;
	
	JFrame mainWindow = null;
	ArrayList<JFrame> windows = new ArrayList<JFrame>();
	
	public static DWorldWindowListener getDefault(){
		if(instance == null){
			instance = new DWorldWindowListener();
		}
		return instance;
	}
	
	public void addWindow(JFrame window){
		windows.add(window);
		window.addWindowListener(this);
	}

	public void addMainWindow(JFrame window){
		mainWindow = window;
		window.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		windows.remove(e.getWindow());
		e.getWindow().removeWindowListener(this);
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
		Window w = e.getWindow();
		if(!w.equals(mainWindow)){
			mainWindow.setVisible(true);
		}
		for(JFrame frame : windows){
			if(!w.equals(frame)){
				frame.setVisible(true);
			}	
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
