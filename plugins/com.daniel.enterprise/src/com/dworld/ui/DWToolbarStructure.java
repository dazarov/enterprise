package com.dworld.ui;

import java.util.ArrayList;
import java.util.List;

import com.dworld.core.DWConfiguration;
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
		gameToolBar = new DWToolBar("DWorld toolbar");
		
		gameToolBar.buttons.add(new DWButton(Land.Hero, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.Hero)));
		gameToolBar.buttons.add(new DWButton());
		gameToolBar.buttons.add(new DWButton(Land.GoodSoldier, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodSoldier)));
		gameToolBar.buttons.add(new DWButton(Land.GoodOfficer, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodOfficer)));
		gameToolBar.buttons.add(new DWButton(Land.GoodGeneral, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodGeneral)));
		gameToolBar.buttons.add(new DWButton(Land.GoodTank, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.GoodTank)));
		gameToolBar.buttons.add(new DWButton());
		gameToolBar.buttons.add(new DWButton(Land.BadSoldier, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadSoldier)));
		gameToolBar.buttons.add(new DWButton(Land.BadOfficer, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadOfficer)));
		gameToolBar.buttons.add(new DWButton(Land.BadGeneral, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadGeneral)));
		gameToolBar.buttons.add(new DWButton(Land.BadTank, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.BadTank)));
		gameToolBar.buttons.add(new DWButton());
		gameToolBar.buttons.add(new DWButton(Land.Dark_Knight, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.Dark_Knight)));
		gameToolBar.buttons.add(new DWButton());
		gameToolBar.buttons.add(new DWButton(Land.Peasant, () -> DWConfiguration.getInstance().getEngine().changeManCode(Land.Peasant)));
		
		editorToolBar = new DWToolBar("Palette");
		
		editorToolBar.buttons.add(new DWButton("resources/brush.png", () -> DWConfiguration.getInstance().setDrawMode(DWConfiguration.DRAW_BRUSH)));
		editorToolBar.buttons.add(new DWButton("resources/line.png",  () -> DWConfiguration.getInstance().setDrawMode(DWConfiguration.DRAW_LINE)));
		editorToolBar.buttons.add(new DWButton("resources/rect.png",  () -> DWConfiguration.getInstance().setDrawMode(DWConfiguration.DRAW_RECTANGLE)));
		editorToolBar.buttons.add(new DWButton("resources/fill.png",  () -> DWConfiguration.getInstance().setDrawMode(DWConfiguration.DRAW_FILL)));
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
		editorToolBar.buttons.add(new DWButton(Land.Wall, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wall)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Brick,      () -> DWConfiguration.getInstance().setSelectedMenu(Land.Brick)));
		editorToolBar.buttons.add(new DWButton(Land.WhiteBrick, () -> DWConfiguration.getInstance().setSelectedMenu(Land.WhiteBrick)));
		editorToolBar.buttons.add(new DWButton(Land.Stone,      () -> DWConfiguration.getInstance().setSelectedMenu(Land.Stone)));
		editorToolBar.buttons.add(new DWButton(Land.BlackStone, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BlackStone)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Wood1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood1)));
		editorToolBar.buttons.add(new DWButton(Land.Wood2, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood2)));
		editorToolBar.buttons.add(new DWButton(Land.Wood3, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood3)));
		editorToolBar.buttons.add(new DWButton(Land.Wood4, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Wood4)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Train_Horizontal, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Train_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.Train_Vertical,   () -> DWConfiguration.getInstance().setSelectedMenu(Land.Train_Vertical)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Station_Horizontal, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Station_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.Station_Vertical,   () -> DWConfiguration.getInstance().setSelectedMenu(Land.Station_Vertical)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Rail_Horizontal,    () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Vertical,      () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Vertical)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Diagonal_Up,   () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Diagonal_Up)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Diagonal_Down, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Diagonal_Down)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Up_Right,      () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Up_Right)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Up_Left,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Up_Left)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Down_Right,    () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Down_Right)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Down_Left,     () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Down_Left)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Left_Up,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Left_Up)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Left_Down,     () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Left_Down)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Right_Up,      () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Right_Up)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Right_Down,    () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Right_Down)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Rail_Vertical_Cross, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Vertical_Cross)));
		editorToolBar.buttons.add(new DWButton(Land.Rail_Diagonal_Cross, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rail_Diagonal_Cross)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.WarTrain_Horizontal, () -> DWConfiguration.getInstance().setSelectedMenu(Land.WarTrain_Horizontal)));
		editorToolBar.buttons.add(new DWButton(Land.WarTrain_Vertical,   () -> DWConfiguration.getInstance().setSelectedMenu(Land.WarTrain_Vertical)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalSteelGate,   () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalSteelGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalSteelGate, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalSteelGate)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalWoodGate,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalWoodGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalWoodGate,     () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalWoodGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalConcreteGate,   () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalConcreteGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalConcreteGate, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalConcreteGate)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.ClosedVerticalBrickGate,   () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedVerticalBrickGate)));
		editorToolBar.buttons.add(new DWButton(Land.ClosedHorizontalBrickGate, () -> DWConfiguration.getInstance().setSelectedMenu(Land.ClosedHorizontalBrickGate)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Ammo,    () -> DWConfiguration.getInstance().setSelectedMenu(Land.Ammo)));
		editorToolBar.buttons.add(new DWButton(Land.Grenade, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Grenade)));
		editorToolBar.buttons.add(new DWButton(Land.Rocket,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.Rocket)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Mine,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mine)));
		editorToolBar.buttons.add(new DWButton(Land.Mine_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mine_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.Mine_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mine_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Peasant,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.Peasant)));
		editorToolBar.buttons.add(new DWButton(Land.Peasant_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Peasant_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.Peasant_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.Peasant_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Teleport5, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Teleport5)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadGeneral,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadGeneral)));
		editorToolBar.buttons.add(new DWButton(Land.BadGeneral_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadGeneral_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadGeneral_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadGeneral_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadOfficer,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadOfficer)));
		editorToolBar.buttons.add(new DWButton(Land.BadOfficer_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadOfficer_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadOfficer_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadOfficer_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadSoldier,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadSoldier)));
		editorToolBar.buttons.add(new DWButton(Land.BadSoldier_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadSoldier_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadSoldier_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadSoldier_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadTank,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadTank)));
		editorToolBar.buttons.add(new DWButton(Land.BadTank_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadTank_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadTank_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadTank_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadBunker,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadBunker)));
		editorToolBar.buttons.add(new DWButton(Land.BadBunker_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadBunker_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadBunker_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadBunker_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.BadRadar,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadRadar)));
		editorToolBar.buttons.add(new DWButton(Land.BadRadar_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadRadar_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.BadRadar_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.BadRadar_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Mountain, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Mountain)));
		editorToolBar.buttons.add(new DWButton(Land.Water,    () -> DWConfiguration.getInstance().setSelectedMenu(Land.Water)));
		editorToolBar.buttons.add(new DWButton(Land.Sand,     () -> DWConfiguration.getInstance().setSelectedMenu(Land.Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodGeneral,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodGeneral)));
		editorToolBar.buttons.add(new DWButton(Land.GoodGeneral_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodGeneral_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodGeneral_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodGeneral_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodOfficer,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodOfficer)));
		editorToolBar.buttons.add(new DWButton(Land.GoodOfficer_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodOfficer_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodOfficer_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodOfficer_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodSoldier,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodSoldier)));
		editorToolBar.buttons.add(new DWButton(Land.GoodSoldier_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodSoldier_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodSoldier_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodSoldier_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodTank,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodTank)));
		editorToolBar.buttons.add(new DWButton(Land.GoodTank_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodTank_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodTank_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodTank_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodBunker,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodBunker)));
		editorToolBar.buttons.add(new DWButton(Land.GoodBunker_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodBunker_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodBunker_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodBunker_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.GoodRadar,       () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodRadar)));
		editorToolBar.buttons.add(new DWButton(Land.GoodRadar_Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodRadar_Grass)));
		editorToolBar.buttons.add(new DWButton(Land.GoodRadar_Sand,  () -> DWConfiguration.getInstance().setSelectedMenu(Land.GoodRadar_Sand)));
		editorToolBar.buttons.add(new DWButton());
		editorToolBar.buttons.add(new DWButton(Land.Grass, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Grass)));
		editorToolBar.buttons.add(new DWButton(Land.Tree1, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Tree1)));
		editorToolBar.buttons.add(new DWButton(Land.Tree2, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Tree2)));
		editorToolBar.buttons.add(new DWButton(Land.Tree3, () -> DWConfiguration.getInstance().setSelectedMenu(Land.Tree3)));
	}
}
