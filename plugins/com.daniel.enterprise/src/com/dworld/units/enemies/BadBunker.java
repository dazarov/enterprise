package com.dworld.units.enemies;

import java.util.Set;

import com.dworld.core.Land;
import com.dworld.units.Bunker;

public class BadBunker extends Bunker {

	public BadBunker(int x, int y, int code) {
		super(x, y, code);
	}
	
	@Override
	protected Set<Integer> getListToFightWith(){
		return Land.citizenList;
	}

}
