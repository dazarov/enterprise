package com.dworld.units.enemies;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Radar;
import com.dworld.units.weapon.Rocket;

public class BadRadar extends Radar {
	public BadRadar(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.citizenList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.EnemyFriendly;
	}
}
