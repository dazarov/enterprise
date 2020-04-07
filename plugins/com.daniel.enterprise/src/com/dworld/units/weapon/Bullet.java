package com.dworld.units.weapon;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.units.FlyableUnit;

public class Bullet extends FlyableUnit {
	private int counter=0;

	public Bullet(int x, int y, Direction direction) {
		super(x, y, Land.Bullet, DWConstants.BULLET_SPEED);
		setDirection(direction);
		Land.setForeground(getLocation(), beneath);
	}

	@Override
	protected boolean lookAround() {
		return true;
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
			Land.setForeground(point, Land.Grave);
			break;
		case BadSoldier:
		case BadOfficer:
		case BadGeneral:
		case Dark_Knight:
			Land.setForeground(point, Land.RobotGrave);
			break;
		}
		if (beneath != Land.Vacuum)
			Land.setForeground(getLocation(), getBeneath());
		
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
			Land.setForeground(getLocation().getX(), getLocation().getY(), beneath);
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
