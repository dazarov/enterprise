package com.dworld.units.weapon;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;

public class Bomb extends MovableWeapon {
	int distance;
	boolean first = true;

	public Bomb(int x, int y, Direction direction, int distance) {
		super(x, y, Land.Bomb, DWConstants.BOMB_SPEED);
		setDirection(direction);
		this.distance = distance;
		Land.setLand(getLocation(), beneath);
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.Bomb_Grass;
		case Water:
			return Land.Bomb_Water;
		case Sand:
			return Land.Bomb_Sand;
		default:
			return Land.Bomb;
		}
	}
	
	@Override
	protected Set<Land> getWalkList(){
		return Land.flyAndFindList;
	}

	@Override
	protected boolean lookAround() {
		return true;
	}

	@Override
	protected boolean findNewDirection() {
		if (distance <= 0) {
			die();
		}
		distance--;
		return false;
	}

	@Override
	protected void walk() {
		if (distance <= 0) {
			die();
			return;
		}
		if (first)
			first = false;
		else
			Land.setLand(getLocation(), beneath);
		setLocation(Land.getNewLocation(getLocation(), direction));
		beneath = Land.setLand(getLocation(), this);
		distance--;
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
