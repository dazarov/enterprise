package com.dworld.units.enemies;

import com.dworld.core.Land;

public class BadOfficer extends BadSoldier {
	public BadOfficer(int x, int y, Land land) {
		super(x, y, land);
		mode = STAY_MODE;
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.BadOfficer_Grass;
		case Sand:
			return Land.BadOfficer_Sand;
		default:
			return Land.BadOfficer;
		}
	}
}
