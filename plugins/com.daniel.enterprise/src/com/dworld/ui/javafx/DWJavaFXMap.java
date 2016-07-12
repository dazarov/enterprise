package com.dworld.ui.javafx;

import com.dworld.core.DWConfiguration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DWJavaFXMap {
	public static void showMap(){
		Stage stage = new Stage();
		stage.initOwner(DWConfiguration.getInstance().getUI(DWJavaFXUI.class).getWindow());
		Button btn = new Button();
	    btn.setText("Say 'Hello World'");
	    btn.setOnAction(new EventHandler<ActionEvent>() {

	      @Override
	      public void handle(ActionEvent event) {
	        System.out.println("Hello World!");
	      }
	    });

	    StackPane root = new StackPane();
	    root.getChildren().add(btn);

	    Scene scene = new Scene(root, 300, 250);

	    stage.setTitle("Hello World!");
	    stage.setScene(scene);
	    stage.show();
	}
}
