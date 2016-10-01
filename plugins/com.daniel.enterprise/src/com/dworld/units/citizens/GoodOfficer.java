package com.dworld.units.citizens;

import com.dworld.core.Land;

public class GoodOfficer extends GoodSoldier {
	public GoodOfficer(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.GoodOfficer_Grass;
		case Sand:
			return Land.GoodOfficer_Sand;
		default:
			return Land.GoodOfficer;
		}
	}

}
