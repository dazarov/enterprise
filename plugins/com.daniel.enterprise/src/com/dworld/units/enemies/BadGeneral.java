package com.dworld.units.enemies;

import com.dworld.core.Land;

public class BadGeneral extends BadSoldier {
	public BadGeneral(int x, int y, int code) {
		super(x, y, code);
		mode = STAY_MODE;
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.BadGeneral_Grass;
		case Land.Sand:
			return Land.BadGeneral_Sand;
		default:
			return Land.BadGeneral;
		}
	}
}
