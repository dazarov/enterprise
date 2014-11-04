package com.dworld.core;

import java.awt.Point;

public class Direction {
	private static final int NoWhere = -1;
	private static final int North = 0;
	private static final int South = 1;
	private static final int West = 2;
	private static final int East = 3;
	private static final int NorthWest = 4;
	private static final int SouthWest = 5;
	private static final int NorthEast = 6;
	private static final int SouthEast = 7;
	
	public static final Direction nowhere = new Direction(NoWhere);
	public static final Direction north = new Direction(North);
	public static final Direction south = new Direction(South);
	public static final Direction west = new Direction(West);
	public static final Direction east = new Direction(East);
	public static final Direction northwest = new Direction(NorthWest);
	public static final Direction southwest = new Direction(SouthWest);
	public static final Direction northeast = new Direction(NorthEast);
	public static final Direction southeast = new Direction(SouthEast);
	
	public int value;
	
	public Direction(int value){
		this.value = value;
	}
	
	public String toString(){
		if (value == North)
			return "North";
		else if (value == NorthEast)
			return "NorthEast";
		else if (value == East)
			return "East";
		else if (value == SouthEast)
			return "SouthEast";
		else if (value == South)
			return "South";
		else if (value == SouthWest)
			return "SouthWest";
		else if (value == West)
			return "West";
		else if (value == NorthWest)
			return "NorthWest";
		else return "NoWhere";
	}
	
	public Direction getRight(int times) {
		return getClockwiseDirection(times);
	}
	
	public Direction getClockwiseDirection(int times) {
		Direction direction = this;
		for(int i = 0; i < times; i++){
			direction = direction.getClockwiseDirection();
		}
		return direction;
	}
	
	public Direction getRight() {
		return getClockwiseDirection();
	}

	public Direction getClockwiseDirection() {
		if (value == North)
			return northeast;
		else if (value == NorthEast)
			return east;
		else if (value == East)
			return southeast;
		else if (value == SouthEast)
			return south;
		else if (value == South)
			return southwest;
		else if (value == SouthWest)
			return west;
		else if (value == West)
			return northwest;
		else if (value == NorthWest)
			return north;
		else return nowhere;
	}
	
	public Direction getLeft(int times) {
		return getAnticlockwiseDirection(times);
	}
	
	public Direction getAnticlockwiseDirection(int times) {
		Direction direction = this;
		for(int i = 0; i < times; i++){
			direction = direction.getAnticlockwiseDirection();
		}
		return direction;
	}
	
	public Direction getLeft() {
		return getAnticlockwiseDirection();
	}
	
	public Direction getAnticlockwiseDirection() {
		if (value == North)
			return northwest;
		else if (value == NorthWest)
			return west;
		else if (value == West)
			return southwest;
		else if (value == SouthWest)
			return south;
		else if (value == South)
			return southeast;
		else if (value == SouthEast)
			return east;
		else if (value == East)
			return northeast;
		else if (value == NorthEast)
			return north;
		else return nowhere;
	}
	
	public Direction getOppositeDirection() {
		if (value == North)
			return south;
		else if (value == NorthEast)
			return southwest;
		else if (value == East)
			return west;
		else if (value == SouthEast)
			return northwest;
		else if (value == South)
			return north;
		else if (value == SouthWest)
			return northeast;
		else if (value == West)
			return east;
		else if (value == NorthWest)
			return southeast;
		else return nowhere;
	}
	
	public static Direction findDirection(Point source, Point destination){
		if(source.x > destination.x){ // west
			if(source.y > destination.y) // north
				return northwest;
			else if(source.y == destination.y)
				return west;
			else // south
				return southwest;
		}else if(source.x == destination.x){
			if(source.y > destination.y) // north
				return north;
			else if(source.y == destination.y)
				return nowhere;
			else // south
				return south;
		}else{ // east
			if(source.y > destination.y) // north
				return northeast;
			else if(source.y == destination.y)
				return east;
			else // south
				return southeast;
		}
	}
	
	public static boolean isShortcutAvalable(Point source, Point destination) {
		Point location = (Point) source.clone();
		Direction direction;
		while (!location.equals(destination)) {
			direction = findDirection(location, destination);
			if (Land.canIWalk(location, direction, Land.walkList)) {
				location = Land.getNewLocation(location, direction);
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isClockwiseNearestTurn(Direction source, Direction destination){
		Direction curent = source;
		for(int i=0; i < 4;i++){
			curent = curent.getClockwiseDirection();
			if(curent == destination)
				return true;
		}
		return false;
	}
}
