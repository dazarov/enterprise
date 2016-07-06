package com.dworld.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWEngine;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.ui.swing.DWInfoScreen;
import com.dworld.ui.swing.DWMap;
import com.dworld.ui.swing.DWToolBarBuilder;
import com.dworld.units.Unit;

public class DWMenuStructure {
	public static class DWMenuItem{
		public String label;
		public int imageCode = -1;
		public Runnable runner;
		
		// Separator
		public DWMenuItem(){
		}
		
		public DWMenuItem(String label, Runnable runner){
			this.label = label;
			this.runner = runner;
		}
		
		public DWMenuItem(String label, int imageCode, Runnable runner){
			this(label, runner);
			this.imageCode = imageCode;
		}

	}
	
	public static class DWMenu extends DWMenuItem{
		public List<DWMenuItem> items = new ArrayList<DWMenuItem>();
		
		public DWMenu(String label){
			this.label = label;
		}
	}
	
	public static class DWCheckboxMenuItem extends DWMenuItem{
		public BooleanSupplier isSelected;
		
		public DWCheckboxMenuItem(String label, Runnable runner){
			super(label, runner);
		}
		
		public DWCheckboxMenuItem(String label, BooleanSupplier isSelected, Runnable runner){
			super(label, runner);
			this.isSelected = isSelected;
		}
	}
	
	public static class DWRadioMenuItem extends DWMenuItem{
		public int buttonGroupId;
		
		public DWRadioMenuItem(String label, int imageCode, int buttonGroupId, Runnable runner){
			super(label, imageCode, runner);
			this.buttonGroupId = buttonGroupId;
		}
		
		public DWRadioMenuItem(String label, int buttonGroupId, Runnable runner){
			super(label, runner);
			this.buttonGroupId = buttonGroupId;
		}
	}
	
	public List<DWMenu> menus = new ArrayList<DWMenu>();
	
	public DWMenuStructure(){
		DWConfiguration config = DWConfiguration.getInstance();
		DWEngine engine = config.getEngine();

		DWMenu menu = new DWMenu("File");
		menus.add(menu);

		menu.items.add(new DWMenuItem("Load",        () -> engine.load(DWConfiguration.SAVE_FILE)));
		menu.items.add(new DWMenuItem("Save",        () -> engine.save(DWConfiguration.SAVE_FILE)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Load Backup", () -> engine.load(DWConfiguration.BACKUP_FILE)));
		menu.items.add(new DWMenuItem("Save Backup", () -> engine.save(DWConfiguration.BACKUP_FILE)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Load Test",   () -> engine.load(DWConfiguration.TEST_FILE)));
		menu.items.add(new DWMenuItem("Save Test",   () -> engine.save(DWConfiguration.TEST_FILE)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Exit",        () -> {if(config.getUI().exitConfirmation())System.exit(0);}));
		
		menu = new DWMenu("Game");
		menus.add(menu);
		
		menu.items.add(new DWMenuItem("Map",      () -> DWMap.showMap()));
		menu.items.add(new DWMenuItem("Mini Map", () -> DWMap.showMinimap()));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Pause",    () -> engine.pause(true)));
		menu.items.add(new DWMenuItem("Go",       () -> engine.pause(false)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Info",     () -> new DWInfoScreen()));
		
		menu = new DWMenu("Build");
		menus.add(menu);
		
		menu.items.add(new DWCheckboxMenuItem("Build mode", () -> config.isBuildMode(),
		() -> {
			config.setBuildMode(!config.isBuildMode());
			engine.pause(config.isBuildMode());
			if(config.isBuildMode()){
				DWToolBarBuilder.showPalette();
			}else{
				DWToolBarBuilder.hidePalette();
			}
			
		}));

		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Copy",  () -> SelectionManager.copy()));
		menu.items.add(new DWMenuItem("Paste", () -> SelectionManager.paste()));
		menu.items.add(new DWMenuItem());
		
		DWMenu submenu = new DWMenu("Landscape");
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem("Wall", Land.Wall,              1, () -> config.setSelectedMenu(Land.Wall)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Brick", Land.Brick,            1, () -> config.setSelectedMenu(Land.Brick)));
		submenu.items.add(new DWRadioMenuItem("White Brick", Land.WhiteBrick, 1, () -> config.setSelectedMenu(Land.WhiteBrick)));
		submenu.items.add(new DWRadioMenuItem("White Stone", Land.Stone,      1, () -> config.setSelectedMenu(Land.Stone)));
		submenu.items.add(new DWRadioMenuItem("Black Stone", Land.BlackStone, 1, () -> config.setSelectedMenu(Land.BlackStone)));
		submenu.items.add(new DWRadioMenuItem("Wood", Land.Wood1,             1, () -> config.setSelectedMenu(Land.Wood1)));
		submenu.items.add(new DWRadioMenuItem("Wood", Land.Wood2,             1, () -> config.setSelectedMenu(Land.Wood2)));
		submenu.items.add(new DWRadioMenuItem("Wood", Land.Wood3,             1, () -> config.setSelectedMenu(Land.Wood3)));
		submenu.items.add(new DWRadioMenuItem("Wood", Land.Wood4,             1, () -> config.setSelectedMenu(Land.Wood4)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Mountain", Land.Mountain,      1, () -> config.setSelectedMenu(Land.Mountain)));
		submenu.items.add(new DWRadioMenuItem("Water", Land.Water,            1, () -> config.setSelectedMenu(Land.Water)));
		submenu.items.add(new DWRadioMenuItem("Sand", Land.Sand,              1, () -> config.setSelectedMenu(Land.Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Grass", Land.Grass,            1, () -> config.setSelectedMenu(Land.Grass)));
		submenu.items.add(new DWRadioMenuItem("Tree", Land.Tree1,             1, () -> config.setSelectedMenu(Land.Tree1)));
		submenu.items.add(new DWRadioMenuItem("Tree", Land.Tree2,             1, () -> config.setSelectedMenu(Land.Tree2)));
		submenu.items.add(new DWRadioMenuItem("Tree", Land.Tree3,             1, () -> config.setSelectedMenu(Land.Tree3)));
		
		submenu = new DWMenu("Railroad");
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem("Train", Land.Train_Horizontal, 1, () -> config.setSelectedMenu(Land.Train_Horizontal)));
		submenu.items.add(new DWRadioMenuItem("Train", Land.Train_Vertical,   1, () -> config.setSelectedMenu(Land.Train_Vertical)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Station", Land.Station_Horizontal, 1, () -> config.setSelectedMenu(Land.Station_Horizontal)));
		submenu.items.add(new DWRadioMenuItem("Station", Land.Station_Vertical,   1, () -> config.setSelectedMenu(Land.Station_Vertical)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Horizontal,    1, () -> config.setSelectedMenu(Land.Rail_Horizontal)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Vertical,      1, () -> config.setSelectedMenu(Land.Rail_Vertical)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Diagonal_Up,   1, () -> config.setSelectedMenu(Land.Rail_Diagonal_Up)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Diagonal_Down, 1, () -> config.setSelectedMenu(Land.Rail_Diagonal_Down)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Up_Right,      1, () -> config.setSelectedMenu(Land.Rail_Up_Right)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Up_Left,       1, () -> config.setSelectedMenu(Land.Rail_Up_Left)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Down_Right,    1, () -> config.setSelectedMenu(Land.Rail_Down_Right)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Down_Left,     1, () -> config.setSelectedMenu(Land.Rail_Down_Left)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Left_Up,       1, () -> config.setSelectedMenu(Land.Rail_Left_Up)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Left_Down,     1, () -> config.setSelectedMenu(Land.Rail_Left_Down)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Right_Up,      1, () -> config.setSelectedMenu(Land.Rail_Right_Up)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Right_Down,    1, () -> config.setSelectedMenu(Land.Rail_Right_Down)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Vertical_Cross, 1, () -> config.setSelectedMenu(Land.Rail_Vertical_Cross)));
		submenu.items.add(new DWRadioMenuItem("Rail", Land.Rail_Diagonal_Cross, 1, () -> config.setSelectedMenu(Land.Rail_Diagonal_Cross)));
		
		submenu = new DWMenu("Doors & Gates");
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem("Vertical Steel door",   Land.ClosedVerticalSteelGate,   1, () -> config.setSelectedMenu(Land.ClosedVerticalSteelGate)));
		submenu.items.add(new DWRadioMenuItem("Horizontal Steel door", Land.ClosedHorizontalSteelGate, 1, () -> config.setSelectedMenu(Land.ClosedHorizontalSteelGate)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Vertical Wood door",   Land.ClosedVerticalWoodGate,   1, () -> config.setSelectedMenu(Land.ClosedVerticalWoodGate)));
		submenu.items.add(new DWRadioMenuItem("Horizontal Wood door", Land.ClosedHorizontalWoodGate, 1, () -> config.setSelectedMenu(Land.ClosedHorizontalWoodGate)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Vertical Concrete door",   Land.ClosedVerticalConcreteGate,   1, () -> config.setSelectedMenu(Land.ClosedVerticalConcreteGate)));
		submenu.items.add(new DWRadioMenuItem("Horizontal Concrete door", Land.ClosedHorizontalConcreteGate, 1, () -> config.setSelectedMenu(Land.ClosedHorizontalConcreteGate)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Vertical Brick door",   Land.ClosedVerticalBrickGate,   1, () -> config.setSelectedMenu(Land.ClosedVerticalBrickGate)));
		submenu.items.add(new DWRadioMenuItem("Horizontal Brick door", Land.ClosedHorizontalBrickGate, 1, () -> config.setSelectedMenu(Land.ClosedHorizontalBrickGate)));
		
		submenu = new DWMenu("Ammunition");
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem("Ammo",    Land.Ammo,    1, () -> config.setSelectedMenu(Land.Ammo)));
		submenu.items.add(new DWRadioMenuItem("Grenade", Land.Grenade, 1, () -> config.setSelectedMenu(Land.Grenade)));
		submenu.items.add(new DWRadioMenuItem("Rocket",  Land.Rocket,  1, () -> config.setSelectedMenu(Land.Rocket)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Mine", Land.Mine,       1, () -> config.setSelectedMenu(Land.Mine)));
		submenu.items.add(new DWRadioMenuItem("Mine", Land.Mine_Grass, 1, () -> config.setSelectedMenu(Land.Mine_Grass)));
		submenu.items.add(new DWRadioMenuItem("Mine", Land.Mine_Sand,  1, () -> config.setSelectedMenu(Land.Mine_Sand)));
		
		submenu = new DWMenu("Enemies");
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem("General", Land.BadGeneral,       1, () -> config.setSelectedMenu(Land.BadGeneral)));
		submenu.items.add(new DWRadioMenuItem("General", Land.BadGeneral_Grass, 1, () -> config.setSelectedMenu(Land.BadGeneral_Grass)));
		submenu.items.add(new DWRadioMenuItem("General", Land.BadGeneral_Sand,  1, () -> config.setSelectedMenu(Land.BadGeneral_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Officer", Land.BadOfficer,       1, () -> config.setSelectedMenu(Land.BadOfficer)));
		submenu.items.add(new DWRadioMenuItem("Officer", Land.BadOfficer_Grass, 1, () -> config.setSelectedMenu(Land.BadOfficer_Grass)));
		submenu.items.add(new DWRadioMenuItem("Officer", Land.BadOfficer_Sand,  1, () -> config.setSelectedMenu(Land.BadOfficer_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Robot", Land.BadSoldier,       1, () -> config.setSelectedMenu(Land.BadSoldier)));
		submenu.items.add(new DWRadioMenuItem("Robot", Land.BadSoldier_Grass, 1, () -> config.setSelectedMenu(Land.BadSoldier_Grass)));
		submenu.items.add(new DWRadioMenuItem("Robot", Land.BadSoldier_Sand,  1, () -> config.setSelectedMenu(Land.BadSoldier_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Tank", Land.BadTank,       1, () -> config.setSelectedMenu(Land.BadTank)));
		submenu.items.add(new DWRadioMenuItem("Tank", Land.BadTank_Grass, 1, () -> config.setSelectedMenu(Land.BadTank_Grass)));
		submenu.items.add(new DWRadioMenuItem("Tank", Land.BadTank_Sand,  1, () -> config.setSelectedMenu(Land.BadTank_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Bunker", Land.BadBunker,       1, () -> config.setSelectedMenu(Land.BadBunker)));
		submenu.items.add(new DWRadioMenuItem("Bunker", Land.BadBunker_Grass, 1, () -> config.setSelectedMenu(Land.BadBunker_Grass)));
		submenu.items.add(new DWRadioMenuItem("Bunker", Land.BadBunker_Sand,  1, () -> config.setSelectedMenu(Land.BadBunker_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Radar", Land.BadRadar,       1, () -> config.setSelectedMenu(Land.BadRadar)));
		submenu.items.add(new DWRadioMenuItem("Radar", Land.BadRadar_Grass, 1, () -> config.setSelectedMenu(Land.BadRadar_Grass)));
		submenu.items.add(new DWRadioMenuItem("Radar", Land.BadRadar_Sand,  1, () -> config.setSelectedMenu(Land.BadRadar_Sand)));
		
		submenu = new DWMenu("Citizens");
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem("General", Land.GoodGeneral,       1, () -> config.setSelectedMenu(Land.GoodGeneral)));
		submenu.items.add(new DWRadioMenuItem("General", Land.GoodGeneral_Grass, 1, () -> config.setSelectedMenu(Land.GoodGeneral_Grass)));
		submenu.items.add(new DWRadioMenuItem("General", Land.GoodGeneral_Sand,  1, () -> config.setSelectedMenu(Land.GoodGeneral_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Officer", Land.GoodOfficer,       1, () -> config.setSelectedMenu(Land.GoodOfficer)));
		submenu.items.add(new DWRadioMenuItem("Officer", Land.GoodOfficer_Grass, 1, () -> config.setSelectedMenu(Land.GoodOfficer_Grass)));
		submenu.items.add(new DWRadioMenuItem("Officer", Land.GoodOfficer_Sand,  1, () -> config.setSelectedMenu(Land.GoodOfficer_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Soldier", Land.GoodSoldier,       1, () -> config.setSelectedMenu(Land.GoodSoldier)));
		submenu.items.add(new DWRadioMenuItem("Soldier", Land.GoodSoldier_Grass, 1, () -> config.setSelectedMenu(Land.GoodSoldier_Grass)));
		submenu.items.add(new DWRadioMenuItem("Soldier", Land.GoodSoldier_Sand,  1, () -> config.setSelectedMenu(Land.GoodSoldier_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Tank", Land.GoodTank,       1, () -> config.setSelectedMenu(Land.GoodTank)));
		submenu.items.add(new DWRadioMenuItem("Tank", Land.GoodTank_Grass, 1, () -> config.setSelectedMenu(Land.GoodTank_Grass)));
		submenu.items.add(new DWRadioMenuItem("Tank", Land.GoodTank_Sand,  1, () -> config.setSelectedMenu(Land.GoodTank_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Bunker", Land.GoodBunker,       1, () -> config.setSelectedMenu(Land.GoodBunker)));
		submenu.items.add(new DWRadioMenuItem("Bunker", Land.GoodBunker_Grass, 1, () -> config.setSelectedMenu(Land.GoodBunker_Grass)));
		submenu.items.add(new DWRadioMenuItem("Bunker", Land.GoodBunker_Sand,  1, () -> config.setSelectedMenu(Land.GoodBunker_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Radar", Land.GoodRadar,       1, () -> config.setSelectedMenu(Land.GoodRadar)));
		submenu.items.add(new DWRadioMenuItem("Radar", Land.GoodRadar_Grass, 1, () -> config.setSelectedMenu(Land.GoodRadar_Grass)));
		submenu.items.add(new DWRadioMenuItem("Radar", Land.GoodRadar_Sand,  1, () -> config.setSelectedMenu(Land.GoodRadar_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Peasant", Land.Peasant,       1, () -> config.setSelectedMenu(Land.Peasant)));
		submenu.items.add(new DWRadioMenuItem("Peasant", Land.Peasant_Grass, 1, () -> config.setSelectedMenu(Land.Peasant_Grass)));
		submenu.items.add(new DWRadioMenuItem("Peasant", Land.Peasant_Sand,  1, () -> config.setSelectedMenu(Land.Peasant_Sand)));
		
		submenu = new DWMenu("Special");
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem("Transport Center", Land.Teleport5, 1, () -> config.setSelectedMenu(Land.Teleport5)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem("Center",        Land.Teleport1, 1, () -> config.setSelectedMenu(Land.Teleport1)));
		submenu.items.add(new DWRadioMenuItem("Capital",       Land.Teleport4, 1, () -> config.setSelectedMenu(Land.Teleport4)));
		submenu.items.add(new DWRadioMenuItem("Jail",          Land.Teleport6, 1, () -> config.setSelectedMenu(Land.Teleport6)));
		submenu.items.add(new DWRadioMenuItem("Grand Hotel",   Land.Teleport7, 1, () -> config.setSelectedMenu(Land.Teleport7)));
		submenu.items.add(new DWRadioMenuItem("Bunker",        Land.Teleport8, 1, () -> config.setSelectedMenu(Land.Teleport8)));
		submenu.items.add(new DWRadioMenuItem("Palace",        Land.Teleport9, 1, () -> config.setSelectedMenu(Land.Teleport9)));
		submenu.items.add(new DWRadioMenuItem("Military Base", Land.Teleport11, 1, () -> config.setSelectedMenu(Land.Teleport11)));
		
		menu = new DWMenu("Hero");
		menus.add(menu);
		
		menu.items.add(new DWMenuItem("Hero",         Land.Hero,        () -> engine.changeManCode(Land.Hero)));
		menu.items.add(new DWMenuItem("Peasant",      Land.Peasant,     () -> engine.changeManCode(Land.Peasant)));
		menu.items.add(new DWMenuItem("Soldier",      Land.GoodSoldier, () -> engine.changeManCode(Land.GoodSoldier)));
		menu.items.add(new DWMenuItem("Officer",      Land.GoodOfficer, () -> engine.changeManCode(Land.GoodOfficer)));
		menu.items.add(new DWMenuItem("General",      Land.GoodGeneral, () -> engine.changeManCode(Land.GoodGeneral)));
		menu.items.add(new DWMenuItem("Tank",         Land.GoodTank,    () -> engine.changeManCode(Land.GoodTank)));
		menu.items.add(new DWMenuItem("Robot",        Land.BadSoldier,  () -> engine.changeManCode(Land.BadSoldier)));
		menu.items.add(new DWMenuItem("Gray Officer", Land.BadOfficer,  () -> engine.changeManCode(Land.BadOfficer)));
		menu.items.add(new DWMenuItem("Gray General", Land.BadGeneral,  () -> engine.changeManCode(Land.BadGeneral)));
		menu.items.add(new DWMenuItem("Gray Tank",    Land.BadTank,     () -> engine.changeManCode(Land.BadTank)));
		menu.items.add(new DWMenuItem("Dark Knight",  Land.Dark_Knight, () -> engine.changeManCode(Land.Dark_Knight)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWCheckboxMenuItem("Fight",
			() -> config.isFight(),
			() -> config.setFight(!config.isFight())
		));
		menu.items.add(new DWCheckboxMenuItem("Defense",
			() -> config.isDefense(),
			() -> config.setDefense(!config.isDefense())
		));
		
		menu = new DWMenu("Commands");
		menus.add(menu);
		
		menu.items.add(new DWRadioMenuItem("Attack",  2, () -> config.setAttackMode(true)));
		menu.items.add(new DWRadioMenuItem("Move to", 2, () -> config.setAttackMode(false)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Default Command", () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEFAULT, null)));
		menu.items.add(new DWMenuItem("Activate",        () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_ACTIVATE, null)));
		menu.items.add(new DWMenuItem("Deactivate",      () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEACTIVATE, null)));
		menu.items.add(new DWMenuItem("Stay",            () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_STAY, null)));
		menu.items.add(new DWMenuItem("Move Around",     () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_MOVE_AROUND, null)));
		menu.items.add(new DWMenuItem("Patrol",          () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_PATROL, null)));
		menu.items.add(new DWMenuItem("Defense",         () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEFENSE, null)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem("Open",            () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_OPEN_GATE, null)));
		menu.items.add(new DWMenuItem("Close",           () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_CLOSE_GATE, null)));
	}
}
