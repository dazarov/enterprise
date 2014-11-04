package com.dworld.units.enemies;

import java.util.List;

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
			return Land.Robot_Grass;
		case Land.Sand:
			return Land.Robot_Sand;
		default:
			return Land.Robot;
		}
	}

	@Override
	protected int getGrave(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.RobotGrave_Grass;
		case Land.Sand:
			return Land.RobotGrave_Sand;
		default:
			return Land.RobotGrave;
		}
	}
	
	@Override
	protected List<Integer> getListToFightWith(){
		return Land.citizenList;
	}
}
