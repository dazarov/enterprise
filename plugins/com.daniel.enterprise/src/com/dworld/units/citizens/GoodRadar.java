package com.dworld.units.citizens;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Radar;
import com.dworld.units.weapon.Rocket;

public class GoodRadar extends Radar {

	public GoodRadar(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.enemyList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.ManFriendly;
	}
}
