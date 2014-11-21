package com.dworld.core;

import java.awt.Point;
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
	
	private static ArrayList<IUnit> selectedUnits = new ArrayList<IUnit>();
	
	private static Rectangle selectedArea = null;
	
	private static Point startLine = null, endLine = null;
	
	public static void addSelection(IUnit unit){
		if(!selectedUnits.contains(unit))
			selectedUnits.add(unit);
	}
	
	public static void clearSelection(){
		selectedArea = null;
		selectedUnits.clear();
		startLine = endLine = null;
	}
	
	public static Rectangle getSelectedArea(){
		return selectedArea;
	}
	
	public static void setSelectedArea(Rectangle rectangle){
		selectedArea = rectangle;
		startLine = endLine = null;
	}
	
	public static void setSelectedLine(Point start, Point end){
		selectedArea = null;
		selectedUnits.clear();
		startLine = start;
		endLine = end;
	}
	
	public static ArrayList<Point> getSelectedLine(){
		if(startLine != null){
			ArrayList<Point> line = new ArrayList<Point>();
			line.add(startLine);
			line.add(endLine);
			if(Math.abs(endLine.x - startLine.x) > Math.abs(endLine.y - startLine.y)){
				int steps = Math.abs(endLine.x - startLine.x);
				if(steps > 0){
					int beginX = startLine.x;
					int beginY = startLine.y;
					int xStep = (endLine.x - startLine.x)/steps;
					int yStep = (endLine.y - startLine.y)/steps;
					
					for(int index = 0; index < steps; index++){
						line.add(new Point(beginX+xStep*index, beginY+yStep*index));
					}
				}
			}else{
				int steps = Math.abs(endLine.y - startLine.y);
				if(steps > 0){
					int beginX = startLine.x;
					int beginY = startLine.y;
					int xStep = (endLine.x - startLine.x)/steps;
					int yStep = (endLine.y - startLine.y)/steps;
					
					for(int index = 0; index < steps; index++){
						line.add(new Point(beginX+xStep*index, beginY+yStep*index));
					}
				}
			}
			return line;
		}
		return null;
	}
	
	public static <T> void sendCommand(int commandId, T arg){
		for(IUnit selected: selectedUnits){
			selected.command(commandId, arg);
		}
	}
	
	public static void select(){
		if(selectedArea != null){
			for(int x = selectedArea.x; x < selectedArea.x + selectedArea.width; x++){
				for(int y = selectedArea.y; y < selectedArea.y + selectedArea.height; y++){
					Point point = new Point(x, y);
					IUnit unit = Engine.getEngine().findUnit(point);
					if(unit != null){
						addSelection(unit);
					}
				}
				
			}
		}
	}
	
	public static boolean sendDefaultCommand(Point location){
		IUnit unit = Engine.getEngine().findUnit(location);
		if(unit != null){
			unit.command(DEFAULT_COMMAND, null);
			clearSelection();
			return true;
		}
		return false;
	}

}
