package com.dworld.units.enemies;

import com.dworld.core.Land;

public class BadOfficer extends BadSoldier {
	public BadOfficer(int x, int y, int code) {
		super(x, y, code);
		mode = STAY_MODE;
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.BadOfficer_Grass;
		case Land.Sand:
			return Land.BadOfficer_Sand;
		default:
			return Land.BadOfficer;
		}
	}
}
