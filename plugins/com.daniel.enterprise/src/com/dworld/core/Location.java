package com.dworld.core;

/**
 * Immutable location
 * @author daniel
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
        long bits = java.lang.Double.doubleToLongBits(getX());
        bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }
	
}
