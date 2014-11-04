package com.dworld.test;

import com.dworld.units.MovableUnit;

public class TestMovableUnit extends MovableUnit {

	public TestMovableUnit(int x, int y, int code, double speed) {
		super(x, y, code, speed);
	}

	@Override
	protected boolean lookAround() {
		return false;
	}

	@Override
	protected int getGrave(int beneath) {
		return 0;
	}
}
