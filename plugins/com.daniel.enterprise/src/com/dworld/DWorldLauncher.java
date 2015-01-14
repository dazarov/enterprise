package com.dworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.dworld.core.DWorldConstants;
import com.dworld.core.Direction;
import com.dworld.core.Engine;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.ui.DWorldMenuBuilder;
import com.dworld.ui.DWorldWindowListener;
import com.dworld.ui.DrawWorld;
import com.dworld.ui.DWorldToolBarBuilder;
import com.dworld.ui.Map;
import com.dworld.units.ControlledUnit;

public class DWorldLauncher implements KeyListener, MouseListener, MouseMotionListener {
	public static final String SAVE_FILE = "/save.dat";
	public static final String BACKUP_FILE = "/backup.dat";
	public static final String TEST_FILE = "/test.dat";
	
	public static final int DRAW_BRUSH = 1;
	public static final int DRAW_LINE = 2;
	public static final int DRAW_RECTANGLE = 3;
	public static final int DRAW_FILL = 4;
	
	private static int draw_mode = DRAW_BRUSH;
	
	private static DWorldLauncher launcher;
	private static ControlledUnit controlledUnit = null;
	private JFrame window;
	private static String path;
	private JPanel panel = null;
	private int selectedElement = Land.Brick;
	private boolean buildMode = false;
	public static final String TITLE = "D World";
	public static final String MODIFYED_TITLE = "*D World";
	
	private static boolean attack_mode = false;
	
	private Engine engine;

	public DWorldLauncher() {

	}
	
	public static void setDrawMode(int mode){
		draw_mode = mode;
	}
	
	public static DWorldLauncher getLauncher(){
		return launcher;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launcher = new DWorldLauncher();

		launcher.init();
		
		launcher.start();
	}
	
	public static String getPath(){
		return path;
	}

	private void init() {
		File jar = new File(DWorldLauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		if(jar.exists()){
			path = jar.getParent();
		}else{
			path = "";
		}
		
		window = new JFrame();
		engine = new Engine(window);
		DWorldWindowListener.getDefault().addMainWindow(window);
		initMenu();
		initWindow();
	}

	private void start() {
		Land.load(SAVE_FILE, window);
		engine.init();
		engine.run();
	}

	private void initWindow() {

		window.setTitle(TITLE);
		window.setIconImage(new ImageIcon("resources/land/patriot.gif")
				.getImage());
		panel = new JPanel() {
			static final long serialVersionUID = 12;

			public void paint(Graphics g) {
				DrawWorld.draw(g);
			}
		};
		
		panel.setSize(DWorldConstants.UI_WIDTH * DWorldConstants.UI_IMAGE_WIDTH, DWorldConstants.UI_HEIGHT * DWorldConstants.UI_IMAGE_HEIGHT);
		window.setLayout(new BorderLayout());
		window.add(panel, BorderLayout.CENTER);
		initToolBar();
		window.pack();
		window.setSize(DWorldConstants.UI_WIDTH * DWorldConstants.UI_IMAGE_WIDTH + 8,
				DWorldConstants.UI_HEIGHT * DWorldConstants.UI_IMAGE_HEIGHT + 48);
		window.setLocation(480, 10);
		window.addKeyListener(launcher);
		panel.addMouseListener(launcher);
		panel.addMouseMotionListener(launcher);
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
		
		Map.switchMinimap();
	}
	
	public boolean exitConfirmation(){
		if (Land.isDirty()) {
			int n = JOptionPane.showConfirmDialog(window,
					"Do you want to save the game?", "Question",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				engine.saveAndExit(SAVE_FILE);
				return false;
			}
		}
		return true;
	}

	private void initMenu() {
		DWorldMenuBuilder menuBuilder = new DWorldMenuBuilder(window);
		
		window.setJMenuBar(menuBuilder.buildMenu());
	}
	
	private void initToolBar(){
		DWorldToolBarBuilder toolBarBuilder = new DWorldToolBarBuilder(window);
		JToolBar toolBar = toolBarBuilder.buildToolBar();
		
		window.add(toolBar, BorderLayout.NORTH);
	}
	
	public void doKeyPressed(int keyCode, int keyModifiers){
		if (keyCode == 0)
			return;
		else if (keyCode == 27) {
			if(exitConfirmation())
				System.exit(0);
		}else if (keyCode == 77) { // m
			Map.showMap();
		}else if (keyCode == 78) { // n
			Map.switchMinimap();
		}
		
		if (controlledUnit != null)
			controlledUnit.control(keyCode, keyModifiers);
		 
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

	Point selectedPoint = null;
	
	private Point getLocation(int mouseX, int mouseY){
		return new Point(
			DrawWorld.getX() + (mouseX - 3) / DWorldConstants.UI_IMAGE_WIDTH,
			DrawWorld.getY() + mouseY / DWorldConstants.UI_IMAGE_HEIGHT
		);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
		
		Point location = getLocation(e.getX(), e.getY());

		if (!buildMode) { // selection mode
			if (e.getButton() == MouseEvent.BUTTON1) {
				SelectionManager.clearSelection();
				selectedPoint = location;
				SelectionManager.setSelectedArea(new Rectangle(selectedPoint,
						new Dimension(1, 1)));
			} else {
				if(!SelectionManager.sendDefaultCommand(location)){
					if(attack_mode){
						SelectionManager.<Point>sendCommand(SelectionManager.ATTACK_COMMAND, location);
					}else{
						SelectionManager.<Point>sendCommand(SelectionManager.MOVE_TO_COMMAND, location);
					}
				}
				
				selectedPoint = null;
				SelectionManager.setSelectedArea(null);
			}
			return;
		}else if(isRect()){
			SelectionManager.clearSelection();
			selectedPoint = location;
			SelectionManager.setSelectedArea(new Rectangle(selectedPoint,
					new Dimension(1, 1)));
		}else if(isLine()){
				SelectionManager.clearSelection();
				selectedPoint = location;
				SelectionManager.setSelectedLine(selectedPoint, selectedPoint);
		}else if(isBrush()){
			if (e.getButton() == MouseEvent.BUTTON1){
				Land.setLand(location, selectedElement);
				try {
					Land.loadUnit(selectedElement, location.x, location.y, null);
				} catch (IOException e1) {
					// do nothing
				}
			}else{
				Land.setLand(location, Land.Empty);
			}
	
			Land.modified(window);
		}
	}
	
	public boolean isRect(){
		return draw_mode == DRAW_RECTANGLE;
	}
	
	public boolean isLine(){
		return draw_mode == DRAW_LINE;
	}
	
	public boolean isBrush(){
		return draw_mode == DRAW_BRUSH;
	}
	
	public boolean isFill(){
		return draw_mode == DRAW_FILL;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button = e.getButton();
		if(isBuildMode()){
			if(isRect()){
				Rectangle rectangle = SelectionManager.getSelectedArea();
				if(rectangle == null)
					return;
				for(int x = rectangle.x; x < rectangle.x+rectangle.width; x++){
					for(int y = rectangle.y; y < rectangle.y+rectangle.height; y++){
						if (e.getButton() == MouseEvent.BUTTON1)
							Land.setLand(new Point(x, y), selectedElement);
						else
							Land.setLand(new Point(x, y), Land.Empty);
					}
				}
				Land.modified(window);
			}else if(isLine()){
				ArrayList<Point> points = SelectionManager.getSelectedLine();
				if(points != null){
					for(Point point : points){
						if (e.getButton() == MouseEvent.BUTTON1){
							Land.setLand(point, selectedElement);
						}else{
							Land.setLand(point, Land.Empty);
						}
					}
				}
				Land.modified(window);
			}else if(isFill()){
				Point location = getLocation(e.getX(), e.getY());
				int oldCode = Land.getLand(location);
				if (e.getButton() == MouseEvent.BUTTON1){
					fill(location.x, location.y, oldCode, selectedElement);
				}else{
					fill(location.x, location.y, oldCode, Land.Empty);
				}
				
				Land.modified(window);
			}
			SelectionManager.clearSelection();
		}
		if(!isBuildMode()){
			SelectionManager.select();
		}
	}

	public void setSelectedElement(int code){
		selectedElement = code;
	}

	public int getSelectedElement(){
		return selectedElement;
	}
	
	public void setAttackMode(boolean mode){
		attack_mode = mode;
	}
	
	public static ControlledUnit getControlledUnit() {
		return controlledUnit;
	}

	public static void setControlledUnit(ControlledUnit controlledUnit) {
		DWorldLauncher.controlledUnit = controlledUnit;
	}

	public boolean isBuildMode() {
		return buildMode;
	}

	public void setBuildMode(boolean buildMode) {
		this.buildMode = buildMode;
	}

	int lastX = 0, lastY = 0;

	@Override
	public void mouseDragged(MouseEvent e) {

		Point location = getLocation(e.getX(), e.getY());
		if (lastX == location.x && lastY == location.y)
			return;
		lastX = location.x;
		lastY = location.y;
		if (!isBuildMode() || isRect()) {
			if (selectedPoint != null) {
				int topX, topY, width, height;

				if (location.x < selectedPoint.x) {
					topX = location.x;
					width = selectedPoint.x - location.x + 1;
				} else if (location.x == selectedPoint.x) {
					topX = location.x;
					width = 1;
				} else {
					topX = selectedPoint.x;
					width = location.x - selectedPoint.x + 1;
				}

				if (location.y < selectedPoint.y) {
					topY = location.y;
					height = selectedPoint.y - location.y + 1;
				} else if (location.y == selectedPoint.y) {
					topY = location.y;
					height = 1;
				} else {
					topY = selectedPoint.y;
					height = location.y - selectedPoint.y + 1;
				}

				SelectionManager.setSelectedArea(new Rectangle(topX, topY,
						width, height));
			}
			return;
		}else if(isBuildMode() && isLine()){
			SelectionManager.setSelectedLine(selectedPoint, location);
			return;
		}

		if(isBrush()){
			if (button == MouseEvent.BUTTON1)
				Land.setLand(location, selectedElement);
			else
				Land.setLand(location, Land.Empty);
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}
	
//	private void fill2(int x, int y, int oldCode, int newCode){
//		if(oldCode == newCode){
//			return;
//		}
//		int startX = x;
//		int startY = y;
//		while(Land.getLand(startX, startY) == oldCode){
//			startY--;
//		}
//		startY++;
//		while(Land.getLand(startX, startY) == oldCode){
//			startX--;
//		}
//		startX++;
//		int xx = startX;
//		int yy = startY;
//		while(true){
//			while(Land.getLand(xx, yy) == oldCode){
//				Land.setLand(xx, yy, newCode);
//				xx++;
//			}
//			xx = startX;
//			yy++;
//			if(Land.getLand(xx, yy) != oldCode){
//				return;
//			}
//		}
//	}
	
	private void fill(int x, int y, int oldCode, int newCode){
		if(oldCode == newCode){
			return;
		}
		while(true){
			if(Land.getLand(x, y) != oldCode){
				return;
			}
			int startX = x;
			int startY = y;
			while(Land.getLand(startX, startY) == oldCode){
				startY--;
			}
			startY++;
			while(Land.getLand(startX, startY) == oldCode){
				startX--;
			}
			startX++;
			Direction direction = Direction.east;
			Point location = new Point(startX, startY);
			while(true){
				Land.setLand(location, newCode);
				direction = findDirection(location, oldCode, newCode, direction);
				if(direction == Direction.nowhere){
					break;
				}
				location = Land.getNewLocation(location, direction);
			}
		}
	}
	
	private Direction findDirection(Point location, int oldCode, int newCode, Direction direction){
		Direction dir = direction.getAnticlockwiseDirection(2);
		for(int i = 0; i < 3; i++){
			if(Land.getLand(location, dir) == oldCode){
				return dir; 
			}
			dir = dir.getClockwiseDirection(2);
		}
		return Direction.nowhere;
	}
}
