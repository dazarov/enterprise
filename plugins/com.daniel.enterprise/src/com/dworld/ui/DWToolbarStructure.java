package com.dworld.ui;

import java.util.ArrayList;
import java.util.List;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWEngine;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;

public class DWToolbarStructure {
	public static class DWToolBar{
		public String label;
		public List<DWButton> buttons = new ArrayList<DWButton>();
		
		public DWToolBar(String lable){
			this.label = lable;
		}
	}
	
	public static class DWButton{
		public String imagePath;
		public int imageCode;
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
	
	public DWToolBar gameToolBar;
	
	public DWToolBar editorToolBar;
	
	public DWToolbarStructure(){
		DWConfiguration config = DWConfiguration.getInstance();
		DWEngine engine = config.getEngine();
		
		gameToolBar = new DWToolBar("DWorld toolbar");
		
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
		
		editorToolBar = new DWToolBar("Palette");
		
		editorToolBar.buttons.add(new DWButton("resources/brush.png", () -> config.setDrawMode(DWConfiguration.DRAW_BRUSH)));
		editorToolBar.buttons.add(new DWButton("resources/line.png",  () -> config.setDrawMode(DWConfiguration.DRAW_LINE)));
		editorToolBar.buttons.add(new DWButton("resources/rect.png",  () -> config.setDrawMode(DWConfiguration.DRAW_RECTANGLE)));
		editorToolBar.buttons.add(new DWButton("resources/fill.png",  () -> config.setDrawMode(DWConfiguration.DRAW_FILL)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton("resources/FlipVertically.png",   () -> SelectionManager.flipVertically()));
		editorToolBar.buttons.add(new DWButton("resources/FlipHorizontally.png", () -> SelectionManager.flipHorizontally()));
		editorToolBar.buttons.add(new DWButton("resources/TurnRight.png",        () -> SelectionManager.turnRight()));
		editorToolBar.buttons.add(new DWButton("resources/TurnLeft.png",         () -> SelectionManager.turnLeft()));
		editorToolBar.buttons.add(new DWButton("resources/MoveUp.png",           () -> SelectionManager.moveUp()));
		editorToolBar.buttons.add(new DWButton("resources/MoveDown.png",         () -> SelectionManager.moveDown()));
		editorToolBar.buttons.add(new DWButton("resources/MoveRight.png",        () -> SelectionManager.moveRight()));
		editorToolBar.buttons.add(new DWButton("resources/MoveLeft.png",         () -> SelectionManager.moveLeft()));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Wall, () -> config.setSelectedMenu(Land.Wall)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Brick,      () -> config.setSelectedMenu(Land.Brick)));
		editorToolBar.buttons.add(new DWButton(Land.WhiteBrick, () -> config.setSelectedMenu(Land.WhiteBrick)));
		editorToolBar.buttons.add(new DWButton(Land.Stone,      () -> config.setSelectedMenu(Land.Stone)));
		editorToolBar.buttons.add(new DWButton(Land.BlackStone, () -> config.setSelectedMenu(Land.BlackStone)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Wood1, () -> config.setSelectedMenu(Land.Wood1)));
		editorToolBar.buttons.add(new DWButton(Land.Wood2, () -> config.setSelectedMenu(Land.Wood2)));
		editorToolBar.buttons.add(new DWButton(Land.Wood3, () -> config.setSelectedMenu(Land.Wood3)));
		editorToolBar.buttons.add(new DWButton(Land.Wood4, () -> config.setSelectedMenu(Land.Wood4)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Train_Horizontal, () -> config.setSelectedMenu(Land.Train_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.Train_Vertical,   () -> config.setSelectedMenu(Land.Train_Vertical)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Station_Horizontal, () -> config.setSelectedMenu(Land.Station_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.Station_Vertical,   () -> config.setSelectedMenu(Land.Station_Vertical)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Rail_Horizontal,    () -> config.setSelectedMenu(Land.Rail_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Vertical,      () -> config.setSelectedMenu(Land.Rail_Vertical)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Diagonal_Up,   () -> config.setSelectedMenu(Land.Rail_Diagonal_Up)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Diagonal_Down, () -> config.setSelectedMenu(Land.Rail_Diagonal_Down)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Up_Right,      () -> config.setSelectedMenu(Land.Rail_Up_Right)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Up_Left,       () -> config.setSelectedMenu(Land.Rail_Up_Left)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Down_Right,    () -> config.setSelectedMenu(Land.Rail_Down_Right)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Down_Left,     () -> config.setSelectedMenu(Land.Rail_Down_Left)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Left_Up,       () -> config.setSelectedMenu(Land.Rail_Left_Up)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Left_Down,     () -> config.setSelectedMenu(Land.Rail_Left_Down)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Right_Up,      () -> config.setSelectedMenu(Land.Rail_Right_Up)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Right_Down,    () -> config.setSelectedMenu(Land.Rail_Right_Down)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Rail_Vertical_Cross, () -> config.setSelectedMenu(Land.Rail_Vertical_Cross)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Diagonal_Cross, () -> config.setSelectedMenu(Land.Rail_Diagonal_Cross)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.WarTrain_Horizontal, () -> config.setSelectedMenu(Land.WarTrain_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.WarTrain_Vertical,   () -> config.setSelectedMenu(Land.WarTrain_Vertical)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalSteelGate,   () -> config.setSelectedMenu(Land.ClosedVerticalSteelGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalSteelGate, () -> config.setSelectedMenu(Land.ClosedHorizontalSteelGate)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalWoodGate,       () -> config.setSelectedMenu(Land.ClosedVerticalWoodGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalWoodGate,     () -> config.setSelectedMenu(Land.ClosedHorizontalWoodGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalConcreteGate,   () -> config.setSelectedMenu(Land.ClosedVerticalConcreteGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalConcreteGate, () -> config.setSelectedMenu(Land.ClosedHorizontalConcreteGate)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalBrickGate,   () -> config.setSelectedMenu(Land.ClosedVerticalBrickGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalBrickGate, () -> config.setSelectedMenu(Land.ClosedHorizontalBrickGate)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Ammo,    () -> config.setSelectedMenu(Land.Ammo)));
		editorToolBar.buttons.add(new DWButton(Land.Grenade, () -> config.setSelectedMenu(Land.Grenade)));
		editorToolBar.buttons.add(new DWButton(Land.Rocket,  () -> config.setSelectedMenu(Land.Rocket)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Mine,       () -> config.setSelectedMenu(Land.Mine)));
		editorToolBar.buttons.add(new DWButton(Land.Mine_Grass, () -> config.setSelectedMenu(Land.Mine_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.Mine_Sand,  () -> config.setSelectedMenu(Land.Mine_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Peasant,       () -> config.setSelectedMenu(Land.Peasant)));
		editorToolBar.buttons.add(new DWButton(Land.Peasant_Grass, () -> config.setSelectedMenu(Land.Peasant_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.Peasant_Sand,  () -> config.setSelectedMenu(Land.Peasant_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Teleport5, () -> config.setSelectedMenu(Land.Teleport5)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadGeneral,       () -> config.setSelectedMenu(Land.BadGeneral)));
		editorToolBar.buttons.add(new DWButton(Land.BadGeneral_Grass, () -> config.setSelectedMenu(Land.BadGeneral_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadGeneral_Sand,  () -> config.setSelectedMenu(Land.BadGeneral_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadOfficer,       () -> config.setSelectedMenu(Land.BadOfficer)));
		editorToolBar.buttons.add(new DWButton(Land.BadOfficer_Grass, () -> config.setSelectedMenu(Land.BadOfficer_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadOfficer_Sand,  () -> config.setSelectedMenu(Land.BadOfficer_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadSoldier,       () -> config.setSelectedMenu(Land.BadSoldier)));
		editorToolBar.buttons.add(new DWButton(Land.BadSoldier_Grass, () -> config.setSelectedMenu(Land.BadSoldier_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadSoldier_Sand,  () -> config.setSelectedMenu(Land.BadSoldier_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadTank,       () -> config.setSelectedMenu(Land.BadTank)));
		editorToolBar.buttons.add(new DWButton(Land.BadTank_Grass, () -> config.setSelectedMenu(Land.BadTank_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadTank_Sand,  () -> config.setSelectedMenu(Land.BadTank_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadBunker,       () -> config.setSelectedMenu(Land.BadBunker)));
		editorToolBar.buttons.add(new DWButton(Land.BadBunker_Grass, () -> config.setSelectedMenu(Land.BadBunker_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadBunker_Sand,  () -> config.setSelectedMenu(Land.BadBunker_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadRadar,       () -> config.setSelectedMenu(Land.BadRadar)));
		editorToolBar.buttons.add(new DWButton(Land.BadRadar_Grass, () -> config.setSelectedMenu(Land.BadRadar_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadRadar_Sand,  () -> config.setSelectedMenu(Land.BadRadar_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Mountain, () -> config.setSelectedMenu(Land.Mountain)));
		editorToolBar.buttons.add(new DWButton(Land.Water,    () -> config.setSelectedMenu(Land.Water)));
		editorToolBar.buttons.add(new DWButton(Land.Sand,     () -> config.setSelectedMenu(Land.Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodGeneral,       () -> config.setSelectedMenu(Land.GoodGeneral)));
		editorToolBar.buttons.add(new DWButton(Land.GoodGeneral_Grass, () -> config.setSelectedMenu(Land.GoodGeneral_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodGeneral_Sand,  () -> config.setSelectedMenu(Land.GoodGeneral_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodOfficer,       () -> config.setSelectedMenu(Land.GoodOfficer)));
		editorToolBar.buttons.add(new DWButton(Land.GoodOfficer_Grass, () -> config.setSelectedMenu(Land.GoodOfficer_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodOfficer_Sand,  () -> config.setSelectedMenu(Land.GoodOfficer_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodSoldier,       () -> config.setSelectedMenu(Land.GoodSoldier)));
		editorToolBar.buttons.add(new DWButton(Land.GoodSoldier_Grass, () -> config.setSelectedMenu(Land.GoodSoldier_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodSoldier_Sand,  () -> config.setSelectedMenu(Land.GoodSoldier_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodTank,       () -> config.setSelectedMenu(Land.GoodTank)));
		editorToolBar.buttons.add(new DWButton(Land.GoodTank_Grass, () -> config.setSelectedMenu(Land.GoodTank_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodTank_Sand,  () -> config.setSelectedMenu(Land.GoodTank_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodBunker,       () -> config.setSelectedMenu(Land.GoodBunker)));
		editorToolBar.buttons.add(new DWButton(Land.GoodBunker_Grass, () -> config.setSelectedMenu(Land.GoodBunker_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodBunker_Sand,  () -> config.setSelectedMenu(Land.GoodBunker_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodRadar,       () -> config.setSelectedMenu(Land.GoodRadar)));
		editorToolBar.buttons.add(new DWButton(Land.GoodRadar_Grass, () -> config.setSelectedMenu(Land.GoodRadar_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodRadar_Sand,  () -> config.setSelectedMenu(Land.GoodRadar_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Grass, () -> config.setSelectedMenu(Land.Grass)));
		editorToolBar.buttons.add(new DWButton(Land.Tree1, () -> config.setSelectedMenu(Land.Tree1)));
		editorToolBar.buttons.add(new DWButton(Land.Tree2, () -> config.setSelectedMenu(Land.Tree2)));
		editorToolBar.buttons.add(new DWButton(Land.Tree3, () -> config.setSelectedMenu(Land.Tree3)));
	}
}
