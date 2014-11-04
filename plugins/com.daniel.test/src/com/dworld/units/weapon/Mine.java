package com.dworld.units.weapon;

import com.dworld.core.Land;
import com.dworld.units.ActiveUnit;

public class Mine extends ActiveUnit {

	public Mine(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
	}

	@Override
	public void step() {
		int land = Land.getLand(getLocation());
		if(Land.unsaveListContains(land)) return;
		if(land != code){
			die();
			return;
		}
	}
}
