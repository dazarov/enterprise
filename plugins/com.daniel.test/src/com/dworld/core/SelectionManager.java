package com.dworld.core;

import java.awt.Rectangle;
import java.util.ArrayList;

public class SelectionManager {
	// commands
	
	public static final int DEFAULT_COMMAND		= 0;
	public static final int ACTIVATE_COMMAND	= 1;
	public static final int DEACTIVATE_COMMAND	= 2;
	
	public static final int STAY_COMMAND		= 3;
	public static final int MOVE_AROUND_COMMAND = 4;
	public static final int MOVE_TO_COMMAND		= 5;
	public static final int ATTACK_COMMAND		= 6;
	public static final int PATROL_COMMAND		= 7;
	public static final int DEFENSE_COMMAND		= 8;
	
	// open then deactivate
	public static final int OPEN_COMMAND		= 9;
	
	// close then deactivate
	public static final int CLOSE_COMMAND		= 10;
	
	private static ArrayList<IActive> selectedUnits = new ArrayList<IActive>();
	
	private static Rectangle selectedArea = null;
	
	public static void addSelection(IActive unit){
		if(!selectedUnits.contains(unit))
			selectedUnits.add(unit);
	}
	
	public static void clearSelection(){
		selectedUnits.clear();
	}
	
	public static Rectangle getSelectedArea(){
		return selectedArea;
	}
	
	public static void setSelectedArea(Rectangle rectangle){
		selectedArea = rectangle;
	}
	
	public static <T> void sendCommand(int commandId, T arg){
		for(IActive selected: selectedUnits){
			selected.command(commandId, arg);
		}
	}

}
