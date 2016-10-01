package com.dworld.units.citizens;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Soldier;

public class GoodSoldier extends Soldier {
	public GoodSoldier(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.GoodSoldier_Grass;
		case Sand:
			return Land.GoodSoldier_Sand;
		default:
			return Land.GoodSoldier;
		}
	}
	
	@Override
	protected Land getGrave(Land beneath){
		switch(beneath){
		case Grass:
			return Land.Grave_Grass;
		case Sand:
			return Land.Grave_Sand;
		default:
			return Land.Grave;
		}
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.enemyList;
	}
}
