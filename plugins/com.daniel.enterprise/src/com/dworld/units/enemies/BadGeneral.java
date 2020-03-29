package com.dworld.units.enemies;

import com.dworld.core.Land;

public class BadGeneral extends BadSoldier {
	public BadGeneral(int x, int y, Land land) {
		super(x, y, land);
		mode = STAY_MODE;
	}
}
