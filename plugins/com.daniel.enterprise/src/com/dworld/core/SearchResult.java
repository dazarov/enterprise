package com.dworld.core;

public class SearchResult {
	private int distance = -1;
	private int resultCode = Land.Vacuum;
	private Direction direction = Direction.nowhere;
	
	public SearchResult(int code, int dist, Direction dir){
		resultCode = code;
		distance = dist;
		direction = dir;
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
	
	
}
