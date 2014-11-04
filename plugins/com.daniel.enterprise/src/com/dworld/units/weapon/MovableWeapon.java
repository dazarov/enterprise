package com.dworld.units.weapon;

import com.dworld.core.Land;
import com.dworld.units.MovableUnit;

public abstract class MovableWeapon extends MovableUnit{

	public MovableWeapon(int x, int y, int code, double speed) {
		super(x, y, code, speed);
	}

	@Override
	protected int getDefaultBeneath(int code) {
		return Land.getLand(getLocation());
	}

}
