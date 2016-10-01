package com.dworld.units;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.IMovable;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.core.SearchResult;

public abstract class MovableUnit extends ActiveUnit implements IMovable {
	public static final int STAY_MODE			= 1;
	public static final int MOVE_AROUND_MODE	= 2;
	public static final int MOVE_TO_MODE		= 3;
	public static final int ATTACK_MODE			= 4;
	public static final int PATROL_MODE			= 5;
	public static final int DEFENSE_MODE		= 6;
	
	protected int mode = STAY_MODE;
	
	protected boolean selfDefense = false;
	
	protected Land beneath;
	protected double speed, defaultSpeed;
	protected Direction direction = Direction.NORTH;
	
	protected Location destination = null;

	public MovableUnit(int x, int y, Land land, double speed) {
		super(x, y);
		this.land = land;
		beneath = getDefaultBeneath(land);
		Land.initLand(getLocation(), beneath, this);
		setSpeed(speed);
	}
	
	@Override
	public void die(){
		super.die();
		if (beneath != Land.Vacuum)
			Land.setLand(getLocation(), getBeneath());
		else
			Land.setLand(getLocation(), Land.Empty);
	}

	@Override
	public Land getBeneath() {
		return beneath;
	}

	@Override
	public void setBeneath(Land beneath) {
		this.beneath = beneath;
	}

	private double curent = DWConstants.MAX_SPEED;
	
	public final void step() {
		if (!lookAround()) {
			curent += DWConstants.MAX_SPEED;
			return;
		}
		
		if(isGoing()){
			go();
		}else{
			stop();
			return;
		}
		
		curent -= speed;
		
		if (curent <= 0) {
			curent += DWConstants.MAX_SPEED;
			findDirection();
			if (Land.canIWalk(getLocation(), direction, getWalkList())) {
				walk();
			} else{
				if(!findNewDirection())
					return;
			}
		}
	}
	
	protected Set<Land> getWalkList(){
		return Land.walkList;
	}
	
	protected boolean isGoing(){
		if(selfDefense){
			return true;
		}
		if(mode == STAY_MODE){
			return false;
		}else if(mode == MOVE_TO_MODE){
			if(getLocation().equals(destination)){
				status = INIT_STATUS;
				mode = STAY_MODE;
				return false;
			}
		}
		return true;
	}
	
	protected void findDirection(){
		if(mode == MOVE_TO_MODE){
			direction = findDestination();
		}
	}

	protected boolean findNewDirection() {
		direction = direction.getClockwiseDirection();
		return true;
	}
	
	protected abstract boolean lookAround();

//	protected final boolean lookAround(){
//		return getLogic().looked();
//	}
	
	protected boolean checkLand(){
		if (Land.getLand(getLocation()) != getLand(beneath)) {
			die();
			Land.setLand(getLocation(), getGrave(beneath));
			return false;
		}
		return true;
	}

	protected void walk() {
		Land.setLand(getLocation(), beneath);
		setLocation(Land.getNewLocation(getLocation(), direction));
		beneath = Land.setLand(getLocation(), this);
		if(selfDefense){
			selfDefense = false;
		}
		getLogic().walked();
	}
	
	@Override
	public Land getLand(Land beneath){
		return land;
	}
	
	protected Land getDefaultBeneath(Land land){
		switch(land){
		case Hero_Grass:
		case GoodSoldier_Grass:
		case BadSoldier_Grass:
		case Peasant_Grass:
		case GoodOfficer_Grass:
		case GoodGeneral_Grass:
		case BadOfficer_Grass:
		case BadGeneral_Grass:
		case Dark_Knight_Grass:
		case BadTank_Grass:
		case GoodTank_Grass:
			
			return Land.Grass;
			
		case Hero_Sand:
		case GoodSoldier_Sand:
		case BadSoldier_Sand:
		case Peasant_Sand:
		case GoodOfficer_Sand:
		case GoodGeneral_Sand:
		case BadOfficer_Sand:
		case BadGeneral_Sand:
		case Dark_Knight_Sand:
		case BadTank_Sand:
		case GoodTank_Sand:
			
			return Land.Sand;
			
			default:
				return Land.Empty;
		}

	}
	
	abstract protected Land getGrave(Land beneath);

	protected double getSpeed() {
		return speed;
	}

	protected void setSpeed(double speed) {
		if (speed > DWConstants.MAX_SPEED)
			this.speed = DWConstants.MAX_SPEED;
		else if (speed < 0)
			this.speed = DWConstants.STOP_SPEED;
		else
			this.speed = speed;
		
		defaultSpeed = speed;
	}
	
	private void stop(){
		speed = DWConstants.STOP_SPEED;
	}

	private void go(){
		speed = defaultSpeed;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public void save(OutputStream stream) throws IOException{
		//stream.write(direction);
	}
	
	@Override
	public void load(InputStream stream) throws IOException{
		//direction = stream.read();
	}
	
	protected Direction lastDefenseMove = Direction.NORTHWEST;
	protected int[] sources = new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
	
	protected boolean lightDefenseComplex(){
		SearchResult result;
		Direction dir = Direction.NORTH;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < 8; i++) {
			result = Land.search(getLocation(), dir, Land.bulletList);
			if (result != null && result.getDistance() >= 2) {
				if(sources[i] >= 0 && sources[i] > result.getDistance()){
					list.add(new Integer(dir.ordinal()));
					Direction opposite = dir.getOppositeDirection();
					list.add(new Integer(opposite.ordinal()));
				}else
					sources[i] = result.getDistance();
			}else
				sources[i] = -1;
			dir = dir.getClockwiseDirection();
		}
		
		if(list.size() > 0){
			dir = lastDefenseMove.getClockwiseDirection();
			lastDefenseMove = dir;
			for (int i = 0; i < 8; i++) {
				if(list.contains(new Integer(dir.ordinal()))) continue;
				if(Land.canIWalk(getLocation(), dir, getWalkList())){
					direction = dir;
					selfDefense = true;
					return true;
				}
				dir = dir.getClockwiseDirection();
			}
		}
		return false;
	}
	
//	private class Danger{
//		//private Point location;
//		//private Direction direction = Direction.nowhere;
//		//private Point probable = null;
//		long lastModification = 0;
//		
//		public Danger(Point point, long modification){
//			//this.location = point;
//			this.lastModification = modification;
//		}
//		
//		public void setLocation(Point location, long time){
//			//this.location = location;
//			this.lastModification = time;
//		}
//	}
//	
//	private ArrayList<Danger> dangers = new ArrayList<Danger>();
//	private long time = 0;
	
	//private static final int AREA_SIZE = 40;
	//private static final int CLEAN_DELTA = 3;
	
//	protected boolean heavyDefenseComplex(){
//		time++;
//		int startX = getLocation().x - AREA_SIZE/2;
//		if(startX < 0) startX = 0;
//		int startY = getLocation().y - AREA_SIZE/2;
//		if(startY < 0) startY = 0;
//		
//		// searching
//		for(int x = startX; x < startX+AREA_SIZE; x++){
//			for(int y = startY; y < startY+AREA_SIZE; y++){
//				Point point = new Point(x,y);
//				int code = Land.getLand(point);
//				if(code == Land.Bullet){
//					Danger danger = findDanger(point);
//					if(danger == null)
//						createDanger(point);
//					else
//						operateDanger(danger, point);
//				}
//			}
//		}
//		clean();
//		
//		// processing
//		
//		return false;
//	}
	
//	private Danger findDanger(Point point){
//		return null;
//	}
//	
//	private void createDanger(Point point){
//		Danger danger = new Danger(point, time);
//		dangers.add(danger);
//	}
//	
//	private void operateDanger(Danger danger, Point point){
//		danger.setLocation(point, time);
//	}
	
//	private void clean(){
//		for(int i = dangers.size()-1; i <= 0; i--){
//			Danger danger = dangers.get(i);
//			if(time-danger.lastModification > CLEAN_DELTA)
//				dangers.remove(i);
//		}
//	}
	
	@Override
	public void command(int commandId, Object[] args){
		super.command(commandId, args);

		if(commandId == EXTERNAL_COMMAND_STAY){
			mode = STAY_MODE;
		}else if(commandId == EXTERNAL_COMMAND_MOVE_AROUND){
			mode = MOVE_AROUND_MODE;
		}else if(commandId == EXTERNAL_COMMAND_MOVE_TO){
			destination = (Location)args[0];
			status = INIT_STATUS;
			mode = MOVE_TO_MODE;
		}
	}
	
	protected static final int INIT_STATUS = 0;
	protected static final int DIRECT_STATUS = 1;
	protected static final int APROACH_STATUS = 2;
	protected static final int CONNECT_STATUS = 3;
	protected static final int AROUND_STATUS = 4;
	
	protected int status = INIT_STATUS;
	
	private Direction ld = Direction.NOWHERE;
	
	protected Direction findDestination(){
		if(status == INIT_STATUS){
			if(Direction.isShortcutAvalable(getLocation(), destination)){
				status = DIRECT_STATUS;
			}else{
				status = APROACH_STATUS;
			}
		}
		if(status == APROACH_STATUS){
			ld = Direction.findDirection(getLocation(), destination);
			if(!Land.canIWalk(getLocation(), ld, Land.walkList)){
				status = CONNECT_STATUS;
			}else{
				return ld;
			}
		}
		
		if(status == CONNECT_STATUS){
			Direction d = direction;
			for(int i = 0; i < 8; i++){
				if(Land.canIWalk(getLocation(), d, Land.walkList)){
					ld = d;
					status = AROUND_STATUS;
					return d;
				}
				d = d.getLeft();
			}
			ld = Direction.NOWHERE;
			return ld;
		}
		
		if(status == AROUND_STATUS){
			if(Direction.isShortcutAvalable(getLocation(), destination)){
				status = DIRECT_STATUS;
			}else{
				Direction d = direction.getRight(3);
				for(int i = 0; i < 8; i++){
					if(Land.canIWalk(getLocation(), d, Land.walkList)){
						ld = d;
						return d;
					}
					d = d.getLeft();
				}
				ld = Direction.NOWHERE;
				return ld;
			}
		}
		
		if(status == DIRECT_STATUS){
			ld = Direction.findDirection(getLocation(), destination);
			return ld;
		}
		ld = Direction.NOWHERE;
		return ld;
	}
	
	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}

}
