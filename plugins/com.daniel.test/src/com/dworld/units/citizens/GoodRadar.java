package com.dworld.units.citizens;

import java.util.List;

import com.dworld.core.Land;
import com.dworld.units.Radar;
import com.dworld.units.weapon.Rocket;

public class GoodRadar extends Radar {

	public GoodRadar(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	protected List<Integer> getListToFightWith(){
		return Land.enemyList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.ManFriendly;
	}
}
