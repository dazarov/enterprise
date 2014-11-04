package com.dworld.units.citizens;

import java.util.List;

import com.dworld.core.Land;
import com.dworld.units.Tank;
import com.dworld.units.weapon.Rocket;

public class GoodTank extends Tank {

	public GoodTank(int x, int y, int code) {
		super(x, y, Land.GoodTank);
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.GoodTank_Grass;
		case Land.Sand:
			return Land.GoodTank_Sand;
		default:
			return Land.GoodTank;
		}
	}

	@Override
	protected int getGrave(int beneath){
		return Land.TankGrave;
	}
	
	@Override
	protected List<Integer> getListToFightWith(){
		return Land.enemyList;
	}
	
	@Override
	protected List<Integer> getArmoredListToFightWith(){
		return Land.armoredEnemyList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.ManFriendly;
	}
	
}
