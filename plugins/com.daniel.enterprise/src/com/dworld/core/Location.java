package com.dworld.core;

import java.util.Objects;

/**
 * Immutable location
 *
 */
public final class Location {

	private final int x;
	private final int y;
	
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Location move(int deltaX, int deltaY){
		return new Location(x+deltaX, y+deltaY);
	}
	
	public double distance(Location other) {
        double px = other.getX() - this.getX();
        double py = other.getY() - this.getY();
        return Math.sqrt(px * px + py * py);
    }
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Location)){
			return false;
		}
		Location other = (Location)obj;
		return this.x == other.x && this.y == other.y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
    }
	
	@Override
	public String toString(){
		return "Location ["+x+", "+y+"]";
	}
	
}
