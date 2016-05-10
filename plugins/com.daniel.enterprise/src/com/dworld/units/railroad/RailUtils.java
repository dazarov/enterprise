package com.dworld.units.railroad;

import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.Location;

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
		case Land.WarTrain_Vertical:
		case Land.WarTrain_Horizontal:
		case Land.WarTrain_Diagonal_Up:
		case Land.WarTrain_Diagonal_Down:
			return direction;
			
		case Land.Rail_Up_Right:
		case Land.Train_Up_Right:
		case Land.WarTrain_Up_Right:
			if(direction == Direction.NORTH){
				return Direction.NORTHEAST;
			}else{
				return Direction.SOUTH;
			}
			
		case Land.Rail_Up_Left:
		case Land.Train_Up_Left:
		case Land.WarTrain_Up_Left:
			if(direction == Direction.NORTH){
				return Direction.NORTHWEST;
			}else{
				return Direction.SOUTH;
			}
			
		case Land.Rail_Down_Right:
		case Land.Train_Down_Right:
			if(direction == Direction.SOUTH){
				return Direction.SOUTHWEST;
			}else{
				return Direction.NORTH;
			}
			
		case Land.Rail_Down_Left:
		case Land.Train_Down_Left:
		case Land.WarTrain_Down_Left:
			if(direction == Direction.SOUTH){
				return Direction.SOUTHEAST;
			}else{
				return Direction.NORTH;
			}
			
		case Land.Rail_Right_Up:
		case Land.Train_Right_Up:
		case Land.WarTrain_Right_Up:
			if(direction == Direction.EAST){
				return Direction.NORTHEAST;
			}else{
				return Direction.WEST;
			}
			
		case Land.Rail_Right_Down:
		case Land.Train_Right_Down:
		case Land.WarTrain_Right_Down:
			if(direction == Direction.EAST){
				return Direction.SOUTHEAST;
			}else{
				return Direction.WEST;
			}
			
		case Land.Rail_Left_Up:
		case Land.Train_Left_Up:
		case Land.WarTrain_Left_Up:
			if(direction == Direction.WEST){
				return Direction.NORTHWEST;
			}else{
				return Direction.EAST;
			}
			
		case Land.Rail_Left_Down:
		case Land.Train_Left_Down:
		case Land.WarTrain_Left_Down:
			if(direction == Direction.WEST){
				return Direction.SOUTHWEST;
			}else{
				return Direction.EAST;
			}
		}
		return direction;
	}
	
	
	public static void initTrain(final Train train, final int code) {
		Direction direction = null;
		boolean reversable = true;
		Direction nonCircleDirection = getNonCircleInitialDirection(code, train.getDefaultBeneath(code));
		
		Location startLocation = train.getLocation();
		
		Location location = startLocation;
		int cc = code;
		Direction dd = nonCircleDirection;
		
		Location northLocation = location;
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
			
			if(location.getY() <= northLocation.getY() && (cc == Land.Rail_Horizontal || cc == Land.Train_Horizontal|| cc == Land.WarTrain_Horizontal)){
				northLocation = location;
				northDirection = dd;
			}
			if(location.equals(startLocation)){
				reversable = false;
				if(northDirection == Direction.WEST){
					direction = nonCircleDirection;
				}else{
					direction = nonCircleDirection.getOppositeDirection();
				}
				break;
			}
		}
		
		train.initTrain(direction, reversable);
	}

	private static Direction getNonCircleInitialDirection(int code, int beneath) {
		switch(beneath){
		case Land.Rail_Vertical:
			return Direction.NORTH;
			
		case Land.Rail_Horizontal:
			return Direction.EAST;
			
		case Land.Rail_Diagonal_Up:
			return  Direction.NORTHEAST;
			
		case Land.Rail_Diagonal_Down:
			return Direction.SOUTHEAST;
			
		case Land.Rail_Up_Right:
			return Direction.NORTH;
			
		case Land.Rail_Up_Left:
			return Direction.NORTH;
			
		case Land.Rail_Down_Right:
			return Direction.SOUTH;
			
		case Land.Rail_Down_Left:
			return Direction.SOUTH;
			
		case Land.Rail_Right_Up:
			return Direction.EAST;
			
		case Land.Rail_Right_Down:
			return Direction.EAST;
			
		case Land.Rail_Left_Up:
			return Direction.WEST;
			
		case Land.Rail_Left_Down:
			return Direction.WEST;
			
		case Land.Rail_Vertical_Cross:
			if(code == Land.Train_Vertical_Cross || code == Land.WarTrain_Vertical_Cross){
				return Direction.NORTH;
			}else{
				return Direction.EAST;
			}
		case Land.Rail_Diagonal_Cross:
			if(code == Land.Train_Diagonal_Up_Cross || code == Land.WarTrain_Diagonal_Up_Cross){
				return Direction.NORTHEAST;
			}else{
				return Direction.SOUTHEAST;
			}
		}
		return Direction.NORTH;
	}
}
