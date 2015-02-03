package com.dworld.units;

import com.dworld.core.DWConstants;

public abstract class Soldier extends MovableUnit {
	public Soldier(int x, int y, int code) {
		super(x, y, code, DWConstants.SOLDIER_SPEED);
	}
	
	@Override
	protected boolean lookAround() {
		if (!checkLand()) {
			return false;
		}
		
		lightDefenseComplex();
		
		fireBullets();
		
		return true;
	}
}
