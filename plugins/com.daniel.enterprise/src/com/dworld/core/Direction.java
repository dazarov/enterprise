package com.dworld.core;

import java.awt.Point;

public enum Direction {
	NORTH{
		@Override
		public String toString(){
			return "NORTH";
		}
		
		@Override
		public Direction getClockwiseDirection() {
			return NORTHEAST;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return NORTHWEST;
		}
		
		@Override
		public Direction getOppositeDirection() {
			return SOUTH;
		}
	},
	SOUTH{
		@Override
		public String toString(){
			return "SOUTH";
		}

		@Override
		public Direction getClockwiseDirection() {
			return SOUTHWEST;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return SOUTHEAST;
		}
		@Override
		public Direction getOppositeDirection() {
			return NORTH;
		}
	},
	WEST{
		@Override
		public String toString(){
			return "WEST";
		}

		@Override
		public Direction getClockwiseDirection() {
			return NORTHWEST;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return SOUTHWEST;
		}
		
		@Override
		public Direction getOppositeDirection() {
			return EAST;
		}
	},
	EAST{
		@Override
		public String toString(){
			return "EAST";
		}

		@Override
		public Direction getClockwiseDirection() {
			return SOUTHEAST;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return NORTHEAST;
		}
		
		@Override
		public Direction getOppositeDirection() {
			return WEST;
		}
	},
	NORTHWEST{
		@Override
		public String toString(){
			return "NORTHWEST";
		}

		@Override
		public Direction getClockwiseDirection() {
			return NORTH;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return WEST;
		}
		@Override
		public Direction getOppositeDirection() {
			return SOUTHEAST;
		}
	},
	SOUTHWEST{
		@Override
		public String toString(){
			return "SOUTHWEST";
		}
		
		@Override
		public Direction getClockwiseDirection() {
			return WEST;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return SOUTH;
		}
		
		@Override
		public Direction getOppositeDirection() {
			return NORTHEAST;
		}
	},
	NORTHEAST{
		@Override
		public String toString(){
			return "NORTHEAST";
		}

		@Override
		public Direction getClockwiseDirection() {
			return EAST;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return NORTH;
		}

		@Override
		public Direction getOppositeDirection() {
			return SOUTHWEST;
		}
	},
	SOUTHEAST{
		@Override
		public String toString(){
			return "SOUTHEAST";
		}
		
		@Override
		public Direction getClockwiseDirection() {
			return SOUTH;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return EAST;
		}

		@Override
		public Direction getOppositeDirection() {
			return NORTHWEST;
		}
	},
	NOWHERE{
		@Override
		public String toString(){
			return "NOWHERE";
		}
		
		@Override
		public Direction getClockwiseDirection() {
			return NOWHERE;
		}
		
		@Override
		public Direction getAnticlockwiseDirection() {
			return NOWHERE;
		}
		
		@Override
		public Direction getOppositeDirection() {
			return NOWHERE;
		}
	};
	
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
	
	public abstract Direction getClockwiseDirection();

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
	
	public abstract Direction getAnticlockwiseDirection();
	
	public abstract Direction getOppositeDirection();
	
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
