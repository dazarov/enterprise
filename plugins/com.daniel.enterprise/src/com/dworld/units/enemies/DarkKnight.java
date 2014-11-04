package com.dworld.units.enemies;

import com.dworld.core.Land;

public class DarkKnight extends BadSoldier {
	public DarkKnight(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.Dark_Knight_Grass;
		case Land.Sand:
			return Land.Dark_Knight_Sand;
		default:
			return Land.Dark_Knight;
		}
	}
}
