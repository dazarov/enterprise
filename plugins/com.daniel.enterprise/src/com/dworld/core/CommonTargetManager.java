package com.dworld.core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class CommonTargetManager {
	private static HashMap<IMovable, CommonTarget>friends = new HashMap<IMovable, CommonTarget>();
	private static HashMap<IMovable, CommonTarget>enemies = new HashMap<IMovable, CommonTarget>();
	
	public static void reportTarget(Point targetLocation){
		IUnit targetUnit = DWEngine.getEngine().findUnit(targetLocation);
		
		if(targetUnit instanceof IMovable){
			addTarget((IMovable)targetUnit, targetLocation);
		}
	}
	
	public static Point getTargetLocation(IUnit sourceUnit){
		cleanUpTargets(DWEngine.getEngine().getFrameID());
		
		Point sourceLocation = sourceUnit.getLocation();
		
		HashMap<IMovable, CommonTarget> targets = getTargets(!isFriend(sourceUnit));
		
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
			closestTarget.lastSeenFrame = DWEngine.getEngine().getFrameID();
			return closestTarget.lastSeenLocation;
		}
		return null;
	}
	
	private static void addTarget(IMovable targetUnit, Point targetLocation){
		HashMap<IMovable, CommonTarget> targets = getTargets(isFriend(targetUnit));
		
		CommonTarget target = targets.get(targetUnit);
		if(target != null){
			target.lastSeenLocation = targetLocation;
			target.lastSeenFrame = DWEngine.getEngine().getFrameID();
		}else if(targets.size() < DWConstants.COMMON_TARGET_MAX_NUMBER){
			targets.put(targetUnit, new CommonTarget(targetUnit, targetLocation, DWEngine.getEngine().getFrameID()));
		}
	}
	
	private static boolean isFriend(IUnit unit){
		return Land.citizenList.contains(Land.getLand(unit.getLocation()));
	}
	
	private static HashMap<IMovable, CommonTarget> getTargets(boolean friend){
		if(friend){
			return friends;
		}else{
			return enemies;
		}
	}
	
	private static void cleanUpTargets(long frame){
		if(frame%DWConstants.COMMON_TARGET_FRAME_CLEAR == 0){
			cleanUp(friends, frame);
			cleanUp(enemies, frame);
		}
	}
	
	private static void cleanUp(HashMap<IMovable, CommonTarget> targets, long frame){
		ArrayList<CommonTarget> targetsToDelete = new ArrayList<CommonTarget>();
		for(CommonTarget target : targets.values()){
			if(!target.unit.isAlive() || (target.unit instanceof IActive && !((IActive)target.unit).isActive())){
				targetsToDelete.add(target);
			}else if(frame - target.lastSeenFrame > DWConstants.COMMON_TARGET_FRAME_NUMBER){
				targetsToDelete.add(target);
			}
		}
		
		for(CommonTarget target : targetsToDelete){
			targets.remove(target);
		}
	}
	
	static class CommonTarget{
		IMovable unit;
		Point lastSeenLocation;
		long lastSeenFrame;
		
		public CommonTarget(IMovable unit, Point location, long frame){
			this.unit = unit;
			this.lastSeenLocation = location;
			this.lastSeenFrame = frame;
		}
	}
}