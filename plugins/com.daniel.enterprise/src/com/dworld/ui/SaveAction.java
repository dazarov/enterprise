package com.dworld.ui;

import com.dworld.core.DWConfiguration;

public class SaveAction implements IMonitoredRunnable{
	String fileName;
	
	public SaveAction(String fileName){
		this.fileName = fileName;
	}

	@Override
	public void run(IProgressMonitor monitor) {
		DWConfiguration.getInstance().getEngine().save(fileName, monitor);
	}

}
