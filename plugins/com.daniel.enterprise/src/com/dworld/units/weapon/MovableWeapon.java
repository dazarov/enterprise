package com.dworld.units.weapon;

import com.dworld.core.Land;
import com.dworld.units.MovableUnit;

public abstract class MovableWeapon extends MovableUnit{

	public MovableWeapon(int x, int y, Land land, double speed) {
		super(x, y, land, speed);
	}

	@Override
	protected Land getDefaultBeneath(Land land) {
		return Land.getLand(getLocation());
	}

}
