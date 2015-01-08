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
			return Land.GoodOfficer_Grass;
		case Land.Sand:
			return Land.GoodOfficer_Sand;
		default:
			return Land.GoodOfficer;
		}
	}

}
