package com.dworld.core;

public interface IActive extends IUnit{
	public void step();
	
	public boolean isActive();
	
	public void activate();
	
	public void deactivate();
	
	public void fireBullet(Direction direction);
	
	public void fireGrenade(Direction direction, int distance);
	
	public void fireRocket(Direction direction);
	
	public void fireCannonBall(Direction direction);
}
