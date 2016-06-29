package com.dworld.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.DWUnitFactory;
import com.dworld.core.IUnit;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.core.SelectionManager;
import com.dworld.units.Unit;

public class DWMouseListener {
	Location selectedPoint = null;
	
	private Location getLocation(int mouseX, int mouseY){
		return new Location(
			DWConfiguration.getInstance().getUI().getImages().getX() + (mouseX - 3) / DWConstants.UI_IMAGE_WIDTH,
			DWConfiguration.getInstance().getUI().getImages().getY() + mouseY / DWConstants.UI_IMAGE_HEIGHT
		);
	}

	public void doMousePressed(int button, int mouseX, int mouseY) {
		Location location = getLocation(mouseX, mouseY);

		if (!DWConfiguration.getInstance().isBuildMode()) { // selection mode
			if (button == MouseEvent.BUTTON1) {
				SelectionManager.clearSelection();
				selectedPoint = location;
				SelectionManager.setSelectedArea(new Rectangle(selectedPoint.getX(), selectedPoint.getY(), 1, 1));
			} else {
				if(!SelectionManager.sendDefaultCommand(location)){
					if(DWConfiguration.getInstance().isAttackMode()){
						SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_ATTACK, new Object[]{location});
					}else{
						SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_MOVE_TO, new Object[]{location});
					}
				}
				
				selectedPoint = null;
				SelectionManager.setSelectedArea(SelectionManager.NULL_RECTANGLE);
			}
			return;
		}else if(isRect()){
			SelectionManager.clearSelection();
			selectedPoint = location;
			SelectionManager.setSelectedArea(new Rectangle(selectedPoint.getX(), selectedPoint.getY(), 1, 1));
		}else if(isLine()){
				SelectionManager.clearSelection();
				selectedPoint = location;
				SelectionManager.setSelectedLine(selectedPoint, selectedPoint);
		}else if(isBrush()){
			if (button == MouseEvent.BUTTON1){
				Land.setLand(location, DWConfiguration.getInstance().getSelectedMenu());
				DWUnitFactory.createUnit(DWConfiguration.getInstance().getSelectedMenu(), location.getX(), location.getY());
			}else{
				IUnit unit = DWConfiguration.getInstance().getEngine().findUnit(location);
				if(unit != null){
					unit.die();
				}
				Land.setLand(location, Land.Empty);
			}
			DWConfiguration.getInstance().getLauncher().setModified();
		}
	}
	
	public boolean isRect(){
		return DWConfiguration.getInstance().getDrawMode() == DWConfiguration.DRAW_RECTANGLE;
	}
	
	public boolean isLine(){
		return DWConfiguration.getInstance().getDrawMode() == DWConfiguration.DRAW_LINE;
	}
	
	public boolean isBrush(){
		return DWConfiguration.getInstance().getDrawMode() == DWConfiguration.DRAW_BRUSH;
	}
	
	public boolean isFill(){
		return DWConfiguration.getInstance().getDrawMode() == DWConfiguration.DRAW_FILL;
	}

	public void doMouseReleased(int button, int mouseX, int mouseY) {
		if(DWConfiguration.getInstance().isBuildMode()){
			if(isRect()){
				Rectangle rectangle = SelectionManager.getSelectedArea();
				if(rectangle == SelectionManager.NULL_RECTANGLE)
					return;
				for(int x = rectangle.x; x < rectangle.x+rectangle.width; x++){
					for(int y = rectangle.y; y < rectangle.y+rectangle.height; y++){
						if (button == MouseEvent.BUTTON1)
							Land.setLand(new Location(x, y), DWConfiguration.getInstance().getSelectedMenu());
						else
							Land.setLand(new Location(x, y), Land.Empty);
					}
				}
				DWConfiguration.getInstance().getLauncher().setModified();
			}else if(isLine()){
				ArrayList<Location> points = SelectionManager.getSelectedLine();
				if(points != null){
					for(Location point : points){
						if (button == MouseEvent.BUTTON1){
							Land.setLand(point, DWConfiguration.getInstance().getSelectedMenu());
						}else{
							Land.setLand(point, Land.Empty);
						}
					}
				}
				DWConfiguration.getInstance().getLauncher().setModified();
			}else if(isFill()){
				Location location = getLocation(mouseX, mouseY);
				int oldCode = Land.getLand(location);
				if (button == MouseEvent.BUTTON1){
					SelectionManager.fill(location.getX(), location.getY(), oldCode, DWConfiguration.getInstance().getSelectedMenu());
				}else{
					SelectionManager.fill(location.getX(), location.getY(), oldCode, Land.Empty);
				}
				
				DWConfiguration.getInstance().getLauncher().setModified();
			}
			SelectionManager.clearSelection();
		}
		if(!DWConfiguration.getInstance().isBuildMode()){
			SelectionManager.select();
		}
	}
	
	int lastX = 0, lastY = 0;

	public void doMouseDragged(int button, int mouseX, int mouseY) {

		Location location = getLocation(mouseX, mouseY);
		if (lastX == location.getX() && lastY == location.getY())
			return;
		lastX = location.getX();
		lastY = location.getY();
		if (!DWConfiguration.getInstance().isBuildMode() || isRect()) {
			if (selectedPoint != null) {
				int topX, topY, width, height;

				if (location.getX() < selectedPoint.getX()) {
					topX = location.getX();
					width = selectedPoint.getX() - location.getX() + 1;
				} else if (location.getX() == selectedPoint.getX()) {
					topX = location.getX();
					width = 1;
				} else {
					topX = selectedPoint.getX();
					width = location.getX() - selectedPoint.getX() + 1;
				}

				if (location.getY() < selectedPoint.getY()) {
					topY = location.getY();
					height = selectedPoint.getY() - location.getY() + 1;
				} else if (location.getY() == selectedPoint.getY()) {
					topY = location.getY();
					height = 1;
				} else {
					topY = selectedPoint.getY();
					height = location.getY() - selectedPoint.getY() + 1;
				}

				SelectionManager.setSelectedArea(new Rectangle(topX, topY,
						width, height));
			}
			return;
		}else if(DWConfiguration.getInstance().isBuildMode() && isLine()){
			SelectionManager.setSelectedLine(selectedPoint, location);
			return;
		}

		if(isBrush()){
			if (button == MouseEvent.BUTTON1)
				Land.setLand(location, DWConfiguration.getInstance().getSelectedMenu());
			else
				Land.setLand(location, Land.Empty);
		}
	}

}
