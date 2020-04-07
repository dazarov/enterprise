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
		public Land imageCode = Land.Vacuum;
		public Runnable runner;
		
		// Separator
		public DWButton(){
			
		}
		
		public DWButton(Land imageCode, Runnable runner){
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
			
			toolbar.buttons.add(new DWButton(Land.Wall, () -> config.setSelectedCode(Land.Wall)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Brick,      () -> config.setSelectedCode(Land.Brick)));
			toolbar.buttons.add(new DWButton(Land.WhiteBrick, () -> config.setSelectedCode(Land.WhiteBrick)));
			toolbar.buttons.add(new DWButton(Land.Stone,      () -> config.setSelectedCode(Land.Stone)));
			toolbar.buttons.add(new DWButton(Land.BlackStone, () -> config.setSelectedCode(Land.BlackStone)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Wood1, () -> config.setSelectedCode(Land.Wood1)));
			toolbar.buttons.add(new DWButton(Land.Wood2, () -> config.setSelectedCode(Land.Wood2)));
			toolbar.buttons.add(new DWButton(Land.Wood3, () -> config.setSelectedCode(Land.Wood3)));
			toolbar.buttons.add(new DWButton(Land.Wood4, () -> config.setSelectedCode(Land.Wood4)));
			
			toolbar = new DWToolbar("Palette 3");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton(Land.Train_Horizontal, () -> config.setSelectedCode(Land.Train_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.Train_Vertical,   () -> config.setSelectedCode(Land.Train_Vertical)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Station_Horizontal, () -> config.setSelectedCode(Land.Station_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.Station_Vertical,   () -> config.setSelectedCode(Land.Station_Vertical)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Rail_Horizontal,    () -> config.setSelectedCode(Land.Rail_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.Rail_Vertical,      () -> config.setSelectedCode(Land.Rail_Vertical)));
			toolbar.buttons.add(new DWButton(Land.Rail_Diagonal_Up,   () -> config.setSelectedCode(Land.Rail_Diagonal_Up)));
			toolbar.buttons.add(new DWButton(Land.Rail_Diagonal_Down, () -> config.setSelectedCode(Land.Rail_Diagonal_Down)));
			toolbar.buttons.add(new DWButton(Land.Rail_Up_Right,      () -> config.setSelectedCode(Land.Rail_Up_Right)));
			toolbar.buttons.add(new DWButton(Land.Rail_Up_Left,       () -> config.setSelectedCode(Land.Rail_Up_Left)));
			toolbar.buttons.add(new DWButton(Land.Rail_Down_Right,    () -> config.setSelectedCode(Land.Rail_Down_Right)));
			toolbar.buttons.add(new DWButton(Land.Rail_Down_Left,     () -> config.setSelectedCode(Land.Rail_Down_Left)));
			toolbar.buttons.add(new DWButton(Land.Rail_Left_Up,       () -> config.setSelectedCode(Land.Rail_Left_Up)));
			toolbar.buttons.add(new DWButton(Land.Rail_Left_Down,     () -> config.setSelectedCode(Land.Rail_Left_Down)));
			toolbar.buttons.add(new DWButton(Land.Rail_Right_Up,      () -> config.setSelectedCode(Land.Rail_Right_Up)));
			toolbar.buttons.add(new DWButton(Land.Rail_Right_Down,    () -> config.setSelectedCode(Land.Rail_Right_Down)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Rail_Vertical_Cross, () -> config.setSelectedCode(Land.Rail_Vertical_Cross)));
			toolbar.buttons.add(new DWButton(Land.Rail_Diagonal_Cross, () -> config.setSelectedCode(Land.Rail_Diagonal_Cross)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.WarTrain_Horizontal, () -> config.setSelectedCode(Land.WarTrain_Horizontal)));
			toolbar.buttons.add(new DWButton(Land.WarTrain_Vertical,   () -> config.setSelectedCode(Land.WarTrain_Vertical)));
			
			toolbar = new DWToolbar("Palette 4");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalSteelGate,   () -> config.setSelectedCode(Land.ClosedVerticalSteelGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalSteelGate, () -> config.setSelectedCode(Land.ClosedHorizontalSteelGate)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalWoodGate,       () -> config.setSelectedCode(Land.ClosedVerticalWoodGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalWoodGate,     () -> config.setSelectedCode(Land.ClosedHorizontalWoodGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalConcreteGate,   () -> config.setSelectedCode(Land.ClosedVerticalConcreteGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalConcreteGate, () -> config.setSelectedCode(Land.ClosedHorizontalConcreteGate)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.ClosedVerticalBrickGate,   () -> config.setSelectedCode(Land.ClosedVerticalBrickGate)));
			toolbar.buttons.add(new DWButton(Land.ClosedHorizontalBrickGate, () -> config.setSelectedCode(Land.ClosedHorizontalBrickGate)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Ammo,    () -> config.setSelectedCode(Land.Ammo)));
			toolbar.buttons.add(new DWButton(Land.Grenade, () -> config.setSelectedCode(Land.Grenade)));
			toolbar.buttons.add(new DWButton(Land.Rocket,  () -> config.setSelectedCode(Land.Rocket)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Mine,       () -> config.setSelectedCode(Land.Mine)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Peasant,       () -> config.setSelectedCode(Land.Peasant)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Teleport5, () -> config.setSelectedCode(Land.Teleport5)));
			
			toolbar = new DWToolbar("Palette 5");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton(Land.BadGeneral,       () -> config.setSelectedCode(Land.BadGeneral)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadOfficer,       () -> config.setSelectedCode(Land.BadOfficer)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadSoldier,       () -> config.setSelectedCode(Land.BadSoldier)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadTank,       () -> config.setSelectedCode(Land.BadTank)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadBunker,       () -> config.setSelectedCode(Land.BadBunker)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.BadRadar,       () -> config.setSelectedCode(Land.BadRadar)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Mountain, () -> config.setSelectedCode(Land.Mountain)));
			toolbar.buttons.add(new DWButton(Land.Water,    () -> config.setSelectedCode(Land.Water)));
			toolbar.buttons.add(new DWButton(Land.Sand,     () -> config.setSelectedCode(Land.Sand)));
			
			toolbar = new DWToolbar("Palette 6");
			toolbars.add(toolbar);
			
			toolbar.buttons.add(new DWButton(Land.GoodGeneral,       () -> config.setSelectedCode(Land.GoodGeneral)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodOfficer,       () -> config.setSelectedCode(Land.GoodOfficer)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodSoldier,       () -> config.setSelectedCode(Land.GoodSoldier)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodTank,       () -> config.setSelectedCode(Land.GoodTank)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodBunker,       () -> config.setSelectedCode(Land.GoodBunker)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.GoodRadar,       () -> config.setSelectedCode(Land.GoodRadar)));
			toolbar.buttons.add(new DWButton());
			toolbar.buttons.add(new DWButton(Land.Grass, () -> config.setSelectedCode(Land.Grass)));
			toolbar.buttons.add(new DWButton(Land.Tree1, () -> config.setSelectedCode(Land.Tree1)));
			toolbar.buttons.add(new DWButton(Land.Tree2, () -> config.setSelectedCode(Land.Tree2)));
			toolbar.buttons.add(new DWButton(Land.Tree3, () -> config.setSelectedCode(Land.Tree3)));
		}
		return toolbars;
	}
}
