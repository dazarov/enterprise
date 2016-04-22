package com.dworld.core;

import java.awt.Point;

public enum Direction {
	NORTH,
	SOUTH,
	WEST,
	EAST,
	NORTHWEST,
	SOUTHWEST,
	NORTHEAST,
	SOUTHEAST,
	NOWHERE;
	
	public String toString(){
		if (this == NORTH)
			return "NORTH";
		else if (this == NORTHEAST)
			return "NORTHEAST";
		else if (this == EAST)
			return "EAST";
		else if (this == SOUTHEAST)
			return "SOUTHEAST";
		else if (this == SOUTH)
			return "SOUTH";
		else if (this == SOUTHWEST)
			return "SOUTHWEST";
		else if (this == WEST)
			return "WEST";
		else if (this == NORTHWEST)
			return "NORTHWEST";
		else return "NOWHERE";
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
		if (this == NORTH)
			return NORTHEAST;
		else if (this == NORTHEAST)
			return EAST;
		else if (this == EAST)
			return SOUTHEAST;
		else if (this == SOUTHEAST)
			return SOUTH;
		else if (this == SOUTH)
			return SOUTHWEST;
		else if (this == SOUTHWEST)
			return WEST;
		else if (this == WEST)
			return NORTHWEST;
		else if (this == NORTHWEST)
			return NORTH;
		else return NOWHERE;
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
		if (this == NORTH)
			return NORTHWEST;
		else if (this == NORTHWEST)
			return WEST;
		else if (this == WEST)
			return SOUTHWEST;
		else if (this == SOUTHWEST)
			return SOUTH;
		else if (this == SOUTH)
			return SOUTHEAST;
		else if (this == SOUTHEAST)
			return EAST;
		else if (this == EAST)
			return NORTHEAST;
		else if (this == NORTHEAST)
			return NORTH;
		else return NOWHERE;
	}
	
	public Direction getOppositeDirection() {
		if (this == NORTH)
			return SOUTH;
		else if (this == NORTHEAST)
			return SOUTHWEST;
		else if (this == EAST)
			return WEST;
		else if (this == SOUTHEAST)
			return NORTHWEST;
		else if (this == SOUTH)
			return NORTH;
		else if (this == SOUTHWEST)
			return NORTHEAST;
		else if (this == WEST)
			return EAST;
		else if (this == NORTHWEST)
			return SOUTHEAST;
		else return NOWHERE;
	}
	
	public static Direction findDirection(Point source, Point destination){
		if(source.x > destination.x){ // WEST
			if(source.y > destination.y) // NORTH
				return NORTHWEST;
			else if(source.y == destination.y)
				return WEST;
			else // SOUTH
				return SOUTHWEST;
		}else if(source.x == destination.x){
			if(source.y > destination.y) // NORTH
				return NORTH;
			else if(source.y == destination.y)
				return NOWHERE;
			else // SOUTH
				return SOUTH;
		}else{ // EAST
			if(source.y > destination.y) // NORTH
				return NORTHEAST;
			else if(source.y == destination.y)
				return EAST;
			else // SOUTH
				return SOUTHEAST;
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
