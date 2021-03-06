package com.dworld.ui.swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.dworld.core.DWConfiguration;
import com.dworld.core.Land;
import com.dworld.ui.DWToolbarStructure;
import com.dworld.ui.DWToolbarStructure.DWButton;
import com.dworld.ui.DWToolbarStructure.DWToolbar;

public class DWSwingToolbarBuilder {
	private static DWSwingWindow palette = null;
	
	public JToolBar buildToolBar(){
		
		DWToolbar tb = new DWToolbarStructure().getMainToolBar();
		
		
		return createToolBar(tb, false);
	}
	
	public static void showPalette(){
		System.out.println("Show Palette...");
		if(palette != null){
			System.out.println("Palette exist - "+palette);
			palette.setVisible(true);
			return;
		}
		palette = new DWSwingWindow("Building Palette", DWSwingWindow.ORIENTATION_LEFT);
		System.out.println("Palette created - "+palette);
		palette.setLayout(new GridLayout(1,5));
		
		List<DWToolbar> tbs = new DWToolbarStructure().getPalette();
		
		for(DWToolbar tb : tbs){
			JToolBar toolbar = createToolBar(tb, true);
			palette.add(toolbar);
		}
		
		palette.pack();
		palette.setSize(200, 720);
		palette.setFocusableWindowState(false);
		palette.setFocusable(false);
		palette.setVisible(true);
		DWSwingWindowListener.getDefault().addWindow(palette);
	}
	
	public static void hidePalette(){
		System.out.println("Hide Palette..."+SwingUtilities.isEventDispatchThread());
		if(palette != null){
			System.out.println("Palette hiding - "+palette);
			DWSwingWindowListener.getDefault().removeWindow(palette);
			palette.close();
			palette = null;
		}
	}
	
	private static JToolBar createToolBar(DWToolbar model, boolean vertical){
		JToolBar toolBar = new JToolBar(model.label);
		toolBar.setFloatable(false);
		toolBar.setFocusable(false);
		if(vertical){
			toolBar.setOrientation(SwingConstants.VERTICAL);
		}
		for(DWButton button : model.buttons){
			if(button.runner == null){
				toolBar.addSeparator();
			}else{
				JButton jButton = new JButton();
				jButton.setBackground(Color.black);
				if(button.imageCode != Land.Vacuum){
					jButton.setIcon(new ImageIcon(getImage(button.imageCode)));
				} else if(button.imagePath != null){
					jButton.setIcon(new ImageIcon(loadImage(button.imagePath)));
				}
				jButton.addActionListener(e -> button.runner.run());
				toolBar.add(jButton);
			}
		}
		
		return toolBar;
	}
	
	
	private static Image getImage(Land land){
		return DWConfiguration.getInstance().getUI().getImages(DWSwingImages.class).getImage(land);
	}

	private static Image loadImage(String path){
		return DWConfiguration.getInstance().getUI().getImages(DWSwingImages.class).loadImage(path);
	}
}
