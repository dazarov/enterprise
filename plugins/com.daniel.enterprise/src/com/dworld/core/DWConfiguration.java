package com.dworld.core;

import java.util.Locale;

import com.dworld.DWJavaFXLauncher;
import com.dworld.DWSwingLauncher;
import com.dworld.pathfinding.AStarPathFinder;
import com.dworld.pathfinding.PathFinder;
import com.dworld.ui.DWUI;
import com.dworld.ui.javafx.DWJavaFXUI;
import com.dworld.ui.swing.DWSwingUI;
import com.dworld.units.ControlledUnit;

public class DWConfiguration {
	public static final String SAVE_FILE = "/save.dat";
	public static final String BACKUP_FILE = "/backup.dat";
	public static final String TEST_FILE = "/test.dat";
	public static final String MODIFYED_TITLE = "*D World";
	
	public static final int NO_DRAW = 0;
	public static final int DRAW_BRUSH = 1;
	public static final int DRAW_LINE = 2;
	public static final int DRAW_RECTANGLE = 3;
	public static final int DRAW_FILL = 4;
	
	public static final int ACTION_LOAD_MAIN = 0;
	public static final int ACTION_LOAD_BACKUP = 1;
	public static final int ACTION_LOAD_TEST = 2;
	
	private boolean fightActive = false;
	private boolean defenseActive = true;
	
	private static final DWConfiguration instance= new DWConfiguration();
	
	public static DWConfiguration getInstance(){
		return instance;
	}
	
	private final DWEngine engine;
	
	private ControlledUnit controlledUnit;
	private ILauncher launcher;
	
	private DWUI ui;
	
	private int drawMode = DRAW_BRUSH;
	private Land selectedLand = Land.Brick;
	private boolean buildMode;
	
	private boolean attackMode;

	private String pathName;
	
	private Locale locale = new Locale("en", "US");
	
	private PathFinder finder;
	
	public PathFinder getPathFinder(){
		if(finder == null){
			finder = new AStarPathFinder(Land.Vacuum, 500, false);
		}
		return finder;
	}
	
	public Locale getLocale(){
		return locale;
	}
	
	private DWConfiguration(){
		engine = new DWEngine();
	}
	
	public DWEngine getEngine(){
		return engine;
	}
	
	public DWUI getUI(){
		return ui;
	}
	
	@SuppressWarnings({ "unchecked" })
	public <V extends DWUI> V getUI(Class<V> type){
		if(ui.getClass().equals(type)){
			return (V)ui;
		}
		throw new IllegalArgumentException();
	}
	
	public ControlledUnit getControlledUnit() {
		return controlledUnit;
	}

	public void setControlledUnit(ControlledUnit controlledUnit) {
		this.controlledUnit = controlledUnit;
	}
	
	public void setLauncher(ILauncher launcher){
		this.launcher = launcher;
		if(launcher instanceof DWSwingLauncher){
			ui = new DWSwingUI();
		}else if(launcher instanceof DWJavaFXLauncher){
			ui = new DWJavaFXUI();
		}
	}
	
	public ILauncher getLauncher(){
		return launcher;
	}
	
	public void setDrawMode(int mode){
		drawMode = mode;
	}
	
	public int getDrawMode(){
		if(buildMode){
			return drawMode;
		}else{
			return NO_DRAW;
		}
	}

	public void setBuildMode(boolean mode){
		buildMode = mode;
	}
	
	public boolean isBuildMode(){
		return buildMode;
	}

	public void setAttackMode(boolean mode){
		attackMode = mode;
	}
	
	public boolean isAttackMode(){
		return attackMode;
	}

	public void setSelectedCode(Land element){
		selectedLand = element;
	}
	
	public Land getSelectedCode(){
		return selectedLand;
	}

	public void setPathName(String pathName){
		this.pathName = pathName;
	}
	
	public String getPathName(){
		return pathName;
	}
	
	public boolean isFight(){
		return fightActive;
	}

	public void setFight(boolean fight){
		fightActive = fight;
	}
	
	public boolean isDefense(){
		return defenseActive;
	}

	public void setDefense(boolean defense){
		defenseActive = defense;
	}

}
