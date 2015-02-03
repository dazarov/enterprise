package com.dworld.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.dworld.DWLauncher;
import com.dworld.core.DWEngine;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.ui.actions.ChangeManCodeAction;
import com.dworld.ui.actions.SelectElementAction;

public class DWMenuBuilder {
	private JFrame window;
	
	public DWMenuBuilder(JFrame window){
		this.window = window;
	}
	
	public JMenuBar buildMenu() {
		JCheckBoxMenuItem cbMenuItem;
		JMenuItem menuItem;

		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");

		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().load(DWLauncher.SAVE_FILE);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().save(DWLauncher.SAVE_FILE);
			}
		});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Load Backup");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().load(DWLauncher.BACKUP_FILE);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save Backup");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().save(DWLauncher.BACKUP_FILE);
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();

		menuItem = new JMenuItem("Load Test");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().load(DWLauncher.TEST_FILE);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save Test");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().save(DWLauncher.TEST_FILE);
			}
		});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DWLauncher.getLauncher().exitConfirmation())
					System.exit(0);
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Game");
		menuBar.add(menu);

		menuItem = new JMenuItem("Map");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWMap.showMap();
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Mini Map");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWMap.showMinimap();
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();

		menuItem = new JMenuItem("Pause");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().pause(true);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Go");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWEngine.getEngine().pause(false);
			}
		});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Info");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DWInfoScreen(window, DWLauncher.getControlledUnit());
			}
		});
		menu.add(menuItem);

		menu = new JMenu("Build");
		cbMenuItem = new JCheckBoxMenuItem("Build mode");
		cbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.getLauncher().setBuildMode(!DWLauncher.getLauncher().isBuildMode());
				DWEngine.getEngine().pause(DWLauncher.getLauncher().isBuildMode());
				if(DWLauncher.getLauncher().isBuildMode()){
					DWToolBarBuilder.showPalette();
				}else{
					DWToolBarBuilder.hidePalette();
				}
			}
		});
		menu.add(cbMenuItem);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.copy();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.paste();
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();

		ButtonGroup group = new ButtonGroup();

		JMenu subMenu = new JMenu("Landscape");

		createBuildMenuItem(subMenu, group, "Wall", Land.Wall);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Brick", Land.Brick);
		createBuildMenuItem(subMenu, group, "White Brick", Land.WhiteBrick);
		createBuildMenuItem(subMenu, group, "White Stone", Land.Stone);
		createBuildMenuItem(subMenu, group, "Black Stone", Land.BlackStone);
		createBuildMenuItem(subMenu, group, "Wood", Land.Wood1);
		createBuildMenuItem(subMenu, group, "Wood", Land.Wood2);
		createBuildMenuItem(subMenu, group, "Wood", Land.Wood3);
		createBuildMenuItem(subMenu, group, "Wood", Land.Wood4);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Mountain", Land.Mountain);
		createBuildMenuItem(subMenu, group, "Water", Land.Water);
		createBuildMenuItem(subMenu, group, "Sand", Land.Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Grass", Land.Grass);
		createBuildMenuItem(subMenu, group, "Tree", Land.Tree1);
		createBuildMenuItem(subMenu, group, "Tree", Land.Tree2);
		createBuildMenuItem(subMenu, group, "Tree", Land.Tree3);

		menu.add(subMenu);
		
		subMenu = new JMenu("Railroad");
		
		createBuildMenuItem(subMenu, group, "Train",
				Land.Train_Horizontal);
		createBuildMenuItem(subMenu, group, "Train",
				Land.Train_Vertical);
		createBuildMenuItem(subMenu, group, "Station",
				Land.Station_Horizontal);
		createBuildMenuItem(subMenu, group, "Station",
				Land.Station_Vertical);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Horizontal);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Vertical);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Diagonal_Up);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Diagonal_Down);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Up_Right);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Up_Left);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Down_Right);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Down_Left);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Left_Up);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Left_Down);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Right_Up);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Right_Down);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Vertical_Cross);
		createBuildMenuItem(subMenu, group, "Rail",
				Land.Rail_Diagonal_Cross);
		
		
		menu.add(subMenu);

		subMenu = new JMenu("Doors&Gates");

		createBuildMenuItem(subMenu, group, "Vertical Steel door",
				Land.ClosedVerticalSteelGate);
		createBuildMenuItem(subMenu, group, "Horizontal Steel door",
				Land.ClosedHorizontalSteelGate);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Vertical Wood door",
				Land.ClosedVerticalWoodGate);
		createBuildMenuItem(subMenu, group, "Horizontal Wood door",
				Land.ClosedHorizontalWoodGate);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Vertical Concrete door",
				Land.ClosedVerticalConcreteGate);
		createBuildMenuItem(subMenu, group, "Horizontal Concrete door",
				Land.ClosedHorizontalConcreteGate);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Vertical Brick door",
				Land.ClosedVerticalBrickGate);
		createBuildMenuItem(subMenu, group, "Horizontal Brick door",
				Land.ClosedHorizontalBrickGate);

		menu.add(subMenu);

		subMenu = new JMenu("Ammunition");

		createBuildMenuItem(subMenu, group, "Ammo", Land.Ammo);
		createBuildMenuItem(subMenu, group, "Grenada", Land.Grenada);
		createBuildMenuItem(subMenu, group, "Rocket", Land.Rocket);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Mine", Land.Mine);
		createBuildMenuItem(subMenu, group, "Mine", Land.Mine_Grass);
		createBuildMenuItem(subMenu, group, "Mine", Land.Mine_Sand);

		menu.add(subMenu);

		subMenu = new JMenu("Enemies");

		createBuildMenuItem(subMenu, group, "General", Land.BadGeneral);
		createBuildMenuItem(subMenu, group, "General", Land.BadGeneral_Grass);
		createBuildMenuItem(subMenu, group, "General", Land.BadGeneral_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Officer", Land.BadOfficer);
		createBuildMenuItem(subMenu, group, "Officer", Land.BadOfficer_Grass);
		createBuildMenuItem(subMenu, group, "Officer", Land.BadOfficer_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Robot", Land.BadSoldier);
		createBuildMenuItem(subMenu, group, "Robot", Land.BadSoldier_Grass);
		createBuildMenuItem(subMenu, group, "Robot", Land.BadSoldier_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Tank", Land.BadTank);
		createBuildMenuItem(subMenu, group, "Tank", Land.BadTank_Grass);
		createBuildMenuItem(subMenu, group, "Tank", Land.BadTank_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Bunker", Land.BadBunker);
		createBuildMenuItem(subMenu, group, "Bunker", Land.BadBunker_Grass);
		createBuildMenuItem(subMenu, group, "Bunker", Land.BadBunker_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Radar", Land.BadRadar);
		createBuildMenuItem(subMenu, group, "Radar", Land.BadRadar_Grass);
		createBuildMenuItem(subMenu, group, "Radar", Land.BadRadar_Sand);

		menu.add(subMenu);

		subMenu = new JMenu("Citizens");

		createBuildMenuItem(subMenu, group, "General", Land.GoodGeneral);
		createBuildMenuItem(subMenu, group, "General", Land.GoodGeneral_Grass);
		createBuildMenuItem(subMenu, group, "General", Land.GoodGeneral_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Officer", Land.GoodOfficer);
		createBuildMenuItem(subMenu, group, "Officer", Land.GoodOfficer_Grass);
		createBuildMenuItem(subMenu, group, "Officer", Land.GoodOfficer_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Soldier", Land.GoodSoldier);
		createBuildMenuItem(subMenu, group, "Soldier", Land.GoodSoldier_Grass);
		createBuildMenuItem(subMenu, group, "Soldier", Land.GoodSoldier_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Tank", Land.GoodTank);
		createBuildMenuItem(subMenu, group, "Tank", Land.GoodTank_Grass);
		createBuildMenuItem(subMenu, group, "Tank", Land.GoodTank_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Bunker", Land.GoodBunker);
		createBuildMenuItem(subMenu, group, "Bunker", Land.GoodBunker_Grass);
		createBuildMenuItem(subMenu, group, "Bunker", Land.GoodBunker_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Radar", Land.GoodRadar);
		createBuildMenuItem(subMenu, group, "Radar", Land.GoodRadar_Grass);
		createBuildMenuItem(subMenu, group, "Radar", Land.GoodRadar_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Peasant", Land.Peasant);
		createBuildMenuItem(subMenu, group, "Peasant", Land.Peasant_Grass);
		createBuildMenuItem(subMenu, group, "Peasant", Land.Peasant_Sand);

		menu.add(subMenu);

		subMenu = new JMenu("Special");

		createBuildMenuItem(subMenu, group, "Transport Center", Land.Teleport5);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Center", Land.Teleport1);
		createBuildMenuItem(subMenu, group, "Capital", Land.Teleport4);
		createBuildMenuItem(subMenu, group, "Jail", Land.Teleport6);
		createBuildMenuItem(subMenu, group, "Grand Hotel", Land.Teleport7);
		createBuildMenuItem(subMenu, group, "Bunker", Land.Teleport8);
		createBuildMenuItem(subMenu, group, "Palace", Land.Teleport9);
		createBuildMenuItem(subMenu, group, "Military Base", Land.Teleport11);

		menu.add(subMenu);

		menuBar.add(menu);
		
		menu = new JMenu("Hero");
		
		createHeroMenuItem(menu, "Hero", Land.Hero);
		createHeroMenuItem(menu, "Peasant", Land.Peasant);
		createHeroMenuItem(menu, "Soldier", Land.GoodSoldier);
		createHeroMenuItem(menu, "Officer", Land.GoodOfficer);
		createHeroMenuItem(menu, "General", Land.GoodGeneral);
		createHeroMenuItem(menu, "Robot", Land.BadSoldier);
		createHeroMenuItem(menu, "Tank", Land.GoodTank);
		createHeroMenuItem(menu, "Gray Officer", Land.BadOfficer);
		createHeroMenuItem(menu, "Gray General", Land.BadGeneral);
		createHeroMenuItem(menu, "Gray Tank", Land.BadTank);
		createHeroMenuItem(menu, "Dark Knight", Land.Dark_Knight);
		
		menu.addSeparator();
		
		menuItem = new JCheckBoxMenuItem("Fight");
		if(DWLauncher.getControlledUnit() != null)
			menuItem.setSelected(DWLauncher.getControlledUnit().isFight());
		else
			menuItem.setSelected(false);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.getControlledUnit().setFight(!DWLauncher.getControlledUnit().isFight());
			}
		});
		menu.add(menuItem);
		
		menuItem = new JCheckBoxMenuItem("Defense");
		if(DWLauncher.getControlledUnit() != null)
			menuItem.setSelected(DWLauncher.getControlledUnit().isDefense());
		else
			menuItem.setSelected(true);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.getControlledUnit().setDefense(!DWLauncher.getControlledUnit().isDefense());
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu = new JMenu("Commands");
		
		ButtonGroup group2 = new ButtonGroup();
		
		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Attack");
		rbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.getLauncher().setAttackMode(true);
			}
		});
		rbMenuItem.setSelected(false);
		group2.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Move to");
		rbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DWLauncher.getLauncher().setAttackMode(false);
			}
		});
		rbMenuItem.setSelected(true);
		group2.add(rbMenuItem);
		menu.add(rbMenuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Default Command");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.DEFAULT_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Activate");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.ACTIVATE_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Deactivate");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.DEACTIVATE_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Stay");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.STAY_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Move Around");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.MOVE_AROUND_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Patrol");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.PATROL_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Defense");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.DEFENSE_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.OPEN_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Close");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionManager.sendCommand(SelectionManager.CLOSE_COMMAND, null);
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);

		return menuBar;
	}
	
	private void createHeroMenuItem(JMenu menu, String name, int code){
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setIcon(new ImageIcon(DWDraw.getImage(code)));
		menuItem.addActionListener(new ChangeManCodeAction(window, code));
		menu.add(menuItem);
	}

	private void createBuildMenuItem(JMenu menu, ButtonGroup group,
			String name, int code) {
		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(name);
		rbMenuItem.setIcon(new ImageIcon(DWDraw.getImage(code)));
		rbMenuItem.addActionListener(new SelectElementAction(code));
		if (DWLauncher.getLauncher().getSelectedElement() == code)
			rbMenuItem.setSelected(true);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);
	}

}
