package com.dworld.units.weapon;

import com.dworld.core.ISlow;
import com.dworld.core.Land;
import com.dworld.units.ActiveUnit;

public class Mine extends ActiveUnit implements ISlow {

	public Mine(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
	}

	@Override
	public void step() {
		Land l = Land.getLand(getLocation());
		if(Land.unsaveListContains(l)) return;
		if(l != land){
			die();
			return;
		}
	}
}
