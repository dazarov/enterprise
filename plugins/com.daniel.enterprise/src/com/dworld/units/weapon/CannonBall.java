package com.dworld.units.weapon;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;

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
	public int getCode(int beneath){
		switch(beneath){
		case Land.Grass:
		case Land.Mine_Grass:
		case Land.Bullet_Grass:
		case Land.Bomb_Grass:
		case Land.CannonBall_Grass:
			return Land.CannonBall_Grass;
		case Land.Water:
		case Land.Bullet_Water:
		case Land.Bomb_Water:
		case Land.CannonBall_Water:
			return Land.CannonBall_Water;
		case Land.Sand:
		case Land.Mine_Sand:
		case Land.Bullet_Sand:
		case Land.Bomb_Sand:
		case Land.CannonBall_Sand:
			return Land.CannonBall_Sand;
		default:
			return Land.CannonBall;
		}
	}
	
	@Override
	protected Set<Integer> getWalkList(){
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
	protected int getGrave(int beneath) {
		return beneath;
	}
	
	@Override
	protected boolean isGoing(){
		return true;
	}
}
