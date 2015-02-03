package com.dworld.units;

import com.dworld.core.DWConstants;

public abstract class Bunker extends ActiveUnit {

	public Bunker(int x, int y, int code) {
		super(x, y, code);
	}

	@Override
	public void step() {
		if (!checkLand()) {
			return;
		}
		
		fireBullets(DWConstants.BUNKER_VISIBLE_DISTANCE);
	}
}
