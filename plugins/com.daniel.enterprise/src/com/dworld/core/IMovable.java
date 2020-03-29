package com.dworld.core;

public interface IMovable extends IActive{
	public Land getBeneath();

	public void setBeneath(Land beneath);
	
	public Land getLand();
	
	@Deprecated
	public Direction getDirection();

	@Deprecated
	public void setDirection(Direction direction);
	
	public void move(Direction direction);
}
