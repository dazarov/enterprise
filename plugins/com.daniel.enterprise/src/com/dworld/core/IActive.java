package com.dworld.core;

public interface IActive extends IUnit{
	public void step();
	
	public boolean isActive();
	
	public void activate();
	
	public void deactivate();
}
