package com.dworld.units.railroad;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.units.weapon.Rocket;

public class WarTrain extends Train {

	public WarTrain(int x, int y, Land land) {
		super(x, y, land);
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public Land getLand(){
		Land background = Land.getBackground(getLocation());
		switch(background){
		case Rail_Vertical:
			return Land.WarTrain_Vertical;
			
		case Rail_Horizontal:
			return Land.WarTrain_Horizontal;
			
		case Rail_Diagonal_Up:
			return Land.WarTrain_Diagonal_Up;
			
		case Rail_Diagonal_Down:
			return Land.WarTrain_Diagonal_Down;
			
		case Rail_Up_Right:
			return Land.WarTrain_Up_Right;
			
		case Rail_Up_Left:
			return Land.WarTrain_Up_Left;
			
		case Rail_Down_Right:
			return Land.WarTrain_Down_Right;
			
		case Rail_Down_Left:
			return Land.WarTrain_Down_Left;
			
		case Rail_Right_Up:
			return Land.WarTrain_Right_Up;
			
		case Rail_Right_Down:
			return Land.WarTrain_Right_Down;
			
		case Rail_Left_Up:
			return Land.WarTrain_Left_Up;
			
		case Rail_Left_Down:
			return Land.WarTrain_Left_Down;
			
		case Rail_Vertical_Cross:
			if(direction == Direction.NORTH || direction == Direction.SOUTH){
				return Land.WarTrain_Vertical_Cross;
			}else{
				return Land.WarTrain_Horizontal_Cross;
			}
			
		case Rail_Diagonal_Cross:
			if(direction == Direction.NORTHEAST || direction == Direction.SOUTHWEST){
				return Land.WarTrain_Diagonal_Up_Cross;
			}else{
				return Land.WarTrain_Diagonal_Down_Cross;
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
		//searchForStation();
		//fireAgainstRocket();
		
		fireCannonBalls(DWConstants.TANK_ARMORED_DISTANCE);
		
		fireBullets(DWConstants.TANK_NOTARMORED_DISTANCE);
		return true;
	}
	
	@Override
	protected Set<Land> getListToFightWith(){
		return Land.enemyList;
	}
	
	@Override
	protected Set<Land> getArmoredListToFightWith(){
		return Land.armoredEnemyList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.ManFriendly;
	}

}
