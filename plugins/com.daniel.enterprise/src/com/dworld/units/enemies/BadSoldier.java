package com.dworld.units.enemies;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Soldier;

public class BadSoldier extends Soldier {
	
	public BadSoldier(int x, int y, Land land) {
		super(x, y, land);
		mode = MOVE_AROUND_MODE;
	}

	@Override
	protected Land getGrave(Land beneath){
		return Land.RobotGrave;
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.citizenList;
	}
}
