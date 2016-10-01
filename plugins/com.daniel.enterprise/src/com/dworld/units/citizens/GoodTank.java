package com.dworld.units.citizens;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Tank;
import com.dworld.units.weapon.Rocket;

public class GoodTank extends Tank {

	public GoodTank(int x, int y, Land land) {
		super(x, y, Land.GoodTank);
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.GoodTank_Grass;
		case Sand:
			return Land.GoodTank_Sand;
		default:
			return Land.GoodTank;
		}
	}

	@Override
	protected Land getGrave(Land beneath){
		return Land.TankGrave;
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.enemyList;
	}
	
	@Override
	protected Set<Land> getArmoredListToFightWith(){
		return Land.armoredEnemyList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.ManFriendly;
	}
	
}
