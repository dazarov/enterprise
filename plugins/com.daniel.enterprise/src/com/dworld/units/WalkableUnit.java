package com.dworld.units;

import com.dworld.core.Land;

abstract public class WalkableUnit extends MovableUnit {

	public WalkableUnit(int x, int y, Land land, double speed) {
		super(x, y, land, speed);
	}
	
	@Override
	protected boolean canMove() {
		return Land.canIWalk(getLocation(), direction, Land.walkList);
	}

}
