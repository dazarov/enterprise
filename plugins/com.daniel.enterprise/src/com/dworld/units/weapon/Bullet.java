package com.dworld.units.weapon;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.ui.DWSounds;

public class Bullet extends MovableWeapon {
	private int counter=0;

	public Bullet(int x, int y, Direction direction) {
		super(x, y, Land.Bullet, DWConstants.BULLET_SPEED);
		setDirection(direction);
		Land.setLand(getLocation(), beneath);
	}

	@Override
	protected boolean lookAround() {
		return true;
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
		case Mine_Grass:
		case Bullet_Grass:
		case Bomb_Grass:
		case CannonBall_Grass:
			return Land.Bullet_Grass;
		case Water:
		case Bullet_Water:
		case Bomb_Water:
		case CannonBall_Water:
			return Land.Bullet_Water;
		case Sand:
		case Mine_Sand:
		case Bullet_Sand:
		case Bomb_Sand:
		case CannonBall_Sand:
			return Land.Bullet_Sand;
		default:
			return Land.Bullet;
		}
	}
	
	@Override
	protected Set<Land> getWalkList(){
		return Land.flyAndFindList;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	protected boolean findNewDirection() {
		die();
		Land land = Land.getWalkStop(getLocation(), direction);
		Location point = Land.getNewLocation(getLocation(), direction);
		if (Land.forBulletListContains(land)) {
			Land.explode(point);
			return false;
		}
		switch (land) {
		case Hero:
		case GoodSoldier:
		case GoodOfficer:
		case GoodGeneral:
			Land.setLand(point, Land.Grave);
			break;
		case Hero_Grass:
		case GoodSoldier_Grass:
		case GoodOfficer_Grass:
		case GoodGeneral_Grass:
			Land.setLand(point, Land.Grave_Grass);
			break;
		case Hero_Sand:
		case GoodSoldier_Sand:
		case GoodOfficer_Sand:
		case GoodGeneral_Sand:
			Land.setLand(point, Land.Grave_Sand);
			break;
		case BadSoldier:
		case BadOfficer:
		case BadGeneral:
		case Dark_Knight:
			Land.setLand(point, Land.RobotGrave);
			break;
		case BadSoldier_Grass:
		case BadOfficer_Grass:
		case BadGeneral_Grass:
		case Dark_Knight_Grass:
			Land.setLand(point, Land.BadSoldierGrave_Grass);
			break;
		case BadSoldier_Sand:
		case BadOfficer_Sand:
		case BadGeneral_Sand:
		case Dark_Knight_Sand:
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
		if(counter > DWConstants.BULLET_RANGE){
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
	protected Land getGrave(Land beneath) {
		return beneath;
	}

	@Override
	protected boolean isGoing(){
		return true;
	}
}
