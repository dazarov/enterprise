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
		case Land.Hero:
		case Land.GoodSoldier:
		case Land.GoodOfficer:
		case Land.GoodGeneral:
			Land.setLand(point, Land.Grave);
			break;
		case Land.Hero_Grass:
		case Land.GoodSoldier_Grass:
		case Land.GoodOfficer_Grass:
		case Land.GoodGeneral_Grass:
			Land.setLand(point, Land.Grave_Grass);
			break;
		case Land.Hero_Sand:
		case Land.GoodSoldier_Sand:
		case Land.GoodOfficer_Sand:
		case Land.GoodGeneral_Sand:
			Land.setLand(point, Land.Grave_Sand);
			break;
		case Land.BadSoldier:
		case Land.BadOfficer:
		case Land.BadGeneral:
		case Land.Dark_Knight:
			Land.setLand(point, Land.RobotGrave);
			break;
		case Land.BadSoldier_Grass:
		case Land.BadOfficer_Grass:
		case Land.BadGeneral_Grass:
		case Land.Dark_Knight_Grass:
			Land.setLand(point, Land.BadSoldierGrave_Grass);
			break;
		case Land.BadSoldier_Sand:
		case Land.BadOfficer_Sand:
		case Land.BadGeneral_Sand:
		case Land.Dark_Knight_Sand:
			Land.setLand(point, Land.BadSoldierGrave_Sand);
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
