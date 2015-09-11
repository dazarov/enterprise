package com.dworld.units.logic;

import com.dworld.core.IActive;

public class FireBulletRule extends Rule<IActive> {

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public int getPriority() {
		return HIGHT_PRIORITY;
	}

	@Override
	public boolean process() {
		//getUnit().fireBullets();
		return true;
	}

}
