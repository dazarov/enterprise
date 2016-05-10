package com.dworld.units;

import java.util.List;

import com.dworld.core.DWConstants;
import com.dworld.core.DWUnitFactory;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.units.weapon.Rocket;

public class ControlledUnit extends MovableUnit {
	protected int key = -1;
	protected int modifier = 0;
	protected Location drawPosition = new Location(0,0);
	protected double currentSpeed = DWConstants.STOP_SPEED;
	protected boolean fightActive = false;
	protected boolean defenseActive = true;
	
	public ControlledUnit(int x, int y, int code) {
		super(x, y, code, DWConstants.MAX_SPEED);
		currentSpeed = DWConstants.STOP_SPEED;
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
				drawPosition = drawPosition.move(-5, 0);//x -= 5;
				break;

			case 38: // Up
				drawPosition = drawPosition.move(0, -5);//y -= 5;
				break;

			case 39: // Right
				drawPosition = drawPosition.move(5, 0);//x += 5;
				break;

			case 40: // Down
				drawPosition = drawPosition.move(0, 5);//y += 5;
				break;
			}
			return;
		}else if(modifier == 2){
				switch(key){
				case 37: // Left
					drawPosition = drawPosition.move(-1,0);//x -= 1;
					break;

				case 38: // Up
					drawPosition = drawPosition.move(0,-1);//y -= 1;
					break;

				case 39: // Right
					drawPosition = drawPosition.move(1,0);//x += 1;
					break;

				case 40: // Down
					drawPosition = drawPosition.move(0,1);//y += 1;
					break;
				}
				return;
		}else if(key == 76){ // l key
			Land.setLand(getLocation(), getCode(getBeneath()));
			activate();
			resurrect();
		}
		if (this.key == -1)
			this.key = key;
	}
	
	public Location getDrawPosition(){
		if(modifier == 0){
			drawPosition = getLocation();
			return getLocation();
		}else{
			return drawPosition;
		}
	}
	
	@Override
	public int getCode(int beneath){
		if(code == Land.Hero){
			switch(beneath){
			case Land.Grass:
				return Land.Hero_Grass;
			case Land.Sand:
				return Land.Hero_Sand;
			default:
				return Land.Hero;
			}
		}else if(code == Land.GoodSoldier){
			switch(beneath){
			case Land.Grass:
				return Land.GoodSoldier_Grass;
			case Land.Sand:
				return Land.GoodSoldier_Sand;
			default:
				return Land.GoodSoldier;
			}
		}else if(code == Land.BadSoldier){
			switch(beneath){
			case Land.Grass:
				return Land.BadSoldier_Grass;
			case Land.Sand:
				return Land.BadSoldier_Sand;
			default:
				return Land.BadSoldier;
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
		}else if(code == Land.GoodOfficer){
			switch(beneath){
			case Land.Grass:
				return Land.GoodOfficer_Grass;
			case Land.Sand:
				return Land.GoodOfficer_Sand;
			default:
				return Land.GoodOfficer;
			}
		}else if(code == Land.GoodGeneral){
			switch(beneath){
			case Land.Grass:
				return Land.GoodGeneral_Grass;
			case Land.Sand:
				return Land.GoodGeneral_Sand;
			default:
				return Land.GoodGeneral;
			}
		}else if(code == Land.BadOfficer){
			switch(beneath){
			case Land.Grass:
				return Land.BadOfficer_Grass;
			case Land.Sand:
				return Land.BadOfficer_Sand;
			default:
				return Land.BadOfficer;
			}
		}else if(code == Land.BadGeneral){
			switch(beneath){
			case Land.Grass:
				return Land.BadGeneral_Grass;
			case Land.Sand:
				return Land.BadGeneral_Sand;
			default:
				return Land.BadGeneral;
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
		}else if(code == Land.BadTank){
			switch(beneath){
			case Land.Grass:
				return Land.BadTank_Grass;
			case Land.Sand:
				return Land.BadTank_Sand;
			default:
				return Land.BadTank;
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
				//fireBomb(10);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 10});
				break;
			case '1':
				//fireBomb(1);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 1});
				break;
			case '2':
				//fireBomb(2);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 2});
				break;
			case '3':
				//fireBomb(3);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 3});
				break;
			case '4':
				//fireBomb(4);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 4});
				break;
			case '5':
				//fireBomb(5);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 5});
				break;
			case '6':
				//fireBomb(6);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 6});
				break;
			case '7':
				//fireBomb(7);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 7});
				break;
			case '8':
				//fireBomb(8);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 8});
				break;
			case '9':
				//fireBomb(9);
				command(INTERNAL_COMMAND_FIRE_BOMB, new Object[]{direction, 9});
				break;
				
			case 68: // d
				defenseActive = ! defenseActive;
				break;
				
			case 70: // f
				fightActive = ! fightActive;
				break;
				
			case 87: // w
				currentSpeed = DWConstants.WALK_SPEED;
				speed = currentSpeed;
				break;
				
			case 83: // s
				currentSpeed = 0;
				speed = currentSpeed;
				break;
				
			case 107: // +
				currentSpeed += 0.1;
				if(currentSpeed > DWConstants.MAX_SPEED) currentSpeed = DWConstants.MAX_SPEED;
				speed = currentSpeed;
				break;

			case 109: // -
				currentSpeed -= 0.1;
				if(currentSpeed < 0) currentSpeed = 0;
				speed = currentSpeed;
				break;

			case 82: // r
				//fireRocket(direction);
				command(INTERNAL_COMMAND_FIRE_ROCKET, new Object[]{direction});
				break;
				
			case 84: // t
				
				Land.setLand(getLocation().getX(), getLocation().getY()-1, Land.Teleport5);
				DWUnitFactory.createUnit(Land.Teleport5, getLocation().getX(), getLocation().getY()-1);
				break;

			case 67: // c
				//fireCannon();
				command(INTERNAL_COMMAND_FIRE_CANNON, new Object[]{direction});
				break;

			case 32: // Space bar
				//fireBullet(direction);
				command(INTERNAL_COMMAND_FIRE_BULLET, new Object[]{direction});
				break;

			case 37: // Left
				direction = Direction.WEST;
				speed = DWConstants.MAX_SPEED;
				break;

			case 38: // Up
				direction = Direction.NORTH;
				speed = DWConstants.MAX_SPEED;
				break;

			case 39: // Right
				direction = Direction.EAST;
				speed = DWConstants.MAX_SPEED;
				break;

			case 40: // Down
				direction = Direction.SOUTH;
				speed = DWConstants.MAX_SPEED;
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
		if(Land.citizenList.contains(code)){
			return Rocket.ManFriendly;
		}else{
			return Rocket.EnemyFriendly;
		}
	}
	
//	protected Rocket fireRocket(){
//		return fireRocket(getRocketType());
//	}

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
	public void command(int commandId, Object[] args){
		//System.out.println(" "+getClass()+" command - "+commandId);
		if(commandId > 10){
			super.command(commandId, args);
		}
	}
}
