package com.dworld.units;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dworld.core.Engine;
import com.dworld.core.IUnit;

public abstract class Unit implements IUnit{
	private boolean alive = true;
	
	private Point location;
	
	static private long units = 0;
	
	public static long getUnits(){
		return units;
	}
	
	public Unit(int x, int y){
		alive = true;
		this.location = new Point(x, y);
		units++;
		Engine.getEngine().addUnit(this);
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public void die(){
		alive = false;
	}
	
	@Override
	public void resurrect(){
		alive = true;
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point location) {
		this.location.setLocation(location);
		Engine.getEngine().moveUnit(this);
	}
	
	@Override
	public void save(OutputStream stream) throws IOException{
		
	}
	
	@Override
	public void load(InputStream stream) throws IOException{
		
	}
	
	@Override
	public void init(){
		
	}
	
	abstract public <T> void command(int commandId, T arg);
}
