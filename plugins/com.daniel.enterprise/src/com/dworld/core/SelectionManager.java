package com.dworld.core;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dworld.units.Unit;

public class SelectionManager {
	
	
	private static ArrayList<IUnit> selectedUnits = new ArrayList<IUnit>();
	
	public static final Rectangle NULL_RECTANGLE = new Rectangle(0,0,0,0);
	
	private static Rectangle selectedArea = NULL_RECTANGLE, copiedArea = NULL_RECTANGLE;
	
	private static Location startLine = null, endLine = null;
	
	public static void addSelection(IUnit unit){
		if(!selectedUnits.contains(unit))
			selectedUnits.add(unit);
	}
	
	public static void clearSelection(){
		synchronized(SelectionManager.class){
			selectedArea = NULL_RECTANGLE;
			selectedUnits.clear();
			startLine = endLine = null;
		}
	}
	
	public static Rectangle getSelectedArea(){
		synchronized(SelectionManager.class){
			return (Rectangle)selectedArea.clone();
		}
	}
	
	public static void setSelectedArea(Rectangle rectangle){
		if(rectangle == null){
			throw new IllegalArgumentException("Rectangle must not be null");
		}
		synchronized(SelectionManager.class){
			selectedArea = rectangle;
			startLine = endLine = null;
		}
	}
	
	public static void setSelectedLine(Location start, Location end){
		synchronized(SelectionManager.class){
			selectedArea = NULL_RECTANGLE;
			selectedUnits.clear();
			startLine = start;
			endLine = end;
		}
	}
	
	public static ArrayList<Location> getSelectedLine(){
		return getSelectedLineInternal(startLine, endLine);
	}
	
	private static ArrayList<Location> getSelectedLineInternal(Location start, Location end){
		if(start != null){
			ArrayList<Location> line = new ArrayList<Location>();
			line.add(start);
			line.add(end);
			if(Math.abs(end.getX() - start.getX()) > Math.abs(end.getY() - start.getY())){
				int steps = Math.abs(end.getX() - start.getX());
				if(steps > 0){
					int beginX = start.getX();
					int beginY = start.getY();
					int xStep = (end.getX() - start.getX())/steps;
					int yStep = (end.getY() - start.getY())/steps;
					
					for(int index = 0; index < steps; index++){
						line.add(new Location(beginX+xStep*index, beginY+yStep*index));
					}
				}
			}else{
				int steps = Math.abs(end.getY() - start.getY());
				if(steps > 0){
					int beginX = start.getX();
					int beginY = start.getY();
					int xStep = (end.getX() - start.getX())/steps;
					int yStep = (end.getY() - start.getY())/steps;
					
					for(int index = 0; index < steps; index++){
						line.add(new Location(beginX+xStep*index, beginY+yStep*index));
					}
				}
			}
			return line;
		}
		return null;
	}
	
	public static void sendCommand(int commandId, Object[] args){
		for(IUnit selected: selectedUnits){
			selected.command(commandId, args);
		}
	}
	
	public static void select(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < selectedArea.x + selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y + selectedArea.height; y++){
						Location point = new Location(x, y);
						List<IUnit> list = DWConfiguration.getInstance().getEngine().findUnit(point);
						if(list != null){
							for(IUnit unit : list){
								addSelection(unit);
							}
						}
					}
					
				}
			}
		}
	}
	
	public static boolean sendDefaultCommand(Location location){
		List<IUnit> list = DWConfiguration.getInstance().getEngine().findUnit(location);
		if(list != null){
			for(IUnit unit : list){
				unit.command(Unit.EXTERNAL_COMMAND_DEFAULT, null);
				clearSelection();
			}
			return true;
		}
		return false;
	}
	
	public static void modifySelection(int deltaX, int deltaY, int deltaWidth, int deltaHeight){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				selectedArea.x += deltaX;
				if(selectedArea.x < 0){
					selectedArea.x = 0;
				}
	
				selectedArea.y += deltaY;
				if(selectedArea.y < 0){
					selectedArea.y = 0;
				}
				
				selectedArea.width += deltaWidth;
				if(selectedArea.width < 0){
					selectedArea.width = 0;
				}
				
				selectedArea.height += deltaHeight;
				if(selectedArea.height < 0){
					selectedArea.height = 0;
				}
			}
		}
	}
	
	
	public static void copy(){
		synchronized(SelectionManager.class){
			copiedArea = selectedArea;
		}
	}

	public static void delete(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < selectedArea.x + selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y + selectedArea.height; y++){
						Land.setBackground(x, y, Land.Empty);
						Land.setForeground(x, y, Land.Empty);
					}
					
				}
			}
		}
	}

	public static void paste(){
		synchronized(SelectionManager.class){
			if(copiedArea != NULL_RECTANGLE && selectedArea != null && !copiedArea.intersects(selectedArea)){
				Location copyTo = new Location(selectedArea.x, selectedArea.y);
				int newX = copyTo.getX(), newY = copyTo.getY();
				
				for(int x = copiedArea.x; x < copiedArea.x+copiedArea.width; x++){
					for(int y = copiedArea.y; y < copiedArea.y+copiedArea.height; y++){
						Land land = Land.getLand(x, y);
						Land.setForeground(newX, newY, land);
						newY++;
					}
					newY = copyTo.getY();
					newX++;
				}
				selectedArea.width = copiedArea.width;
				selectedArea.height = copiedArea.height;
				Land.modified();
			}
		}
	}
	
	public static void turnRight(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				HashMap<Location, Land> doneMap = new HashMap<>();
				
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						Location point1 = new Location(x,y);
						Land land1 = doneMap.get(point1);
						if(land1 == null){
							land1 = Land.getTurnedLand(point1);
						}
						Location point2 = new Location(
							selectedArea.x + (selectedArea.height - 1 - (y - selectedArea.y)),
							selectedArea.y + (x - selectedArea.x)
						);
						
						Land land2 = Land.getTurnedLand(point2);
						if(selectedArea.contains(new Point(point2.getX(), point2.getY())) &&
								getLocationWeight(point1) < getLocationWeight(point2)){
							doneMap.put(point2, land2);
						}
						
						Land.setForeground(point2, land1);
					}
				}
				
				int width = selectedArea.width;
				selectedArea.width = selectedArea.height;
				selectedArea.height = width;
				Land.modified();
			}
		}
	}
	
	public static void turnLeft(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				HashMap<Location, Land> doneMap = new HashMap<>();
				
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						Location point1 = new Location(x,y);
						Land land1 = doneMap.get(point1);
						if(land1 == null){
							land1 = Land.getTurnedLand(point1);
						}
						Location point2 = new Location(
								selectedArea.x + (y-selectedArea.y),
								selectedArea.y + (selectedArea.width - 1 - (x-selectedArea.x))
							);
						
						Land land2 = Land.getTurnedLand(point2);
						if(selectedArea.contains(new Point(point2.getX(), point2.getY())) &&
								getLocationWeight(point1) < getLocationWeight(point2)){
							doneMap.put(point2, land2);
						}
						
						Land.setForeground(point2, land1);
					}
				}

				int width = selectedArea.width;
				selectedArea.width = selectedArea.height;
				selectedArea.height = width;
				Land.modified();
			}
		}
	}

	public static void flipVertically(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < (selectedArea.y+selectedArea.height/2); y++){
						Land land1 = Land.getLand(x, y);
						int y2 = (selectedArea.y + selectedArea.height-1)-(y-selectedArea.y);
						Land land2 = Land.getLand(x, y2);
						Land.setForeground(x, y, land2);
						Land.setForeground(x, y2, land1);
					}
				}
				Land.modified();
			}
		}
	}
	
	public static void flipHorizontally(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < (selectedArea.x+selectedArea.width/2); x++){
					for(int y = selectedArea.y; y < (selectedArea.y+selectedArea.height); y++){
						Land land1 = Land.getLand(x, y);
						int x2 = (selectedArea.x + selectedArea.width-1)-(x-selectedArea.x);
						Land land2 = Land.getLand(x2, y);
						Land.setForeground(x, y, land2);
						Land.setForeground(x2, y, land1);
					}
				}
				Land.modified();
			}
		}
	}
	
	public static void moveUp(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
					for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
						Land land1 = Land.getLand(x, y);
						int y2 = y - 1;
						Land land2 = Land.getLand(x, y2);
						Land.setForeground(x, y, land2);
						Land.setForeground(x, y2, land1);
					}
				}
				selectedArea.y = selectedArea.y - 1;
				Land.modified();
			}
		}
	}

	public static void moveDown(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int y = selectedArea.y+selectedArea.height-1; y >= selectedArea.y; y--){
					for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
						Land land1 = Land.getLand(x, y);
						int y2 = y + 1;
						Land land2 = Land.getLand(x, y2);
						Land.setForeground(x, y, land2);
						Land.setForeground(x, y2, land1);
					}
				}
				selectedArea.y = selectedArea.y + 1;
				Land.modified();
			}
		}
	}
	
	public static void moveLeft(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						Land land1 = Land.getLand(x, y);
						int x2 = x - 1;
						Land land2 = Land.getLand(x2, y);
						Land.setForeground(x, y, land2);
						Land.setForeground(x2, y, land1);
					}
				}
				selectedArea.x = selectedArea.x - 1;
				Land.modified();
			}
		}
	}
	
	public static void moveRight(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x+selectedArea.width-1; x >= selectedArea.x; x--){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						Land land1 = Land.getLand(x, y);
						int x2 = x + 1;
						Land land2 = Land.getLand(x2, y);
						Land.setForeground(x, y, land2);
						Land.setForeground(x2, y, land1);
					}
				}
				selectedArea.x = selectedArea.x + 1;
				Land.modified();
			}
		}
	}
	
	public static void fill(int x, int y, Land oldLand, Land newLand){
		if(SelectionManager.getSelectedArea() != SelectionManager.NULL_RECTANGLE && SelectionManager.getSelectedArea().contains(x,y)){
			for(int x1 = SelectionManager.getSelectedArea().x; x1 < SelectionManager.getSelectedArea().x + SelectionManager.getSelectedArea().width; x1++){
				for(int y1 = SelectionManager.getSelectedArea().y; y1 < SelectionManager.getSelectedArea().getY() + SelectionManager.getSelectedArea().height; y1++){
					Land land = Land.getLand(x1, y1);
					if(land == oldLand){
						Land.setForeground(x1, y1, newLand);
					}
				}
				
			}
			return;
		}
		if(oldLand == newLand){
			return;
		}
		int startX = x;
		int startY = y;
		while(Land.getLand(startX, startY) == oldLand){
			startY--;
		}
		startY++;
		startX++;
		int xx = startX;
		int yy = startY;
		while(true){
			while(Land.getLand(xx, yy) == oldLand){
				Land.setForeground(xx, yy, newLand);
				xx++;
			}
			xx = startX-1;
			while(Land.getLand(xx, yy) == oldLand){
				Land.setForeground(xx, yy, newLand);
				xx--;
			}
			xx = startX;
			
			yy++;
			if(Land.getLand(xx, yy) != oldLand){
				return;
			}
		}
	}
	
	private static int getLocationWeight(Location location){
		return location.getX() * 5000 + location.getY();
	}
	
//	private void fill(int x, int y, int oldCode, int newCode){
//		if(oldCode == newCode){
//			return;
//		}
//		while(true){
//			if(Land.getLand(x, y) != oldCode){
//				return;
//			}
//			int startX = x;
//			int startY = y;
//			while(Land.getLand(startX, startY) == oldCode){
//				startY--;
//			}
//			startY++;
//			while(Land.getLand(startX, startY) == oldCode){
//				startX--;
//			}
//			startX++;
//			Direction direction = Direction.east;
//			Point location = new Point(startX, startY);
//			while(true){
//				Land.setLand(location, newCode);
//				direction = findDirection(location, oldCode, newCode, direction);
//				if(direction == Direction.nowhere){
//					break;
//				}
//				location = Land.getNewLocation(location, direction);
//			}
//		}
//	}

}
