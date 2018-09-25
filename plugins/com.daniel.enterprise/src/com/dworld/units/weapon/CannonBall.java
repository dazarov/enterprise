package com.dworld.units.weapon;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.ui.DWSounds;

public class CannonBall extends MovableWeapon {
	private int counter=0;
	private int range = DWConstants.CANNONBALL_RANGE;
	
	public CannonBall(int x, int y, Direction direction, int range) {
		this(x, y, direction);
		if(range <= DWConstants.CANNONBALL_RANGE){
			this.range = range;
		}
	}

	public CannonBall(int x, int y, Direction direction) {
		super(x, y, Land.CannonBall, DWConstants.CANNONBALL_SPEED);
		setDirection(direction);
		Land.setLand(getLocation(), beneath);
		DWSounds.CANNON_SHOT.playSound();
	}

	@Override
	protected boolean lookAround() {
		return true;
	}
	
	@Override
	public void die(){
		super.die();
		Land.explode(getLocation());
	}
	
	@Override
	public Land getLand(Land beneath){
		switch(beneath){
		case Grass:
		case Mine_Grass:
		case Bullet_Grass:
		case Bomb_Grass:
		case CannonBall_Grass:
			return Land.CannonBall_Grass;
		case Water:
		case Bullet_Water:
		case Bomb_Water:
		case CannonBall_Water:
			return Land.CannonBall_Water;
		case Sand:
		case Mine_Sand:
		case Bullet_Sand:
		case Bomb_Sand:
		case CannonBall_Sand:
			return Land.CannonBall_Sand;
		default:
			return Land.CannonBall;
		}
	}
	
	@Override
	protected Set<Land> getWalkList(){
		return Land.flyAndFindList;
	}

	@Override
	protected boolean findNewDirection() {
		die();
		return false;
	}

	@Override
	protected void walk() {
		counter++;
		if(counter > range){
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
