package com.dworld.units.railroad;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.ui.DWSounds;
import com.dworld.units.MovableUnit;


public class Train extends MovableUnit {

	private Land oldBeneath = Land.Vacuum;
	private boolean going = true;
	private int delay = 0;
	private static final int STOP = 300;
	private static final int WAIT = 100;
	private boolean reversable = true;

	public Train(int x, int y, Land land) {
		super(x, y, land, DWConstants.TRAIN_SPEED);
	}

	@Override
	public void init() {
		initDirection(land);
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	protected Land getDefaultBeneath(Land land){
		switch(land){
		case Train_Vertical:
			return Land.Rail_Vertical;
			
		case Train_Horizontal:
			return Land.Rail_Horizontal;
			
		case Train_Diagonal_Up:
			return Land.Rail_Diagonal_Up;
			
		case Train_Diagonal_Down:
			return Land.Rail_Diagonal_Down;
			
		case Train_Up_Right:
			return Land.Rail_Up_Right;
			
		case Train_Up_Left:
			return Land.Rail_Up_Left;
			
		case Train_Down_Right:
			return Land.Rail_Down_Right;
			
		case Train_Down_Left:
			return Land.Rail_Down_Left;
			
		case Train_Right_Up:
			return Land.Rail_Right_Up;
			
		case Train_Right_Down:
			return Land.Rail_Right_Down;
			
		case Train_Left_Up:
			return Land.Rail_Left_Up;
			
		case Train_Left_Down:
			return Land.Rail_Left_Down;
			
		case Train_Vertical_Cross:
			return Land.Rail_Vertical_Cross;
			
		case Train_Horizontal_Cross:
			return Land.Rail_Vertical_Cross;
			
		case Train_Diagonal_Up_Cross:
			return Land.Rail_Diagonal_Cross;
			
		case Train_Diagonal_Down_Cross:
			return Land.Rail_Diagonal_Cross;
		}
		return Land.Empty;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Rail_Vertical:
			return Land.Train_Vertical;
			
		case Rail_Horizontal:
			return Land.Train_Horizontal;
			
		case Rail_Diagonal_Up:
			return Land.Train_Diagonal_Up;
			
		case Rail_Diagonal_Down:
			return Land.Train_Diagonal_Down;
			
		case Rail_Up_Right:
			return Land.Train_Up_Right;
			
		case Rail_Up_Left:
			return Land.Train_Up_Left;
			
		case Rail_Down_Right:
			return Land.Train_Down_Right;
			
		case Rail_Down_Left:
			return Land.Train_Down_Left;
			
		case Rail_Right_Up:
			return Land.Train_Right_Up;
			
		case Rail_Right_Down:
			return Land.Train_Right_Down;
			
		case Rail_Left_Up:
			return Land.Train_Left_Up;
			
		case Rail_Left_Down:
			return Land.Train_Left_Down;
			
		case Rail_Vertical_Cross:
			if(direction == Direction.NORTH || direction == Direction.SOUTH){
				return Land.Train_Vertical_Cross;
			}else{
				return Land.Train_Horizontal_Cross;
			}
			
		case Rail_Diagonal_Cross:
			if(direction == Direction.NORTHEAST || direction == Direction.SOUTHWEST){
				return Land.Train_Diagonal_Up_Cross;
			}else{
				return Land.Train_Diagonal_Down_Cross;
			}
		}
		return Land.Empty;
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
	
	private void initDirection(Land land) {
		RailUtils.initTrain(this, land);
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

	
	protected void getRailDirection() {
		if(beneath == oldBeneath){
			return;
		}else{
			oldBeneath = beneath;
		}
		direction = RailUtils.getRailDirection(direction, beneath);
	}
	
	@Override
	protected Set<Land> getWalkList() {
		return Land.railList;
	}


	@Override
	protected Land getGrave(Land beneath) {
		return beneath;
	}
	
	private static final Location defaultPoint = new Location(0,0);
	
	private Location stationLocation = defaultPoint;
	
	protected void searchForStation(){
		if(getLocation().equals(stationLocation)){
			return;
		}
		Land try1 = Land.Vacuum, try2 = Land.Vacuum;
		if(direction == Direction.NORTH || direction == Direction.SOUTH){
			try1 = Land.getLand(getLocation().getX()-1, getLocation().getY());
			try2 = Land.getLand(getLocation().getX()+1, getLocation().getY());
			if(try1 == Land.Station_Vertical || try2 == Land.Station_Vertical){
				stationLocation = getLocation();
				localStop(false);
				return;
			}
		}else if(direction == Direction.EAST || direction == Direction.WEST){
			try1 = Land.getLand(getLocation().getX(), getLocation().getY()-1);
			try2 = Land.getLand(getLocation().getX(), getLocation().getY()+1);
			if(try1 == Land.Station_Horizontal || try2 == Land.Station_Horizontal){
				stationLocation = getLocation();
				localStop(false);
				return;
			}
		}
		stationLocation = defaultPoint;
	}
	
	@Override
	protected void playSound(){
		DWSounds.TRAIN.playSound();
	}

}
