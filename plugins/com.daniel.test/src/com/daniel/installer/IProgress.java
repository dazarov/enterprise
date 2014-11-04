package com.daniel.installer;

public interface IProgress {
	public void run();
	
	public void addProgressListener(IProgressListener listener);
	
	public void removeProgressListener(IProgressListener listener);
}
