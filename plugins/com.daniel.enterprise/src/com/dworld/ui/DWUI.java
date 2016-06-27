package com.dworld.ui;

import javax.swing.JFrame;

import com.dworld.ui.javafx.DWJavaFXImages;
import com.dworld.ui.swing.DWMessageDialog;
import com.dworld.ui.swing.DWProgressMonitor;
import com.dworld.ui.swing.DWSwingImages;

public class DWUI {
	public static final int UI_TYPE_SWING = 0;
	public static final int UI_TYPE_JAVA_FX = 1;
	
	private final int type;
	private JFrame window;
	
	@SuppressWarnings("rawtypes")
	private DWImages images;
		
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <V extends DWImages> V getImages(Class<V> type){
		if(images.getClass().equals(type)){
			return (V)images;
		}
		throw new IllegalArgumentException();
	}
	
	
	public IProgressMonitor getProgressMonitor(String title, int max){
		if(type == UI_TYPE_SWING){
			return new DWProgressMonitor(window, title, max);
		}
		//throw new IllegalStateException();
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
