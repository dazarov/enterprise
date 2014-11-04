package com.daniel.installer;

public interface IProgressListener {
	public void progress(String jobName, int total, int current);
}
