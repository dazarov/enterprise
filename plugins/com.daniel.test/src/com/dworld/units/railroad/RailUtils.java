package com.dworld.units.railroad;

import java.awt.Point;

import com.dworld.core.Direction;
import com.dworld.core.Land;

public class RailUtils {
	
	public static Direction getRailDirection(Direction direction, int beneath) {
		switch(beneath){
		case Land.Rail_Vertical:
		case Land.Rail_Horizontal:
		case Land.Rail_Diagonal_Up:
		case Land.Rail_Diagonal_Down:
		case Land.Train_Vertical:
		case Land.Train_Horizontal:
		case Land.Train_Diagonal_Up:
		case Land.Train_Diagonal_Down:
			return direction;
			
		case Land.Rail_Up_Right:
		case Land.Train_Up_Right:
			if(direction == Direction.north){
				return Direction.northeast;
			}else{
				return Direction.south;
			}
			
		case Land.Rail_Up_Left:
		case Land.Train_Up_Left:
			if(direction == Direction.north){
				return Direction.northwest;
			}else{
				return Direction.south;
			}
			
		case Land.Rail_Down_Right:
		case Land.Train_Down_Right:
			if(direction == Direction.south){
				return Direction.southwest;
			}else{
				return Direction.north;
			}
			
		case Land.Rail_Down_Left:
		case Land.Train_Down_Left:
			if(direction == Direction.south){
				return Direction.southeast;
			}else{
				return Direction.north;
			}
			
		case Land.Rail_Right_Up:
		case Land.Train_Right_Up:
			if(direction == Direction.east){
				return Direction.northeast;
			}else{
				return Direction.west;
			}
			
		case Land.Rail_Right_Down:
		case Land.Train_Right_Down:
			if(direction == Direction.east){
				return Direction.southeast;
			}else{
				return Direction.west;
			}
			
		case Land.Rail_Left_Up:
		case Land.Train_Left_Up:
			if(direction == Direction.west){
				return Direction.northwest;
			}else{
				return Direction.east;
			}
			
		case Land.Rail_Left_Down:
		case Land.Train_Left_Down:
			if(direction == Direction.west){
				return Direction.southwest;
			}else{
				return Direction.east;
			}
		}
		return direction;
	}
	
	
	public static void initTrain(final Train train, final int code) {
		Direction direction = null;
		boolean reversable = true;
		Direction nonCircleDirection = getNonCircleInitialDirection(getDefaultBeneath(code));
		
		Point startLocation = (Point)train.getLocation().clone();
		
		Point location = (Point)startLocation.clone();
		int cc = code;
		Direction dd = nonCircleDirection;
		
		Point northLocation = (Point)location.clone();
		Direction northDirection = dd;
		
		while(true){
			dd = getRailDirection(dd, cc);

			location = Land.getNewLocation(location, dd);

			cc = Land.getLand(location);
			if(!Land.railList.contains(cc) && !Land.trainList.contains(cc)){
				reversable = true;
				direction = nonCircleDirection;
				break;
			}
			
			if(location.y <= northLocation.y && (cc == Land.Rail_Horizontal || cc == Land.Train_Horizontal)){
				northLocation = (Point)location.clone();
				northDirection = dd;
			}
			if(location.equals(startLocation)){
				reversable = false;
				if(northDirection == Direction.west){
					direction = nonCircleDirection;
				}else{
					direction = nonCircleDirection.getOppositeDirection();
				}
				break;
			}
		}
		
		train.initTrain(direction, reversable);
	}

	private static Direction getNonCircleInitialDirection(int beneath) {
		switch(beneath){
		case Land.Rail_Vertical:
			return Direction.north;
			
		case Land.Rail_Horizontal:
			return Direction.east;
			
		case Land.Rail_Diagonal_Up:
			return  Direction.northeast;
			
		case Land.Rail_Diagonal_Down:
			return Direction.southeast;
			
		case Land.Rail_Up_Right:
			return Direction.north;
			
		case Land.Rail_Up_Left:
			return Direction.north;
			
		case Land.Rail_Down_Right:
			return Direction.south;
			
		case Land.Rail_Down_Left:
			return Direction.south;
			
		case Land.Rail_Right_Up:
			return Direction.east;
			
		case Land.Rail_Right_Down:
			return Direction.east;
			
		case Land.Rail_Left_Up:
			return Direction.west;
			
		case Land.Rail_Left_Down:
			return Direction.west;
		}
		return Direction.north;
	}

	public static int getDefaultBeneath(int code){
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

		}
		return Land.Empty;
	}
	
	public static int getCode(int beneath){
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

		}
		return Land.Empty;

	}

}
