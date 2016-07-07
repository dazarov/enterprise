package com.dworld.ui;

import java.util.ArrayList;
import java.util.List;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWEngine;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;

public class DWToolbarStructure {
	public static class DWToolbar{
		public String label;
		public List<DWButton> buttons = new ArrayList<DWButton>();
		
		public DWToolbar(String lable){
			this.label = lable;
		}
	}
	
	public static class DWButton{
		public String imagePath;
		public int imageCode = -1;
		public Runnable runner;
		
		// Separator
		public DWButton(){
			
		}
		
		public DWButton(int imageCode, Runnable runner){
			this.imageCode = imageCode;
			this.runner = runner;
		}
		
		public DWButton(String imagePath, Runnable runner){
			this.imagePath = imagePath;
			this.runner = runner;
		}
	}
	
	private static DWToolbar gameToolBar;
	
	private static List<DWToolbar> toolbars;
	
	public DWToolbar getMainToolBar(){
		if(gameToolBar == null){
			DWConfiguration config = DWConfiguration.getInstance();
			DWEngine engine = config.getEngine();
			
			gameToolBar = new DWToolbar("DWorld toolbar");
			
			gameToolBar.buttons.add(new DWButton(Land.Hero, () -> engine.changeManCode(Land.Hero)));
			gameToolBar.buttons.add(new DWButton());
			gameToolBar.buttons.add(new DWButton(Land.GoodSoldier, () -> engine.changeManCode(Land.GoodSoldier)));
			gameToolBar.buttons.add(new DWButton(Land.GoodOfficer, () -> engine.changeManCode(Land.GoodOfficer)));
			gameToolBar.buttons.add(new DWButton(Land.GoodGeneral, () -> engine.changeManCode(Land.GoodGeneral)));
			gameToolBar.buttons.add(new DWButton(Land.GoodTank,    () -> engine.changeManCode(Land.GoodTank)));
			gameToolBar.buttons.add(new DWButton());
			gameToolBar.buttons.add(new DWButton(Land.BadSoldier, () -> engine.changeManCode(Land.BadSoldier)));
			gameToolBar.buttons.add(new DWButton(Land.BadOfficer, () -> engine.changeManCode(Land.BadOfficer)));
			gameToolBar.buttons.add(new DWButton(Land.BadGeneral, () -> engine.changeManCode(Land.BadGeneral)));
			gameToolBar.buttons.add(new DWButton(Land.BadTank,    () -> engine.changeManCode(Land.BadTank)));
			gameToolBar.buttons.add(new DWButton());
			gameToolBar.buttons.add(new DWButton(Land.Dark_Knight, () -> engine.changeManCode(Land.Dark_Knight)));
			gameToolBar.buttons.add(new DWButton());
			gameToolBar.buttons.add(new DWButton(Land.Peasant, () -> engine.changeManCode(Land.Peasant)));
		}
		return gameToolBar;
	}
	
	public List<DWToolbar> getPalette(){
		if(toolbars == null){
			toolbars = new ArrayList<>();
			
			DWConfiguration config = DWConfiguration.getInstance();
			
			
			DWToolbar toolbar = new DWToolbar("Palette 1");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton("resources/brush.png", () -> config.setDrawMode(DWConfiguration.DRAW_BRUSH)));
			toolbar.buttons.add(new DWButton("resources/line.png",  () -> config.setDrawMode(DWConfiguration.DRAW_LINE)));
			toolbar.buttons.add(new DWButton("resources/rect.png",  () -> config.setDrawMode(DWConfiguration.DRAW_RECTANGLE)));
			toolbar.buttons.add(new DWButton("resources/fill.png",  () -> config.setDrawMode(DWConfiguration.DRAW_FILL)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton("resources/FlipVertically.png",   () -> SelectionManager.flipVertically()));
			toolbar.buttons.add(new DWButton("resources/FlipHorizontally.png", () -> SelectionManager.flipHorizontally()));
			toolbar.buttons.add(new DWButton("resources/TurnRight.png",        () -> SelectionManager.turnRight()));
			toolbar.buttons.add(new DWButton("resources/TurnLeft.png",         () -> SelectionManager.turnLeft()));
			toolbar.buttons.add(new DWButton("resources/MoveUp.png",           () -> SelectionManager.moveUp()));
			toolbar.buttons.add(new DWButton("resources/MoveDown.png",         () -> SelectionManager.moveDown()));
			toolbar.buttons.add(new DWButton("resources/MoveRight.png",        () -> SelectionManager.moveRight()));
			toolbar.buttons.add(new DWButton("resources/MoveLeft.png",         () -> SelectionManager.moveLeft()));
			
			toolbar = new DWToolbar("Palette 2");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton(Land.Wall, () -> config.setSelectedMenu(Land.Wall)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Brick,      () -> config.setSelectedMenu(Land.Brick)));
			toolbar.buttons.add(new DWButton(Land.WhiteBrick, () -> config.setSelectedMenu(Land.WhiteBrick)));
			toolbar.buttons.add(new DWButton(Land.Stone,      () -> config.setSelectedMenu(Land.Stone)));
			toolbar.buttons.add(new DWButton(Land.BlackStone, () -> config.setSelectedMenu(Land.BlackStone)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Wood1, () -> config.setSelectedMenu(Land.Wood1)));
			toolbar.buttons.add(new DWButton(Land.Wood2, () -> config.setSelectedMenu(Land.Wood2)));
			toolbar.buttons.add(new DWButton(Land.Wood3, () -> config.setSelectedMenu(Land.Wood3)));
			toolbar.buttons.add(new DWButton(Land.Wood4, () -> config.setSelectedMenu(Land.Wood4)));
			
			toolbar = new DWToolbar("Palette 3");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton(Land.Train_Horizontal, () -> config.setSelectedMenu(Land.Train_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.Train_Vertical,   () -> config.setSelectedMenu(Land.Train_Vertical)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Station_Horizontal, () -> config.setSelectedMenu(Land.Station_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.Station_Vertical,   () -> config.setSelectedMenu(Land.Station_Vertical)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Rail_Horizontal,    () -> config.setSelectedMenu(Land.Rail_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.Rail_Vertical,      () -> config.setSelectedMenu(Land.Rail_Vertical)));
			toolbar.buttons.add(new DWButton(Land.Rail_Diagonal_Up,   () -> config.setSelectedMenu(Land.Rail_Diagonal_Up)));
			toolbar.buttons.add(new DWButton(Land.Rail_Diagonal_Down, () -> config.setSelectedMenu(Land.Rail_Diagonal_Down)));
			toolbar.buttons.add(new DWButton(Land.Rail_Up_Right,      () -> config.setSelectedMenu(Land.Rail_Up_Right)));
			toolbar.buttons.add(new DWButton(Land.Rail_Up_Left,       () -> config.setSelectedMenu(Land.Rail_Up_Left)));
			toolbar.buttons.add(new DWButton(Land.Rail_Down_Right,    () -> config.setSelectedMenu(Land.Rail_Down_Right)));
			toolbar.buttons.add(new DWButton(Land.Rail_Down_Left,     () -> config.setSelectedMenu(Land.Rail_Down_Left)));
			toolbar.buttons.add(new DWButton(Land.Rail_Left_Up,       () -> config.setSelectedMenu(Land.Rail_Left_Up)));
			toolbar.buttons.add(new DWButton(Land.Rail_Left_Down,     () -> config.setSelectedMenu(Land.Rail_Left_Down)));
			toolbar.buttons.add(new DWButton(Land.Rail_Right_Up,      () -> config.setSelectedMenu(Land.Rail_Right_Up)));
			toolbar.buttons.add(new DWButton(Land.Rail_Right_Down,    () -> config.setSelectedMenu(Land.Rail_Right_Down)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Rail_Vertical_Cross, () -> config.setSelectedMenu(Land.Rail_Vertical_Cross)));
			toolbar.buttons.add(new DWButton(Land.Rail_Diagonal_Cross, () -> config.setSelectedMenu(Land.Rail_Diagonal_Cross)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.WarTrain_Horizontal, () -> config.setSelectedMenu(Land.WarTrain_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.WarTrain_Vertical,   () -> config.setSelectedMenu(Land.WarTrain_Vertical)));
			
			toolbar = new DWToolbar("Palette 4");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalSteelGate,   () -> config.setSelectedMenu(Land.ClosedVerticalSteelGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalSteelGate, () -> config.setSelectedMenu(Land.ClosedHorizontalSteelGate)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalWoodGate,       () -> config.setSelectedMenu(Land.ClosedVerticalWoodGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalWoodGate,     () -> config.setSelectedMenu(Land.ClosedHorizontalWoodGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalConcreteGate,   () -> config.setSelectedMenu(Land.ClosedVerticalConcreteGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalConcreteGate, () -> config.setSelectedMenu(Land.ClosedHorizontalConcreteGate)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalBrickGate,   () -> config.setSelectedMenu(Land.ClosedVerticalBrickGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalBrickGate, () -> config.setSelectedMenu(Land.ClosedHorizontalBrickGate)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Ammo,    () -> config.setSelectedMenu(Land.Ammo)));
			toolbar.buttons.add(new DWButton(Land.Grenade, () -> config.setSelectedMenu(Land.Grenade)));
			toolbar.buttons.add(new DWButton(Land.Rocket,  () -> config.setSelectedMenu(Land.Rocket)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Mine,       () -> config.setSelectedMenu(Land.Mine)));
			toolbar.buttons.add(new DWButton(Land.Mine_Grass, () -> config.setSelectedMenu(Land.Mine_Grass)));
			toolbar.buttons.add(new DWButton(Land.Mine_Sand,  () -> config.setSelectedMenu(Land.Mine_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Peasant,       () -> config.setSelectedMenu(Land.Peasant)));
			toolbar.buttons.add(new DWButton(Land.Peasant_Grass, () -> config.setSelectedMenu(Land.Peasant_Grass)));
			toolbar.buttons.add(new DWButton(Land.Peasant_Sand,  () -> config.setSelectedMenu(Land.Peasant_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Teleport5, () -> config.setSelectedMenu(Land.Teleport5)));
			
			toolbar = new DWToolbar("Palette 5");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton(Land.BadGeneral,       () -> config.setSelectedMenu(Land.BadGeneral)));
			toolbar.buttons.add(new DWButton(Land.BadGeneral_Grass, () -> config.setSelectedMenu(Land.BadGeneral_Grass)));
			toolbar.buttons.add(new DWButton(Land.BadGeneral_Sand,  () -> config.setSelectedMenu(Land.BadGeneral_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadOfficer,       () -> config.setSelectedMenu(Land.BadOfficer)));
			toolbar.buttons.add(new DWButton(Land.BadOfficer_Grass, () -> config.setSelectedMenu(Land.BadOfficer_Grass)));
			toolbar.buttons.add(new DWButton(Land.BadOfficer_Sand,  () -> config.setSelectedMenu(Land.BadOfficer_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadSoldier,       () -> config.setSelectedMenu(Land.BadSoldier)));
			toolbar.buttons.add(new DWButton(Land.BadSoldier_Grass, () -> config.setSelectedMenu(Land.BadSoldier_Grass)));
			toolbar.buttons.add(new DWButton(Land.BadSoldier_Sand,  () -> config.setSelectedMenu(Land.BadSoldier_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadTank,       () -> config.setSelectedMenu(Land.BadTank)));
			toolbar.buttons.add(new DWButton(Land.BadTank_Grass, () -> config.setSelectedMenu(Land.BadTank_Grass)));
			toolbar.buttons.add(new DWButton(Land.BadTank_Sand,  () -> config.setSelectedMenu(Land.BadTank_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadBunker,       () -> config.setSelectedMenu(Land.BadBunker)));
			toolbar.buttons.add(new DWButton(Land.BadBunker_Grass, () -> config.setSelectedMenu(Land.BadBunker_Grass)));
			toolbar.buttons.add(new DWButton(Land.BadBunker_Sand,  () -> config.setSelectedMenu(Land.BadBunker_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadRadar,       () -> config.setSelectedMenu(Land.BadRadar)));
			toolbar.buttons.add(new DWButton(Land.BadRadar_Grass, () -> config.setSelectedMenu(Land.BadRadar_Grass)));
			toolbar.buttons.add(new DWButton(Land.BadRadar_Sand,  () -> config.setSelectedMenu(Land.BadRadar_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Mountain, () -> config.setSelectedMenu(Land.Mountain)));
			toolbar.buttons.add(new DWButton(Land.Water,    () -> config.setSelectedMenu(Land.Water)));
			toolbar.buttons.add(new DWButton(Land.Sand,     () -> config.setSelectedMenu(Land.Sand)));
			
			toolbar = new DWToolbar("Palette 6");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton(Land.GoodGeneral,       () -> config.setSelectedMenu(Land.GoodGeneral)));
			toolbar.buttons.add(new DWButton(Land.GoodGeneral_Grass, () -> config.setSelectedMenu(Land.GoodGeneral_Grass)));
			toolbar.buttons.add(new DWButton(Land.GoodGeneral_Sand,  () -> config.setSelectedMenu(Land.GoodGeneral_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodOfficer,       () -> config.setSelectedMenu(Land.GoodOfficer)));
			toolbar.buttons.add(new DWButton(Land.GoodOfficer_Grass, () -> config.setSelectedMenu(Land.GoodOfficer_Grass)));
			toolbar.buttons.add(new DWButton(Land.GoodOfficer_Sand,  () -> config.setSelectedMenu(Land.GoodOfficer_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodSoldier,       () -> config.setSelectedMenu(Land.GoodSoldier)));
			toolbar.buttons.add(new DWButton(Land.GoodSoldier_Grass, () -> config.setSelectedMenu(Land.GoodSoldier_Grass)));
			toolbar.buttons.add(new DWButton(Land.GoodSoldier_Sand,  () -> config.setSelectedMenu(Land.GoodSoldier_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodTank,       () -> config.setSelectedMenu(Land.GoodTank)));
			toolbar.buttons.add(new DWButton(Land.GoodTank_Grass, () -> config.setSelectedMenu(Land.GoodTank_Grass)));
			toolbar.buttons.add(new DWButton(Land.GoodTank_Sand,  () -> config.setSelectedMenu(Land.GoodTank_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodBunker,       () -> config.setSelectedMenu(Land.GoodBunker)));
			toolbar.buttons.add(new DWButton(Land.GoodBunker_Grass, () -> config.setSelectedMenu(Land.GoodBunker_Grass)));
			toolbar.buttons.add(new DWButton(Land.GoodBunker_Sand,  () -> config.setSelectedMenu(Land.GoodBunker_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodRadar,       () -> config.setSelectedMenu(Land.GoodRadar)));
			toolbar.buttons.add(new DWButton(Land.GoodRadar_Grass, () -> config.setSelectedMenu(Land.GoodRadar_Grass)));
			toolbar.buttons.add(new DWButton(Land.GoodRadar_Sand,  () -> config.setSelectedMenu(Land.GoodRadar_Sand)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Grass, () -> config.setSelectedMenu(Land.Grass)));
			toolbar.buttons.add(new DWButton(Land.Tree1, () -> config.setSelectedMenu(Land.Tree1)));
			toolbar.buttons.add(new DWButton(Land.Tree2, () -> config.setSelectedMenu(Land.Tree2)));
			toolbar.buttons.add(new DWButton(Land.Tree3, () -> config.setSelectedMenu(Land.Tree3)));
		}
		return toolbars;
	}
}
