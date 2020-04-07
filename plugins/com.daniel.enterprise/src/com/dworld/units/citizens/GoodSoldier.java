package com.dworld.units.citizens;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Soldier;

public class GoodSoldier extends Soldier {
	public GoodSoldier(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	protected Land getGrave(Land beneath){
		return Land.Grave;
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.enemyList;
	}
}
