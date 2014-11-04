package com.dworld.units.citizens;

import java.util.List;

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
			return Land.Soldier_Grass;
		case Land.Sand:
			return Land.Soldier_Sand;
		default:
			return Land.Soldier;
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
	protected List<Integer> getListToFightWith(){
		return Land.enemyList;
	}
}
