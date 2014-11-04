package com.dworld.core;

public interface IActive extends IUnit{
	public void step();
	
	public boolean isActive();
	
	public void activate();
	
	public void deactivate();
	
	public <T> void command(int commandId, T arg);
}
