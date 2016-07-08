/**
 * 
 */
package com.dworld.ui.swing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

/**
 * @author daniel
 *
 */
public class DWWindowListener implements WindowListener, ComponentListener{
	private static DWWindowListener instance;
	
	JFrame mainWindow = null;
	Set<DWWindow> windows = new HashSet<DWWindow>();
	
	private DWWindowListener(){
		
	}
	
	public static synchronized DWWindowListener getDefault(){
		if(instance == null){
			instance = new DWWindowListener();
		}
		return instance;
	}
	
	public void addWindow(DWWindow window){
		windows.add(window);
		window.addWindowListener(this);
		window.addComponentListener(this);
		relocate(window);
	}
	
	public void removeWindow(DWWindow window){
		windows.remove(window);
		window.removeWindowListener(this);
		window.removeComponentListener(this);
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
		System.out.println("listener window closing...");
		windows.remove(e.getWindow());
		e.getWindow().removeWindowListener(this);
		e.getWindow().removeComponentListener(this);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("listener window closed...");
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
			for(DWWindow window : windows){
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
	
	private void relocate(DWWindow window){
		Point mainLocation = mainWindow.getLocation();
		Dimension mainSize = mainWindow.getSize();
		Dimension size = window.getSize();
		if(window.getOrientation() == DWWindow.ORIENTATION_RIGHT){
			window.setLocation(mainLocation.x+mainSize.width, mainLocation.y+(mainSize.height-size.height)/2);
		}else if(window.getOrientation() == DWWindow.ORIENTATION_LEFT){
			window.setLocation(mainLocation.x-size.width, mainLocation.y+(mainSize.height-size.height)/2);
		}
	}

}
