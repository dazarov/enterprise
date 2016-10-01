package com.dworld.units.enemies;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Bunker;

public class BadBunker extends Bunker {

	public BadBunker(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.citizenList;
	}

}
