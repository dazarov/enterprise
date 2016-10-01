package com.dworld.units;

import com.dworld.core.DWConstants;
import com.dworld.core.Land;

public abstract class Tank extends MovableUnit {

	public Tank(int x, int y, Land land) {
		super(x, y, land, DWConstants.TANK_SPEED);
	}
	
	@Override
	protected boolean lookAround() {
		if (!checkLand()) {
			return false;
		}
		
		fireAgainstRocket();
		
		fireCannonBalls(DWConstants.TANK_ARMORED_DISTANCE);
		
		fireBullets(DWConstants.TANK_NOTARMORED_DISTANCE);
		
		extendedFireCannonBalls(DWConstants.TANK_ARMORED_DISTANCE);
		return true;
	}
}
