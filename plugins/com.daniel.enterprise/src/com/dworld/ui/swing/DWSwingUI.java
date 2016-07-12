package com.dworld.ui.swing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.dworld.core.DWConfiguration;
import com.dworld.core.Land;
import com.dworld.ui.DWUI;
import com.dworld.ui.IProgressMonitor;

public class DWSwingUI extends DWUI {
	private JFrame window;
	
	public DWSwingUI() {
		window = new JFrame();
		images = new DWSwingImages();
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public boolean exitConfirmation(){
		if (Land.isDirty()) {
			int n = JOptionPane.showConfirmDialog(window,
					"Do you want to save the game?", "Question",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				DWConfiguration.getInstance().getLauncher().saveAndExit(DWConfiguration.SAVE_FILE);
				return false;
			}
		}
		return true;
	}
	
	public IProgressMonitor getProgressMonitor(String title, int max){
		return new DWProgressMonitor(title);
	}
	
	public void showMessageDialog(String title, String message){
		new DWMessageDialog(window, title, message);
	}
	
	public void setTitle(String title){
		window.setTitle(title);
	}
	
	public void repaint(){
		window.repaint();
	}
	
	public void showMap(){
		DWMap.showMap();
	}
	
	public void showPalette(){
		DWSwingToolbarBuilder.showPalette();
	}
	
	public void hidePalette(){
		DWSwingToolbarBuilder.hidePalette();
	}
	
	public void toggleMinimap(){
		DWMap.switchMinimap();
	}
	
	public void showInfoScreen(){
		new DWInfoScreen();
	}

	@Override
	public void refreshMinimap() {
		DWMap.refreshMinimap();
	}
}
