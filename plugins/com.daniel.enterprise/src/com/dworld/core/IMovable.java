package com.dworld.core;

public interface IMovable extends IActive{
	public int getBeneath();

	public void setBeneath(int beneath);
	
	public int getCode(int beneath);
	
	@Deprecated
	public Direction getDirection();

	@Deprecated
	public void setDirection(Direction direction);
	
	public void move(Direction direction);
}
