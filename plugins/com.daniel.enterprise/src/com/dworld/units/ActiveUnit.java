package com.dworld.units;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.dworld.core.IActive;
import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.DWEngine;
import com.dworld.core.Land;
import com.dworld.core.SearchResult;
import com.dworld.core.SelectionManager;
import com.dworld.core.Target;
import com.dworld.units.weapon.Bomb;
import com.dworld.units.weapon.Bullet;
import com.dworld.units.weapon.CannonBall;
import com.dworld.units.weapon.Rocket;

public abstract class ActiveUnit extends Unit implements IActive {
	private boolean active = true;
	
	protected int code;

	// Special constructor for MovableUnit only
	public ActiveUnit(int x, int y) {
		super(x, y);
		//if(DWorldLauncher.launcher.isBuildMode()) active = false;
	}

	// Constructor for immovable units
	public ActiveUnit(int x, int y, int code) {
		this(x, y);
		this.code = code;
		Land.setLand(getLocation(), code);
		//if(DWorldLauncher.launcher.isBuildMode()) active = false;
	}

	@Override
	public void die() {
		if (isAlive()) {
			DWEngine.getEngine().removeElement(this);
			super.die();
		}
	}

	@Override
	public boolean isActive() {
//		if(SelectionManager.getSelectedArea() != null && SelectionManager.getSelectedArea().contains(getLocation()))
//			SelectionManager.addSelection(this);
		
		return active;
	}
	
	@Override
	public void activate(){
		active = true;
	}
	
	@Override
	public void deactivate(){
		active = false;
	}
	
	protected Direction[] findUnit(List<Integer> list) {
		if(list == null){
			throw new IllegalArgumentException("Illegal argument list, make sure you defined methods getListToFightWith() and getArmoredListToFightWith() !");
		}
		return findUnit(list, DWConstants.VISIBLE_DISTANCE);
	}

	protected Direction[] findUnit(List<Integer> list, final int maxDistance) {
		if(list == null){
			throw new IllegalArgumentException("Illegal argument list, make sure you defined methods getListToFightWith() and getArmoredListToFightWith() !");
		}
		ArrayList<Direction> directions = new ArrayList<Direction>();
		int distance;
		Direction dir = Direction.north;
		for (int i = 0; i < 8; i++) {
			distance = Land.findUnit(getLocation(), dir, list, maxDistance);
			if (distance >= 0) {
				directions.add(dir);
			}
			dir = dir.getClockwiseDirection();
		}
		return directions.toArray(new Direction[]{});
	}
	
	protected Target findTarget(final int x, final int y){
		int dX = getLocation().x - x;
		int aX = Math.abs(dX);
		int dY = getLocation().y - y;
		int aY = Math.abs(dY);
		
		int distance = (int)Math.hypot(aX, aY); 

		if(aX > aY){
			distance = aX;
		}else{
			distance = aY;
		}
		
		double c = Math.hypot(aX, aY);
		
		double angle = Math.acos(aY / c);
		
		if(dX > 0){ // west
			if(dY > 0){ // north
				if(angle < (0.125*Math.PI)){
					return new Target(Direction.north, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.northwest, distance);
				}else{
					return new Target(Direction.west, distance);
				}
			}else{ // south
				if(angle < (0.125*Math.PI)){
					return new Target(Direction.south, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.southwest, distance);
				}else{
					return new Target(Direction.west, distance);
				}
			}
		}else{ // east
			if(dY > 0){ // north
				if(angle < (0.125*Math.PI)){
					return new Target(Direction.north, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.northeast, distance);
				}else{
					return new Target(Direction.east, distance);
				}
			}else{ // south
				if(angle < (0.125*Math.PI)){
					return new Target(Direction.south, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.southeast, distance);
				}else{
					return new Target(Direction.east, distance);
				}
			}
		}
	}
	
	protected Target[] extendedFindUnit(List<Integer> list, final int maxDistance) {
		ArrayList<Target> targets = new ArrayList<Target>();
		if(list == null){
			throw new IllegalArgumentException("Illegal argument list, make sure you defined methods getListToFightWith() and getArmoredListToFightWith() !");
		}
		
		for(int x = getLocation().x - maxDistance; x < getLocation().x + maxDistance; x++){
			for(int y = getLocation().y - maxDistance; y < getLocation().y + maxDistance; y++){
				int code = Land.getLand(x, y);
				if(list.contains(code)){
					targets.add(findTarget(x, y));
				}
			}
		}
		
		return targets.toArray(new Target[]{});
	}
	
	protected void doDefaultCommand(){
		System.out.println(" "+getClass()+" doDefaultCommand");
		if(isActive()){
			deactivate();
		}else{
			activate();
		}
	}
	
	public <T> void command(int commandId, T arg){
		System.out.println(" "+getClass()+" command "+commandId);
		if(commandId == SelectionManager.DEFAULT_COMMAND){
			doDefaultCommand();
		}else if(commandId == SelectionManager.ACTIVATE_COMMAND){
			activate();
		}else if(commandId == SelectionManager.DEACTIVATE_COMMAND){
			deactivate();
		}
	}
	
	protected List<Integer> getListToFightWith(){
		return null;
	}
	
	private Bullet[] bullet = new Bullet[8];
	
	
	protected void fireBullets(final int distance){
		Direction[] dirs = findUnit(getListToFightWith(), distance);
		for(Direction dir : dirs) {
			if(bullet[dir.value] == null || !bullet[dir.value].isAlive()){
				bullet[dir.value] = fireBullet(dir);
			}
		}
	}
	
	protected void fireBullets(){
		fireBullets(DWConstants.VISIBLE_DISTANCE);
	}
	
	protected Direction fireAgainstRocket(){
		SearchResult result;
		Direction dir = Direction.north;
		for (int i = 0; i < 8; i++) {
			result = Land.search(getLocation(), dir, Land.rocketList);
			if (result != null) {
				Direction rocketDirection = Rocket.getDirection(result.getResultCode());
				if(rocketDirection.getOppositeDirection() == dir){
					if(bullet[dir.value] == null || !bullet[dir.value].isAlive()){
						bullet[dir.value] = fireBullet(dir);
						return dir;
					}
				}
			}
			dir = dir.getClockwiseDirection();
		}
		return Direction.nowhere;
	}
	
	protected int getRocketType(){
		return -1;
	}
	
	private Rocket[] rocket = new Rocket[8];
	
	protected void fireRockets(){
		fireRockets(DWConstants.VISIBLE_DISTANCE);
	}
	
	protected void fireRockets(final int distance){
		Direction[] dirs = findUnit(getListToFightWith(), distance);
		for(Direction dir : dirs) {
			if(rocket[dir.value] == null || !rocket[dir.value].isAlive()){
				rocket[dir.value] = fireRocket(dir, getRocketType());
			}
		}
	}
	
	protected void extendedFireRockets(final int distance){
		Target[] targets = extendedFindUnit(getListToFightWith(), distance);
		for (Target target : targets) {
			Direction targetDirection = target.getDirection();
			int targetDistance = target.getDistance();
			if(targetDistance > DWConstants.MIN_RANGE){
				if(rocket[targetDirection.value] == null || !rocket[targetDirection.value].isAlive()){
					if(checkDistance(targetDirection, targetDistance)){
						rocket[targetDirection.value] = fireRocket(targetDirection, getRocketType());
					}
				}
			}
		}
	}
	
	protected List<Integer> getArmoredListToFightWith(){
		return null;
	}
	
	private CannonBall[] cannonBalls = new CannonBall[8];
	
	protected void fireCannonBalls(int distance){
		Direction[] dirs = findUnit(getArmoredListToFightWith(), distance);
		for(Direction dir : dirs) {
			if(cannonBalls[dir.value] == null || !cannonBalls[dir.value].isAlive()){
				cannonBalls[dir.value] = fireCannon(dir);
			}
		}
	}
	
	protected void extendedFireCannonBalls(final int distance){
		Target[] targets = extendedFindUnit(getListToFightWith(), distance);
		for(Target target : targets) {
			Direction targetDirection = target.getDirection();
			int targetDistance = target.getDistance();
			if(targetDistance > DWConstants.MIN_RANGE){
				if(cannonBalls[targetDirection.value] == null || !cannonBalls[targetDirection.value].isAlive()){
					if(checkDistance(targetDirection, targetDistance)){
						cannonBalls[targetDirection.value] = fireCannon(targetDirection, targetDistance);
					}
				}
			}
		}
	}
	
	protected boolean checkDistance(final Direction direction, final int distance){
		Point point = Land.getNewLocation(getLocation(), direction);
		int range = 1;
		while(range <= distance){
			if(!Land.canIWalk(point, direction, Land.flyAndFindList)){
				return false;
			}
			point = Land.getNewLocation(point, direction);
			range++;
		}
		return true;
	}
	
	protected Bullet fireBullet(Direction bulletDirection){
		return new Bullet(getLocation().x, getLocation().y, bulletDirection);
	}
	
	protected Bomb fireBomb(Direction bombDirection, int distance){
		return new Bomb(getLocation().x, getLocation().y, bombDirection, distance);
	}
	
	protected Rocket fireRocket(Direction rocketDirection, int rocketType){
		if(rocketType == -1){
			throw new IllegalArgumentException("Illegal argument rocketType, define method getRocketType()!");
		}
		return new Rocket(getLocation().x, getLocation().y, rocketDirection, rocketType);
	}
	
	protected CannonBall fireCannon(Direction cannonDirection){
		return new CannonBall(getLocation().x, getLocation().y, cannonDirection);
	}

	protected CannonBall fireCannon(Direction cannonDirection, int distance){
		return new CannonBall(getLocation().x, getLocation().y, cannonDirection, distance);
	}
	
	protected boolean checkLand(){
		if (Land.getLand(getLocation()) != code) {
			die();
			Land.setLand(getLocation(), Land.Empty);
			return false;
		}
		return true;
	}
}
