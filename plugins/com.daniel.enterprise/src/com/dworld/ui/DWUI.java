package com.dworld.ui;

public abstract class DWUI {
	
	@SuppressWarnings("rawtypes")
	protected DWImages images;
	
	private DWKeyListener keyListener = new DWKeyListener();
	private DWMouseListener mouseListener = new DWMouseListener();
	
	public DWUI(){
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
	
	public abstract boolean exitConfirmation();
	
	public abstract IProgressMonitor getProgressMonitor(String title, int max);
	
	public abstract void showMessageDialog(String title, String message);
	
	public abstract void setTitle(String title);
	
	public abstract void repaint();
	
	public abstract void refreshMinimap();
	
	public abstract void showMap();
	
	public abstract void showPalette();
	
	public abstract void hidePalette();
	
	public abstract void toggleMinimap();
	
	public abstract void showInfoScreen();
}
