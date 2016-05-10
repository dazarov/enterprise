package com.dworld.core;

public enum Direction {
	NORTH {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(0, -1);
		}
	},
	SOUTH {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(0, 1);
		}
	},
	WEST {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(-1, 0);
		}
	},
	EAST {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(1, 0);
		}
	},
	NORTHWEST {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(-1, -1);
		}
	},
	SOUTHWEST {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(-1, 1);
		}
	},
	NORTHEAST {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(1, -1);
		}
	},
	SOUTHEAST {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location.move(1, 1);
		}
	},
	NOWHERE {
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
		
		@Override
		public Location getNewLocation(Location location) {
			return location;
		}
	};

	public abstract Location getNewLocation(Location location);
	
	public abstract Direction getClockwiseDirection();
	
	public abstract Direction getAnticlockwiseDirection();
	
	public abstract Direction getOppositeDirection();
	
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
	
	public static Direction findDirection(Location source, Location destination){
		if(source.getX() > destination.getX()){ // WEST
			if(source.getY() > destination.getY()) // NORTH
				return NORTHWEST;
			else if(source.getY() == destination.getY())
				return WEST;
			else // SOUTH
				return SOUTHWEST;
		}else if(source.getX() == destination.getX()){
			if(source.getY() > destination.getY()) // NORTH
				return NORTH;
			else if(source.getY() == destination.getY())
				return NOWHERE;
			else // SOUTH
				return SOUTH;
		}else{ // EAST
			if(source.getY() > destination.getY()) // NORTH
				return NORTHEAST;
			else if(source.getY() == destination.getY())
				return EAST;
			else // SOUTH
				return SOUTHEAST;
		}
	}
	
	public static boolean isShortcutAvalable(Location source, Location destination) {
		Location location = source;
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
