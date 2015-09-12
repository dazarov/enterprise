package com.dworld.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.dworld.DWLauncher;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.ui.actions.ChangeManCodeAction;
import com.dworld.ui.actions.SelectElementAction;

public class DWToolBarBuilder {
	private static DWWindow palette = null;
	private JFrame window;
	
	public DWToolBarBuilder(JFrame window){
		this.window = window;
	}
	
	public JToolBar buildToolBar(){
		JToolBar toolBar = new JToolBar("DWorld toolbar");
		toolBar.setFloatable(false);
		toolBar.setFocusable(false);
		
		createHeroButton(toolBar, Land.Hero);
		toolBar.addSeparator();
		createHeroButton(toolBar, Land.GoodSoldier);
		createHeroButton(toolBar, Land.GoodOfficer);
		createHeroButton(toolBar, Land.GoodGeneral);
		createHeroButton(toolBar, Land.GoodTank);
		toolBar.addSeparator();
		createHeroButton(toolBar, Land.BadSoldier);
		createHeroButton(toolBar, Land.BadOfficer);
		createHeroButton(toolBar, Land.BadGeneral);
		createHeroButton(toolBar, Land.BadTank);
		toolBar.addSeparator();
		createHeroButton(toolBar, Land.Dark_Knight);
		toolBar.addSeparator();
		createHeroButton(toolBar, Land.Peasant);
		
		return toolBar;
	}
	
	private void createHeroButton(JToolBar toolBar, final int code){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.getImage(code)));
		button.addActionListener(new ChangeManCodeAction(window, code));
		
		toolBar.add(button);
	}
	public static void showPalette(){
		palette = new DWWindow("Building Palette", DWWindow.ORIENTATION_LEFT);
		palette.setLayout(new GridLayout(1,5));
		
		initToolBar();
		
		palette.pack();
		palette.setSize(200, 720);
		palette.setFocusableWindowState(false);
		palette.setFocusable(false);
		palette.setVisible(true);
		DWWindowListener.getDefault().addWindow(palette);
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
		toolBar.addSeparator();
		createToolButton(toolBar, Land.WarTrain_Horizontal);
		createToolButton(toolBar, Land.WarTrain_Vertical);
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
		createToolButton(toolBar, Land.BadGeneral);
		createToolButton(toolBar, Land.BadGeneral_Grass);
		createToolButton(toolBar, Land.BadGeneral_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.BadOfficer);
		createToolButton(toolBar, Land.BadOfficer_Grass);
		createToolButton(toolBar, Land.BadOfficer_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.BadSoldier);
		createToolButton(toolBar, Land.BadSoldier_Grass);
		createToolButton(toolBar, Land.BadSoldier_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.BadTank);
		createToolButton(toolBar, Land.BadTank_Grass);
		createToolButton(toolBar, Land.BadTank_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.BadBunker);
		createToolButton(toolBar, Land.BadBunker_Grass);
		createToolButton(toolBar, Land.BadBunker_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.BadRadar);
		createToolButton(toolBar, Land.BadRadar_Grass);
		createToolButton(toolBar, Land.BadRadar_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Mountain);
		createToolButton(toolBar, Land.Water);
		createToolButton(toolBar, Land.Sand);
		palette.add(toolBar);
		
		toolBar = createToolBar();
		createToolButton(toolBar, Land.GoodGeneral);
		createToolButton(toolBar, Land.GoodGeneral_Grass);
		createToolButton(toolBar, Land.GoodGeneral_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.GoodOfficer);
		createToolButton(toolBar, Land.GoodOfficer_Grass);
		createToolButton(toolBar, Land.GoodOfficer_Sand);
		toolBar.addSeparator();
		createToolButton(toolBar, Land.GoodSoldier);
		createToolButton(toolBar, Land.GoodSoldier_Grass);
		createToolButton(toolBar, Land.GoodSoldier_Sand);
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
		toolBar.addSeparator();
		createToolButton(toolBar, Land.Grass);
		createToolButton(toolBar, Land.Tree1);
		createToolButton(toolBar, Land.Tree2);
		createToolButton(toolBar, Land.Tree3);
		palette.add(toolBar);
	}
	
	private static void createToolButton(JToolBar toolBar, final int code){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.getImage(code)));
		button.addActionListener(new SelectElementAction(code));
		
		toolBar.add(button);
	}

	private static void createDrawButtons(JToolBar toolBar){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/brush.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.setDrawMode(DWLauncher.DRAW_BRUSH);
			}
		});
		toolBar.add(button);

		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/line.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.setDrawMode(DWLauncher.DRAW_LINE);
			}
		});
		toolBar.add(button);

		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/rect.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.setDrawMode(DWLauncher.DRAW_RECTANGLE);
			}
		});
		toolBar.add(button);
		
		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/fill.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.setDrawMode(DWLauncher.DRAW_FILL);
			}
		});
		toolBar.add(button);
		
		toolBar.addSeparator();
		
		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/FlipVertically.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.flipVertically();
			}
		});
		toolBar.add(button);

		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/FlipHorizontally.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.flipHorizontally();
			}
		});
		toolBar.add(button);
		
		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/TurnRight.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.turnRight();
			}
		});
		toolBar.add(button);

		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/TurnLeft.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.turnLeft();
			}
		});
		toolBar.add(button);
		
		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/MoveUp.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.moveUp();
			}
		});
		toolBar.add(button);
		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/MoveDown.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.moveDown();
			}
		});
		toolBar.add(button);
		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/MoveRight.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.moveRight();
			}
		});
		toolBar.add(button);
		button = new JButton();
		button.setIcon(new ImageIcon(DWDraw.loadImage("resources/MoveLeft.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.moveLeft();
			}
		});
		toolBar.add(button);
		
//		button = new JButton("Copy");
//		//button.setIcon(new ImageIcon(DrawWorld.loadImage("resources/fill.png")));
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				SelectionManager.copy();
//			}
//		});
//		toolBar.add(button);
//		
//		button = new JButton("Paste");
//		//button.setIcon(new ImageIcon(DrawWorld.loadImage("resources/fill.png")));
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				SelectionManager.paste();
//			}
//		});
//		toolBar.add(button);
	}
}
