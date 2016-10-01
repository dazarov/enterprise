package com.dworld.units;

import com.dworld.core.DWConstants;
import com.dworld.core.Land;

public abstract class Bunker extends ActiveUnit {

	public Bunker(int x, int y, Land land) {
		super(x, y, land);
	}

	@Override
	public void step() {
		if (!checkLand()) {
			return;
		}
		
		fireBullets(DWConstants.BUNKER_VISIBLE_DISTANCE);
	}
}
