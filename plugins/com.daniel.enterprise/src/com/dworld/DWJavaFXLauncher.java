package com.dworld;

import java.io.File;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.DWEngine;
import com.dworld.core.ILauncher;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.ui.javafx.DWJavaFXKeyConverter;
import com.dworld.ui.javafx.DWJavaFXKeyConverter.KeyInfo;
import com.dworld.ui.swing.DWMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DWJavaFXLauncher extends Application implements ILauncher{
	
	private DWEngine engine;
	
	private DWConfiguration configuration;
	
	@Override
    public void init() throws Exception {
        super.init();
        String pathName = "";
		File jar = new File(DWSwingLauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		if(jar.exists()){
			pathName = jar.getParent();
		}
		configuration = DWConfiguration.getInstance();
		configuration.setLauncher(this);
		
		//window = configuration.getUI().getWindow();
		engine = configuration.getEngine();
		
		configuration.setPathName(pathName);
		//DWWindowListener.getDefault().addMainWindow(window);
		//initMenu();
		//initWindow();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Land.load(DWConfiguration.SAVE_FILE);
		engine.init();
		
		primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        
        Canvas canvas = new Canvas(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH, DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        DWConfiguration.getInstance().getUI().setGraphicsContext(gc);
        
        StackPane holder = new StackPane();
        holder.setStyle("-fx-background-color: black; -fx-color: yellow");
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        EventHandler<KeyEvent> keyEventHandler = new KeyEventHandler();
        
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);
        primaryStage.show();
        new Thread(engine).start();
	}
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void setModified() {
	}

	@Override
	public void setSaved() {
	}

	@Override
	public boolean exitConfirmation() {
		return true;
	}
	
	private class KeyEventHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			
			if (event.getEventType() == KeyEvent.KEY_PRESSED) {
				KeyInfo info = DWJavaFXKeyConverter.convert(event);
				if(info != null){
					doKeyPressed(info.code, info.modifiers);
				}
			}
		}
		
	}
	
	public void doKeyPressed(int keyCode, int keyModifiers){
		if (keyCode == 0)
			return;
		else if (keyCode == 27) {
			if(exitConfirmation())
				System.exit(0);
		}else if (keyCode == 77) { // m
			DWMap.showMap();
		}else if (keyCode == 78) { // n
			DWMap.switchMinimap();
		}
		// Alt
		if(keyModifiers == 8 && !configuration.isBuildMode()){
			switch(keyCode){
			case 37: // Left
				SelectionManager.modifySelection(0, 0, -1, 0);
				return;

			case 38: // Up
				SelectionManager.modifySelection(0, 0, 0, -1);
				return;

			case 39: // Right
				SelectionManager.modifySelection(0, 0, 1, 0);
				return;

			case 40: // Down
				SelectionManager.modifySelection(0, 0, 0, 1);
				return;
			}
		}
		if(keyModifiers == 0 && configuration.isBuildMode()){
			switch(keyCode){
			case 37: // Left
				SelectionManager.moveLeft();
				return;

			case 38: // Up
				SelectionManager.moveUp();
				return;

			case 39: // Right
				SelectionManager.moveRight();
				return;

			case 40: // Down
				SelectionManager.moveDown();
				return;

			case 127: // Del
				SelectionManager.delete();
				return;
			}
		}
		// Ctrl
		if(keyModifiers == 2){
			if(keyCode == 67){ // Ctrl+c
				SelectionManager.copy();
				return;
			}else if(keyCode == 86){ // Ctrl+v
				SelectionManager.paste();
				return;
			}
		}
		
		if (DWConfiguration.getInstance().getControlledUnit() != null)
			DWConfiguration.getInstance().getControlledUnit().control(keyCode, keyModifiers);
		 
	}

}
