package com.dworld.core;

public interface ILauncher {
	void setModified();
	void setSaved();
	void load(String fileName);
	void save(String fileName);
	void saveAndExit(String fileName);
}
