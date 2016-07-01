package com.dworld.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.dworld.core.DWConfiguration;
import com.dworld.core.Land;
import com.dworld.ui.javafx.DWJavaFXImages;
import com.dworld.ui.javafx.DWJavaFXProgressMonitor;
import com.dworld.ui.swing.DWMessageDialog;
import com.dworld.ui.swing.DWProgressMonitor;
import com.dworld.ui.swing.DWSwingImages;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

public class DWUI {
	public static final int UI_TYPE_SWING = 0;
	public static final int UI_TYPE_JAVA_FX = 1;
	
	private final int type;
	private JFrame window;
	private GraphicsContext gc;
	
	@SuppressWarnings("rawtypes")
	private DWImages images;
	
	private DWKeyListener keyListener = new DWKeyListener();
	private DWMouseListener mouseListener = new DWMouseListener();
	
	public DWUI(int type){
		this.type = type;
		if(type == UI_TYPE_SWING){
			window = new JFrame();
			images = new DWSwingImages();
		}else if(type == UI_TYPE_JAVA_FX){
			images = new DWJavaFXImages();
		}
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public DWKeyListener getKeyListener(){
		return keyListener;
	}
	
	public DWMouseListener getMouseListener(){
		return mouseListener;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <V extends DWImages> V getImages(Class<V> type){
		if(images.getClass().equals(type)){
			return (V)images;
		}
		throw new IllegalArgumentException();
	}
	
	@SuppressWarnings("rawtypes")
	public DWImages getImages(){
		return images;
	}
	
	public boolean exitConfirmation(){
		if(type == UI_TYPE_SWING){
			if (Land.isDirty()) {
				int n = JOptionPane.showConfirmDialog(window,
						"Do you want to save the game?", "Question",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					DWConfiguration.getInstance().getEngine().saveAndExit(DWConfiguration.SAVE_FILE);
					return false;
				}
			}
		}
		return true;
	}
	
	
	public IProgressMonitor getProgressMonitor(String title, int max){
		if(type == UI_TYPE_SWING){
			return new DWProgressMonitor(window, title, max);
		}else if(type == UI_TYPE_JAVA_FX){
			return new DWJavaFXProgressMonitor();
		}
		throw new IllegalStateException();
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
	
	public void setGraphicsContext(GraphicsContext gc){
		this.gc = gc;
	}
	
	public void repaint(){
		if(type == UI_TYPE_SWING){
			window.repaint();
		}else if(type == UI_TYPE_JAVA_FX){
			Platform.runLater(() -> getImages(DWJavaFXImages.class).draw(gc));
		}
	}
	
	
}
