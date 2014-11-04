package com.dworld.units;

import com.dworld.core.DWorldConstants;

public abstract class Soldier extends MovableUnit {
	public Soldier(int x, int y, int code) {
		super(x, y, code, DWorldConstants.SOLDIER_SPEED);
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
