package com.dworld.units.railroad;

import java.awt.Point;
import java.util.List;

import com.dworld.core.DWorldConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.units.MovableUnit;


public class Train extends MovableUnit {

	private int oldBeneath = Land.Vacuum;
	private boolean going = true;
	private int delay = 0;
	private static final int STOP = 300;
	private static final int WAIT = 100;
	private boolean reversable = true;

	public Train(int x, int y, int code) {
		super(x, y, code, DWorldConstants.TRAIN_SPEED);
	}

	@Override
	public void init() {
		initDirection(code);
	}
	
	@Override
	protected int getDefaultBeneath(int code){
		return RailUtils.getDefaultBeneath(code);
	}
	
	@Override
	public int getCode(int beneath){
		return RailUtils.getCode(beneath, direction);
	}

	@Override
	protected boolean lookAround() {
		if (!checkLand()) {
			return false;
		}
		getRailDirection();
		searchForStation();
		return true;
	}
	
	private void localStop(boolean reverse){
		going = false;
		if(reverse){
			delay = WAIT;
		}else{
			delay = STOP;
		}
		if(reverse && reversable){
			direction = direction.getOppositeDirection();
		}
	}
	
	@Override
	protected boolean isGoing(){
		if(!going){
			if(delay > 0){
				delay--;
			}else{
				going = true;
			}
		}
		return going;
	}
	
	private void initDirection(int code) {
		RailUtils.initTrain(this, code);
	}
	
	public void initTrain(Direction direction, boolean reversable){
		this.direction = direction;
		this.reversable = reversable;
	}
	
	@Override
	protected boolean findNewDirection() {
		localStop(true);
		return true;
	}

	
	private void getRailDirection() {
		if(beneath == oldBeneath){
			return;
		}else{
			oldBeneath = beneath;
		}
		direction = RailUtils.getRailDirection(direction, beneath);
	}
	
	@Override
	protected List<Integer> getWalkList() {
		return Land.railList;
	}


	@Override
	protected int getGrave(int beneath) {
		return beneath;
	}
	
	private static final Point defaultPoint = new Point(0,0);
	
	private Point stationLocation = defaultPoint;
	
	private void searchForStation(){
		if(getLocation().equals(stationLocation)){
			return;
		}
		int try1 = Land.Vacuum, try2 = Land.Vacuum;
		if(direction == Direction.north || direction == Direction.south){
			try1 = Land.getLand(getLocation().x-1, getLocation().y);
			try2 = Land.getLand(getLocation().x+1, getLocation().y);
			if(try1 == Land.Station_Vertical || try2 == Land.Station_Vertical){
				stationLocation = (Point)getLocation().clone();
				localStop(false);
				return;
			}
		}else if(direction == Direction.east || direction == Direction.west){
			try1 = Land.getLand(getLocation().x, getLocation().y-1);
			try2 = Land.getLand(getLocation().x, getLocation().y+1);
			if(try1 == Land.Station_Horizontal || try2 == Land.Station_Horizontal){
				stationLocation = (Point)getLocation().clone();
				localStop(false);
				return;
			}
		}
		stationLocation = defaultPoint;
	}
	
}
