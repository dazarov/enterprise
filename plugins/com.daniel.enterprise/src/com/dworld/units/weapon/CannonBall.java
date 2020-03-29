package com.dworld.units.weapon;

import java.util.Set;

import com.dworld.core.DWConstants;
import com.dworld.core.Direction;
import com.dworld.core.Land;
import com.dworld.ui.DWSounds;
import com.dworld.units.FlyableUnit;

public class CannonBall extends FlyableUnit {
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
			Land.setForeground(getLocation().getX(), getLocation().getY(), beneath);
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
