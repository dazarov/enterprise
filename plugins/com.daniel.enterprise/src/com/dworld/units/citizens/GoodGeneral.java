package com.dworld.units.citizens;

import com.dworld.core.Land;

public class GoodGeneral extends GoodSoldier {
	public GoodGeneral(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.GoodGeneral_Grass;
		case Sand:
			return Land.GoodGeneral_Sand;
		default:
			return Land.GoodGeneral;
		}
	}

}
