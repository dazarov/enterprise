package com.dworld.units;

import java.util.HashMap;

import com.dworld.core.DWEngine;
import com.dworld.core.ISlow;
import com.dworld.core.IUnit;
import com.dworld.core.Land;
import com.dworld.core.Location;

public class Teleport extends ActiveUnit implements ISlow {
	private static HashMap<Integer, Location> toPoints = new HashMap<>();
	static{
		toPoints.put(new Integer(Land.Teleport1), new Location(Land.getMaxX()/2, Land.getMaxY()/2)); 	// Center Fort
		toPoints.put(new Integer(Land.Teleport2), new Location(Land.getMaxX()-25, 15));				// Safest Place
		toPoints.put(new Integer(Land.Teleport3), new Location(3353, 2053));							// Scientific Center
		toPoints.put(new Integer(Land.Teleport4), new Location(1232, 1690));							// Capital Palace
		toPoints.put(new Integer(Land.Teleport5), new Location(Land.getMaxX()-15, Land.getMaxY()/2));	// Transport Center
		toPoints.put(new Integer(Land.Teleport6), new Location(1488, 1204));							// Jail
		toPoints.put(new Integer(Land.Teleport7), new Location(1276, 1273));							// Grand Hotel
		toPoints.put(new Integer(Land.Teleport8), new Location(1485, 2128));							// Secret Bunker
		toPoints.put(new Integer(Land.Teleport9), new Location(1362, 1972));							// Palace
		toPoints.put(new Integer(Land.Teleport10), new Location(Land.getMaxX()/2, 5));					// Dark Knight Palace
		toPoints.put(new Integer(Land.Teleport11), new Location(1573, 1380));							// Military Base
		toPoints.put(new Integer(Land.Teleport12), new Location(2884, 1103));							// Island
		toPoints.put(new Integer(Land.Teleport13), new Location(0, 0));
		toPoints.put(new Integer(Land.Teleport14), new Location(0, 0));
		toPoints.put(new Integer(Land.Teleport15), new Location(0, 0));
	}
	
	private Location pointToGo;

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
		Location herePoint = getPoint();
		int hereCode = Land.getLand(herePoint);
		if (Land.heroList.contains(hereCode)) {
			IUnit unit = DWEngine.getEngine().findUnit(herePoint);
			if(unit instanceof MovableUnit){
				int thereCode = Land.getLand(pointToGo);
				if(thereCode == Land.Empty || thereCode == Land.Grass || thereCode == Land.Sand){
					Land.setLand(herePoint, ((MovableUnit) unit).getBeneath());
					
					Land.setLand(pointToGo, ((MovableUnit) unit).getCode(thereCode));
					
					unit.setLocation((Location)pointToGo);
					((MovableUnit) unit).setBeneath(thereCode);
				}
			}
		}
	}

	private Location getPoint() {
		return new Location(getLocation().getX(), getLocation().getY() + 1);
	}
}
