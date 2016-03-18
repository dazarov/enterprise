package com.dworld.units;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dworld.core.DWEngine;
import com.dworld.core.IUnit;

public abstract class Unit implements IUnit{
	
	// External Commands (from the user)
	public static final int EXTERNAL_COMMAND_DEFAULT		= 0;
	public static final int EXTERNAL_COMMAND_ACTIVATE		= 1;
	public static final int EXTERNAL_COMMAND_DEACTIVATE		= 2;
	
	public static final int EXTERNAL_COMMAND_STAY			= 3;
	public static final int EXTERNAL_COMMAND_MOVE_AROUND 	= 4;
	public static final int EXTERNAL_COMMAND_MOVE_TO		= 5; // Point should be used as an argument
	public static final int EXTERNAL_COMMAND_ATTACK			= 6; // Point should be used as an argument
	public static final int EXTERNAL_COMMAND_PATROL			= 7;
	public static final int EXTERNAL_COMMAND_DEFENSE		= 8;
	
	// open then deactivate
	public static final int EXTERNAL_COMMAND_OPEN_GATE		= 9;
	
	// close then deactivate
	public static final int EXTERNAL_COMMAND_CLOSE_GATE		= 10;
	
	// Internal Commands (from the rules)
	public static final int INTERNAL_COMMAND_FIRE_BULLET 	= 11; // Direction should be used as an argument
	public static final int INTERNAL_COMMAND_FIRE_CANNON 	= 12; // Direction should be used as an argument
	public static final int INTERNAL_COMMAND_FIRE_ROCKET 	= 13; // Direction should be used as an argument
	public static final int INTERNAL_COMMAND_FIRE_BOMB 		= 14; // Direction should be used as an argument
	
	public static final int INTERNAL_COMMAND_START 			= 15; // Direction should be used as an argument
	public static final int INTERNAL_COMMAND_STOP 			= 16;
	
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
	
	abstract public void command(int commandId, Object[] args);
}
