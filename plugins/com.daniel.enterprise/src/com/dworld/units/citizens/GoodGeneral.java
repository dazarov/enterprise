package com.dworld.units.citizens;

import com.dworld.core.Land;

public class GoodGeneral extends GoodSoldier {
	public GoodGeneral(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.General_Grass;
		case Land.Sand:
			return Land.General_Sand;
		default:
			return Land.General;
		}
	}

}
