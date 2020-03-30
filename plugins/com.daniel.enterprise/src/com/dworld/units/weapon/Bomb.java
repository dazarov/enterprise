package com.dworld.units.weapon;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.ui.DWSounds;
import com.dworld.units.WalkableUnit;

public class Bomb extends WalkableUnit {
	int distance;
	boolean first = true;

	public Bomb(int x, int y, Direction direction, int distance) {
		super(x, y, Land.Bomb, DWConstants.BOMB_SPEED);
		setDirection(direction);
		this.distance = distance;
		Land.setLand(getLocation(), beneath);
		DWSounds.GRENADE.playSound();
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
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
			Land.setForeground(getLocation().getX(), getLocation().getY(), beneath);
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
