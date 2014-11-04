package com.dworld.units.citizens;

import java.util.List;

import com.dworld.core.Land;
import com.dworld.units.Bunker;

public class GoodBunker extends Bunker {

	public GoodBunker(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	protected List<Integer> getListToFightWith(){
		return Land.enemyList;
	}
}
