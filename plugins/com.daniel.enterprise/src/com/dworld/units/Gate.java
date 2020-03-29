package com.dworld.units;

import com.dworld.core.ISlow;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.ui.DWSounds;

public class Gate extends ActiveUnit implements ISlow{
	// orientation
	public static final int Vertical = 0;
	public static final int Horizontal = 1;

	// material
	public static final int Steel = 0;
	public static final int Wood = 1;
	public static final int Concrete = 2;
	public static final int Brick = 3;

	// state
	public static final int Closed = 0;
	public static final int Opened = 1;
	
	private int orientation;
	private int material;
	private int state;
	
	public Gate(int x, int y, Land land) {
		super(x, y, Land.Vacuum);
		this.material = getMaterial(land);
		this.orientation = getOrientation(land);
		this.state = Closed;
		this.land = getLand();
		Land.setLand(getLocation(), land);
	}
	
	private int getMaterial(Land land){
		switch(land){
		case OpenedHorizontalSteelGate:
		case ClosedHorizontalSteelGate:
		case OpenedVerticalSteelGate:
		case ClosedVerticalSteelGate:
			
			return Steel;
			
		case OpenedHorizontalWoodGate:
		case ClosedHorizontalWoodGate:
		case OpenedVerticalWoodGate:
		case ClosedVerticalWoodGate:
			
			return Wood;
			
		case OpenedHorizontalConcreteGate:
		case ClosedHorizontalConcreteGate:
		case OpenedVerticalConcreteGate:
		case ClosedVerticalConcreteGate:
			
			return Concrete;
			
		default:
			
			return Brick;
		}
		
	}
	
	private int getOrientation(Land land){
		switch(land){
		case OpenedHorizontalSteelGate:
		case ClosedHorizontalSteelGate:
		case OpenedHorizontalWoodGate:
		case ClosedHorizontalWoodGate:
		case OpenedHorizontalConcreteGate:
		case ClosedHorizontalConcreteGate:
		case OpenedHorizontalBrickGate:
		case ClosedHorizontalBrickGate:
			
			return Horizontal;
			
		default:
			return Vertical;
		}
	}

	protected Land getLand() {
		if (material == Steel) {
			if (orientation == Vertical) {
				if (state == Opened)
					return Land.OpenedVerticalSteelGate;
				else
					return Land.ClosedVerticalSteelGate;
			} else { // Horizontal
				if (state == Opened)
					return Land.OpenedHorizontalSteelGate;
				else
					return Land.ClosedHorizontalSteelGate;
			}
		} else if(material == Wood){ // Wood
			if (orientation == Vertical) {
				if (state == Opened)
					return Land.OpenedVerticalWoodGate;
				else
					return Land.ClosedVerticalWoodGate;
			} else { // Horizontal
				if (state == Opened)
					return Land.OpenedHorizontalWoodGate;
				else
					return Land.ClosedHorizontalWoodGate;
			}
		} else if(material == Concrete){
			if (orientation == Vertical) {
				if (state == Opened)
					return Land.OpenedVerticalConcreteGate;
				else
					return Land.ClosedVerticalConcreteGate;
			} else { // Horizontal
				if (state == Opened)
					return Land.OpenedHorizontalConcreteGate;
				else
					return Land.ClosedHorizontalConcreteGate;
			}
		} else{  // Brick
			if (orientation == Vertical) {
				if (state == Opened)
					return Land.OpenedVerticalBrickGate;
				else
					return Land.ClosedVerticalBrickGate;
			} else { // Horizontal
				if (state == Opened)
					return Land.OpenedHorizontalBrickGate;
				else
					return Land.ClosedHorizontalBrickGate;
			}
		}
	}

	@Override
	public void step() {
		if(state == Closed && Land.getLand(getLocation()) != getLand()){
			die();
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			Location point = getPoint(i);
			Land l = Land.getForeground(point.getX(), point.getY());
			if (Land.citizenList.contains(l)) {
				if (state == Closed) {
					state = Opened;
					land = getLand();
					if (i != 0)
						Land.setLand(getLocation(), land);
					DWSounds.DDOR.playSound();
				}
				return;
			}
		}
		if (state == Opened) {
			state = Closed;
			land = getLand();
			Land.setLand(getLocation(), land);
			DWSounds.DDOR.playSound();
		}
	}

	private Location getPoint(int first) {
		Location location = getLocation();
		if (orientation == Vertical) {
			if (first == 0)
				return location;
			else if (first == 1)
				return location.move(1, 0);//new Location(location.x + 1, location.y);
			else
				return location.move(-1, 0);//new Location(location.x - 1, location.y);
		} else { // horizontal
			if (first == 0)
				return location;
			else if (first == 1)
				return location.move(0, 1);//new Location(location.x, location.y + 1);
			else
				return location.move(0, -1);//new Location(location.x, location.y - 1);
		}
	}
	
	@Override
	public void command(int commandId, Object[] args){
		super.command(commandId, args);
		
		if(commandId == EXTERNAL_COMMAND_OPEN_GATE){
			state = Opened;
			Land.setLand(getLocation(), getLand());
			deactivate();
		}else if(commandId == EXTERNAL_COMMAND_CLOSE_GATE){
			state = Closed;
			Land.setLand(getLocation(), getLand());
			deactivate();
		}
	}
	
	protected void doDefaultCommand(){
		System.out.println(" "+getClass()+" doDefaultCommand");
		if(state == Opened){
			state = Closed;
			Land.setLand(getLocation(), getLand());
			activate();
		}else if(state == Closed){
			state = Opened;
			Land.setLand(getLocation(), getLand());
			deactivate();
		}
	}
}
