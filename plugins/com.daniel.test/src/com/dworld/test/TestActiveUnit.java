package com.dworld.test;

import com.dworld.units.ActiveUnit;

public class TestActiveUnit extends ActiveUnit {

	public TestActiveUnit(int x, int y) {
		super(x, y);
	}

	public TestActiveUnit(int x, int y, int code) {
		super(x, y, code);
	}

	@Override
	public void step() {
	}

}
