package com.dworld.ui;

import com.dworld.core.DWConfiguration;

public class LoadAction implements IMonitoredRunnable{
	String fileName;
	
	public LoadAction(String fileName){
		this.fileName = fileName;
	}

	@Override
	public void run(IProgressMonitor monitor) {
		DWConfiguration.getInstance().getEngine().load(fileName, monitor);
	}

}
