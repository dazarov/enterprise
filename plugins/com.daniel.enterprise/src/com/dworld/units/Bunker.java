package com.dworld.units;

import com.dworld.core.DWorldConstants;

public abstract class Bunker extends ActiveUnit {

	public Bunker(int x, int y, int code) {
		super(x, y, code);
	}

	@Override
	public void step() {
		if (!checkLand()) {
			return;
		}
		
		fireBullets(DWorldConstants.BUNKER_VISIBLE_DISTANCE);
	}
}
