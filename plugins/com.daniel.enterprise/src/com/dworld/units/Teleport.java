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
		toPoints.put(new Integer(Land.Teleport1), new Point(Land.getMaxX()/2, Land.getMaxY()/2)); 	// Center Fort
		toPoints.put(new Integer(Land.Teleport2), new Point(Land.getMaxX()-25, 15));				// Safest Place
		toPoints.put(new Integer(Land.Teleport3), new Point(3353, 2053));							// Scientific Center
		toPoints.put(new Integer(Land.Teleport4), new Point(1232, 1690));							// Capital Palace
		toPoints.put(new Integer(Land.Teleport5), new Point(Land.getMaxX()-15, Land.getMaxY()/2));	// Transport Center
		toPoints.put(new Integer(Land.Teleport6), new Point(1488, 1204));							// Jail
		toPoints.put(new Integer(Land.Teleport7), new Point(1276, 1273));							// Grand Hotel
		toPoints.put(new Integer(Land.Teleport8), new Point(1763, 2000));							// Secret Bunker
		toPoints.put(new Integer(Land.Teleport9), new Point(1362, 1972));							// Palace
		toPoints.put(new Integer(Land.Teleport10), new Point(Land.getMaxX()/2, 5));					// Dark Knight Palace
		toPoints.put(new Integer(Land.Teleport11), new Point(1573, 1380));							// Military Base
		toPoints.put(new Integer(Land.Teleport12), new Point(3485, 1100));							// Island
		toPoints.put(new Integer(Land.Teleport13), new Point(0, 0));
		toPoints.put(new Integer(Land.Teleport14), new Point(0, 0));
		toPoints.put(new Integer(Land.Teleport15), new Point(0, 0));
	}
	
	private Point pointToGo;

	public Teleport(int x, int y, int code) {
		super(x, y, code);
		pointToGo = toPoints.get(new Integer(code));
	}

	@Override
	public void step() {
		if(Land.getLand(getLocation()) != code){
			die();
			return;
		}
		Point herePoint = getPoint();
		int hereCode = Land.getLand(herePoint);
		if (Land.heroList.contains(hereCode)) {
			IUnit unit = Engine.getEngine().findUnit(herePoint);
			if(unit instanceof MovableUnit){
				int thereCode = Land.getLand(pointToGo);
				if(thereCode == Land.Empty || thereCode == Land.Grass || thereCode == Land.Sand){
					Land.setLand(herePoint, ((MovableUnit) unit).getBeneath());
					
					Land.setLand(pointToGo, ((MovableUnit) unit).getCode(thereCode));
					
					unit.setLocation((Point)pointToGo.clone());
					((MovableUnit) unit).setBeneath(thereCode);
				}
			}
		}
	}

	private Point getPoint() {
		return new Point(getLocation().x, getLocation().y + 1);
	}
}
