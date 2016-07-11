package com.dworld.ui;

@FunctionalInterface
public interface IMonitoredRunnable {
	void run(IProgressMonitor monitor);
}
