package com.dworld.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.dworld.DWorldLauncher;
import com.dworld.core.Land;

public class BuildingPalette {
	private static JFrame palette = null;
	
	public static void showPalette(){
		palette = new JFrame();
		palette.setTitle("Building Palette");
		palette.setLayout(new GridLayout(1,5));
		
		initToolBar();
		
		palette.pack();
		palette.setLocation(0, 0);
		palette.setSize(200, 1000);
		palette.setFocusableWindowState(false);
		palette.setFocusable(false);
		palette.setVisible(true);
		
	}
	
	public static void hidePalette(){
		if(palette != null){
			palette.setVisible(false);
			palette.dispose();
		}
	}
	
	private static JToolBar createToolBar(){
		JToolBar toolBar = new JToolBar("");
		toolBar.setFloatable(false);
		toolBar.setFocusable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		return toolBar;
	}
	
	private static void initToolBar(){
		JToolBar toolBar = createToolBar();
		
		createToolButton(toolBar, Land.Wall);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Brick);
		createToolButton(toolBar, Land.WhiteBrick);
		createToolButton(toolBar, Land.Stone);
		createToolButton(toolBar, Land.BlackStone);
		createToolButton(toolBar, Land.Wood1);
		createToolButton(toolBar, Land.Wood2);
		createToolButton(toolBar, Land.Wood3);
		createToolButton(toolBar, Land.Wood4);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Grass);
		createToolButton(toolBar, Land.Water);
		createToolButton(toolBar, Land.Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Tree1);
		createToolButton(toolBar, Land.Tree2);
		createToolButton(toolBar, Land.Tree3);
		palette.add(toolBar);
		
		toolBar = createToolBar();
		createToolButton(toolBar, Land.Train_Horizontal);
		createToolButton(toolBar, Land.Train_Vertical);
		createToolButton(toolBar, Land.Station_Horizontal);
		createToolButton(toolBar, Land.Station_Vertical);
		createToolButton(toolBar, Land.Rail_Horizontal);
		createToolButton(toolBar, Land.Rail_Vertical);
		createToolButton(toolBar, Land.Rail_Diagonal_Up);
		createToolButton(toolBar, Land.Rail_Diagonal_Down);
		createToolButton(toolBar, Land.Rail_Up_Right);
		createToolButton(toolBar, Land.Rail_Up_Left);
		createToolButton(toolBar, Land.Rail_Down_Right);
		createToolButton(toolBar, Land.Rail_Down_Left);
		createToolButton(toolBar, Land.Rail_Left_Up);
		createToolButton(toolBar, Land.Rail_Left_Down);
		createToolButton(toolBar, Land.Rail_Right_Up);
		createToolButton(toolBar, Land.Rail_Right_Down);
		createToolButton(toolBar, Land.Rail_Vertical_Cross);
		createToolButton(toolBar, Land.Rail_Diagonal_Cross);
		palette.add(toolBar);
		
		toolBar = createToolBar();
		createToolButton(toolBar, Land.ClosedVerticalSteelGate);
		createToolButton(toolBar, Land.ClosedHorizontalSteelGate);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.ClosedVerticalWoodGate);
		createToolButton(toolBar, Land.ClosedHorizontalWoodGate);
		createToolButton(toolBar, Land.ClosedVerticalConcreteGate);
		createToolButton(toolBar, Land.ClosedHorizontalConcreteGate);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.ClosedVerticalBrickGate);
		createToolButton(toolBar, Land.ClosedHorizontalBrickGate);
		//palette.add(toolBar);
		
		//toolBar = createToolBar();
		createToolButton(toolBar, Land.Ammo);
		createToolButton(toolBar, Land.Grenada);
		createToolButton(toolBar, Land.Rocket);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Mine);
		createToolButton(toolBar, Land.Mine_Grass);
		createToolButton(toolBar, Land.Mine_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Gray_General);
		createToolButton(toolBar, Land.Gray_General_Grass);
		createToolButton(toolBar, Land.Gray_General_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Gray_Officer);
		createToolButton(toolBar, Land.Gray_Officer_Grass);
		createToolButton(toolBar, Land.Gray_Officer_Sand);
		palette.add(toolBar);
		
		toolBar = createToolBar();
		createToolButton(toolBar, Land.Robot);
		createToolButton(toolBar, Land.Robot_Grass);
		createToolButton(toolBar, Land.Robot_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Tank);
		createToolButton(toolBar, Land.Tank_Grass);
		createToolButton(toolBar, Land.Tank_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Bunker);
		createToolButton(toolBar, Land.Bunker_Grass);
		createToolButton(toolBar, Land.Bunker_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Radar);
		createToolButton(toolBar, Land.Radar_Grass);
		createToolButton(toolBar, Land.Radar_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.General);
		createToolButton(toolBar, Land.General_Grass);
		createToolButton(toolBar, Land.General_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Officer);
		createToolButton(toolBar, Land.Officer_Grass);
		createToolButton(toolBar, Land.Officer_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Soldier);
		createToolButton(toolBar, Land.Soldier_Grass);
		createToolButton(toolBar, Land.Soldier_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.GoodTank);
		createToolButton(toolBar, Land.GoodTank_Grass);
		createToolButton(toolBar, Land.GoodTank_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.GoodBunker);
		createToolButton(toolBar, Land.GoodBunker_Grass);
		createToolButton(toolBar, Land.GoodBunker_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.GoodRadar);
		createToolButton(toolBar, Land.GoodRadar_Grass);
		createToolButton(toolBar, Land.GoodRadar_Sand);
		palette.add(toolBar);
		
		toolBar = createToolBar();
		createToolButton(toolBar, Land.Peasant);
		createToolButton(toolBar, Land.Peasant_Grass);
		createToolButton(toolBar, Land.Peasant_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Teleport5);
		palette.add(toolBar);
	}
	
	private static void createToolButton(JToolBar toolBar, final int code){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		button.addActionListener(new SelectElementAction(code));
		
		toolBar.add(button);
	}
	
	static class SelectElementAction implements ActionListener {
		int code;

		public SelectElementAction(int code) {
			this.code = code;
		}

		public void actionPerformed(ActionEvent e) {
			DWorldLauncher.launcher.setSelectedElement(code);
		}
	}

}
