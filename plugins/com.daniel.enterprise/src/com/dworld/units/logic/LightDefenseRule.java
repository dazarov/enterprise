package com.dworld.units.logic;

public class LightDefenseRule extends MovementRule {

	@Override
	public int getPriority() {
		return HIGHT_PRIORITY;
	}

	@Override
	public boolean test() {
		//getUnit().lightDefenseComplex();
		return true;
	}

	@Override
	public boolean process() {
		//getUnit().lightDefenseComplex();
		return true;
	}

}
