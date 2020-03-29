package com.dworld.units;

import com.dworld.core.DWConstants;
import com.dworld.core.Land;

public abstract class Soldier extends WalkableUnit {
	public Soldier(int x, int y, Land land) {
		super(x, y, land, DWConstants.SOLDIER_SPEED);
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
