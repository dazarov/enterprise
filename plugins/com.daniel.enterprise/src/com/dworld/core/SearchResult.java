package com.dworld.core;

import java.awt.Point;

public class SearchResult {
	private int distance = -1;
	private int resultCode = Land.Vacuum;
	private Direction direction = Direction.nowhere;
	private Point location;
	
	public SearchResult(int code, int dist, Direction dir, Point location){
		resultCode = code;
		distance = dist;
		direction = dir;
		this.location = location;
	}

	public int getDistance() {
		return distance;
	}

	public int getResultCode() {
		return resultCode;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public Point getLocation(){
		return location;
	}
	
	
}
