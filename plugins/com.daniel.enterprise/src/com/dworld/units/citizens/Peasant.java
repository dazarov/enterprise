package com.dworld.units.citizens;

import com.dworld.core.DWConstants;
import com.dworld.core.Land;
import com.dworld.units.MovableUnit;
import com.dworld.units.WalkableUnit;

public class Peasant extends WalkableUnit {

	public Peasant(int x, int y, Land land) {
		super(x, y, land, DWConstants.PEASANT_SPEED);
		mode = STAY_MODE;
	}

	@Override
	protected Land getGrave(Land beneath) {
		return Land.Empty;
	}
	
	@Override
	protected boolean lookAround() {
		if (Land.getLand(getLocation()) != getLand()) {
			die();
			Land.setLand(getLocation(), getGrave(beneath));
			return false;
		}
		return true;
	}

}
