package com.dworld.units;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.dworld.core.IUnit;
import com.dworld.core.Land;
import com.dworld.units.citizens.GoodBunker;
import com.dworld.units.citizens.GoodGeneral;
import com.dworld.units.citizens.GoodOfficer;
import com.dworld.units.citizens.GoodRadar;
import com.dworld.units.citizens.GoodSoldier;
import com.dworld.units.citizens.GoodTank;
import com.dworld.units.enemies.BadBunker;
import com.dworld.units.enemies.BadGeneral;
import com.dworld.units.enemies.BadOfficer;
import com.dworld.units.enemies.BadRadar;
import com.dworld.units.enemies.BadSoldier;
import com.dworld.units.enemies.BadTank;
import com.dworld.units.enemies.DarkKnight;
import com.dworld.units.railroad.Train;
import com.dworld.units.weapon.Mine;

public class UnitFactory {
	private static HashMap<Integer, Class<? extends Unit>> unitMap = new HashMap<Integer, Class<? extends Unit>>();
	static{
		unitMap.put(Land.Robot, BadSoldier.class);
		unitMap.put(Land.Robot_Grass, BadSoldier.class);
		unitMap.put(Land.Robot_Sand, BadSoldier.class);
		
		unitMap.put(Land.Gray_Officer, BadOfficer.class);
		unitMap.put(Land.Gray_Officer_Grass, BadOfficer.class);
		unitMap.put(Land.Gray_Officer_Sand, BadOfficer.class);

		unitMap.put(Land.Gray_General, BadGeneral.class);
		unitMap.put(Land.Gray_General_Grass, BadGeneral.class);
		unitMap.put(Land.Gray_General_Sand, BadGeneral.class);

		unitMap.put(Land.Dark_Knight, DarkKnight.class);
		unitMap.put(Land.Dark_Knight_Grass, DarkKnight.class);
		unitMap.put(Land.Dark_Knight_Sand, DarkKnight.class);

		unitMap.put(Land.Tank, BadTank.class);
		unitMap.put(Land.Tank_Grass, BadTank.class);
		unitMap.put(Land.Tank_Sand, BadTank.class);

		unitMap.put(Land.Bunker, BadBunker.class);
		unitMap.put(Land.Bunker_Grass, BadBunker.class);
		unitMap.put(Land.Bunker_Sand, BadBunker.class);

		unitMap.put(Land.Radar, BadRadar.class);
		unitMap.put(Land.Radar_Grass, BadRadar.class);
		unitMap.put(Land.Radar_Sand, BadRadar.class);

		unitMap.put(Land.Mine, Mine.class);
		unitMap.put(Land.Mine_Grass, Mine.class);
		unitMap.put(Land.Mine_Sand, Mine.class);

		unitMap.put(Land.Soldier, GoodSoldier.class);
		unitMap.put(Land.Soldier_Grass, GoodSoldier.class);
		unitMap.put(Land.Soldier_Sand, GoodSoldier.class);

//		unitMap.put(Land.Peasant, Peasant.class);
//		unitMap.put(Land.Peasant_Grass, Peasant.class);
//		unitMap.put(Land.Peasant_Sand, Peasant.class);

		unitMap.put(Land.Officer, GoodOfficer.class);
		unitMap.put(Land.Officer_Grass, GoodOfficer.class);
		unitMap.put(Land.Officer_Sand, GoodOfficer.class);

		unitMap.put(Land.General, GoodGeneral.class);
		unitMap.put(Land.General_Grass, GoodGeneral.class);
		unitMap.put(Land.General_Sand, GoodGeneral.class);

		unitMap.put(Land.GoodTank, GoodTank.class);
		unitMap.put(Land.GoodTank_Grass, GoodTank.class);
		unitMap.put(Land.GoodTank_Sand, GoodTank.class);

		unitMap.put(Land.GoodRadar, GoodRadar.class);
		unitMap.put(Land.GoodRadar_Grass, GoodRadar.class);
		unitMap.put(Land.GoodRadar_Sand, GoodRadar.class);

		unitMap.put(Land.GoodBunker, GoodBunker.class);
		unitMap.put(Land.GoodBunker_Grass, GoodBunker.class);
		unitMap.put(Land.GoodBunker_Sand, GoodBunker.class);

		unitMap.put(Land.Teleport1, Teleport.class);
		unitMap.put(Land.Teleport2, Teleport.class);
		unitMap.put(Land.Teleport3, Teleport.class);
		unitMap.put(Land.Teleport4, Teleport.class);
		unitMap.put(Land.Teleport5, Teleport.class);
		unitMap.put(Land.Teleport6, Teleport.class);
		unitMap.put(Land.Teleport7, Teleport.class);
		unitMap.put(Land.Teleport8, Teleport.class);
		unitMap.put(Land.Teleport9, Teleport.class);
		
		unitMap.put(Land.Train_Vertical, Train.class);
		unitMap.put(Land.Train_Horizontal, Train.class);
		unitMap.put(Land.Train_Diagonal_Up, Train.class);
		unitMap.put(Land.Train_Diagonal_Down, Train.class);
		unitMap.put(Land.Train_Up_Right, Train.class);
		unitMap.put(Land.Train_Up_Left, Train.class);
		unitMap.put(Land.Train_Down_Right, Train.class);
		unitMap.put(Land.Train_Down_Left, Train.class);
		unitMap.put(Land.Train_Right_Up, Train.class);
		unitMap.put(Land.Train_Right_Down, Train.class);
		unitMap.put(Land.Train_Left_Up, Train.class);
		unitMap.put(Land.Train_Left_Down, Train.class);
		
//		unitMap.put(Land.Car_Vertical, Car.class);
//		unitMap.put(Land.Car_Horizontal, Car.class);
//		unitMap.put(Land.Car_Diagonal_Up, Car.class);
//		unitMap.put(Land.Car_Diagonal_Down, Car.class);
//		unitMap.put(Land.Car_Up_Right, Car.class);
//		unitMap.put(Land.Car_Up_Left, Car.class);
//		unitMap.put(Land.Car_Down_Right, Car.class);
//		unitMap.put(Land.Car_Down_Left, Car.class);
//		unitMap.put(Land.Car_Right_Up, Car.class);
//		unitMap.put(Land.Car_Right_Down, Car.class);
//		unitMap.put(Land.Car_Left_Up, Car.class);
//		unitMap.put(Land.Car_Left_Down, Car.class);
		
		unitMap.put(Land.OpenedHorizontalSteelGate, Gate.class);
		unitMap.put(Land.ClosedHorizontalSteelGate, Gate.class);
		unitMap.put(Land.OpenedVerticalSteelGate, Gate.class);
		unitMap.put(Land.ClosedVerticalSteelGate, Gate.class);
		unitMap.put(Land.OpenedHorizontalWoodGate, Gate.class);
		unitMap.put(Land.ClosedHorizontalWoodGate, Gate.class);
		unitMap.put(Land.OpenedVerticalWoodGate, Gate.class);
		unitMap.put(Land.ClosedVerticalWoodGate, Gate.class);
		unitMap.put(Land.OpenedHorizontalConcreteGate, Gate.class);
		unitMap.put(Land.ClosedHorizontalConcreteGate, Gate.class);
		unitMap.put(Land.OpenedVerticalConcreteGate, Gate.class);
		unitMap.put(Land.ClosedVerticalConcreteGate, Gate.class);
		unitMap.put(Land.OpenedHorizontalBrickGate, Gate.class);
		unitMap.put(Land.ClosedHorizontalBrickGate, Gate.class);
		unitMap.put(Land.OpenedVerticalBrickGate, Gate.class);
		unitMap.put(Land.ClosedVerticalBrickGate, Gate.class);
	};
	
	public static IUnit createUnit(int code, int x, int y){
		Unit unit = null;
		Class<? extends Unit> unitClass = unitMap.get(code);
		if(unitClass != null){
			Constructor<? extends Unit> constructor = null;
			
			try {
				constructor = unitClass.getConstructor(new Class<?> []{int.class, int.class, int.class});
				unit = constructor.newInstance(new Object[]{x, y, code});
			} catch (Exception e) {
				System.out.println("Could not create instance of "+unitClass.toString());
			}
		}
		
		return unit;
	}
}
