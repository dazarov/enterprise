package com.dworld.ui.javafx;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.DWToolbarStructure;
import com.dworld.ui.DWToolbarStructure.DWButton;
import com.dworld.ui.DWToolbarStructure.DWToolbar;

import javafx.geometry.Orientation;
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
		ToolBar toolbar = new ToolBar(){
			@Override
			public void requestFocus(){}
		};
		toolbar.requestFocus();
		if(vertical){
			toolbar.setOrientation(Orientation.VERTICAL);
		}
		for(DWButton dButton : model.buttons){
			if(dButton.runner == null){
				toolbar.getItems().add(new Separator());
			}else{
				Button button = new Button(){
					@Override
					public void requestFocus(){}
				};
				if(dButton.imageCode != -1){
					button.setGraphic(new ImageView(getImage(dButton.imageCode)));
				} else if(dButton.imagePath != null){
					button.setGraphic(new ImageView(loadImage(dButton.imagePath)));
				}
				button.setOnAction(e -> dButton.runner.run());
				toolbar.getItems().add(button);
			}
		}
		
		return toolbar;
	}
	
	private static Image getImage(int code){
		return DWConfiguration.getInstance().getUI().getImages(DWJavaFXImages.class).getImage(code);
	}

	private static Image loadImage(String path){
		return DWConfiguration.getInstance().getUI().getImages(DWJavaFXImages.class).loadImage(path);
	}

}
