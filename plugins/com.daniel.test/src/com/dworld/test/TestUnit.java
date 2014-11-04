package com.dworld.test;

import com.dworld.units.Unit;

public class TestUnit extends Unit {

	public TestUnit(int x, int y) {
		super(x, y);
	}

	@Override
	public <T> void command(int commandId, T arg) {
	}

}
