package com.dworld.ui.javafx;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.DWToolbarStructure;
import com.dworld.ui.DWToolbarStructure.DWButton;
import com.dworld.ui.DWToolbarStructure.DWToolbar;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DWJavaFXToolbarBuilder {
	
	public ToolBar buildToolBar(){
		
		DWToolbar tb = new DWToolbarStructure().getMainToolBar();
		
		
		return createToolBar(tb, false);
	}
	
	private static ToolBar createToolBar(DWToolbar model, boolean vertical){
		ToolBar toolBar = new ToolBar();
		//toolBar.setFloatable(false);
		//toolBar.setFocusable(false);
		//if(vertical){
		//	toolBar.setOrientation(SwingConstants.VERTICAL);
		//
		for(DWButton button : model.buttons){
			if(button.runner == null){
				toolBar.getItems().add(new Separator());
			}else{
				Button jButton = new Button();
				if(button.imageCode != -1){
					jButton.setGraphic(new ImageView(getImage(button.imageCode)));
				} else if(button.imagePath != null){
					jButton.setGraphic(new ImageView(loadImage(button.imagePath)));
				}
				jButton.setOnAction(e -> button.runner.run());
				toolBar.getItems().add(jButton);
			}
		}
		
		return toolBar;
	}
	
	private static Image getImage(int code){
		return DWConfiguration.getInstance().getUI().getImages(DWJavaFXImages.class).getImage(code);
	}

	private static Image loadImage(String path){
		return DWConfiguration.getInstance().getUI().getImages(DWJavaFXImages.class).loadImage(path);
	}

}
