package com.dworld.units;

import java.awt.Point;
import java.util.HashMap;

import com.dworld.core.Engine;
import com.dworld.core.ISlow;
import com.dworld.core.IUnit;
import com.dworld.core.Land;

public class Teleport extends ActiveUnit implements ISlow {
	private static HashMap<Integer, Point> toPoints = new HashMap<Integer, Point>();
	static{
		toPoints.put(new Integer(Land.Teleport1), new Point(Land.getMaxX()/2, Land.getMaxY()/2)); 	// Center
		toPoints.put(new Integer(Land.Teleport2), new Point(Land.getMaxX()-25, 15));				// Home
		toPoints.put(new Integer(Land.Teleport3), new Point(3353, 2053));							// Scientific Center
		toPoints.put(new Integer(Land.Teleport4), new Point(1232, 1690));							// Capital
		toPoints.put(new Integer(Land.Teleport5), new Point(Land.getMaxX()-15, Land.getMaxY()/2));	// Transport Center
		toPoints.put(new Integer(Land.Teleport6), new Point(1488, 1204));							// Jail
		toPoints.put(new Integer(Land.Teleport7), new Point(1276, 1273));							// Grand Hotel
		toPoints.put(new Integer(Land.Teleport8), new Point(1763, 2000));							// Bunker
		toPoints.put(new Integer(Land.Teleport9), new Point(1362, 1972));							// Palace
	} 

	public Teleport(int x, int y, int code) {
		super(x, y, code);
	}

	@Override
	public void step() {
		if(Land.getLand(getLocation()) != code){
			die();
			return;
		}
		Point point = getPoint();
		int land = Land.getLand(point);
		if (land == Land.Man || land == Land.Man_Grass) {
			IUnit unit = Engine.getEngine().findUnit(point);
			if(unit instanceof MovableUnit){
				int check = Land.getLand(toPoints.get(new Integer(code)));
				if(check == Land.Empty || check == Land.Grass){
					if(land == Land.Man)
						Land.setLand(point, Land.Empty);
					else
						Land.setLand(point, Land.Grass);
					if(check == Land.Empty)
						Land.setLand(toPoints.get(new Integer(code)), Land.Man);
					else
						Land.setLand(toPoints.get(new Integer(code)), Land.Man_Grass);
					
					unit.setLocation((Point)toPoints.get(new Integer(code)).clone());
					((MovableUnit) unit).setBeneath(check);
				}
			}
		}
	}

	private Point getPoint() {
		return new Point(getLocation().x, getLocation().y + 1);
	}
}
