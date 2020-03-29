package com.dworld.units.enemies;

import com.dworld.core.Land;

public class BadOfficer extends BadSoldier {
	public BadOfficer(int x, int y, Land land) {
		super(x, y, land);
		mode = STAY_MODE;
	}
}
