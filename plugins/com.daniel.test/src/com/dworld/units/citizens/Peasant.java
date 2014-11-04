package com.dworld.units.citizens;

import com.dworld.core.DWorldConstants;
import com.dworld.core.Land;
import com.dworld.units.MovableUnit;

public class Peasant extends MovableUnit {

	public Peasant(int x, int y, int code) {
		super(x, y, code, DWorldConstants.PEASANT_SPEED);
		mode = STAY_MODE;
	}

	@Override
	protected int getGrave(int beneath) {
		return Land.Empty;
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.Peasant_Grass;
		case Land.Sand:
			return Land.Peasant_Sand;
		default:
			return Land.Peasant;
		}
	}

	@Override
	protected boolean lookAround() {
		if (Land.getLand(getLocation()) != getCode(beneath)) {
			die();
			Land.setLand(getLocation(), getGrave(beneath));
			return false;
		}
		return true;
	}

}
