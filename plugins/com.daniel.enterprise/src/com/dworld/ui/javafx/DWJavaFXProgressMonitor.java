package com.dworld.ui.javafx;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.IProgressMonitor;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class DWJavaFXProgressMonitor implements IProgressMonitor{
	private Stage stage;
	private ProgressBar bar;
	
	public DWJavaFXProgressMonitor(String title){
		stage = new Stage();
		stage.initOwner(DWConfiguration.getInstance().getUI(DWJavaFXUI.class).getWindow());
		
		bar = new ProgressBar();
		
		Scene scene = new Scene(bar, 300, 20);

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
		bar.progressProperty().bind(task.progressProperty());
	}

}
