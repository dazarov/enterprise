package com.dworld.units.railroad;

import java.awt.Point;
import java.util.List;

import com.dworld.core.DWConstants;
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
		super(x, y, code, DWConstants.TRAIN_SPEED);
	}

	@Override
	public void init() {
		initDirection(code);
	}
	
	@Override
	protected int getDefaultBeneath(int code){
		switch(code){
		case Land.Train_Vertical:
			return Land.Rail_Vertical;
			
		case Land.Train_Horizontal:
			return Land.Rail_Horizontal;
			
		case Land.Train_Diagonal_Up:
			return Land.Rail_Diagonal_Up;
			
		case Land.Train_Diagonal_Down:
			return Land.Rail_Diagonal_Down;
			
		case Land.Train_Up_Right:
			return Land.Rail_Up_Right;
			
		case Land.Train_Up_Left:
			return Land.Rail_Up_Left;
			
		case Land.Train_Down_Right:
			return Land.Rail_Down_Right;
			
		case Land.Train_Down_Left:
			return Land.Rail_Down_Left;
			
		case Land.Train_Right_Up:
			return Land.Rail_Right_Up;
			
		case Land.Train_Right_Down:
			return Land.Rail_Right_Down;
			
		case Land.Train_Left_Up:
			return Land.Rail_Left_Up;
			
		case Land.Train_Left_Down:
			return Land.Rail_Left_Down;
			
		case Land.Train_Vertical_Cross:
			return Land.Rail_Vertical_Cross;
			
		case Land.Train_Horizontal_Cross:
			return Land.Rail_Vertical_Cross;
			
		case Land.Train_Diagonal_Up_Cross:
			return Land.Rail_Diagonal_Cross;
			
		case Land.Train_Diagonal_Down_Cross:
			return Land.Rail_Diagonal_Cross;
		}
		return Land.Empty;
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Rail_Vertical:
			return Land.Train_Vertical;
			
		case Land.Rail_Horizontal:
			return Land.Train_Horizontal;
			
		case Land.Rail_Diagonal_Up:
			return Land.Train_Diagonal_Up;
			
		case Land.Rail_Diagonal_Down:
			return Land.Train_Diagonal_Down;
			
		case Land.Rail_Up_Right:
			return Land.Train_Up_Right;
			
		case Land.Rail_Up_Left:
			return Land.Train_Up_Left;
			
		case Land.Rail_Down_Right:
			return Land.Train_Down_Right;
			
		case Land.Rail_Down_Left:
			return Land.Train_Down_Left;
			
		case Land.Rail_Right_Up:
			return Land.Train_Right_Up;
			
		case Land.Rail_Right_Down:
			return Land.Train_Right_Down;
			
		case Land.Rail_Left_Up:
			return Land.Train_Left_Up;
			
		case Land.Rail_Left_Down:
			return Land.Train_Left_Down;
			
		case Land.Rail_Vertical_Cross:
			if(direction == Direction.NORTH || direction == Direction.SOUTH){
				return Land.Train_Vertical_Cross;
			}else{
				return Land.Train_Horizontal_Cross;
			}
			
		case Land.Rail_Diagonal_Cross:
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

	
	protected void getRailDirection() {
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
	
	protected void searchForStation(){
		if(getLocation().equals(stationLocation)){
			return;
		}
		int try1 = Land.Vacuum, try2 = Land.Vacuum;
		if(direction == Direction.NORTH || direction == Direction.SOUTH){
			try1 = Land.getLand(getLocation().x-1, getLocation().y);
			try2 = Land.getLand(getLocation().x+1, getLocation().y);
			if(try1 == Land.Station_Vertical || try2 == Land.Station_Vertical){
				stationLocation = (Point)getLocation().clone();
				localStop(false);
				return;
			}
		}else if(direction == Direction.EAST || direction == Direction.WEST){
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
