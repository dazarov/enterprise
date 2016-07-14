package com.dworld.ui.javafx;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.IProgressMonitor;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DWJavaFXProgressMonitor implements IProgressMonitor{
	private Stage stage;
	private ProgressBar bar;
	private Task<Integer> task;
	
	public DWJavaFXProgressMonitor(String title){
		stage = new Stage();
		stage.initOwner(DWConfiguration.getInstance().getUI(DWJavaFXUI.class).getWindow());
		
		bar = new ProgressBar();
		bar.setPrefSize(300, 20);
		
		Button button = new Button("Cancel");
		
		button.setOnAction(e -> task.cancel());
		
		VBox vbox = new VBox(bar, button);
		vbox.setAlignment(Pos.CENTER);
		VBox.setMargin(bar, new Insets(5,5,5,5));
		VBox.setMargin(button, new Insets(0,0,5,0));
		
		Scene scene = new Scene(vbox);

	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	}

	@Override
	public void progress(int progress) {
		bar.setProgress(progress);
	}

	@Override
	public void close() {
		stage.close();
	}
	
	public void bind(Task<Integer> task){
		this.task = task;
		bar.progressProperty().bind(task.progressProperty());
	}

	@Override
	public boolean isCancelled() {
		return task.isCancelled();
	}

}
