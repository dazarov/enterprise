package com.dworld.units.weapon;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.SearchResult;

public class Rocket extends MovableWeapon {
	public final static int ManFriendly = 1;
	public final static int EnemyFriendly = 2;
	private int counter=0;

	private int type;

	public Rocket(int x, int y, Direction direction, int type) {
		super(x, y, Land.RocketNorth, DWConstants.ROCKET_SPEED);
		this.type = type;
		setDirection(direction);
		land = getLand(beneath);
		Land.setLand(getLocation(), beneath);
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
	}

	@Override
	public Land getLand(Land beneath) {
		if(beneath == Land.Grass || beneath == Land.Mine_Grass){
			if(direction == Direction.NORTH)
				return Land.RocketNorth_Grass;
			else if(direction == Direction.SOUTH)
				return Land.RocketSouth_Grass;
			else if(direction == Direction.EAST)
				return Land.RocketEast_Grass;
			else if(direction == Direction.WEST)
				return Land.RocketWest_Grass;
			else if(direction == Direction.NORTHWEST)
				return Land.RocketNorthWest_Grass;
			else if(direction == Direction.NORTHEAST)
				return Land.RocketNorthEast_Grass;
			else if(direction == Direction.SOUTHWEST)
				return Land.RocketSouthWest_Grass;
			else if(direction == Direction.SOUTHEAST)
				return Land.RocketSouthEast_Grass;
			
			return Land.Rocket;
		}else if(beneath == Land.Water){
			if(direction == Direction.NORTH)
				return Land.RocketNorth_Water;
			else if(direction == Direction.SOUTH)
				return Land.RocketSouth_Water;
			else if(direction == Direction.EAST)
				return Land.RocketEast_Water;
			else if(direction == Direction.WEST)
				return Land.RocketWest_Water;
			else if(direction == Direction.NORTHWEST)
				return Land.RocketNorthWest_Water;
			else if(direction == Direction.NORTHEAST)
				return Land.RocketNorthEast_Water;
			else if(direction == Direction.SOUTHWEST)
				return Land.RocketSouthWest_Water;
			else if(direction == Direction.SOUTHEAST)
				return Land.RocketSouthEast_Water;
			
			return Land.Rocket;
		}else if(beneath == Land.Sand || beneath == Land.Mine_Sand){
			if(direction == Direction.NORTH)
				return Land.RocketNorth_Sand;
			else if(direction == Direction.SOUTH)
				return Land.RocketSouth_Sand;
			else if(direction == Direction.EAST)
				return Land.RocketEast_Sand;
			else if(direction == Direction.WEST)
				return Land.RocketWest_Sand;
			else if(direction == Direction.NORTHWEST)
				return Land.RocketNorthWest_Sand;
			else if(direction == Direction.NORTHEAST)
				return Land.RocketNorthEast_Sand;
			else if(direction == Direction.SOUTHWEST)
				return Land.RocketSouthWest_Sand;
			else if(direction == Direction.SOUTHEAST)
				return Land.RocketSouthEast_Sand;
			
			return Land.Rocket;
		}else{
			if(direction == Direction.NORTH)
				return Land.RocketNorth;
			else if(direction == Direction.SOUTH)
				return Land.RocketSouth;
			else if(direction == Direction.EAST)
				return Land.RocketEast;
			else if(direction == Direction.WEST)
				return Land.RocketWest;
			else if(direction == Direction.NORTHWEST)
				return Land.RocketNorthWest;
			else if(direction == Direction.NORTHEAST)
				return Land.RocketNorthEast;
			else if(direction == Direction.SOUTHWEST)
				return Land.RocketSouthWest;
			else if(direction == Direction.SOUTHEAST)
				return Land.RocketSouthEast;
			
			return Land.Rocket;
		}
	}
	
	public static Direction getDirection(Land land) {
		switch(land){
		case RocketNorth:
		case RocketNorth_Grass:
		case RocketNorth_Sand:
		case RocketNorth_Water:
			return Direction.NORTH;
		case RocketSouth:
		case RocketSouth_Grass:
		case RocketSouth_Sand:
		case RocketSouth_Water:
			return Direction.SOUTH;
		case RocketEast:
		case RocketEast_Grass:
		case RocketEast_Sand:
		case RocketEast_Water:
			return Direction.EAST;
		case RocketWest:
		case RocketWest_Grass:
		case RocketWest_Sand:
		case RocketWest_Water:
			return Direction.WEST;
		case RocketNorthWest:
		case RocketNorthWest_Grass:
		case RocketNorthWest_Sand:
		case RocketNorthWest_Water:
			return Direction.NORTHWEST;
		case RocketNorthEast:
		case RocketNorthEast_Grass:
		case RocketNorthEast_Sand:
		case RocketNorthEast_Water:
			return Direction.NORTHEAST;
		case RocketSouthWest:
		case RocketSouthWest_Grass:
		case RocketSouthWest_Sand:
		case RocketSouthWest_Water:
			return Direction.SOUTHWEST;
		case RocketSouthEast:
		case RocketSouthEast_Grass:
		case RocketSouthEast_Sand:
		case RocketSouthEast_Water:
			return Direction.SOUTHEAST;
		}
		
		return Direction.NOWHERE;	
			
	}
	
	@Override
	protected Set<Land> getWalkList(){
		return Land.flyAndFindList;
	}

	private boolean first=true;

	@Override
	protected boolean lookAround() {
		if (Land.getLand(getLocation()) != getLand(beneath) && !first) {
			die();
			return false;
		}

		Direction dir = Direction.NOWHERE;
		if (type == EnemyFriendly){
			dir = findTarget(Land.citizenList, DWConstants.ROCKET_VISIBLE_DISTANCE);
		}else{
			dir = findTarget(Land.enemyList, DWConstants.ROCKET_VISIBLE_DISTANCE);
		}
		if (dir != Direction.NOWHERE && direction != dir) {
			direction = dir;
			land = getLand(beneath);
			Land.setLand(getLocation(), land);
		}
		return true;
	}

	@Override
	protected boolean findNewDirection() {
		die();
		return false;
	}

	@Override
	protected void walk() {
		counter++;
		if(counter > DWConstants.ROCKET_RANGE){
			die();
			return;
		}
		if(first) first = false;
		
		Land.setLand(getLocation(), beneath);
		setLocation(Land.getNewLocation(getLocation(), direction));
		beneath = Land.setLand(getLocation(), this);
		if (Land.rocketListContains(beneath)) {
			die();
			return;
		}
	}

	protected int getType() {
		return type;
	}

	protected void setType(int type) {
		this.type = type;
	}

	protected Direction findTarget(Set<Land> list, final int maxDistance) {
		SearchResult result;
		Direction dir = direction;
		for (int i = 0; i < 5; i++) {
			switch (i) {
			case 1:
				dir = dir.getClockwiseDirection();
				break;
			case 2:
				dir = dir.getAnticlockwiseDirection();
				dir = dir.getAnticlockwiseDirection();
				break;
			case 3:
				dir = dir.getClockwiseDirection();
				dir = dir.getClockwiseDirection();
				dir = dir.getClockwiseDirection();
				break;
			case 4:
				dir = dir.getAnticlockwiseDirection();
				dir = dir.getAnticlockwiseDirection();
				dir = dir.getAnticlockwiseDirection();
				dir = dir.getAnticlockwiseDirection();
				break;
			}
			result = Land.search(getLocation(), dir, list, maxDistance);
			if (result != null) {
				return dir;
			}
		}
		return Direction.NOWHERE;
	}

	@Override
	protected Land getGrave(Land beneath) {
		return beneath;
	}
	
	@Override
	protected boolean isGoing(){
		return true;
	}
}
