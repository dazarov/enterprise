package com.dworld.units;

import java.util.ArrayList;
import java.util.Set;

import com.dworld.core.CommonTargetManager;
import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.IActive;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.core.SearchResult;
import com.dworld.core.Target;
import com.dworld.ui.DWSounds;
import com.dworld.units.logic.UnitLogic;
import com.dworld.units.weapon.Bomb;
import com.dworld.units.weapon.Bullet;
import com.dworld.units.weapon.CannonBall;
import com.dworld.units.weapon.Rocket;

public abstract class ActiveUnit extends Unit implements IActive {
	private boolean active = true;
	
	protected Land land;
	
	private UnitLogic logic;
	
	private Bullet[] bullets = new Bullet[8];
	
	//private Bomb[] grenades = new Bomb[8];
	
	private Rocket[] rockets = new Rocket[8];
	
	private CannonBall[] cannonBalls = new CannonBall[8];

	// Special constructor for MovableUnit only
	public ActiveUnit(int x, int y) {
		super(x, y);
		logic = new UnitLogic(this);
		//if(DWorldLauncher.launcher.isBuildMode()) active = false;
	}

	// Constructor for immovable units
	public ActiveUnit(int x, int y, Land land) {
		this(x, y);
		this.land = land;
		Land.setLand(getLocation(), land);
		//if(DWorldLauncher.launcher.isBuildMode()) active = false;
	}
	
	protected UnitLogic getLogic(){
		return logic;
	}

	@Override
	public void die() {
		if (isAlive()) {
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
	
	protected Direction[] findUnit(Set<Land> list) {
		if(list == null){
			throw new IllegalArgumentException("Illegal argument list, make sure you defined methods getListToFightWith() and getArmoredListToFightWith() !");
		}
		return findUnit(list, DWConstants.VISIBLE_DISTANCE);
	}

	protected Direction[] findUnit(Set<Land> list, final int maxDistance) {
		if(list == null){
			throw new IllegalArgumentException("Illegal argument list, make sure you defined methods getListToFightWith() and getArmoredListToFightWith() !");
		}
		ArrayList<Direction> directions = new ArrayList<Direction>();
		SearchResult result;
		Direction dir = Direction.NORTH;
		for (int i = 0; i < 8; i++) {
			result = Land.search(getLocation(), dir, list, maxDistance);
			if (result != null) {
				directions.add(dir);
				CommonTargetManager.reportTarget(result.getLocation());
			}
			dir = dir.getClockwiseDirection();
		}
		return directions.toArray(new Direction[]{});
	}
	
	protected Target findTarget(final int x, final int y){
		int dX = getLocation().getX() - x;
		int aX = Math.abs(dX);
		int dY = getLocation().getY() - y;
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
					return new Target(Direction.NORTH, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.NORTHWEST, distance);
				}else{
					return new Target(Direction.WEST, distance);
				}
			}else{ // south
				if(angle < (0.125*Math.PI)){
					return new Target(Direction.SOUTH, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.SOUTHWEST, distance);
				}else{
					return new Target(Direction.WEST, distance);
				}
			}
		}else{ // east
			if(dY > 0){ // north
				if(angle < (0.125*Math.PI)){
					return new Target(Direction.NORTH, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.NORTHEAST, distance);
				}else{
					return new Target(Direction.EAST, distance);
				}
			}else{ // south
				if(angle < (0.125*Math.PI)){
					return new Target(Direction.SOUTH, distance);
				}else if(angle >= (0.125*Math.PI) && angle < (0.375*Math.PI)){
					return new Target(Direction.SOUTHEAST, distance);
				}else{
					return new Target(Direction.EAST, distance);
				}
			}
		}
	}
	
	protected Target[] extendedFindUnit(Set<Land> list, final int maxDistance) {
		ArrayList<Target> targets = new ArrayList<Target>();
		if(list == null){
			throw new IllegalArgumentException("Illegal argument list, make sure you defined methods getListToFightWith() and getArmoredListToFightWith() !");
		}
		
		for(int x = getLocation().getX() - maxDistance; x < getLocation().getX() + maxDistance; x++){
			for(int y = getLocation().getY() - maxDistance; y < getLocation().getY() + maxDistance; y++){
				Land land = Land.getLand(x, y);
				if(list.contains(land)){
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
	
	@Override
	public void command(int commandId, Object[] args){
		System.out.println(" "+getClass()+" command "+commandId);
		if(commandId == Unit.EXTERNAL_COMMAND_DEFAULT){
			doDefaultCommand();
		}else if(commandId == EXTERNAL_COMMAND_ACTIVATE){
			activate();
		}else if(commandId == EXTERNAL_COMMAND_DEACTIVATE){
			deactivate();
		}else if(commandId == INTERNAL_COMMAND_FIRE_BULLET){
			Direction d = (Direction)args[0];
			fireBullet(d);
		}else if(commandId == INTERNAL_COMMAND_FIRE_ROCKET){
			Direction d = (Direction)args[0];
			fireRocket(d);
		}else if(commandId == INTERNAL_COMMAND_FIRE_CANNON){
			Direction d = (Direction)args[0];
			if(args.length > 1){
				Integer distance = (Integer)args[1];
				fireCannon(d, distance);
			}else{
				fireCannon(d);
			}
		}else if(commandId == INTERNAL_COMMAND_FIRE_BOMB){
			Direction d = (Direction)args[0];
			Integer distance = (Integer)args[1];
			fireBomb(d, distance);
		}
	}
	
	protected Set<Land> getListToFightWith(){
		return null;
	}
	
	
	
	
	protected void fireBullets(final int distance){
		Direction[] dirs = findUnit(getListToFightWith(), distance);
		for(Direction dir : dirs) {
			fireBullet(dir);
		}
	}
	
	protected void fireBullets(){
		fireBullets(DWConstants.VISIBLE_DISTANCE);
	}
	
	protected void fireAgainstRocket(){
		SearchResult result;
		Direction dir = Direction.NORTH;
		for (int i = 0; i < 8; i++) {
			result = Land.search(getLocation(), dir, Land.rocketList);
			if (result != null) {
				Direction rocketDirection = Rocket.getDirection(result.getResultLand());
				if(rocketDirection.getOppositeDirection() == dir){
					fireBullet(dir);
//					if(bullets[dir.value] == null || !bullets[dir.value].isAlive()){
//						bullets[dir.value] = fireBullet(dir);
//						return dir;
//					}
				}
			}
			dir = dir.getClockwiseDirection();
		}
		//return Direction.nowhere;
	}
	
	protected int getRocketType(){
		return -1;
	}
	
	
	
	protected void fireRockets(){
		fireRockets(DWConstants.VISIBLE_DISTANCE);
	}
	
	protected void fireRockets(final int distance){
		Direction[] dirs = findUnit(getListToFightWith(), distance);
		for(Direction dir : dirs) {
			if(rockets[dir.ordinal()] == null || !rockets[dir.ordinal()].isAlive()){
				rockets[dir.ordinal()] = fireRocket(dir, getRocketType());
			}
		}
	}
	
	protected void extendedFireRockets(final int distance){
		Target[] targets = extendedFindUnit(getListToFightWith(), distance);
		for (Target target : targets) {
			Direction targetDirection = target.getDirection();
			int targetDistance = target.getDistance();
			if(targetDistance > DWConstants.MIN_RANGE){
				if(rockets[targetDirection.ordinal()] == null || !rockets[targetDirection.ordinal()].isAlive()){
					if(checkDistance(targetDirection, targetDistance)){
						rockets[targetDirection.ordinal()] = fireRocket(targetDirection, getRocketType());
					}
				}
			}
		}
	}
	
	protected Set<Land> getArmoredListToFightWith(){
		return null;
	}
	
	
	
	protected void fireCannonBalls(int distance){
		Direction[] dirs = findUnit(getArmoredListToFightWith(), distance);
		for(Direction dir : dirs) {
			if(cannonBalls[dir.ordinal()] == null || !cannonBalls[dir.ordinal()].isAlive()){
				cannonBalls[dir.ordinal()] = fireCannon(dir);
			}
		}
	}
	
	protected void extendedFireCannonBalls(final int distance){
		Target[] targets = extendedFindUnit(getListToFightWith(), distance);
		for(Target target : targets) {
			Direction targetDirection = target.getDirection();
			int targetDistance = target.getDistance();
			if(targetDistance > DWConstants.MIN_RANGE){
				if(cannonBalls[targetDirection.ordinal()] == null || !cannonBalls[targetDirection.ordinal()].isAlive()){
					if(checkDistance(targetDirection, targetDistance)){
						cannonBalls[targetDirection.ordinal()] = fireCannon(targetDirection, targetDistance);
					}
				}
			}
		}
	}
	
	protected boolean checkDistance(final Direction direction, final int distance){
		Location point = Land.getNewLocation(getLocation(), direction);
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
	
	private void fireBullet(Direction direction){
		Bullet bullet = bullets[direction.ordinal()];
		if(bullet == null || !bullet.isAlive()){
			bullets[direction.ordinal()] = new Bullet(getLocation().getX(), getLocation().getY(), direction);
			DWSounds.SHOT.playSound();
		}
	}
	
	private void fireRocket(Direction direction){
		Rocket rocket = rockets[direction.ordinal()];
		if(rocket == null || !rocket.isAlive()){
			rockets[direction.ordinal()] = new Rocket(getLocation().getX(), getLocation().getY(), direction, getRocketType());
		}
	}

	private Bomb fireBomb(Direction bombDirection, int distance){
		return new Bomb(getLocation().getX(), getLocation().getY(), bombDirection, distance);
	}
	
	private Rocket fireRocket(Direction rocketDirection, int rocketType){
		if(rocketType == -1){
			throw new IllegalArgumentException("Illegal argument rocketType, define method getRocketType()!");
		}
		return new Rocket(getLocation().getX(), getLocation().getY(), rocketDirection, rocketType);
	}
	
	private CannonBall fireCannon(Direction cannonDirection){
		return new CannonBall(getLocation().getX(), getLocation().getY(), cannonDirection);
	}

	protected CannonBall fireCannon(Direction cannonDirection, int distance){
		return new CannonBall(getLocation().getX(), getLocation().getY(), cannonDirection, distance);
	}
	
	protected boolean checkLand(){
		if (Land.getLand(getLocation()) != land) {
			die();
			Land.setLand(getLocation(), Land.Empty);
			return false;
		}
		return true;
	}
}
