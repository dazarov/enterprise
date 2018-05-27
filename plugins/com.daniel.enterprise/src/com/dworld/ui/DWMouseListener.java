package com.dworld.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.DWUnitFactory;
import com.dworld.core.IUnit;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.core.SelectionManager;
import com.dworld.units.Unit;

public class DWMouseListener {
	Location startLocation = null;
	int startButton = 0;
	
	private Location getLocation(int mouseX, int mouseY){
		return new Location(
			DWConfiguration.getInstance().getUI().getImages().getX() + (mouseX - 3) / DWConstants.UI_IMAGE_WIDTH,
			DWConfiguration.getInstance().getUI().getImages().getY() + mouseY / DWConstants.UI_IMAGE_HEIGHT
		);
	}

	public void doMousePressed(int button, int mouseX, int mouseY) {
		Location location = getLocation(mouseX, mouseY);
		startButton = button;

		if (!DWConfiguration.getInstance().isBuildMode()) { // selection mode
			if (button == MouseEvent.BUTTON1) {
				SelectionManager.clearSelection();
				startLocation = location;
				SelectionManager.setSelectedArea(new Rectangle(startLocation.getX(), startLocation.getY(), 1, 1));
			} else {
				if(!SelectionManager.sendDefaultCommand(location)){
					if(DWConfiguration.getInstance().isAttackMode()){
						SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_ATTACK, new Object[]{location});
					}else{
						SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_MOVE_TO, new Object[]{location});
					}
				}
				
				startLocation = null;
				SelectionManager.setSelectedArea(SelectionManager.NULL_RECTANGLE);
			}
			return;
		}else if(isRect()){
			SelectionManager.clearSelection();
			startLocation = location;
			SelectionManager.setSelectedArea(new Rectangle(startLocation.getX(), startLocation.getY(), 1, 1));
		}else if(isLine()){
				SelectionManager.clearSelection();
				startLocation = location;
				SelectionManager.setSelectedLine(startLocation, startLocation);
		}else if(isBrush()){
			if (button == MouseEvent.BUTTON1){
				Land.setLand(location, DWConfiguration.getInstance().getSelectedCode());
				DWUnitFactory.createUnit(DWConfiguration.getInstance().getSelectedCode(), location.getX(), location.getY());
			}else{
				List<IUnit> list = DWConfiguration.getInstance().getEngine().findUnit(location);
				if(list != null){
					for(IUnit unit : list){
						unit.die();
					}
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
							Land.setLand(new Location(x, y), DWConfiguration.getInstance().getSelectedCode());
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
							Land.setLand(point, DWConfiguration.getInstance().getSelectedCode());
						}else{
							Land.setLand(point, Land.Empty);
						}
					}
				}
				DWConfiguration.getInstance().getLauncher().setModified();
			}else if(isFill()){
				Location location = getLocation(mouseX, mouseY);
				Land oldLand = Land.getLand(location);
				if (button == MouseEvent.BUTTON1){
					SelectionManager.fill(location.getX(), location.getY(), oldLand, DWConfiguration.getInstance().getSelectedCode());
				}else{
					SelectionManager.fill(location.getX(), location.getY(), oldLand, Land.Empty);
				}
				
				DWConfiguration.getInstance().getLauncher().setModified();
			}
			SelectionManager.clearSelection();
		}
		if(!DWConfiguration.getInstance().isBuildMode()){
			SelectionManager.select();
		}
	}
	
	Location lastLocation = new Location(0,0);

	public void doMouseDragged(int mouseX, int mouseY) {

		Location location = getLocation(mouseX, mouseY);
		if (lastLocation.equals(location))
			return;
		lastLocation = location;
		if (!DWConfiguration.getInstance().isBuildMode() || isRect()) {
			if (startLocation != null) {
				int topX, topY, width, height;

				if (location.getX() < startLocation.getX()) {
					topX = location.getX();
					width = startLocation.getX() - location.getX() + 1;
				} else if (location.getX() == startLocation.getX()) {
					topX = location.getX();
					width = 1;
				} else {
					topX = startLocation.getX();
					width = location.getX() - startLocation.getX() + 1;
				}

				if (location.getY() < startLocation.getY()) {
					topY = location.getY();
					height = startLocation.getY() - location.getY() + 1;
				} else if (location.getY() == startLocation.getY()) {
					topY = location.getY();
					height = 1;
				} else {
					topY = startLocation.getY();
					height = location.getY() - startLocation.getY() + 1;
				}

				SelectionManager.setSelectedArea(new Rectangle(topX, topY,
						width, height));
			}
			return;
		}else if(isLine()){
			SelectionManager.setSelectedLine(startLocation, location);
			return;
		}else if(isBrush()){
			if (startButton == MouseEvent.BUTTON1){
				Land.setLand(location, DWConfiguration.getInstance().getSelectedCode());
			} else {
				Land.setLand(location, Land.Empty);
			}
		}
	}

}
