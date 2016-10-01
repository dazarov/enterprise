package com.dworld.units.citizens;

import com.dworld.core.DWConstants;
import com.dworld.core.Land;
import com.dworld.units.MovableUnit;

public class Peasant extends MovableUnit {

	public Peasant(int x, int y, Land land) {
		super(x, y, land, DWConstants.PEASANT_SPEED);
		mode = STAY_MODE;
	}

	@Override
	protected Land getGrave(Land beneath) {
		return Land.Empty;
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.Peasant_Grass;
		case Sand:
			return Land.Peasant_Sand;
		default:
			return Land.Peasant;
		}
	}

	@Override
	protected boolean lookAround() {
		if (Land.getLand(getLocation()) != getLand(beneath)) {
			die();
			Land.setLand(getLocation(), getGrave(beneath));
			return false;
		}
		return true;
	}

}
