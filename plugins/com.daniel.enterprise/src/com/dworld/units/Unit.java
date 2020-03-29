package com.dworld.units;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dworld.core.DWConfiguration;
import com.dworld.core.IUnit;
import com.dworld.core.Location;

public abstract class Unit implements IUnit{
	
	// External Commands (from the user)
	public static final int EXTERNAL_COMMAND_DEFAULT		= 0;
	public static final int EXTERNAL_COMMAND_ACTIVATE		= 1;
	public static final int EXTERNAL_COMMAND_DEACTIVATE		= 2;
	
	public static final int EXTERNAL_COMMAND_STAY			= 3;
	public static final int EXTERNAL_COMMAND_MOVE_AROUND 	= 4;
	public static final int EXTERNAL_COMMAND_MOVE_TO		= 5; // Location should be used as an argument
	public static final int EXTERNAL_COMMAND_ATTACK			= 6; // Location should be used as an argument
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
	
	private Location location;
	
	static private int units = 0;
	
	public static int getUnits(){
		return units;
	}
	
	private final int id;
	
	public Unit(int x, int y){
		alive = true;
		this.location = new Location(x, y);
		units++;
		id = units;
		DWConfiguration.getInstance().getEngine().addUnit(this);
	}
	
	@Override
	public int hashCode(){
		return id;
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Unit)){
			return false;
		}
		return this.id == ((Unit)other).id;
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
		DWConfiguration.getInstance().getEngine().addUnit(this);
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		if(!(this instanceof MovableUnit)){
			throw new IllegalStateException("Illegal use of method setLocation, make sure your unit is instance of MovableUnit!");
		}
		Location prev = this.location;
		this.location = location;
		DWConfiguration.getInstance().getEngine().moveUnit(this, prev);
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
	
	@Override
	public String toString(){
		return "Unit " + this.getClass() + " at " + location;
	}
	
	abstract public void command(int commandId, Object[] args);
}
