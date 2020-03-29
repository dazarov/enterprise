package com.dworld.units;

import java.util.HashMap;

import com.dworld.core.DWConfiguration;
import com.dworld.core.ISlow;
import com.dworld.core.IUnit;
import com.dworld.core.Land;
import com.dworld.core.Location;

public class Teleport extends ActiveUnit implements ISlow {
	private static HashMap<Land, Location> toPoints = new HashMap<>();
	static{
		toPoints.put(Land.Teleport1, new Location(Land.getMaxX()/2, Land.getMaxY()/2)); 	// Center Fort
		toPoints.put(Land.Teleport2, new Location(Land.getMaxX()-25, 15));				// Safest Place
		toPoints.put(Land.Teleport3, new Location(3353, 2053));							// Scientific Center
		toPoints.put(Land.Teleport4, new Location(1232, 1690));							// Capital Palace
		toPoints.put(Land.Teleport5, new Location(Land.getMaxX()-15, Land.getMaxY()/2));	// Transport Center
		toPoints.put(Land.Teleport6, new Location(1488, 1204));							// Jail
		toPoints.put(Land.Teleport7, new Location(1276, 1273));							// Grand Hotel
		toPoints.put(Land.Teleport8, new Location(1485, 2128));							// Secret Bunker
		toPoints.put(Land.Teleport9, new Location(1362, 1972));							// Palace
		toPoints.put(Land.Teleport10, new Location(Land.getMaxX()/2, 5));					// Dark Knight Palace
		toPoints.put(Land.Teleport11, new Location(1573, 1380));							// Military Base
		toPoints.put(Land.Teleport12, new Location(2884, 1103));							// Island
		toPoints.put(Land.Teleport13, new Location(0, 0));
		toPoints.put(Land.Teleport14, new Location(0, 0));
		toPoints.put(Land.Teleport15, new Location(0, 0));
	}
	
	private Location pointToGo;

	public Teleport(int x, int y, Land land) {
		super(x, y, land);
		pointToGo = toPoints.get(land);
	}

	@Override
	public void step() {
		if(Land.getLand(getLocation()) != land){
			die();
			return;
		}
		Location herePoint = getPoint();
		Land hereLand = Land.getLand(herePoint);
		if (Land.heroList.contains(hereLand)) {
			IUnit unit = DWConfiguration.getInstance().getEngine().findUnit(herePoint).get(0);
			if(unit instanceof MovableUnit){
				Land thereLand = Land.getLand(pointToGo);
				if(thereLand == Land.Empty || thereLand == Land.Grass || thereLand == Land.Sand){
					Land.setLand(herePoint, ((MovableUnit) unit).getBeneath());
					
					Land.setLand(pointToGo, ((MovableUnit) unit).getLand());
					
					unit.setLocation((Location)pointToGo);
					((MovableUnit) unit).setBeneath(thereLand);
				}
			}
		}
	}

	private Location getPoint() {
		return getLocation().move(0, 1);
	}
}
