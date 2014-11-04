package com.dworld.units;

import java.awt.Point;
import java.util.List;

import com.dworld.core.DWorldConstants;
import com.dworld.core.Direction;
import com.dworld.core.Engine;
import com.dworld.core.Land;
import com.dworld.units.weapon.Rocket;

public class ControlledUnit extends MovableUnit {
	protected int key = -1;
	protected int modifier = 0;
	protected Point drawPosition = new Point(0,0);
	protected double currentSpeed = DWorldConstants.STOP_SPEED;
	protected boolean fightActive = false;
	protected boolean defenseActive = true;
	
	public ControlledUnit(int x, int y, int code) {
		super(x, y, code, DWorldConstants.MAX_SPEED);
		currentSpeed = DWorldConstants.STOP_SPEED;
		speed = currentSpeed;
	}
	
	public void setCode(int code){
		this.code = code;
		
		Land.setLand(getLocation(), getCode(getBeneath()));
	}
	
	protected List<Integer> getListToFightWith(){
		if(Land.citizenList.contains(code) || Land.armoredCitizenList.contains(code))
			return Land.enemyList;
		else
			return Land.citizenList;
	}
	
	@Override
	public boolean isActive() {
		return true;
	}

	public void control(int key, int modifier) {
		this.modifier = modifier;
		if(modifier == 1){
			switch(key){
			case 37: // Left
				drawPosition.x -= 5;
				break;

			case 38: // Up
				drawPosition.y -= 5;
				break;

			case 39: // Right
				drawPosition.x += 5;
				break;

			case 40: // Down
				drawPosition.y += 5;
				break;
			}
			return;
		}else if(key == 76){ // l key
			Land.setLand(getLocation(), getCode(getBeneath()));
			activate();
			resurrect();
			Engine.getEngine().addUnit(this);
		}
		if (this.key == -1)
			this.key = key;
	}
	
	public Point getDrawPosition(){
		if(modifier != 1){
			drawPosition = (Point)getLocation().clone();
			return getLocation();
		}else{
			return drawPosition;
		}
	}
	
	@Override
	public int getCode(int beneath){
		if(code == Land.Man){
			switch(beneath){
			case Land.Grass:
				return Land.Man_Grass;
			case Land.Sand:
				return Land.Man_Sand;
			default:
				return Land.Man;
			}
		}else if(code == Land.Soldier){
			switch(beneath){
			case Land.Grass:
				return Land.Soldier_Grass;
			case Land.Sand:
				return Land.Soldier_Sand;
			default:
				return Land.Soldier;
			}
		}else if(code == Land.Robot){
			switch(beneath){
			case Land.Grass:
				return Land.Robot_Grass;
			case Land.Sand:
				return Land.Robot_Sand;
			default:
				return Land.Robot;
			}
		}else if(code == Land.Peasant){
			switch(beneath){
			case Land.Grass:
				return Land.Peasant_Grass;
			case Land.Sand:
				return Land.Peasant_Sand;
			default:
				return Land.Peasant;
			}
		}else if(code == Land.Officer){
			switch(beneath){
			case Land.Grass:
				return Land.Officer_Grass;
			case Land.Sand:
				return Land.Officer_Sand;
			default:
				return Land.Officer;
			}
		}else if(code == Land.General){
			switch(beneath){
			case Land.Grass:
				return Land.General_Grass;
			case Land.Sand:
				return Land.General_Sand;
			default:
				return Land.General;
			}
		}else if(code == Land.Gray_Officer){
			switch(beneath){
			case Land.Grass:
				return Land.Gray_Officer_Grass;
			case Land.Sand:
				return Land.Gray_Officer_Sand;
			default:
				return Land.Gray_Officer;
			}
		}else if(code == Land.Gray_General){
			switch(beneath){
			case Land.Grass:
				return Land.Gray_General_Grass;
			case Land.Sand:
				return Land.Gray_General_Sand;
			default:
				return Land.Gray_General;
			}
		}else if(code == Land.Dark_Knight){
			switch(beneath){
			case Land.Grass:
				return Land.Dark_Knight_Grass;
			case Land.Sand:
				return Land.Dark_Knight_Sand;
			default:
				return Land.Dark_Knight;
			}
		}else if(code == Land.GoodTank){
			switch(beneath){
			case Land.Grass:
				return Land.GoodTank_Grass;
			case Land.Sand:
				return Land.GoodTank_Sand;
			default:
				return Land.GoodTank;
			}
		}else if(code == Land.Tank){
			switch(beneath){
			case Land.Grass:
				return Land.Tank_Grass;
			case Land.Sand:
				return Land.Tank_Sand;
			default:
				return Land.Tank;
			}
		}else{
			return Land.Food;
		}
		
	}
	
	@Override
	protected boolean lookAround() {
		if (Land.getLand(getLocation()) != getCode(beneath)) {
			die();
			switch(beneath){
			case Land.Grass:
				Land.setLand(getLocation(), Land.Grave_Grass);
				break;
			case Land.Sand:
				Land.setLand(getLocation(), Land.Grave_Sand);
				break;
			default:
				Land.setLand(getLocation(), Land.Grave);
			}
			return false;
		}
		if(fightActive){
			fireBullets();
		}
		
		if(defenseActive){
			fireAgainstRocket();
			if(lightDefenseComplex()) return true;
		}
		
		if (key != -1) {
			switch (key) {
			case '0':
				fireBomb(10);
				break;
			case '1':
				fireBomb(1);
				break;
			case '2':
				fireBomb(2);
				break;
			case '3':
				fireBomb(3);
				break;
			case '4':
				fireBomb(4);
				break;
			case '5':
				fireBomb(5);
				break;
			case '6':
				fireBomb(6);
				break;
			case '7':
				fireBomb(7);
				break;
			case '8':
				fireBomb(8);
				break;
			case '9':
				fireBomb(9);
				break;
				
			case 68: // d
				defenseActive = ! defenseActive;
				break;
				
			case 70: // f
				fightActive = ! fightActive;
				break;
				
			case 87: // w
				currentSpeed = DWorldConstants.WALK_SPEED;
				speed = currentSpeed;
				break;
				
			case 83: // s
				currentSpeed = 0;
				speed = currentSpeed;
				break;
				
			case 107: // +
				currentSpeed += 0.1;
				if(currentSpeed > DWorldConstants.MAX_SPEED) currentSpeed = DWorldConstants.MAX_SPEED;
				speed = currentSpeed;
				break;

			case 109: // -
				currentSpeed -= 0.1;
				if(currentSpeed < 0) currentSpeed = 0;
				speed = currentSpeed;
				break;

			case 82: // r
				fireRocket();
				break;
				
			case 84: // t
				fireCannon();
				break;

			case 32: // Space bar
				fireBullet();
				break;

			case 37: // Left
				direction = Direction.west;
				speed = DWorldConstants.MAX_SPEED;
				break;

			case 38: // Up
				direction = Direction.north;
				speed = DWorldConstants.MAX_SPEED;
				break;

			case 39: // Right
				direction = Direction.east;
				speed = DWorldConstants.MAX_SPEED;
				break;

			case 40: // Down
				direction = Direction.south;
				speed = DWorldConstants.MAX_SPEED;
				break;
			}
			key = -1;
		} else {
			speed = currentSpeed;
		}
		return true;
	}
	
	@Override
	protected boolean findNewDirection() {
		return true;
	}
	
	public boolean isFight(){
		return fightActive;
	}

	public void setFight(boolean fight){
		fightActive = fight;
	}
	
	public boolean isDefense(){
		return defenseActive;
	}

	public void setDefense(boolean defense){
		defenseActive = defense;
	}
	
	protected int getRocketType(){
		if(code == Land.Man || code == Land.Soldier || code == Land.Officer || code == Land.General || code == Land.Peasant)
			return Rocket.ManFriendly;
		else
			return Rocket.EnemyFriendly;
	}
	
	protected Rocket fireRocket(){
		return fireRocket(getRocketType());
	}

	@Override
	protected int getGrave(int beneath){
		switch(beneath){
		case Land.Grass:
			return Land.Grave_Grass;
		case Land.Sand:
			return Land.Grave_Sand;
		default:
			return Land.Grave;
		}
	}
	
	@Override
	protected boolean isGoing(){
		if(selfDefense){
			return true;
		}
		if(speed == 0){
			return false;
		}
		return true;
	}
	
	@Override
	public <T> void command(int commandId, T arg){
		
	}
}
