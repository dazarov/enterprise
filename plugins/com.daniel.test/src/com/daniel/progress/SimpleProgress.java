package com.daniel.progress;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.daniel.installer.IProgress;
import com.daniel.installer.IProgressListener;

public class SimpleProgress implements IProgress {
	private ArrayList<IProgressListener> listeners = new ArrayList<IProgressListener>();

	@Override
	public void run() {
		
		progressLoop("++ Step 11111", 100000);
		
//		progressLoop("++ Step 2", 100000);
//		
//		progressLoop("++ Step 3", 100000);
//		
//		progressLoop("++ Step 4", 100000);
//		
//		progressLoop("++ Step 5", 10000);
//		
//		progressLoop("++ Step 6", 100000);
//		
//		progressLoop("++ Step 7", 10000);
//		
//		progressLoop("++ Step 8", 1000000);
	}
	
	private void progressLoop(String name, int size){
		for(int i = 0; i < size; i++){
			fireProgress(name, size, i+1);
		}
	}
	
	private void fireProgress(final String jobName, final int total, final int current){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				for(IProgressListener listener : listeners){
					listener.progress(jobName, total, current);
				}
			}
        	
        });
		
	}

	@Override
	public void addProgressListener(IProgressListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeProgressListener(IProgressListener listener) {
		listeners.remove(listener);
	}
}
