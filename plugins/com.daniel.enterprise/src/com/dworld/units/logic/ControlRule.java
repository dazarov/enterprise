package com.dworld.units.logic;

public class ControlRule extends MovementRule {

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public boolean test() {
		return false;
	}

	@Override
	public boolean process() {
		return false;
	}

}
