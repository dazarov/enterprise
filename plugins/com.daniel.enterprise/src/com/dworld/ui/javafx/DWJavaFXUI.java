package com.dworld.ui.javafx;

import com.dworld.DWJavaFXLauncher;
import com.dworld.core.DWConfiguration;
import com.dworld.ui.DWUI;
import com.dworld.ui.IProgressMonitor;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Window;

public class DWJavaFXUI extends DWUI {
	private GraphicsContext gc;
	private Window window;
	
	public DWJavaFXUI(){
		images = new DWJavaFXImages();
	}
	
	public void setWindow(Window window){
		this.window = window;
	}
	
	public Window getWindow(){
		return window;
	}
	
	public boolean exitConfirmation(){
		return true;
	}
	
	public IProgressMonitor getProgressMonitor(String title, int max){
		return new DWJavaFXProgressMonitor();
	}
	
	public void showMessageDialog(String title, String message){
	}
	
	public void setTitle(String title){
		((DWJavaFXLauncher)DWConfiguration.getInstance().getLauncher()).setTitle(title);
	}
	
	public void setGraphicsContext(GraphicsContext gc){
		this.gc = gc;
	}
	
	public void repaint(){
		Platform.runLater(() -> getImages(DWJavaFXImages.class).draw(gc));
	}
	
	public void showMap(){
		DWJavaFXMap.showMap();
	}
	
	public void showPalette(){
	}
	
	public void hidePalette(){
	}
	
	public void toggleMinimap(){
	}
	
	public void showInfoScreen(){
	}

	@Override
	public void refreshMinimap() {
		// TODO Auto-generated method stub
		
	}
}
