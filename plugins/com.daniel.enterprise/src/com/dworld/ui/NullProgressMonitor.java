package com.dworld.ui;

public class NullProgressMonitor implements IProgressMonitor {

	@Override
	public void progress(int progress) {
	}

	@Override
	public void close() {
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

}
