package com.dworld.units.weapon;

import java.util.List;

import com.dworld.core.DWorldConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;

public class Rocket extends MovableWeapon {
	public final static int ManFriendly = 1;
	public final static int EnemyFriendly = 2;
	private int counter=0;

	private int type;

	public Rocket(int x, int y, Direction direction, int type) {
		super(x, y, Land.RocketNorth, DWorldConstants.ROCKET_SPEED);
		this.type = type;
		setDirection(direction);
		code = getCode(beneath);
		Land.setLand(getLocation(), beneath);
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
	}

	@Override
	public int getCode(int beneath) {
		if(beneath == Land.Grass || beneath == Land.Mine_Grass){
			if(direction == Direction.north)
				return Land.RocketNorth_Grass;
			else if(direction == Direction.south)
				return Land.RocketSouth_Grass;
			else if(direction == Direction.east)
				return Land.RocketEast_Grass;
			else if(direction == Direction.west)
				return Land.RocketWest_Grass;
			else if(direction == Direction.northwest)
				return Land.RocketNorthWest_Grass;
			else if(direction == Direction.northeast)
				return Land.RocketNorthEast_Grass;
			else if(direction == Direction.southwest)
				return Land.RocketSouthWest_Grass;
			else if(direction == Direction.southeast)
				return Land.RocketSouthEast_Grass;
			
			return Land.Rocket;
		}else if(beneath == Land.Water){
			if(direction == Direction.north)
				return Land.RocketNorth_Water;
			else if(direction == Direction.south)
				return Land.RocketSouth_Water;
			else if(direction == Direction.east)
				return Land.RocketEast_Water;
			else if(direction == Direction.west)
				return Land.RocketWest_Water;
			else if(direction == Direction.northwest)
				return Land.RocketNorthWest_Water;
			else if(direction == Direction.northeast)
				return Land.RocketNorthEast_Water;
			else if(direction == Direction.southwest)
				return Land.RocketSouthWest_Water;
			else if(direction == Direction.southeast)
				return Land.RocketSouthEast_Water;
			
			return Land.Rocket;
		}else if(beneath == Land.Sand || beneath == Land.Mine_Sand){
			if(direction == Direction.north)
				return Land.RocketNorth_Sand;
			else if(direction == Direction.south)
				return Land.RocketSouth_Sand;
			else if(direction == Direction.east)
				return Land.RocketEast_Sand;
			else if(direction == Direction.west)
				return Land.RocketWest_Sand;
			else if(direction == Direction.northwest)
				return Land.RocketNorthWest_Sand;
			else if(direction == Direction.northeast)
				return Land.RocketNorthEast_Sand;
			else if(direction == Direction.southwest)
				return Land.RocketSouthWest_Sand;
			else if(direction == Direction.southeast)
				return Land.RocketSouthEast_Sand;
			
			return Land.Rocket;
		}else{
			if(direction == Direction.north)
				return Land.RocketNorth;
			else if(direction == Direction.south)
				return Land.RocketSouth;
			else if(direction == Direction.east)
				return Land.RocketEast;
			else if(direction == Direction.west)
				return Land.RocketWest;
			else if(direction == Direction.northwest)
				return Land.RocketNorthWest;
			else if(direction == Direction.northeast)
				return Land.RocketNorthEast;
			else if(direction == Direction.southwest)
				return Land.RocketSouthWest;
			else if(direction == Direction.southeast)
				return Land.RocketSouthEast;
			
			return Land.Rocket;
		}
	}
	
	public static Direction getDirection(int code) {
		switch(code){
		case Land.RocketNorth:
		case Land.RocketNorth_Grass:
		case Land.RocketNorth_Sand:
		case Land.RocketNorth_Water:
			return Direction.north;
		case Land.RocketSouth:
		case Land.RocketSouth_Grass:
		case Land.RocketSouth_Sand:
		case Land.RocketSouth_Water:
			return Direction.south;
		case Land.RocketEast:
		case Land.RocketEast_Grass:
		case Land.RocketEast_Sand:
		case Land.RocketEast_Water:
			return Direction.east;
		case Land.RocketWest:
		case Land.RocketWest_Grass:
		case Land.RocketWest_Sand:
		case Land.RocketWest_Water:
			return Direction.west;
		case Land.RocketNorthWest:
		case Land.RocketNorthWest_Grass:
		case Land.RocketNorthWest_Sand:
		case Land.RocketNorthWest_Water:
			return Direction.northwest;
		case Land.RocketNorthEast:
		case Land.RocketNorthEast_Grass:
		case Land.RocketNorthEast_Sand:
		case Land.RocketNorthEast_Water:
			return Direction.northeast;
		case Land.RocketSouthWest:
		case Land.RocketSouthWest_Grass:
		case Land.RocketSouthWest_Sand:
		case Land.RocketSouthWest_Water:
			return Direction.southwest;
		case Land.RocketSouthEast:
		case Land.RocketSouthEast_Grass:
		case Land.RocketSouthEast_Sand:
		case Land.RocketSouthEast_Water:
			return Direction.southeast;
		}
		
		return Direction.nowhere;	
			
	}
	
	@Override
	protected List<Integer> getWalkList(){
		return Land.flyAndFindList;
	}

	private boolean first=true;

	@Override
	protected boolean lookAround() {
		if (Land.getLand(getLocation()) != getCode(beneath) && !first) {
			die();
			return false;
		}

		Direction dir = Direction.nowhere;
		if (type == EnemyFriendly){
			dir = findTarget(Land.citizenList, DWorldConstants.ROCKET_VISIBLE_DISTANCE);
		}else{
			dir = findTarget(Land.enemyList, DWorldConstants.ROCKET_VISIBLE_DISTANCE);
		}
		if (dir != Direction.nowhere && direction != dir) {
			direction = dir;
			code = getCode(beneath);
			Land.setLand(getLocation(), code);
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
		if(counter > DWorldConstants.ROCKET_RANGE){
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

	protected Direction findTarget(List<Integer> list, final int maxDistance) {
		int distance;
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
			distance = Land.findUnit(getLocation(), dir, list, maxDistance);
			if (distance >= 0) {
				return dir;
			}
		}
		return Direction.nowhere;
	}

	@Override
	protected int getGrave(int beneath) {
		return beneath;
	}
	
	@Override
	protected boolean isGoing(){
		return true;
	}
}
