package com.dworld.units;

import com.dworld.core.DWorldConstants;
import com.dworld.core.ISlow;

public abstract class Radar extends ActiveUnit implements ISlow {
	
	public Radar(int x, int y, int code) {
		super(x, y, code);
	}

	@Override
	public void step() {
		if (!checkLand()) {
			return;
		}
		
		extendedFireRockets(DWorldConstants.RADAR_VISIBLE_DISTANCE);
	}
}
