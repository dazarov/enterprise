package com.dworld.units.enemies;

import java.util.List;

import com.dworld.core.Land;
import com.dworld.units.Tank;
import com.dworld.units.weapon.Rocket;

public class BadTank extends Tank {

	public BadTank(int x, int y, int code) {
		super(x, y, Land.Tank);
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.Tank_Grass;
		case Land.Sand:
			return Land.Tank_Sand;
		default:
			return Land.Tank;
		}
	}
	
	@Override
	protected int getGrave(int beneath){
		return Land.TankGrave;
	}
	
	@Override
	protected List<Integer> getListToFightWith(){
		return Land.citizenList;
	}
	
	@Override
	protected List<Integer> getArmoredListToFightWith(){
		return Land.armoredCitizenList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.EnemyFriendly;
	}}
