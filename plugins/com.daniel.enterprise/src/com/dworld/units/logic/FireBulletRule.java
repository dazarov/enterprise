package com.dworld.units.logic;

import com.dworld.core.IActive;

public class FireBulletRule extends Rule<IActive> {

	@Override
	public int getPriority() {
		return HIGHT_PRIORITY;
	}

	@Override
	public boolean test() {
		//getUnit().fireBullets();
		return true;
	}

	@Override
	public boolean process() {
		//getUnit().fireBullets();
		return true;
	}

}
