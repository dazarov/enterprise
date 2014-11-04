package com.dworld.core;

public class Target {
	private Direction direction;
	private int distance;

	public Target(Direction direction, int distance) {
		this.direction = direction;
		this.distance = distance;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public int getDistance(){
		return distance;
	}

}
