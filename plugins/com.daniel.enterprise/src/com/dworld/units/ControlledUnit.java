package com.dworld.units;

import java.util.Set;

import com.dworld.core.DWConfiguration;
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
	
	public ControlledUnit(int x, int y, Land land) {
		super(x, y, land, DWConstants.MAX_SPEED);
		currentSpeed = DWConstants.STOP_SPEED;
		speed = currentSpeed;
	}
	
	public void setLand(Land land){
		this.land = land;
		
		Land.setLand(getLocation(), getLand(getBeneath()));
	}
	
	protected Set<Land> getListToFightWith(){
		if(Land.citizenList.contains(land) || Land.armoredCitizenList.contains(land))
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
			Land.setLand(getLocation(), getLand(getBeneath()));
			activate();
			resurrect();
		}
		synchronized(this){
			if (this.key == -1){
				this.key = key;
			}
		}
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
	public Land getLand(Land beneath){
		if(land == Land.Hero){
			switch(beneath){
			case Grass:
				return Land.Hero_Grass;
			case Sand:
				return Land.Hero_Sand;
			default:
				return Land.Hero;
			}
		}else if(land == Land.GoodSoldier){
			switch(beneath){
			case Grass:
				return Land.GoodSoldier_Grass;
			case Sand:
				return Land.GoodSoldier_Sand;
			default:
				return Land.GoodSoldier;
			}
		}else if(land == Land.BadSoldier){
			switch(beneath){
			case Grass:
				return Land.BadSoldier_Grass;
			case Sand:
				return Land.BadSoldier_Sand;
			default:
				return Land.BadSoldier;
			}
		}else if(land == Land.Peasant){
			switch(beneath){
			case Grass:
				return Land.Peasant_Grass;
			case Sand:
				return Land.Peasant_Sand;
			default:
				return Land.Peasant;
			}
		}else if(land == Land.GoodOfficer){
			switch(beneath){
			case Grass:
				return Land.GoodOfficer_Grass;
			case Sand:
				return Land.GoodOfficer_Sand;
			default:
				return Land.GoodOfficer;
			}
		}else if(land == Land.GoodGeneral){
			switch(beneath){
			case Grass:
				return Land.GoodGeneral_Grass;
			case Sand:
				return Land.GoodGeneral_Sand;
			default:
				return Land.GoodGeneral;
			}
		}else if(land == Land.BadOfficer){
			switch(beneath){
			case Grass:
				return Land.BadOfficer_Grass;
			case Sand:
				return Land.BadOfficer_Sand;
			default:
				return Land.BadOfficer;
			}
		}else if(land == Land.BadGeneral){
			switch(beneath){
			case Grass:
				return Land.BadGeneral_Grass;
			case Sand:
				return Land.BadGeneral_Sand;
			default:
				return Land.BadGeneral;
			}
		}else if(land == Land.Dark_Knight){
			switch(beneath){
			case Grass:
				return Land.Dark_Knight_Grass;
			case Sand:
				return Land.Dark_Knight_Sand;
			default:
				return Land.Dark_Knight;
			}
		}else if(land == Land.GoodTank){
			switch(beneath){
			case Grass:
				return Land.GoodTank_Grass;
			case Sand:
				return Land.GoodTank_Sand;
			default:
				return Land.GoodTank;
			}
		}else if(land == Land.BadTank){
			switch(beneath){
			case Grass:
				return Land.BadTank_Grass;
			case Sand:
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
		if (Land.getLand(getLocation()) != getLand(beneath)) {
			die();
			switch(beneath){
			case Grass:
				Land.setLand(getLocation(), Land.Grave_Grass);
				break;
			case Sand:
				Land.setLand(getLocation(), Land.Grave_Sand);
				break;
			default:
				Land.setLand(getLocation(), Land.Grave);
			}
			return false;
		}
		if(DWConfiguration.getInstance().isFight()){
			fireBullets();
		}
		
		if(DWConfiguration.getInstance().isDefense()){
			fireAgainstRocket();
			if(lightDefenseComplex()) return true;
		}
		synchronized(this){
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
				DWConfiguration.getInstance().setDefense(!DWConfiguration.getInstance().isDefense());
				break;
				
			case 70: // f
				DWConfiguration.getInstance().setFight(!DWConfiguration.getInstance().isFight());
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
		}
		return true;
	}
	
	@Override
	protected boolean findNewDirection() {
		return true;
	}
	
	protected int getRocketType(){
		if(Land.citizenList.contains(land)){
			return Rocket.ManFriendly;
		}else{
			return Rocket.EnemyFriendly;
		}
	}
	
	@Override
	protected Land getGrave(Land beneath){
		switch(beneath){
		case Grass:
			return Land.Grave_Grass;
		case Sand:
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
