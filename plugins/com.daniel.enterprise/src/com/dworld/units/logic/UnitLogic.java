package com.dworld.units.logic;

import java.util.ArrayList;

import com.dworld.core.IActive;

public class UnitLogic {
	public static final int STAY_MODE			= 1;
	public static final int MOVE_AROUND_MODE	= 2;
	public static final int MOVE_TO_MODE		= 3;
	public static final int ATTACK_MODE			= 4;
	public static final int PATROL_MODE			= 5;
	public static final int DEFENSE_MODE		= 6;
	
	protected int state = STAY_MODE;
	
	private IActive unit;
	@SuppressWarnings("rawtypes")
	private ArrayList<Rule> highRules = new ArrayList<Rule>();
	@SuppressWarnings("rawtypes")
	private ArrayList<Rule> lowRules = new ArrayList<Rule>();

	
	public UnitLogic(IActive unit){
		this.unit = unit;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addRule(Rule rule){
		rule.setUnit(unit);
		if(rule.getPriority() == MovementRule.HIGHT_PRIORITY){
			highRules.add(rule);
		}else{
			lowRules.add(rule);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public boolean looked(){
		for(Rule rule : highRules){
			if(!rule.process()){
				return false;
			}
		}
		for(Rule rule : lowRules){
			if(!rule.process()){
				return false;
			}
		}
		
		return true;
	}
	
	public void walked(){
		
	}
}
