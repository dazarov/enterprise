package com.dworld.units;

import java.awt.Point;

import com.dworld.core.CommonTargetManager;
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
