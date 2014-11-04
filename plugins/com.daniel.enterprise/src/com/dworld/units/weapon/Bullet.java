package com.dworld.units.weapon;

import java.awt.Point;
import java.util.List;

import com.dworld.core.DWorldConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;

public class Bullet extends MovableWeapon {
	private int counter=0;

	public Bullet(int x, int y, Direction direction) {
		super(x, y, Land.Bullet, DWorldConstants.BULLET_SPEED);
		setDirection(direction);
		Land.setLand(getLocation(), beneath);
	}

	@Override
	protected boolean lookAround() {
		return true;
	}
	
	@Override
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
		case Land.Mine_Grass:
		case Land.Bullet_Grass:
		case Land.Bomb_Grass:
		case Land.CannonBall_Grass:
			return Land.Bullet_Grass;
		case Land.Water:
		case Land.Bullet_Water:
		case Land.Bomb_Water:
		case Land.CannonBall_Water:
			return Land.Bullet_Water;
		case Land.Sand:
		case Land.Mine_Sand:
		case Land.Bullet_Sand:
		case Land.Bomb_Sand:
		case Land.CannonBall_Sand:
			return Land.Bullet_Sand;
		default:
			return Land.Bullet;
		}
	}
	
	@Override
	protected List<Integer> getWalkList(){
		return Land.flyAndFindList;
	}

	@Override
	protected boolean findNewDirection() {
		die();
		int code = Land.getWalkStop(getLocation(), direction);
		Point point = Land.getNewLocation(getLocation(), direction);
		if (Land.forBulletListContains(code)) {
			Land.explode(point);
			return false;
		}
		switch (code) {
		case Land.Man:
		case Land.Soldier:
		case Land.Officer:
		case Land.General:
			Land.setLand(point, Land.Grave);
			break;
		case Land.Man_Grass:
		case Land.Soldier_Grass:
		case Land.Officer_Grass:
		case Land.General_Grass:
			Land.setLand(point, Land.Grave_Grass);
			break;
		case Land.Man_Sand:
		case Land.Soldier_Sand:
		case Land.Officer_Sand:
		case Land.General_Sand:
			Land.setLand(point, Land.Grave_Sand);
			break;
		case Land.Robot:
		case Land.Gray_Officer:
		case Land.Gray_General:
		case Land.Dark_Knight:
			Land.setLand(point, Land.RobotGrave);
			break;
		case Land.Robot_Grass:
		case Land.Gray_Officer_Grass:
		case Land.Gray_General_Grass:
		case Land.Dark_Knight_Grass:
			Land.setLand(point, Land.RobotGrave_Grass);
			break;
		case Land.Robot_Sand:
		case Land.Gray_Officer_Sand:
		case Land.Gray_General_Sand:
		case Land.Dark_Knight_Sand:
			Land.setLand(point, Land.RobotGrave_Sand);
			break;
		}
		if (beneath != Land.Vacuum)
			Land.setLand(getLocation(), getBeneath());
		
		return false;
	}

	@Override
	protected void walk() {
		counter++;
		if(counter > DWorldConstants.BULLET_RANGE){
			die();
			return;
		}
		if (beneath != Land.Vacuum)
			Land.setLand(getLocation(), beneath);
		setLocation(Land.getNewLocation(getLocation(), direction));
		beneath = Land.setLand(getLocation(), this);
		if (Land.bulletListContains(beneath))
			beneath = Land.Vacuum;
		if (Land.forBulletListContains(beneath)) {
			die();
			Land.explode(getLocation());
			return;
		}
	}

	@Override
	protected int getGrave(int beneath) {
		return beneath;
	}

	@Override
	protected boolean isGoing(){
		return true;
	}
}
