package com.dworld.units.weapon;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.SearchResult;
import com.dworld.ui.DWSounds;
import com.dworld.units.FlyableUnit;

public class Rocket extends FlyableUnit {
	public final static int ManFriendly = 1;
	public final static int EnemyFriendly = 2;
	private int counter=0;

	private int type;

	public Rocket(int x, int y, Direction direction, int type) {
		super(x, y, Land.RocketNorth, DWConstants.ROCKET_SPEED);
		this.type = type;
		setDirection(direction);
		land = getLand();
		Land.setLand(getLocation(), beneath);
		DWSounds.ROCKET_LAUNCH.playSound();
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
	}

	@Override
	public Land getLand() {
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
	
	public static Direction getDirection(Land land) {
		switch(land){
		case RocketNorth:
			return Direction.NORTH;
		case RocketSouth:
			return Direction.SOUTH;
		case RocketEast:
			return Direction.EAST;
		case RocketWest:
			return Direction.WEST;
		case RocketNorthWest:
			return Direction.NORTHWEST;
		case RocketNorthEast:
			return Direction.NORTHEAST;
		case RocketSouthWest:
			return Direction.SOUTHWEST;
		case RocketSouthEast:
			return Direction.SOUTHEAST;
		default:
			return Direction.NOWHERE;
		}
	}
	
	@Override
	protected Set<Land> getWalkList(){
		return Land.flyAndFindList;
	}

	private boolean first=true;

	@Override
	protected boolean lookAround() {
		if (Land.getLand(getLocation()) != getLand() && !first) {
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
			land = getLand();
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
		
		Land.setForeground(getLocation().getX(), getLocation().getY(), beneath);
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
