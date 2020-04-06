package com.dworld.units.railroad;

import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.Location;

public class RailUtils {
	
	@SuppressWarnings("incomplete-switch")
	public static Direction getRailDirection(Direction direction, Land background) {
		switch(background){
		case Rail_Vertical:
		case Rail_Horizontal:
		case Rail_Diagonal_Up:
		case Rail_Diagonal_Down:
			return direction;
			
		case Rail_Up_Right:
			if(direction == Direction.NORTH){
				return Direction.NORTHEAST;
			}else if(direction == Direction.SOUTHWEST){
				return Direction.SOUTH;
			}
			break;
			
		case Rail_Up_Left:
			if(direction == Direction.NORTH){
				return Direction.NORTHWEST;
			}else if(direction == Direction.SOUTHEAST){
				return Direction.SOUTH;
			}
			break;
			
		case Rail_Down_Right:
			if(direction == Direction.SOUTH){
				return Direction.SOUTHWEST;
			}else if(direction == Direction.NORTHEAST){
				return Direction.NORTH;
			}
			break;
			
		case Rail_Down_Left:
			if(direction == Direction.SOUTH){
				return Direction.SOUTHEAST;
			}else if(direction == Direction.NORTHWEST){
				return Direction.NORTH;
			}
			break;
			
		case Rail_Right_Up:
			if(direction == Direction.EAST){
				return Direction.NORTHEAST;
			}else if(direction == Direction.SOUTHWEST){
				return Direction.WEST;
			}
			break;
			
		case Rail_Right_Down:
			if(direction == Direction.EAST){
				return Direction.SOUTHEAST;
			}else if(direction == Direction.NORTHWEST){
				return Direction.WEST;
			}
			break;
			
		case Rail_Left_Up:
			if(direction == Direction.WEST){
				return Direction.NORTHWEST;
			}else if(direction == Direction.SOUTHEAST){
				return Direction.EAST;
			}
			break;
			
		case Rail_Left_Down:
			if(direction == Direction.WEST){
				return Direction.SOUTHWEST;
			}else if(direction == Direction.NORTHEAST){
				return Direction.EAST;
			}
			break;
		}
		return direction;
	}
	
	public static void initTrain(final Train train, Land land) {
		Direction direction = null;
		boolean reversable = true;
		Direction nonCircleDirection = getNonCircleInitialDirection(land, Land.getBackground(train.getLocation()));
		
		Location startLocation = train.getLocation();
		
		Location location = startLocation;
		Land cc = Land.getBackground(train.getLocation());
		Direction dd = nonCircleDirection;
		
		Location northLocation = location;
		Direction northDirection = dd;
		
		while(true){
			dd = getRailDirection(dd, cc);

			location = Land.getNewLocation(location, dd);

			cc = Land.getBackground(location);
			if(!Land.railList.contains(cc)){
				reversable = true;
				direction = nonCircleDirection;
				break;
			}
			
			if(location.getY() <= northLocation.getY() && (cc == Land.Rail_Horizontal)){
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
	private static Direction getNonCircleInitialDirection(Land land, Land background) {
		switch(background){
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
