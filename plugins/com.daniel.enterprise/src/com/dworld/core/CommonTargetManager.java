package com.dworld.core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class CommonTargetManager {
	private static HashMap<IUnit, CommonTarget>friends = new HashMap<IUnit, CommonTarget>();
	private static HashMap<IUnit, CommonTarget>enemies = new HashMap<IUnit, CommonTarget>();
	
	public static void reportTarget(IUnit sourceUnit, Point targetLocation){
		IUnit targetUnit = DWEngine.getEngine().findUnit(targetLocation);
		
		addTarget(targetUnit, targetLocation);
		
		addTarget(sourceUnit, sourceUnit.getLocation());
	}
	
	public static Point getTargetLocation(IUnit sourceUnit, Point sourceLocation){
		cleanUpTargets();
		
		HashMap<IUnit, CommonTarget> targets = getTargets(!isFriend(sourceUnit));
		
		double minDistance = DWConstants.COMMON_TARGET_DISTANCE;
		CommonTarget closestTarget = null;
		
		if(targets.size() < DWConstants.COMMON_TARGET_MAX_NUMBER){
			for(CommonTarget target : targets.values()){
				double distance = target.lastSeenLocation.distance(sourceLocation); 
				if(distance < minDistance){
					minDistance = distance;
					closestTarget = target;
				}
			}
		}
		if(closestTarget != null){
			return closestTarget.lastSeenLocation;
		}
		return null;
	}
	
	private static void addTarget(IUnit targetUnit, Point targetLocation){
		HashMap<IUnit, CommonTarget> targets = getTargets(isFriend(targetUnit));
		
		CommonTarget target = targets.get(targetUnit);
		if(target != null){
			target.lastSeenLocation = targetLocation;
		}else if(targets.size() < DWConstants.COMMON_TARGET_MAX_NUMBER){
			targets.put(targetUnit, new CommonTarget(targetUnit, targetLocation));
		}
	}
	
	private static boolean isFriend(IUnit unit){
		return Land.citizenList.contains(Land.getLand(unit.getLocation()));
	}
	
	private static HashMap<IUnit, CommonTarget> getTargets(boolean friend){
		if(friend){
			return friends;
		}else{
			return enemies;
		}
	}
	
	private static void cleanUpTargets(){
		cleanUp(friends);
		cleanUp(enemies);
		
		if(friends.size() == 0){
			enemies.clear();
		}
		if(enemies.size() == 0){
			friends.clear();
		}
	}
	
	private static void cleanUp(HashMap<IUnit, CommonTarget> targets){
		ArrayList<CommonTarget> targetsToDelete = new ArrayList<CommonTarget>();
		for(CommonTarget target : targets.values()){
			if(!target.unit.isAlive() || (target.unit instanceof IActive && !((IActive)target.unit).isActive())){
				targetsToDelete.add(target);
			}
		}
		
		for(CommonTarget target : targetsToDelete){
			targets.remove(target);
		}
	}
	
	static class CommonTarget{
		IUnit unit;
		Point lastSeenLocation;
		
		public CommonTarget(IUnit unit, Point location){
			this.unit = unit;
			this.lastSeenLocation = location;
		}
	}
}
