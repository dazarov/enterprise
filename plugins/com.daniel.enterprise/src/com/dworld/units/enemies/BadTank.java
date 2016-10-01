package com.dworld.units.enemies;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Tank;
import com.dworld.units.weapon.Rocket;

public class BadTank extends Tank {

	public BadTank(int x, int y, Land land) {
		super(x, y, Land.BadTank);
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
			return Land.BadTank_Grass;
		case Sand:
			return Land.BadTank_Sand;
		default:
			return Land.BadTank;
		}
	}
	
	@Override
	protected Land getGrave(Land beneath){
		return Land.TankGrave;
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.citizenList;
	}
	
	@Override
	protected Set<Land> getArmoredListToFightWith(){
		return Land.armoredCitizenList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.EnemyFriendly;
	}}
