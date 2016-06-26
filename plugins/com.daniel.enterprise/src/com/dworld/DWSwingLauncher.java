package com.dworld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.DWEngine;
import com.dworld.core.DWUnitFactory;
import com.dworld.core.ILauncher;
import com.dworld.core.IUnit;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.core.SelectionManager;
import com.dworld.ui.DWDraw;
import com.dworld.ui.DWMap;
import com.dworld.ui.DWMenuBuilder;
import com.dworld.ui.DWToolBarBuilder;
import com.dworld.ui.DWWindowListener;
import com.dworld.units.Unit;

public class DWSwingLauncher implements KeyListener, MouseListener, MouseMotionListener, ILauncher {
	private JFrame window;
	private JPanel panel = null;
	
	private DWEngine engine;
	
	DWConfiguration configuration;

	public DWSwingLauncher() {

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean start = false;
		
		assert start = true;
		
		if(start){
			DWSwingLauncher launcher = new DWSwingLauncher();
	
			launcher.init();
			
			launcher.start();
		}
	}
	
	private void init() {
		String pathName = "";
		File jar = new File(DWSwingLauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		if(jar.exists()){
			pathName = jar.getParent();
		}
		configuration = DWConfiguration.getInstance();
		configuration.setLauncher(this);
		
		window = configuration.getUI().getWindow();
		engine = configuration.getEngine();
		
		configuration.setPathName(pathName);
		DWWindowListener.getDefault().addMainWindow(window);
		initMenu();
		initWindow();
	}

	private void start() {
		Land.load(DWConfiguration.SAVE_FILE);
		engine.init();
		engine.run();
	}

	private void initWindow() {

		window.setTitle(DWConfiguration.TITLE);
		window.setIconImage(new ImageIcon("resources/land/patriot.gif")
				.getImage());
		panel = new JPanel() {
			static final long serialVersionUID = 12;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				DWDraw.draw(g);
			}
		};
		panel.setOpaque(true);
		panel.setBackground(Color.black);
		
		panel.setSize(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH, DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT);
		window.setLayout(new BorderLayout());
		window.add(panel, BorderLayout.CENTER);
		initToolBar();
		window.pack();
		window.setSize(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH + 8,
				DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT + 48);
		window.setLocation(480, 10);
		window.addKeyListener(this);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		window.setResizable(false);
		window.setVisible(true);
		window.setFocusable(true);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				exitConfirmation();
				System.exit(0);
			}
		});
		
		DWMap.switchMinimap();
	}
	
	public boolean exitConfirmation(){
		if (Land.isDirty()) {
			int n = JOptionPane.showConfirmDialog(window,
					"Do you want to save the game?", "Question",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				engine.saveAndExit(DWConfiguration.SAVE_FILE);
				return false;
			}
		}
		return true;
	}

	private void initMenu() {
		DWMenuBuilder menuBuilder = new DWMenuBuilder(window);
		
		window.setJMenuBar(menuBuilder.buildMenu());
	}
	
	private void initToolBar(){
		DWToolBarBuilder toolBarBuilder = new DWToolBarBuilder(window);
		JToolBar toolBar = toolBarBuilder.buildToolBar();
		
		window.add(toolBar, BorderLayout.NORTH);
	}
	
	/**
	 * Modifier could be
	 * 
	 * 0 - no Ctrl or Shift pressed
	 * 1 - Shift pressed
	 * 2 - Ctrl pressed
	 * 3 - Ctrl and Shift pressed
	 * 
	 * @param keyCode
	 * @param keyModifiers
	 */
	public void doKeyPressed(int keyCode, int keyModifiers){
		if (keyCode == 0)
			return;
		else if (keyCode == 27) {
			if(exitConfirmation())
				System.exit(0);
		}else if (keyCode == 77) { // m
			DWMap.showMap();
		}else if (keyCode == 78) { // n
			DWMap.switchMinimap();
		}
		// Alt
		if(keyModifiers == 8 && !configuration.isBuildMode()){
			switch(keyCode){
			case 37: // Left
				SelectionManager.modifySelection(0, 0, -1, 0);
				return;

			case 38: // Up
				SelectionManager.modifySelection(0, 0, 0, -1);
				return;

			case 39: // Right
				SelectionManager.modifySelection(0, 0, 1, 0);
				return;

			case 40: // Down
				SelectionManager.modifySelection(0, 0, 0, 1);
				return;
			}
		}
		if(keyModifiers == 0 && configuration.isBuildMode()){
			switch(keyCode){
			case 37: // Left
				SelectionManager.moveLeft();
				return;

			case 38: // Up
				SelectionManager.moveUp();
				return;

			case 39: // Right
				SelectionManager.moveRight();
				return;

			case 40: // Down
				SelectionManager.moveDown();
				return;

			case 127: // Del
				SelectionManager.delete();
				return;
			}
		}
		// Ctrl
		if(keyModifiers == 2){
			if(keyCode == 67){ // Ctrl+c
				SelectionManager.copy();
				return;
			}else if(keyCode == 86){ // Ctrl+v
				SelectionManager.paste();
				return;
			}
		}
		
		if (DWConfiguration.getInstance().getControlledUnit() != null)
			DWConfiguration.getInstance().getControlledUnit().control(keyCode, keyModifiers);
		 
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
//		System.out.println("keyPressed");
//		System.out.println("Char - "+event.getKeyChar());
//		System.out.println("Code - "+event.getKeyCode());
//		System.out.println("Modifiers - "+event.getModifiers());
//		System.out.println("ModifiersExt - "+event.getModifiersEx());
		doKeyPressed(event.getKeyCode(), event.getModifiers());
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	int button;

	Location selectedPoint = null;
	
	private Location getLocation(int mouseX, int mouseY){
		return new Location(
			DWDraw.getX() + (mouseX - 3) / DWConstants.UI_IMAGE_WIDTH,
			DWDraw.getY() + mouseY / DWConstants.UI_IMAGE_HEIGHT
		);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
		
		Location location = getLocation(e.getX(), e.getY());

		if (!configuration.isBuildMode()) { // selection mode
			if (e.getButton() == MouseEvent.BUTTON1) {
				SelectionManager.clearSelection();
				selectedPoint = location;
				SelectionManager.setSelectedArea(new Rectangle(selectedPoint.getX(), selectedPoint.getY(), 1, 1));
			} else {
				if(!SelectionManager.sendDefaultCommand(location)){
					if(configuration.isAttackMode()){
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
			if (e.getButton() == MouseEvent.BUTTON1){
				Land.setLand(location, configuration.getSelectedMenu());
				DWUnitFactory.createUnit(configuration.getSelectedMenu(), location.getX(), location.getY());
			}else{
				IUnit unit = engine.findUnit(location);
				if(unit != null){
					unit.die();
				}
				Land.setLand(location, Land.Empty);
			}
	
			setModified();
		}
	}
	
	public boolean isRect(){
		return configuration.getDrawMode() == DWConfiguration.DRAW_RECTANGLE;
	}
	
	public boolean isLine(){
		return configuration.getDrawMode() == DWConfiguration.DRAW_LINE;
	}
	
	public boolean isBrush(){
		return configuration.getDrawMode() == DWConfiguration.DRAW_BRUSH;
	}
	
	public boolean isFill(){
		return configuration.getDrawMode() == DWConfiguration.DRAW_FILL;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button = e.getButton();
		if(configuration.isBuildMode()){
			if(isRect()){
				Rectangle rectangle = SelectionManager.getSelectedArea();
				if(rectangle == SelectionManager.NULL_RECTANGLE)
					return;
				for(int x = rectangle.x; x < rectangle.x+rectangle.width; x++){
					for(int y = rectangle.y; y < rectangle.y+rectangle.height; y++){
						if (e.getButton() == MouseEvent.BUTTON1)
							Land.setLand(new Location(x, y), configuration.getSelectedMenu());
						else
							Land.setLand(new Location(x, y), Land.Empty);
					}
				}
				setModified();
			}else if(isLine()){
				ArrayList<Location> points = SelectionManager.getSelectedLine();
				if(points != null){
					for(Location point : points){
						if (e.getButton() == MouseEvent.BUTTON1){
							Land.setLand(point, configuration.getSelectedMenu());
						}else{
							Land.setLand(point, Land.Empty);
						}
					}
				}
				setModified();
			}else if(isFill()){
				Location location = getLocation(e.getX(), e.getY());
				int oldCode = Land.getLand(location);
				if (e.getButton() == MouseEvent.BUTTON1){
					fill(location.getX(), location.getY(), oldCode, configuration.getSelectedMenu());
				}else{
					fill(location.getX(), location.getY(), oldCode, Land.Empty);
				}
				
				setModified();
			}
			SelectionManager.clearSelection();
		}
		if(!configuration.isBuildMode()){
			SelectionManager.select();
		}
	}
	
	@Override
	public void setModified(){
		Land.modified();
	}
	
	@Override
	public void setSaved(){
		Land.modified();
	}

	int lastX = 0, lastY = 0;

	@Override
	public void mouseDragged(MouseEvent e) {

		Location location = getLocation(e.getX(), e.getY());
		if (lastX == location.getX() && lastY == location.getY())
			return;
		lastX = location.getX();
		lastY = location.getY();
		if (!configuration.isBuildMode() || isRect()) {
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
		}else if(configuration.isBuildMode() && isLine()){
			SelectionManager.setSelectedLine(selectedPoint, location);
			return;
		}

		if(isBrush()){
			if (button == MouseEvent.BUTTON1)
				Land.setLand(location, configuration.getSelectedMenu());
			else
				Land.setLand(location, Land.Empty);
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}
	
	private void fill(int x, int y, int oldCode, int newCode){
		if(SelectionManager.getSelectedArea() != SelectionManager.NULL_RECTANGLE && SelectionManager.getSelectedArea().contains(x,y)){
			for(int x1 = SelectionManager.getSelectedArea().x; x1 < SelectionManager.getSelectedArea().x + SelectionManager.getSelectedArea().width; x1++){
				for(int y1 = SelectionManager.getSelectedArea().y; y1 < SelectionManager.getSelectedArea().getY() + SelectionManager.getSelectedArea().height; y1++){
					int code = Land.getLand(x1, y1);
					if(code == oldCode){
						Land.setLand(x1, y1, newCode);
					}
				}
				
			}
			return;
		}
		if(oldCode == newCode){
			return;
		}
		int startX = x;
		int startY = y;
		while(Land.getLand(startX, startY) == oldCode){
			startY--;
		}
		startY++;
		startX++;
		int xx = startX;
		int yy = startY;
		while(true){
			while(Land.getLand(xx, yy) == oldCode){
				Land.setLand(xx, yy, newCode);
				xx++;
			}
			xx = startX-1;
			while(Land.getLand(xx, yy) == oldCode){
				Land.setLand(xx, yy, newCode);
				xx--;
			}
			xx = startX;
			
			yy++;
			if(Land.getLand(xx, yy) != oldCode){
				return;
			}
		}
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
	
//	private Direction findDirection(Point location, int oldCode, int newCode, Direction direction){
//		Direction dir = direction.getAnticlockwiseDirection(2);
//		for(int i = 0; i < 3; i++){
//			if(Land.getLand(location, dir) == oldCode){
//				return dir; 
//			}
//			dir = dir.getClockwiseDirection(2);
//		}
//		return Direction.nowhere;
//	}
}
