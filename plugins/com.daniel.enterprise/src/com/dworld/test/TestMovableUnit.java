package com.dworld.test;

import com.dworld.core.Land;
import com.dworld.units.MovableUnit;

public class TestMovableUnit extends MovableUnit {

	public TestMovableUnit(int x, int y, Land land, double speed) {
		super(x, y, land, speed);
	}

	@Override
	protected boolean lookAround() {
		return false;
	}

	@Override
	protected Land getGrave(Land beneath) {
		return Land.Empty;
	}

	@Override
	protected boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}
}
