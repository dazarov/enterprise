package com.dworld.core;

import java.lang.reflect.Constructor;
import java.util.EnumMap;
import java.util.Map;

import com.dworld.units.Gate;
import com.dworld.units.Teleport;
import com.dworld.units.Unit;
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
import com.dworld.units.railroad.WarTrain;
import com.dworld.units.weapon.Mine;

public class DWUnitFactory {
	private static Map<Land, Class<? extends Unit>> unitMap = new EnumMap<>(Land.class);
	static{
		unitMap.put(Land.BadSoldier, BadSoldier.class);
		
		unitMap.put(Land.BadOfficer, BadOfficer.class);

		unitMap.put(Land.BadGeneral, BadGeneral.class);

		unitMap.put(Land.Dark_Knight, DarkKnight.class);

		unitMap.put(Land.BadTank, BadTank.class);

		unitMap.put(Land.BadBunker, BadBunker.class);

		unitMap.put(Land.BadRadar, BadRadar.class);

		unitMap.put(Land.Mine, Mine.class);

		unitMap.put(Land.GoodSoldier, GoodSoldier.class);

		unitMap.put(Land.GoodOfficer, GoodOfficer.class);

		unitMap.put(Land.GoodGeneral, GoodGeneral.class);

		unitMap.put(Land.GoodTank, GoodTank.class);

		unitMap.put(Land.GoodRadar, GoodRadar.class);

		unitMap.put(Land.GoodBunker, GoodBunker.class);

		unitMap.put(Land.Teleport1, Teleport.class);
		unitMap.put(Land.Teleport2, Teleport.class);
		unitMap.put(Land.Teleport3, Teleport.class);
		unitMap.put(Land.Teleport4, Teleport.class);
		unitMap.put(Land.Teleport5, Teleport.class);
		unitMap.put(Land.Teleport6, Teleport.class);
		unitMap.put(Land.Teleport7, Teleport.class);
		unitMap.put(Land.Teleport8, Teleport.class);
		unitMap.put(Land.Teleport9, Teleport.class);
		unitMap.put(Land.Teleport10, Teleport.class);
		unitMap.put(Land.Teleport11, Teleport.class);
		unitMap.put(Land.Teleport12, Teleport.class);
		unitMap.put(Land.Teleport13, Teleport.class);
		unitMap.put(Land.Teleport14, Teleport.class);
		unitMap.put(Land.Teleport15, Teleport.class);
		
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
		unitMap.put(Land.Train_Vertical_Cross, Train.class);
		unitMap.put(Land.Train_Horizontal_Cross, Train.class);
		unitMap.put(Land.Train_Diagonal_Up_Cross, Train.class);
		unitMap.put(Land.Train_Diagonal_Down_Cross, Train.class);

		unitMap.put(Land.WarTrain_Vertical, WarTrain.class);
		unitMap.put(Land.WarTrain_Horizontal, WarTrain.class);
		unitMap.put(Land.WarTrain_Diagonal_Up, WarTrain.class);
		unitMap.put(Land.WarTrain_Diagonal_Down, WarTrain.class);
		unitMap.put(Land.WarTrain_Up_Right, WarTrain.class);
		unitMap.put(Land.WarTrain_Up_Left, WarTrain.class);
		unitMap.put(Land.WarTrain_Down_Right, WarTrain.class);
		unitMap.put(Land.WarTrain_Down_Left, WarTrain.class);
		unitMap.put(Land.WarTrain_Right_Up, WarTrain.class);
		unitMap.put(Land.WarTrain_Right_Down, WarTrain.class);
		unitMap.put(Land.WarTrain_Left_Up, WarTrain.class);
		unitMap.put(Land.WarTrain_Left_Down, WarTrain.class);
		unitMap.put(Land.WarTrain_Vertical_Cross, WarTrain.class);
		unitMap.put(Land.WarTrain_Horizontal_Cross, WarTrain.class);
		unitMap.put(Land.WarTrain_Diagonal_Up_Cross, WarTrain.class);
		unitMap.put(Land.WarTrain_Diagonal_Down_Cross, WarTrain.class);
		
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
	
	public static IUnit createUnit(Land land, int x, int y){
		Unit unit = null;
		Class<? extends Unit> unitClass = unitMap.get(land);
		if(unitClass != null){
			Constructor<? extends Unit> constructor = null;
			
			try {
				constructor = unitClass.getConstructor(new Class<?> []{int.class, int.class, Land.class});
				unit = constructor.newInstance(new Object[]{x, y, land});
			} catch (Exception e) {
				System.out.println("Could not create instance of "+unitClass.toString());
				e.printStackTrace();
			}
		}
		
		return unit;
	}
}
