package com.dworld.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IUnit {
	public Location getLocation();
	
	public void setLocation(Location location);
	
	public boolean isAlive();
	
	public void die();
	
	public void resurrect();
	
	public void init();
	
	public void save(OutputStream stream) throws IOException;
	
	public void load(InputStream stream) throws IOException;
	
	public void command(int commandId, Object[] args);
}
