package com.dworld.units;

import com.dworld.core.Land;

public abstract class FlyableUnit extends MovableUnit {

	public FlyableUnit(int x, int y, Land land, double speed) {
		super(x, y, land, speed);
	}
	
	@Override
	protected boolean canMove() {
		return Land.canFly(getLocation(), direction);
	}

}