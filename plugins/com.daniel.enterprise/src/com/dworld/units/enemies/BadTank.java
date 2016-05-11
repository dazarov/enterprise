package com.dworld.units.enemies;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Tank;
import com.dworld.units.weapon.Rocket;

public class BadTank extends Tank {

	public BadTank(int x, int y, int code) {
		super(x, y, Land.BadTank);
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.BadTank_Grass;
		case Land.Sand:
			return Land.BadTank_Sand;
		default:
			return Land.BadTank;
		}
	}
	
	@Override
	protected int getGrave(int beneath){
		return Land.TankGrave;
	}
	
	@Override
	protected Set<Integer> getListToFightWith(){
		return Land.citizenList;
	}
	
	@Override
	protected Set<Integer> getArmoredListToFightWith(){
		return Land.armoredCitizenList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.EnemyFriendly;
	}}
