package com.dworld.units.citizens;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Bunker;

public class GoodBunker extends Bunker {

	public GoodBunker(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	protected Set<Integer> getListToFightWith(){
		return Land.enemyList;
	}
}
