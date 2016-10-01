package com.dworld.test;

import com.dworld.core.Land;
import com.dworld.units.ActiveUnit;

public class TestActiveUnit extends ActiveUnit {

	public TestActiveUnit(int x, int y) {
		super(x, y);
	}

	public TestActiveUnit(int x, int y, Land land) {
		super(x, y, land);
	}

	@Override
	public void step() {
	}

}
