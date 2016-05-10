package com.dworld.core;


public class SearchResult {
	private int distance = -1;
	private int resultCode = Land.Vacuum;
	private Direction direction = Direction.NOWHERE;
	private Location location;
	
	public SearchResult(int code, int dist, Direction dir, Location location){
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
	
	public Location getLocation(){
		return location;
	}
	
	
}
