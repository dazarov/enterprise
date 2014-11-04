package com.dworld.units;

import com.dworld.core.DWorldConstants;

public abstract class Tank extends MovableUnit {

	public Tank(int x, int y, int code) {
		super(x, y, code, DWorldConstants.TANK_SPEED);
	}
	
	@Override
	protected boolean lookAround() {
		if (!checkLand()) {
			return false;
		}
		
		fireAgainstRocket();
		
		fireCannonBalls(DWorldConstants.TANK_ARMORED_DISTANCE);
		
		fireBullets(DWorldConstants.TANK_NOTARMORED_DISTANCE);
		
		extendedFireCannonBalls(DWorldConstants.TANK_ARMORED_DISTANCE);
		return true;
	}
}
