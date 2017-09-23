package com.dworld.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWEngine;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.units.Unit;

public class DWMenuStructure {
	public static class DWMenuItem{
		public String label;
		public Land imageCode = Land.Vacuum;
		public Runnable runner;
		
		// Separator
		public DWMenuItem(){
		}
		
		public DWMenuItem(String label, Runnable runner){
			this.label = label;
			this.runner = runner;
		}
		
		public DWMenuItem(String label, Land imageCode, Runnable runner){
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
		
		public DWRadioMenuItem(String label, Land imageCode, int buttonGroupId, Runnable runner){
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

		DWMenu menu = new DWMenu(DWMessage.FILE.get());
		menus.add(menu);

		menu.items.add(new DWMenuItem(DWMessage.LOAD.get(),        () -> config.getLauncher().load(DWConfiguration.SAVE_FILE)));
		menu.items.add(new DWMenuItem(DWMessage.SAVE.get(),        () -> config.getLauncher().save(DWConfiguration.SAVE_FILE)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.LOAD_BACKUP.get(), () -> config.getLauncher().load(DWConfiguration.BACKUP_FILE)));
		menu.items.add(new DWMenuItem(DWMessage.SAVE_BACKUP.get(), () -> config.getLauncher().save(DWConfiguration.BACKUP_FILE)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.LOAD_TEST.get(),   () -> config.getLauncher().load(DWConfiguration.TEST_FILE)));
		menu.items.add(new DWMenuItem(DWMessage.SAVE_TEST.get(),   () -> config.getLauncher().save(DWConfiguration.TEST_FILE)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.EXIT.get(),        () -> {if(config.getUI().exitConfirmation())System.exit(0);}));
		
		menu = new DWMenu(DWMessage.GAME.get());
		menus.add(menu);
		
		menu.items.add(new DWMenuItem(DWMessage.MAP.get(),      () -> config.getUI().showMap()));
		menu.items.add(new DWMenuItem(DWMessage.MINIMAP.get(), () -> config.getUI().toggleMinimap()));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.PAUSE.get(),    () -> engine.pause(true)));
		menu.items.add(new DWMenuItem(DWMessage.GO.get(),       () -> engine.pause(false)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.INFO.get(),     () -> config.getUI().showInfoScreen()));
		
		menu = new DWMenu(DWMessage.BUILD.get());
		menus.add(menu);
		
		menu.items.add(new DWCheckboxMenuItem(DWMessage.BUILD_MODE.get(), () -> config.isBuildMode(),
		() -> {
			config.setBuildMode(!config.isBuildMode());
			engine.pause(config.isBuildMode());
			if(config.isBuildMode()){
				DWConfiguration.getInstance().getUI().showPalette();
			}else{
				DWConfiguration.getInstance().getUI().hidePalette();
			}
			
		}));

		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.COPY.get(),  () -> SelectionManager.copy()));
		menu.items.add(new DWMenuItem(DWMessage.PASTE.get(), () -> SelectionManager.paste()));
		menu.items.add(new DWMenuItem());
		
		DWMenu submenu = new DWMenu(DWMessage.LANDSCAPE.get());
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem(DWMessage.WALL.get(), Land.Wall,              1, () -> config.setSelectedCode(Land.Wall)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.BRICK.get(), Land.Brick,            1, () -> config.setSelectedCode(Land.Brick)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.WHITE_BRICK.get(), Land.WhiteBrick, 1, () -> config.setSelectedCode(Land.WhiteBrick)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.WHITE_STONE.get(), Land.Stone,      1, () -> config.setSelectedCode(Land.Stone)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BLACK_STONE.get(), Land.BlackStone, 1, () -> config.setSelectedCode(Land.BlackStone)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.WOOD.get(), Land.Wood1,             1, () -> config.setSelectedCode(Land.Wood1)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.WOOD.get(), Land.Wood2,             1, () -> config.setSelectedCode(Land.Wood2)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.WOOD.get(), Land.Wood3,             1, () -> config.setSelectedCode(Land.Wood3)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.WOOD.get(), Land.Wood4,             1, () -> config.setSelectedCode(Land.Wood4)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.MOUNTAIN.get(), Land.Mountain,      1, () -> config.setSelectedCode(Land.Mountain)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.WATER.get(), Land.Water,            1, () -> config.setSelectedCode(Land.Water)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.SAND.get(), Land.Sand,              1, () -> config.setSelectedCode(Land.Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.GRASS.get(), Land.Grass,            1, () -> config.setSelectedCode(Land.Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.TREE.get(), Land.Tree1,             1, () -> config.setSelectedCode(Land.Tree1)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.TREE.get(), Land.Tree2,             1, () -> config.setSelectedCode(Land.Tree2)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.TREE.get(), Land.Tree3,             1, () -> config.setSelectedCode(Land.Tree3)));
		
		submenu = new DWMenu(DWMessage.RAILROAD.get());
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem(DWMessage.TRAIN.get(), Land.Train_Horizontal, 1, () -> config.setSelectedCode(Land.Train_Horizontal)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.TRAIN.get(), Land.Train_Vertical,   1, () -> config.setSelectedCode(Land.Train_Vertical)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.STATION.get(), Land.Station_Horizontal, 1, () -> config.setSelectedCode(Land.Station_Horizontal)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.STATION.get(), Land.Station_Vertical,   1, () -> config.setSelectedCode(Land.Station_Vertical)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Horizontal,    1, () -> config.setSelectedCode(Land.Rail_Horizontal)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Vertical,      1, () -> config.setSelectedCode(Land.Rail_Vertical)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Diagonal_Up,   1, () -> config.setSelectedCode(Land.Rail_Diagonal_Up)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Diagonal_Down, 1, () -> config.setSelectedCode(Land.Rail_Diagonal_Down)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Up_Right,      1, () -> config.setSelectedCode(Land.Rail_Up_Right)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Up_Left,       1, () -> config.setSelectedCode(Land.Rail_Up_Left)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Down_Right,    1, () -> config.setSelectedCode(Land.Rail_Down_Right)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Down_Left,     1, () -> config.setSelectedCode(Land.Rail_Down_Left)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Left_Up,       1, () -> config.setSelectedCode(Land.Rail_Left_Up)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Left_Down,     1, () -> config.setSelectedCode(Land.Rail_Left_Down)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Right_Up,      1, () -> config.setSelectedCode(Land.Rail_Right_Up)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Right_Down,    1, () -> config.setSelectedCode(Land.Rail_Right_Down)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Vertical_Cross, 1, () -> config.setSelectedCode(Land.Rail_Vertical_Cross)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.RAIL.get(), Land.Rail_Diagonal_Cross, 1, () -> config.setSelectedCode(Land.Rail_Diagonal_Cross)));
		
		submenu = new DWMenu(DWMessage.DOORS.get());
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem(DWMessage.VERTICAL_STEEL_DOOR.get(),   Land.ClosedVerticalSteelGate,   1, () -> config.setSelectedCode(Land.ClosedVerticalSteelGate)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.HORIZONTAL_STEEL_DOOR.get(), Land.ClosedHorizontalSteelGate, 1, () -> config.setSelectedCode(Land.ClosedHorizontalSteelGate)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.VERTICAL_WOOD_DOOR.get(),   Land.ClosedVerticalWoodGate,   1, () -> config.setSelectedCode(Land.ClosedVerticalWoodGate)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.HORIZONTAL_WOOD_DOOR.get(), Land.ClosedHorizontalWoodGate, 1, () -> config.setSelectedCode(Land.ClosedHorizontalWoodGate)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.VERTICAL_CONCRETE_DOOR.get(),   Land.ClosedVerticalConcreteGate,   1, () -> config.setSelectedCode(Land.ClosedVerticalConcreteGate)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.HORIZONTAL_CONCRETE_DOOR.get(), Land.ClosedHorizontalConcreteGate, 1, () -> config.setSelectedCode(Land.ClosedHorizontalConcreteGate)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.VETICAL_BRICK_DOOR.get(),   Land.ClosedVerticalBrickGate,   1, () -> config.setSelectedCode(Land.ClosedVerticalBrickGate)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.HORIZONTAL_BRICK_DOOR.get(), Land.ClosedHorizontalBrickGate, 1, () -> config.setSelectedCode(Land.ClosedHorizontalBrickGate)));
		
		submenu = new DWMenu(DWMessage.AMMUNITION.get());
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem(DWMessage.AMMO.get(),    Land.Ammo,    1, () -> config.setSelectedCode(Land.Ammo)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GRENADE.get(), Land.Grenade, 1, () -> config.setSelectedCode(Land.Grenade)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.ROCKET.get(),  Land.Rocket,  1, () -> config.setSelectedCode(Land.Rocket)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.MINE.get(), Land.Mine,       1, () -> config.setSelectedCode(Land.Mine)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.MINE.get(), Land.Mine_Grass, 1, () -> config.setSelectedCode(Land.Mine_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.MINE.get(), Land.Mine_Sand,  1, () -> config.setSelectedCode(Land.Mine_Sand)));
		
		submenu = new DWMenu(DWMessage.ENEMIES.get());
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_GENERAL.get(), Land.BadGeneral,       1, () -> config.setSelectedCode(Land.BadGeneral)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_GENERAL.get(), Land.BadGeneral_Grass, 1, () -> config.setSelectedCode(Land.BadGeneral_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_GENERAL.get(), Land.BadGeneral_Sand,  1, () -> config.setSelectedCode(Land.BadGeneral_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_OFFICER.get(), Land.BadOfficer,       1, () -> config.setSelectedCode(Land.BadOfficer)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_OFFICER.get(), Land.BadOfficer_Grass, 1, () -> config.setSelectedCode(Land.BadOfficer_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_OFFICER.get(), Land.BadOfficer_Sand,  1, () -> config.setSelectedCode(Land.BadOfficer_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_SOLDIER.get(), Land.BadSoldier,       1, () -> config.setSelectedCode(Land.BadSoldier)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_SOLDIER.get(), Land.BadSoldier_Grass, 1, () -> config.setSelectedCode(Land.BadSoldier_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_SOLDIER.get(), Land.BadSoldier_Sand,  1, () -> config.setSelectedCode(Land.BadSoldier_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_TANK.get(), Land.BadTank,       1, () -> config.setSelectedCode(Land.BadTank)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_TANK.get(), Land.BadTank_Grass, 1, () -> config.setSelectedCode(Land.BadTank_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_TANK.get(), Land.BadTank_Sand,  1, () -> config.setSelectedCode(Land.BadTank_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_BUNKER.get(), Land.BadBunker,       1, () -> config.setSelectedCode(Land.BadBunker)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_BUNKER.get(), Land.BadBunker_Grass, 1, () -> config.setSelectedCode(Land.BadBunker_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_BUNKER.get(), Land.BadBunker_Sand,  1, () -> config.setSelectedCode(Land.BadBunker_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_RADAR.get(), Land.BadRadar,       1, () -> config.setSelectedCode(Land.BadRadar)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_RADAR.get(), Land.BadRadar_Grass, 1, () -> config.setSelectedCode(Land.BadRadar_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BAD_RADAR.get(), Land.BadRadar_Sand,  1, () -> config.setSelectedCode(Land.BadRadar_Sand)));
		
		submenu = new DWMenu(DWMessage.CITIZENS.get());
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_GENERAL.get(), Land.GoodGeneral,       1, () -> config.setSelectedCode(Land.GoodGeneral)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_GENERAL.get(), Land.GoodGeneral_Grass, 1, () -> config.setSelectedCode(Land.GoodGeneral_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_GENERAL.get(), Land.GoodGeneral_Sand,  1, () -> config.setSelectedCode(Land.GoodGeneral_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_OFFICER.get(), Land.GoodOfficer,       1, () -> config.setSelectedCode(Land.GoodOfficer)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_OFFICER.get(), Land.GoodOfficer_Grass, 1, () -> config.setSelectedCode(Land.GoodOfficer_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_OFFICER.get(), Land.GoodOfficer_Sand,  1, () -> config.setSelectedCode(Land.GoodOfficer_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_SOLDIER.get(), Land.GoodSoldier,       1, () -> config.setSelectedCode(Land.GoodSoldier)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_SOLDIER.get(), Land.GoodSoldier_Grass, 1, () -> config.setSelectedCode(Land.GoodSoldier_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_SOLDIER.get(), Land.GoodSoldier_Sand,  1, () -> config.setSelectedCode(Land.GoodSoldier_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_TANK.get(), Land.GoodTank,       1, () -> config.setSelectedCode(Land.GoodTank)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_TANK.get(), Land.GoodTank_Grass, 1, () -> config.setSelectedCode(Land.GoodTank_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_TANK.get(), Land.GoodTank_Sand,  1, () -> config.setSelectedCode(Land.GoodTank_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_BUNKER.get(), Land.GoodBunker,       1, () -> config.setSelectedCode(Land.GoodBunker)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_BUNKER.get(), Land.GoodBunker_Grass, 1, () -> config.setSelectedCode(Land.GoodBunker_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_BUNKER.get(), Land.GoodBunker_Sand,  1, () -> config.setSelectedCode(Land.GoodBunker_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_RADAR.get(), Land.GoodRadar,       1, () -> config.setSelectedCode(Land.GoodRadar)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_RADAR.get(), Land.GoodRadar_Grass, 1, () -> config.setSelectedCode(Land.GoodRadar_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GOOD_RADAR.get(), Land.GoodRadar_Sand,  1, () -> config.setSelectedCode(Land.GoodRadar_Sand)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.PEASANT.get(), Land.Peasant,       1, () -> config.setSelectedCode(Land.Peasant)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.PEASANT.get(), Land.Peasant_Grass, 1, () -> config.setSelectedCode(Land.Peasant_Grass)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.PEASANT.get(), Land.Peasant_Sand,  1, () -> config.setSelectedCode(Land.Peasant_Sand)));
		
		submenu = new DWMenu(DWMessage.SPECIAL.get());
		menu.items.add(submenu);
		
		submenu.items.add(new DWRadioMenuItem(DWMessage.TRANSPORT_CENTER.get(), Land.Teleport5, 1, () -> config.setSelectedCode(Land.Teleport5)));
		submenu.items.add(new DWMenuItem());
		submenu.items.add(new DWRadioMenuItem(DWMessage.CENTER.get(),        Land.Teleport1, 1, () -> config.setSelectedCode(Land.Teleport1)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.CAPITAL.get(),       Land.Teleport4, 1, () -> config.setSelectedCode(Land.Teleport4)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.JAIL.get(),          Land.Teleport6, 1, () -> config.setSelectedCode(Land.Teleport6)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.GRAND_HOTEL.get(),   Land.Teleport7, 1, () -> config.setSelectedCode(Land.Teleport7)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.BUNKER.get(),        Land.Teleport8, 1, () -> config.setSelectedCode(Land.Teleport8)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.PALACE.get(),        Land.Teleport9, 1, () -> config.setSelectedCode(Land.Teleport9)));
		submenu.items.add(new DWRadioMenuItem(DWMessage.MILITARY_BASE.get(), Land.Teleport11, 1, () -> config.setSelectedCode(Land.Teleport11)));
		
		menu = new DWMenu(DWMessage.HERO.get());
		menus.add(menu);
		
		menu.items.add(new DWMenuItem(DWMessage.HERO.get(),         Land.Hero,        () -> engine.changeManCode(Land.Hero)));
		menu.items.add(new DWMenuItem(DWMessage.PEASANT.get(),      Land.Peasant,     () -> engine.changeManCode(Land.Peasant)));
		menu.items.add(new DWMenuItem(DWMessage.GOOD_SOLDIER.get(),      Land.GoodSoldier, () -> engine.changeManCode(Land.GoodSoldier)));
		menu.items.add(new DWMenuItem(DWMessage.GOOD_OFFICER.get(),      Land.GoodOfficer, () -> engine.changeManCode(Land.GoodOfficer)));
		menu.items.add(new DWMenuItem(DWMessage.GOOD_GENERAL.get(),      Land.GoodGeneral, () -> engine.changeManCode(Land.GoodGeneral)));
		menu.items.add(new DWMenuItem(DWMessage.GOOD_TANK.get(),         Land.GoodTank,    () -> engine.changeManCode(Land.GoodTank)));
		menu.items.add(new DWMenuItem(DWMessage.BAD_SOLDIER.get(),        Land.BadSoldier,  () -> engine.changeManCode(Land.BadSoldier)));
		menu.items.add(new DWMenuItem(DWMessage.BAD_OFFICER.get(), Land.BadOfficer,  () -> engine.changeManCode(Land.BadOfficer)));
		menu.items.add(new DWMenuItem(DWMessage.BAD_GENERAL.get(), Land.BadGeneral,  () -> engine.changeManCode(Land.BadGeneral)));
		menu.items.add(new DWMenuItem(DWMessage.BAD_TANK.get(),    Land.BadTank,     () -> engine.changeManCode(Land.BadTank)));
		menu.items.add(new DWMenuItem(DWMessage.DARK_KNIGHT.get(),  Land.Dark_Knight, () -> engine.changeManCode(Land.Dark_Knight)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWCheckboxMenuItem(DWMessage.FIGHT.get(),
			() -> config.isFight(),
			() -> config.setFight(!config.isFight())
		));
		menu.items.add(new DWCheckboxMenuItem(DWMessage.DEFENSE.get(),
			() -> config.isDefense(),
			() -> config.setDefense(!config.isDefense())
		));
		
		menu = new DWMenu(DWMessage.COMMANDS.get());
		menus.add(menu);
		
		menu.items.add(new DWRadioMenuItem(DWMessage.ATTACK.get(),  2, () -> config.setAttackMode(true)));
		menu.items.add(new DWRadioMenuItem(DWMessage.MOVE_TO.get(), 2, () -> config.setAttackMode(false)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.DEFAULT_COMMAND.get(), () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEFAULT, null)));
		menu.items.add(new DWMenuItem(DWMessage.ACTIVATE.get(),        () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_ACTIVATE, null)));
		menu.items.add(new DWMenuItem(DWMessage.DEACTIVATE.get(),      () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEACTIVATE, null)));
		menu.items.add(new DWMenuItem(DWMessage.STAY.get(),            () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_STAY, null)));
		menu.items.add(new DWMenuItem(DWMessage.MOVE_AROUND.get(),     () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_MOVE_AROUND, null)));
		menu.items.add(new DWMenuItem(DWMessage.PATROL.get(),          () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_PATROL, null)));
		menu.items.add(new DWMenuItem(DWMessage.DEFENSE.get(),         () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_DEFENSE, null)));
		menu.items.add(new DWMenuItem());
		menu.items.add(new DWMenuItem(DWMessage.OPEN.get(),            () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_OPEN_GATE, null)));
		menu.items.add(new DWMenuItem(DWMessage.CLOSE.get(),           () -> SelectionManager.sendCommand(Unit.EXTERNAL_COMMAND_CLOSE_GATE, null)));
	}
}
