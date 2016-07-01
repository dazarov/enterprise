package com.dworld;

import java.io.File;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.DWEngine;
import com.dworld.core.ILauncher;
import com.dworld.core.Land;
import com.dworld.ui.javafx.DWJavaFXKeyConverter;
import com.dworld.ui.javafx.DWJavaFXKeyConverter.KeyInfo;
import com.dworld.ui.javafx.DWJavaFXMenuBuilder;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
		
		engine = configuration.getEngine();
		
		configuration.setPathName(pathName);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Land.load(DWConfiguration.SAVE_FILE);
		engine.init();
		
		primaryStage.setTitle(DWConfiguration.TITLE);
        Group root = new Group();
        
        Canvas canvas = new Canvas(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH, DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT);
        canvas.setLayoutY(30);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        DWConfiguration.getInstance().getUI().setGraphicsContext(gc);
        
        DWJavaFXMenuBuilder menuBuilder = new DWJavaFXMenuBuilder();
        root.getChildren().addAll(menuBuilder.buildMenu(), canvas);
        //root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH, DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT+20);
        primaryStage.setScene(scene);
        
        KeyEventHandler keyHandler = new KeyEventHandler();
        
        scene.setOnKeyPressed(keyHandler);
        
        MouseEventHandler mouseHandler = new MouseEventHandler();
        
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        
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
	
	private class KeyEventHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			
			if (event.getEventType() == KeyEvent.KEY_PRESSED) {
				KeyInfo info = DWJavaFXKeyConverter.convert(event);
				if(info != null){
					DWConfiguration.getInstance().getUI().getKeyListener().doKeyPressed(info.code, info.modifiers);
				}
			}
		}
	}
	
	private class MouseEventHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
				DWConfiguration.getInstance().getUI().getMouseListener().doMousePressed(event.getButton().ordinal(), (int)event.getX(), (int)event.getY());
			}else if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
				DWConfiguration.getInstance().getUI().getMouseListener().doMouseReleased(event.getButton().ordinal(), (int)event.getX(), (int)event.getY());
			}else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
				DWConfiguration.getInstance().getUI().getMouseListener().doMouseDragged(event.getButton().ordinal(), (int)event.getX(), (int)event.getY());
			}
		}
		
	}

}
