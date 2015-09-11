package com.dworld.units.logic;

import com.dworld.core.IActive;

public abstract class Rule<T extends IActive> {
	public static final int HIGHT_PRIORITY = 0;
	public static final int LOW_PRIORITY = 1;
	
	private T unit;
	
	public void setUnit(T unit){
		this.unit = unit;
	}
	
	protected T getUnit(){
		return unit;
	}
	
	abstract public boolean isActive();
	
	abstract public int getPriority();
	
	/**
	 * if returns false 
	 * @return
	 */
	abstract public boolean process();
	
}
