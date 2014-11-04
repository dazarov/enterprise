package com.dworld.units;

import java.awt.Point;

import com.dworld.core.ISlow;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;

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
	
	public Gate(int x, int y, int code) {
		super(x, y, Land.Vacuum);
		this.material = getMaterial(code);
		this.orientation = getOrientation(code);
		this.state = Closed;
		this.code = getCode();
		Land.setLand(getLocation(), code);
	}
	
	private int getMaterial(int code){
		switch(code){
		case Land.OpenedHorizontalSteelGate:
		case Land.ClosedHorizontalSteelGate:
		case Land.OpenedVerticalSteelGate:
		case Land.ClosedVerticalSteelGate:
			
			return Steel;
			
		case Land.OpenedHorizontalWoodGate:
		case Land.ClosedHorizontalWoodGate:
		case Land.OpenedVerticalWoodGate:
		case Land.ClosedVerticalWoodGate:
			
			return Wood;
			
		case Land.OpenedHorizontalConcreteGate:
		case Land.ClosedHorizontalConcreteGate:
		case Land.OpenedVerticalConcreteGate:
		case Land.ClosedVerticalConcreteGate:
			
			return Concrete;
			
		default:
			
			return Brick;
		}
		
	}
	
	private int getOrientation(int code){
		switch(code){
		case Land.OpenedHorizontalSteelGate:
		case Land.ClosedHorizontalSteelGate:
		case Land.OpenedHorizontalWoodGate:
		case Land.ClosedHorizontalWoodGate:
		case Land.OpenedHorizontalConcreteGate:
		case Land.ClosedHorizontalConcreteGate:
		case Land.OpenedHorizontalBrickGate:
		case Land.ClosedHorizontalBrickGate:
			
			return Horizontal;
			
		default:
			return Vertical;
		}
	}

	protected int getCode() {
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
		if(state == Closed && Land.getLand(getLocation()) != getCode()){
			die();
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			Point point = getPoint(i);
			int land = Land.getLand(point);
			if (Land.citizenList.contains(new Integer(land))) {
				if (state == Closed) {
					state = Opened;
					code = getCode();
					if (i != 0)
						Land.setLand(getLocation(), code);
				}
				return;
			}
		}
		if (state == Opened) {
			state = Closed;
			code = getCode();
			Land.setLand(getLocation(), code);
		}
	}

	private Point getPoint(int first) {
		Point location = getLocation();
		if (orientation == Vertical) {
			if (first == 0)
				return location;
			else if (first == 1)
				return new Point(location.x + 1, location.y);
			else
				return new Point(location.x - 1, location.y);
		} else { // horizontal
			if (first == 0)
				return location;
			else if (first == 1)
				return new Point(location.x, location.y + 1);
			else
				return new Point(location.x, location.y - 1);
		}
	}
	
	@Override
	public <T> void command(int commandId, T arg){
		super.command(commandId, arg);
		
		if(commandId == SelectionManager.OPEN_COMMAND){
			state = Opened;
			Land.setLand(getLocation(), getCode());
			deactivate();
		}else if(commandId == SelectionManager.CLOSE_COMMAND){
			state = Closed;
			Land.setLand(getLocation(), getCode());
			deactivate();
		}
	}
}
