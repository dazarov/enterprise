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
			return Land.GoodGeneral_Grass;
		case Land.Sand:
			return Land.GoodGeneral_Sand;
		default:
			return Land.GoodGeneral;
		}
	}

}
