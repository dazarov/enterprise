package com.dworld.units.railroad;

import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.Location;

public class RailUtils {
	
	@SuppressWarnings("incomplete-switch")
	public static Direction getRailDirection(Direction direction, Land beneath) {
		switch(beneath){
		case Rail_Vertical:
		case Rail_Horizontal:
		case Rail_Diagonal_Up:
		case Rail_Diagonal_Down:
		case Train_Vertical:
		case Train_Horizontal:
		case Train_Diagonal_Up:
		case Train_Diagonal_Down:
		case WarTrain_Vertical:
		case WarTrain_Horizontal:
		case WarTrain_Diagonal_Up:
		case WarTrain_Diagonal_Down:
			return direction;
			
		case Rail_Up_Right:
		case Train_Up_Right:
		case WarTrain_Up_Right:
			if(direction == Direction.NORTH){
				return Direction.NORTHEAST;
			}else{
				return Direction.SOUTH;
			}
			
		case Rail_Up_Left:
		case Train_Up_Left:
		case WarTrain_Up_Left:
			if(direction == Direction.NORTH){
				return Direction.NORTHWEST;
			}else{
				return Direction.SOUTH;
			}
			
		case Rail_Down_Right:
		case Train_Down_Right:
			if(direction == Direction.SOUTH){
				return Direction.SOUTHWEST;
			}else{
				return Direction.NORTH;
			}
			
		case Rail_Down_Left:
		case Train_Down_Left:
		case WarTrain_Down_Left:
			if(direction == Direction.SOUTH){
				return Direction.SOUTHEAST;
			}else{
				return Direction.NORTH;
			}
			
		case Rail_Right_Up:
		case Train_Right_Up:
		case WarTrain_Right_Up:
			if(direction == Direction.EAST){
				return Direction.NORTHEAST;
			}else{
				return Direction.WEST;
			}
			
		case Rail_Right_Down:
		case Train_Right_Down:
		case WarTrain_Right_Down:
			if(direction == Direction.EAST){
				return Direction.SOUTHEAST;
			}else{
				return Direction.WEST;
			}
			
		case Rail_Left_Up:
		case Train_Left_Up:
		case WarTrain_Left_Up:
			if(direction == Direction.WEST){
				return Direction.NORTHWEST;
			}else{
				return Direction.EAST;
			}
			
		case Rail_Left_Down:
		case Train_Left_Down:
		case WarTrain_Left_Down:
			if(direction == Direction.WEST){
				return Direction.SOUTHWEST;
			}else{
				return Direction.EAST;
			}
		}
		return direction;
	}
	
	
	public static void initTrain(final Train train, final Land land) {
		Direction direction = null;
		boolean reversable = true;
		Direction nonCircleDirection = getNonCircleInitialDirection(land, train.getDefaultBeneath(land));
		
		Location startLocation = train.getLocation();
		
		Location location = startLocation;
		Land cc = land;
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

	@SuppressWarnings("incomplete-switch")
	private static Direction getNonCircleInitialDirection(Land land, Land beneath) {
		switch(beneath){
		case Rail_Vertical:
			return Direction.NORTH;
			
		case Rail_Horizontal:
			return Direction.EAST;
			
		case Rail_Diagonal_Up:
			return  Direction.NORTHEAST;
			
		case Rail_Diagonal_Down:
			return Direction.SOUTHEAST;
			
		case Rail_Up_Right:
			return Direction.NORTH;
			
		case Rail_Up_Left:
			return Direction.NORTH;
			
		case Rail_Down_Right:
			return Direction.SOUTH;
			
		case Rail_Down_Left:
			return Direction.SOUTH;
			
		case Rail_Right_Up:
			return Direction.EAST;
			
		case Rail_Right_Down:
			return Direction.EAST;
			
		case Rail_Left_Up:
			return Direction.WEST;
			
		case Rail_Left_Down:
			return Direction.WEST;
			
		case Rail_Vertical_Cross:
			if(land == Land.Train_Vertical_Cross || land == Land.WarTrain_Vertical_Cross){
				return Direction.NORTH;
			}else{
				return Direction.EAST;
			}
		case Rail_Diagonal_Cross:
			if(land == Land.Train_Diagonal_Up_Cross || land == Land.WarTrain_Diagonal_Up_Cross){
				return Direction.NORTHEAST;
			}else{
				return Direction.SOUTHEAST;
			}
		}
		return Direction.NORTH;
	}
}
