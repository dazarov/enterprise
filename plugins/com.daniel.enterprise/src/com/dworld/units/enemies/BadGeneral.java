package com.dworld.units.enemies;

import com.dworld.core.Land;

public class BadGeneral extends BadSoldier {
	public BadGeneral(int x, int y, Land land) {
		super(x, y, land);
		mode = STAY_MODE;
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.BadGeneral_Grass;
		case Sand:
			return Land.BadGeneral_Sand;
		default:
			return Land.BadGeneral;
		}
	}
}
