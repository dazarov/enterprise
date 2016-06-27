package com.dworld.core;

import com.dworld.DWSwingLauncher;
import com.dworld.ui.DWUI;
import com.dworld.ui.javafx.DWJavaFXImages;
import com.dworld.units.ControlledUnit;

public class DWConfiguration {
	public static final String SAVE_FILE = "/save.dat";
	public static final String BACKUP_FILE = "/backup.dat";
	public static final String TEST_FILE = "/test.dat";
	public static final String TITLE = "D World";
	public static final String MODIFYED_TITLE = "*D World";
	
	public static final int DRAW_BRUSH = 1;
	public static final int DRAW_LINE = 2;
	public static final int DRAW_RECTANGLE = 3;
	public static final int DRAW_FILL = 4;
	
	private static volatile DWConfiguration instance;
	
	public static DWConfiguration getInstance(){
		if(instance == null){
			synchronized (DWConfiguration.class) {
				if(instance == null){
					instance = new DWConfiguration();
				}
			}
		}
		return instance;
	}
	
	private final DWEngine engine;
	
	
	private ControlledUnit controlledUnit = null;
	private ILauncher launcher = null;
	
	private DWUI ui = null;
	
	private int drawMode = DRAW_BRUSH;
	private int selectedMenu = Land.Brick;
	private boolean buildMode = false;
	
	private boolean attackMode = false;
	
	private String pathName;
	
	private DWConfiguration(){
		engine = new DWEngine();
	}
	
	public DWEngine getEngine(){
		return engine;
	}
	
	public DWUI getUI(){
		return ui;
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
			ui = new DWUI(DWUI.UI_TYPE_SWING);
		}else if(launcher instanceof DWJavaFXImages){
			ui = new DWUI(DWUI.UI_TYPE_JAVA_FX);
		}
	}
	
	public ILauncher getLauncher(){
		return launcher;
	}
	
	public void setDrawMode(int mode){
		drawMode = mode;
	}
	
	public int getDrawMode(){
		return drawMode;
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

	public void setSelectedMenu(int element){
		selectedMenu = element;
	}
	
	public int getSelectedMenu(){
		return selectedMenu;
	}

	public void setPathName(String pathName){
		this.pathName = pathName;
	}
	
	public String getPathName(){
		return pathName;
	}
}
