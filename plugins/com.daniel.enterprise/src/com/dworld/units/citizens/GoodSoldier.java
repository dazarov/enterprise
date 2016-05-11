package com.dworld.units.citizens;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Soldier;

public class GoodSoldier extends Soldier {
	public GoodSoldier(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.GoodSoldier_Grass;
		case Land.Sand:
			return Land.GoodSoldier_Sand;
		default:
			return Land.GoodSoldier;
		}
	}
	
	@Override
	protected int getGrave(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.Grave_Grass;
		case Land.Sand:
			return Land.Grave_Sand;
		default:
			return Land.Grave;
		}
	}
	
	@Override
	protected Set<Integer> getListToFightWith(){
		return Land.enemyList;
	}
}
