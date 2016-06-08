package com.dworld.core;

import java.awt.Rectangle;
import java.util.ArrayList;

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
						IUnit unit = DWConfiguration.getInstance().getEngine().findUnit(point);
						if(unit != null){
							addSelection(unit);
						}
					}
					
				}
			}
		}
	}
	
	public static boolean sendDefaultCommand(Location location){
		IUnit unit = DWConfiguration.getInstance().getEngine().findUnit(location);
		if(unit != null){
			unit.command(Unit.EXTERNAL_COMMAND_DEFAULT, null);
			clearSelection();
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
						Land.setLand(x, y, Land.Empty);
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
						int code = Land.getLand(x, y);
						Land.setLand(newX, newY, code);
						newY++;
					}
					newY = copyTo.getY();
					newX++;
				}
				selectedArea.width = copiedArea.width;
				selectedArea.height = copiedArea.height;
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}
	
	public static void turnRight(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				ArrayList<Location> doneList = new ArrayList<Location>();
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						Location point1 = new Location(x,y);
						Location point2 = new Location(
							selectedArea.x + (selectedArea.height - (y-selectedArea.y))-1,
							selectedArea.y + (x-selectedArea.x)
						);
						
						if(!doneList.contains(point1)){
							doneList.add(point2);
							int code1 = Land.getTurnedLand(point1);
							int code2 = Land.getTurnedLand(point2);
							Land.setLand(point1, code2);
							Land.setLand(point2, code1);
						}
					}
				}
				int width = selectedArea.width;
				selectedArea.width = selectedArea.height;
				selectedArea.height = width;
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}
	
	public static void turnLeft(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				ArrayList<Location> doneList = new ArrayList<Location>();
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						Location point1 = new Location(x,y);
						Location point2 = new Location(
							selectedArea.x + (y-selectedArea.y),
							selectedArea.y + (selectedArea.width - (x-selectedArea.x))-1
						);
						
						if(!doneList.contains(point1)){
							doneList.add(point2);
							int code1 = Land.getTurnedLand(point1);
							int code2 = Land.getTurnedLand(point2);
							Land.setLand(point1, code2);
							Land.setLand(point2, code1);
						}
					}
				}
				int width = selectedArea.width;
				selectedArea.width = selectedArea.height;
				selectedArea.height = width;
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}

	public static void flipVertically(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < (selectedArea.y+selectedArea.height/2); y++){
						int code1 = Land.getLand(x, y);
						int y2 = (selectedArea.y + selectedArea.height-1)-(y-selectedArea.y);
						int code2 = Land.getLand(x, y2);
						Land.setLand(x, y, code2);
						Land.setLand(x, y2, code1);
					}
				}
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}
	
	public static void flipHorizontally(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < (selectedArea.x+selectedArea.width/2); x++){
					for(int y = selectedArea.y; y < (selectedArea.y+selectedArea.height); y++){
						int code1 = Land.getLand(x, y);
						int x2 = (selectedArea.x + selectedArea.width-1)-(x-selectedArea.x);
						int code2 = Land.getLand(x2, y);
						Land.setLand(x, y, code2);
						Land.setLand(x2, y, code1);
					}
				}
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}
	
	public static void moveUp(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
					for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
						int code1 = Land.getLand(x, y);
						int y2 = y - 1;
						int code2 = Land.getLand(x, y2);
						Land.setLand(x, y, code2);
						Land.setLand(x, y2, code1);
					}
				}
				selectedArea.y = selectedArea.y - 1;
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}

	public static void moveDown(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int y = selectedArea.y+selectedArea.height-1; y >= selectedArea.y; y--){
					for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
						int code1 = Land.getLand(x, y);
						int y2 = y + 1;
						int code2 = Land.getLand(x, y2);
						Land.setLand(x, y, code2);
						Land.setLand(x, y2, code1);
					}
				}
				selectedArea.y = selectedArea.y + 1;
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}
	
	public static void moveLeft(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x; x < selectedArea.x+selectedArea.width; x++){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						int code1 = Land.getLand(x, y);
						int x2 = x - 1;
						int code2 = Land.getLand(x2, y);
						Land.setLand(x, y, code2);
						Land.setLand(x2, y, code1);
					}
				}
				selectedArea.x = selectedArea.x - 1;
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}
	
	public static void moveRight(){
		synchronized(SelectionManager.class){
			if(selectedArea != NULL_RECTANGLE){
				for(int x = selectedArea.x+selectedArea.width-1; x >= selectedArea.x; x--){
					for(int y = selectedArea.y; y < selectedArea.y+selectedArea.height; y++){
						int code1 = Land.getLand(x, y);
						int x2 = x + 1;
						int code2 = Land.getLand(x2, y);
						Land.setLand(x, y, code2);
						Land.setLand(x2, y, code1);
					}
				}
				selectedArea.x = selectedArea.x + 1;
				DWConfiguration.getInstance().getLauncher().setModified();
			}
		}
	}
}
