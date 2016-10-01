package com.dworld.units;

import com.dworld.core.DWConstants;
import com.dworld.core.Land;

public abstract class Soldier extends MovableUnit {
	public Soldier(int x, int y, Land land) {
		super(x, y, land, DWConstants.SOLDIER_SPEED);
	}
	
	@Override
	protected boolean lookAround() {
		if (!checkLand()) {
			return false;
		}
//		if(mode == STAY_MODE){
//			Point point = CommonTargetManager.getTargetLocation(this);
//			if(point != null){
//				mode = MOVE_TO_MODE;
//				destination = point; 
//			}
//		}
		
		lightDefenseComplex();
		
		fireBullets();
		
		return true;
	}
}
