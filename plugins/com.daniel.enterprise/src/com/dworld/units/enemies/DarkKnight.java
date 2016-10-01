package com.dworld.units.enemies;

import com.dworld.core.Land;

public class DarkKnight extends BadSoldier {
	public DarkKnight(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.Dark_Knight_Grass;
		case Sand:
			return Land.Dark_Knight_Sand;
		default:
			return Land.Dark_Knight;
		}
	}
}
