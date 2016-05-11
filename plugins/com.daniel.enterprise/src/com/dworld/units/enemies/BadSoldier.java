package com.dworld.units.enemies;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Soldier;

public class BadSoldier extends Soldier {
	
	public BadSoldier(int x, int y, int code) {
		super(x, y, code);
		mode = MOVE_AROUND_MODE;
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.BadSoldier_Grass;
		case Land.Sand:
			return Land.BadSoldier_Sand;
		default:
			return Land.BadSoldier;
		}
	}

	@Override
	protected int getGrave(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.BadSoldierGrave_Grass;
		case Land.Sand:
			return Land.BadSoldierGrave_Sand;
		default:
			return Land.RobotGrave;
		}
	}
	
	@Override
	protected Set<Integer> getListToFightWith(){
		return Land.citizenList;
	}
}
