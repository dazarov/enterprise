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

public class DWMenu {
	public static class MenuItem{
		public String label;
		public int imageCode = -1;
		public Runnable runner;
		
		// Separator
		public MenuItem(){
		}
		
		public MenuItem(String label, Runnable runner){
			this.label = label;
			this.runner = runner;
		}
		
		public MenuItem(String label, int imageCode, Runnable runner){
			this(label, runner);
			this.imageCode = imageCode;
		}

	}
	
	public static class Menu extends MenuItem{
		public List<MenuItem> items = new ArrayList<MenuItem>();
		
		public Menu(String label){
			this.label = label;
		}
	}
	
	public static class CheckboxMenuItem extends MenuItem{
		public BooleanSupplier isSelected;
		
		public CheckboxMenuItem(String label, Runnable runner){
			super(label, runner);
		}
		
		public CheckboxMenuItem(String label, BooleanSupplier isSelected, Runnable runner){
			super(label, runner);
			this.isSelected = isSelected;
		}
	}
	
	public static class RadioMenuItem extends MenuItem{
		public int buttonGroupId;
		
		public RadioMenuItem(String label, int imageCode, int buttonGroupId, Runnable runner){
			super(label, imageCode, runner);
			this.buttonGroupId = buttonGroupId;
		}
		
		public RadioMenuItem(String label, int buttonGroupId, Runnable runner){
			super(label, runner);
			this.buttonGroupId = buttonGroupId;
		}
	}
	
	public List<Menu> menus = new ArrayList<Menu>();
	
	public DWMenu(){
		DWEngine engine = DWConfiguration.getInstance().getEngine();

		Menu menu = new Menu("File");
		menus.add(menu);

		menu.items.add(new MenuItem("Load",        () -> engine.load(DWConfiguration.SAVE_FILE)));
		menu.items.add(new MenuItem("Save",        () -> engine.save(DWConfiguration.SAVE_FILE)));
		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Load Backup", () -> engine.load(DWConfiguration.BACKUP_FILE)));
		menu.items.add(new MenuItem("Save Backup", () -> engine.save(DWConfiguration.BACKUP_FILE)));
		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Load Test",   () -> engine.load(DWConfiguration.TEST_FILE)));
		menu.items.add(new MenuItem("Save Test",   () -> engine.save(DWConfiguration.TEST_FILE)));
		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Exit",        () -> {if(DWConfiguration.getInstance().getUI().exitConfirmation())System.exit(0);}));
		
		menu = new Menu("Game");
		menus.add(menu);
		
		menu.items.add(new MenuItem("Map",      () -> DWMap.showMap()));
		menu.items.add(new MenuItem("Mini Map", () -> DWMap.showMinimap()));
		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Pause",    () -> engine.pause(true)));
		menu.items.add(new MenuItem("Go",       () -> engine.pause(false)));
		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Info",     () -> new DWInfoScreen()));
		
		menu = new Menu("Build");
		menus.add(menu);
		
		menu.items.add(new CheckboxMenuItem("Build mode", () -> DWConfiguration.getInstance().isBuildMode(),
		() -> {
			DWConfiguration.getInstance().setBuildMode(!DWConfiguration.getInstance().isBuildMode());
			engine.pause(DWConfiguration.getInstance().isBuildMode());
			if(DWConfiguration.getInstance().isBuildMode()){
				DWToolBarBuilder.showPalette();
			}else{
				DWToolBarBuilder.hidePalette();
			}
			
		}));

		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Copy",  () -> SelectionManager.copy()));
		menu.items.add(new MenuItem("Paste", () -> SelectionManager.paste()));
		menu.items.add(new MenuItem());
		
		Menu submenu = new Menu("Landscape");
		menu.items.add(submenu);
		
		submenu.items.add(new RadioMenuItem("Wall", Land.Wall,              1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wall)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Brick", Land.Brick,            1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Brick)));
		submenu.items.add(new RadioMenuItem("White Brick", Land.WhiteBrick, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.WhiteBrick)));
		submenu.items.add(new RadioMenuItem("White Stone", Land.Stone,      1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Stone)));
		submenu.items.add(new RadioMenuItem("Black Stone", Land.BlackStone, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BlackStone)));
		submenu.items.add(new RadioMenuItem("Wood", Land.Wood1,             1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood1)));
		submenu.items.add(new RadioMenuItem("Wood", Land.Wood2,             1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood2)));
		submenu.items.add(new RadioMenuItem("Wood", Land.Wood3,             1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood3)));
		submenu.items.add(new RadioMenuItem("Wood", Land.Wood4,             1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood4)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Mountain", Land.Mountain,      1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mountain)));
		submenu.items.add(new RadioMenuItem("Water", Land.Water,            1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Water)));
		submenu.items.add(new RadioMenuItem("Sand", Land.Sand,              1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Grass", Land.Grass,            1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Grass)));
		submenu.items.add(new RadioMenuItem("Tree", Land.Tree1,             1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Tree1)));
		submenu.items.add(new RadioMenuItem("Tree", Land.Tree2,             1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Tree2)));
		submenu.items.add(new RadioMenuItem("Tree", Land.Tree3,             1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Tree3)));
		
		submenu = new Menu("Railroad");
		menu.items.add(submenu);
		
		submenu.items.add(new RadioMenuItem("Train", Land.Train_Horizontal, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Train_Horizontal)));
		submenu.items.add(new RadioMenuItem("Train", Land.Train_Vertical,   1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Train_Vertical)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Station", Land.Station_Horizontal, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Station_Horizontal)));
		submenu.items.add(new RadioMenuItem("Station", Land.Station_Vertical,   1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Station_Vertical)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Horizontal,    1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Horizontal)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Vertical,      1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Vertical)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Diagonal_Up,   1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Diagonal_Up)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Diagonal_Down, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Diagonal_Down)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Up_Right,      1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Up_Right)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Up_Left,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Up_Left)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Down_Right,    1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Down_Right)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Down_Left,     1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Down_Left)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Left_Up,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Left_Up)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Left_Down,     1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Left_Down)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Right_Up,      1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Right_Up)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Right_Down,    1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Right_Down)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Vertical_Cross, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Vertical_Cross)));
		submenu.items.add(new RadioMenuItem("Rail", Land.Rail_Diagonal_Cross, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Diagonal_Cross)));
		
		submenu = new Menu("Doors & Gates");
		menu.items.add(submenu);
		
		submenu.items.add(new RadioMenuItem("Vertical Steel door",   Land.ClosedVerticalSteelGate,   1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalSteelGate)));
		submenu.items.add(new RadioMenuItem("Horizontal Steel door", Land.ClosedHorizontalSteelGate, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalSteelGate)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Vertical Wood door",   Land.ClosedVerticalWoodGate,   1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalWoodGate)));
		submenu.items.add(new RadioMenuItem("Horizontal Wood door", Land.ClosedHorizontalWoodGate, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalWoodGate)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Vertical Concrete door",   Land.ClosedVerticalConcreteGate,   1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalConcreteGate)));
		submenu.items.add(new RadioMenuItem("Horizontal Concrete door", Land.ClosedHorizontalConcreteGate, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalConcreteGate)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Vertical Brick door",   Land.ClosedVerticalBrickGate,   1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalBrickGate)));
		submenu.items.add(new RadioMenuItem("Horizontal Brick door", Land.ClosedHorizontalBrickGate, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalBrickGate)));
		
		submenu = new Menu("Ammunition");
		menu.items.add(submenu);
		
		submenu.items.add(new RadioMenuItem("Ammo",    Land.Ammo,    1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Ammo)));
		submenu.items.add(new RadioMenuItem("Grenade", Land.Grenade, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Grenade)));
		submenu.items.add(new RadioMenuItem("Rocket",  Land.Rocket,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rocket)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Mine", Land.Mine,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mine)));
		submenu.items.add(new RadioMenuItem("Mine", Land.Mine_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mine_Grass)));
		submenu.items.add(new RadioMenuItem("Mine", Land.Mine_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mine_Sand)));
		
		submenu = new Menu("Enemies");
		menu.items.add(submenu);
		
		submenu.items.add(new RadioMenuItem("General", Land.BadGeneral,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadGeneral)));
		submenu.items.add(new RadioMenuItem("General", Land.BadGeneral_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadGeneral_Grass)));
		submenu.items.add(new RadioMenuItem("General", Land.BadGeneral_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadGeneral_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Officer", Land.BadOfficer,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadOfficer)));
		submenu.items.add(new RadioMenuItem("Officer", Land.BadOfficer_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadOfficer_Grass)));
		submenu.items.add(new RadioMenuItem("Officer", Land.BadOfficer_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadOfficer_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Robot", Land.BadSoldier,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadSoldier)));
		submenu.items.add(new RadioMenuItem("Robot", Land.BadSoldier_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadSoldier_Grass)));
		submenu.items.add(new RadioMenuItem("Robot", Land.BadSoldier_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadSoldier_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Tank", Land.BadTank,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadTank)));
		submenu.items.add(new RadioMenuItem("Tank", Land.BadTank_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadTank_Grass)));
		submenu.items.add(new RadioMenuItem("Tank", Land.BadTank_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadTank_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Bunker", Land.BadBunker,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadBunker)));
		submenu.items.add(new RadioMenuItem("Bunker", Land.BadBunker_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadBunker_Grass)));
		submenu.items.add(new RadioMenuItem("Bunker", Land.BadBunker_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadBunker_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Radar", Land.BadRadar,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadRadar)));
		submenu.items.add(new RadioMenuItem("Radar", Land.BadRadar_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadRadar_Grass)));
		submenu.items.add(new RadioMenuItem("Radar", Land.BadRadar_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadRadar_Sand)));
		
		submenu = new Menu("Citizens");
		menu.items.add(submenu);
		
		submenu.items.add(new RadioMenuItem("General", Land.GoodGeneral,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodGeneral)));
		submenu.items.add(new RadioMenuItem("General", Land.GoodGeneral_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodGeneral_Grass)));
		submenu.items.add(new RadioMenuItem("General", Land.GoodGeneral_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodGeneral_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Officer", Land.GoodOfficer,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodOfficer)));
		submenu.items.add(new RadioMenuItem("Officer", Land.GoodOfficer_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodOfficer_Grass)));
		submenu.items.add(new RadioMenuItem("Officer", Land.GoodOfficer_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodOfficer_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Soldier", Land.GoodSoldier,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodSoldier)));
		submenu.items.add(new RadioMenuItem("Soldier", Land.GoodSoldier_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodSoldier_Grass)));
		submenu.items.add(new RadioMenuItem("Soldier", Land.GoodSoldier_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodSoldier_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Tank", Land.GoodTank,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodTank)));
		submenu.items.add(new RadioMenuItem("Tank", Land.GoodTank_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodTank_Grass)));
		submenu.items.add(new RadioMenuItem("Tank", Land.GoodTank_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodTank_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Bunker", Land.GoodBunker,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodBunker)));
		submenu.items.add(new RadioMenuItem("Bunker", Land.GoodBunker_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodBunker_Grass)));
		submenu.items.add(new RadioMenuItem("Bunker", Land.GoodBunker_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodBunker_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Radar", Land.GoodRadar,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodRadar)));
		submenu.items.add(new RadioMenuItem("Radar", Land.GoodRadar_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodRadar_Grass)));
		submenu.items.add(new RadioMenuItem("Radar", Land.GoodRadar_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodRadar_Sand)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Peasant", Land.Peasant,       1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Peasant)));
		submenu.items.add(new RadioMenuItem("Peasant", Land.Peasant_Grass, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Peasant_Grass)));
		submenu.items.add(new RadioMenuItem("Peasant", Land.Peasant_Sand,  1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Peasant_Sand)));
		
		submenu = new Menu("Special");
		menu.items.add(submenu);
		
		submenu.items.add(new RadioMenuItem("Transport Center", Land.Teleport5, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport5)));
		submenu.items.add(new MenuItem());
		submenu.items.add(new RadioMenuItem("Center",        Land.Teleport1, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport1)));
		submenu.items.add(new RadioMenuItem("Capital",       Land.Teleport4, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport4)));
		submenu.items.add(new RadioMenuItem("Jail",          Land.Teleport6, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport6)));
		submenu.items.add(new RadioMenuItem("Grand Hotel",   Land.Teleport7, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport7)));
		submenu.items.add(new RadioMenuItem("Bunker",        Land.Teleport8, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport8)));
		submenu.items.add(new RadioMenuItem("Palace",        Land.Teleport9, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport9)));
		submenu.items.add(new RadioMenuItem("Military Base", Land.Teleport11, 1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport11)));
		
		menu = new Menu("Hero");
		menus.add(menu);
		
		menu.items.add(new MenuItem("Hero",         Land.Hero,        () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.Hero)));
		menu.items.add(new MenuItem("Peasant",      Land.Peasant,     () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.Peasant)));
		menu.items.add(new MenuItem("Soldier",      Land.GoodSoldier, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodSoldier)));
		menu.items.add(new MenuItem("Officer",      Land.GoodOfficer, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodOfficer)));
		menu.items.add(new MenuItem("General",      Land.GoodGeneral, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodGeneral)));
		menu.items.add(new MenuItem("Tank",         Land.GoodTank,    () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodTank)));
		menu.items.add(new MenuItem("Robot",        Land.BadSoldier,  () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadSoldier)));
		menu.items.add(new MenuItem("Gray Officer", Land.BadOfficer,  () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadOfficer)));
		menu.items.add(new MenuItem("Gray General", Land.BadGeneral,  () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadGeneral)));
		menu.items.add(new MenuItem("Gray Tank",    Land.BadTank,     () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadTank)));
		menu.items.add(new MenuItem("Dark Knight",  Land.Dark_Knight, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.Dark_Knight)));
		menu.items.add(new MenuItem());
		menu.items.add(new CheckboxMenuItem("Fight",
			() -> DWConfiguration.getInstance().isFight(),
			() -> DWConfiguration.getInstance().setFight(!DWConfiguration.getInstance().isFight())
		));
		menu.items.add(new CheckboxMenuItem("Defense",
			() -> DWConfiguration.getInstance().isDefense(),
			() -> DWConfiguration.getInstance().setDefense(!DWConfiguration.getInstance().isDefense())
		));
		
		menu = new Menu("Commands");
		menus.add(menu);
		
		menu.items.add(new RadioMenuItem("Attack",  2, () -> DWConfiguration.getInstance().setAttackMode(true)));
		menu.items.add(new RadioMenuItem("Move to", 2, () -> DWConfiguration.getInstance().setAttackMode(false)));
		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Default Command", () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEFAULT, null)));
		menu.items.add(new MenuItem("Activate",        () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_ACTIVATE, null)));
		menu.items.add(new MenuItem("Deactivate",      () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEACTIVATE, null)));
		menu.items.add(new MenuItem("Stay",            () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_STAY, null)));
		menu.items.add(new MenuItem("Move Around",     () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_MOVE_AROUND, null)));
		menu.items.add(new MenuItem("Patrol",          () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_PATROL, null)));
		menu.items.add(new MenuItem("Defense",         () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEFENSE, null)));
		menu.items.add(new MenuItem());
		menu.items.add(new MenuItem("Open",            () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_OPEN_GATE, null)));
		menu.items.add(new MenuItem("Close",           () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_CLOSE_GATE, null)));
	}
}
