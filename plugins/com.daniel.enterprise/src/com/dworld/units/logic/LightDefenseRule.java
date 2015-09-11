package com.dworld.units.logic;

public class LightDefenseRule extends MovementRule {

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public int getPriority() {
		return HIGHT_PRIORITY;
	}

	@Override
	public boolean process() {
		//getUnit().lightDefenseComplex();
		return true;
	}

}
