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
import com.dworld.ui.actions.ChangeManCodeAction;
import com.dworld.ui.actions.SelectElementAction;

public class DWorldToolBarBuilder {
	private static DWindow palette = null;
	private JFrame window;
	
	public DWorldToolBarBuilder(JFrame window){
		this.window = window;
	}
	
	public JToolBar buildToolBar(){
		JToolBar toolBar = new JToolBar("DWorld toolbar");
		toolBar.setFloatable(false);
		toolBar.setFocusable(false);
		
		createHeroButton(toolBar, Land.Man);
		createHeroButton(toolBar, Land.Peasant);
		createHeroButton(toolBar, Land.Soldier);
		createHeroButton(toolBar, Land.Officer);
		createHeroButton(toolBar, Land.General);
		createHeroButton(toolBar, Land.Robot);
		createHeroButton(toolBar, Land.GoodTank);
		createHeroButton(toolBar, Land.Gray_Officer);
		createHeroButton(toolBar, Land.Gray_General);
		createHeroButton(toolBar, Land.Tank);
		createHeroButton(toolBar, Land.Dark_Knight);
		
		return toolBar;
	}
	
	private void createHeroButton(JToolBar toolBar, final int code){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		button.addActionListener(new ChangeManCodeAction(window, code));
		
		toolBar.add(button);
	}
	public static void showPalette(){
		palette = new DWindow("Building Palette", DWindow.ORIENTATION_LEFT);
		palette.setLayout(new GridLayout(1,5));
		
		initToolBar();
		
		palette.pack();
//		palette.setLocation(300, 100);
		palette.setSize(200, 600);
		palette.setFocusableWindowState(false);
		palette.setFocusable(false);
		palette.setVisible(true);
		DWorldWindowListener.getDefault().addWindow(palette);
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
		
		createDrawButtons(toolBar);
		toolBar.addSeparator();
		
		createToolButton(toolBar, Land.Wall);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Brick);
		createToolButton(toolBar, Land.WhiteBrick);
		createToolButton(toolBar, Land.Stone);
		createToolButton(toolBar, Land.BlackStone);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Wood1);
		createToolButton(toolBar, Land.Wood2);
		createToolButton(toolBar, Land.Wood3);
		createToolButton(toolBar, Land.Wood4);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Water);
		createToolButton(toolBar, Land.Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Grass);
		createToolButton(toolBar, Land.Tree1);
		createToolButton(toolBar, Land.Tree2);
		createToolButton(toolBar, Land.Tree3);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.WarTrain_Horizontal);
		createToolButton(toolBar, Land.WarTrain_Vertical);
		palette.add(toolBar);
		
		toolBar = createToolBar();
		createToolButton(toolBar, Land.Train_Horizontal);
		createToolButton(toolBar, Land.Train_Vertical);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Station_Horizontal);
		createToolButton(toolBar, Land.Station_Vertical);
		toolBar.addSeparator();
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
		toolBar.addSeparator();
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
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Ammo);
		createToolButton(toolBar, Land.Grenada);
		createToolButton(toolBar, Land.Rocket);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Mine);
		createToolButton(toolBar, Land.Mine_Grass);
		createToolButton(toolBar, Land.Mine_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Peasant);
		createToolButton(toolBar, Land.Peasant_Grass);
		createToolButton(toolBar, Land.Peasant_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Teleport5);
		palette.add(toolBar);
		
		toolBar = createToolBar();
		createToolButton(toolBar, Land.Gray_General);
		createToolButton(toolBar, Land.Gray_General_Grass);
		createToolButton(toolBar, Land.Gray_General_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Gray_Officer);
		createToolButton(toolBar, Land.Gray_Officer_Grass);
		createToolButton(toolBar, Land.Gray_Officer_Sand);
		toolBar.addSeparator();
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
		palette.add(toolBar);
		
		toolBar = createToolBar();
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
	}
	
	private static void createToolButton(JToolBar toolBar, final int code){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		button.addActionListener(new SelectElementAction(code));
		
		toolBar.add(button);
	}

	private static void createDrawButtons(JToolBar toolBar){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DrawWorld.loadImage("resources/brush.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWorldLauncher.setDrawMode(DWorldLauncher.BRUSH);
			}
		});
		toolBar.add(button);

		button = new JButton();
		button.setIcon(new ImageIcon(DrawWorld.loadImage("resources/line.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWorldLauncher.setDrawMode(DWorldLauncher.LINE);
			}
		});
		toolBar.add(button);

		button = new JButton();
		button.setIcon(new ImageIcon(DrawWorld.loadImage("resources/rect.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWorldLauncher.setDrawMode(DWorldLauncher.RECTANGLE);
			}
		});
		toolBar.add(button);
	}
}
