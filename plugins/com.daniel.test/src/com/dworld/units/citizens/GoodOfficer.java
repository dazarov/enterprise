package com.dworld.units.citizens;

import com.dworld.core.Land;

public class GoodOfficer extends GoodSoldier {
	public GoodOfficer(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.Officer_Grass;
		case Land.Sand:
			return Land.Officer_Sand;
		default:
			return Land.Officer;
		}
	}

}
