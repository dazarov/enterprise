package com.dworld.units;

import com.dworld.core.DWConstants;
import com.dworld.core.ISlow;
import com.dworld.core.Land;

public abstract class Radar extends ActiveUnit implements ISlow {
	
	public Radar(int x, int y, Land land) {
		super(x, y, land);
	}

	@Override
	public void step() {
		if (!checkLand()) {
			return;
		}
		
		extendedFireRockets(DWConstants.RADAR_VISIBLE_DISTANCE);
	}
}
