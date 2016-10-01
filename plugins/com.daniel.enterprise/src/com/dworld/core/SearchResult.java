package com.dworld.core;


public class SearchResult {
	private int distance = -1;
	private Land resultLand = Land.Vacuum;
	private Direction direction = Direction.NOWHERE;
	private Location location;
	
	public SearchResult(Land land, int dist, Direction dir, Location location){
		resultLand = land;
		distance = dist;
		direction = dir;
		this.location = location;
	}

	public int getDistance() {
		return distance;
	}

	public Land getResultLand() {
		return resultLand;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public Location getLocation(){
		return location;
	}
	
	
}
