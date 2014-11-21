/**
 * 
 */
package com.dworld.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * @author daniel
 *
 */
public class DWorldWindowListener implements WindowListener, ComponentListener{
	private static DWorldWindowListener instance;
	
	JFrame mainWindow = null;
	ArrayList<DWindow> windows = new ArrayList<DWindow>();
	
	public static DWorldWindowListener getDefault(){
		if(instance == null){
			instance = new DWorldWindowListener();
		}
		return instance;
	}
	
	public void addWindow(DWindow window){
		windows.add(window);
		window.addWindowListener(this);
		window.addComponentListener(this);
		relocate(window);
	}

	public void addMainWindow(JFrame window){
		mainWindow = window;
		window.addWindowListener(this);
		window.addComponentListener(this);
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
		e.getWindow().removeComponentListener(this);
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

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		Window w = (Window)e.getComponent();
		if(w.equals(mainWindow)){
			for(DWindow window : windows){
				relocate(window);
			}
		}
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		Window w = (Window)e.getComponent();
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
	public void componentHidden(ComponentEvent e) {
	}
	
	private void relocate(DWindow window){
		Point mainLocation = mainWindow.getLocation();
		Dimension mainSize = mainWindow.getSize();
		Dimension size = window.getSize();
		if(window.getOrientation() == DWindow.ORIENTATION_RIGHT){
			window.setLocation(mainLocation.x+mainSize.width, mainLocation.y+(mainSize.height-size.height)/2);
		}else if(window.getOrientation() == DWindow.ORIENTATION_LEFT){
			window.setLocation(mainLocation.x-size.width, mainLocation.y+(mainSize.height-size.height)/2);
		}
	}

}
