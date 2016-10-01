package com.dworld.units.citizens;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Bunker;

public class GoodBunker extends Bunker {

	public GoodBunker(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.enemyList;
	}
}
