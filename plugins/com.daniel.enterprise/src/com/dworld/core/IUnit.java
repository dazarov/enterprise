package com.dworld.core;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IUnit {
	public Point getLocation();
	
	public void setLocation(Point location);
	
	public boolean isAlive();
	
	public void die();
	
	public void resurrect();
	
	public void init();
	
	public void save(OutputStream stream) throws IOException;
	
	public void load(InputStream stream) throws IOException;
	
	public <T> void command(int commandId, T arg);
}
