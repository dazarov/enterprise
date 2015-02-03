package com.dworld.units.railroad;

import java.util.List;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.units.weapon.Rocket;

public class WarTrain extends Train {

	public WarTrain(int x, int y, int code) {
		super(x, y, code);
	}

	@Override
	protected int getDefaultBeneath(int code){
		switch(code){
		case Land.WarTrain_Vertical:
			return Land.Rail_Vertical;
			
		case Land.WarTrain_Horizontal:
			return Land.Rail_Horizontal;
			
		case Land.WarTrain_Diagonal_Up:
			return Land.Rail_Diagonal_Up;
			
		case Land.WarTrain_Diagonal_Down:
			return Land.Rail_Diagonal_Down;
			
		case Land.WarTrain_Up_Right:
			return Land.Rail_Up_Right;
			
		case Land.WarTrain_Up_Left:
			return Land.Rail_Up_Left;
			
		case Land.WarTrain_Down_Right:
			return Land.Rail_Down_Right;
			
		case Land.WarTrain_Down_Left:
			return Land.Rail_Down_Left;
			
		case Land.WarTrain_Right_Up:
			return Land.Rail_Right_Up;
			
		case Land.WarTrain_Right_Down:
			return Land.Rail_Right_Down;
			
		case Land.WarTrain_Left_Up:
			return Land.Rail_Left_Up;
			
		case Land.WarTrain_Left_Down:
			return Land.Rail_Left_Down;
			
		case Land.WarTrain_Vertical_Cross:
			return Land.Rail_Vertical_Cross;
			
		case Land.WarTrain_Horizontal_Cross:
			return Land.Rail_Vertical_Cross;
			
		case Land.WarTrain_Diagonal_Up_Cross:
			return Land.Rail_Diagonal_Cross;
			
		case Land.WarTrain_Diagonal_Down_Cross:
			return Land.Rail_Diagonal_Cross;
		}
		return Land.Empty;
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Rail_Vertical:
			return Land.WarTrain_Vertical;
			
		case Land.Rail_Horizontal:
			return Land.WarTrain_Horizontal;
			
		case Land.Rail_Diagonal_Up:
			return Land.WarTrain_Diagonal_Up;
			
		case Land.Rail_Diagonal_Down:
			return Land.WarTrain_Diagonal_Down;
			
		case Land.Rail_Up_Right:
			return Land.WarTrain_Up_Right;
			
		case Land.Rail_Up_Left:
			return Land.WarTrain_Up_Left;
			
		case Land.Rail_Down_Right:
			return Land.WarTrain_Down_Right;
			
		case Land.Rail_Down_Left:
			return Land.WarTrain_Down_Left;
			
		case Land.Rail_Right_Up:
			return Land.WarTrain_Right_Up;
			
		case Land.Rail_Right_Down:
			return Land.WarTrain_Right_Down;
			
		case Land.Rail_Left_Up:
			return Land.WarTrain_Left_Up;
			
		case Land.Rail_Left_Down:
			return Land.WarTrain_Left_Down;
			
		case Land.Rail_Vertical_Cross:
			if(direction == Direction.north || direction == Direction.south){
				return Land.WarTrain_Vertical_Cross;
			}else{
				return Land.WarTrain_Horizontal_Cross;
			}
			
		case Land.Rail_Diagonal_Cross:
			if(direction == Direction.northeast || direction == Direction.southwest){
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
	protected List<Integer> getListToFightWith(){
		return Land.enemyList;
	}
	
	@Override
	protected List<Integer> getArmoredListToFightWith(){
		return Land.armoredEnemyList;
	}
	
	@Override
	protected int getRocketType(){
		return Rocket.ManFriendly;
	}

}
