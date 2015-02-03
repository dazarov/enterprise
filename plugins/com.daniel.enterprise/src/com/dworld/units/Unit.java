package com.dworld.units;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dworld.core.DWEngine;
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
		DWEngine.getEngine().addUnit(this);
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
		DWEngine.getEngine().addUnit(this);
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point location) {
		if(!(this instanceof MovableUnit)){
			throw new IllegalStateException("Illegal use of method setLocation, make sure your unit is instance of MovableUnit!");
		}
		this.location.setLocation(location);
		DWEngine.getEngine().moveUnit(this);
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
