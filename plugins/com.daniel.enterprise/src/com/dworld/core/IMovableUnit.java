package com.dworld.core;

public interface IMovableUnit extends IActive{
	public int getBeneath();

	public void setBeneath(int beneath);
	
	public int getCode(int beneath);
	
	public Direction getDirection();

	public void setDirection(Direction direction);
}
