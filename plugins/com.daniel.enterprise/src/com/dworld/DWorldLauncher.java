package com.dworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import com.dworld.core.DWorldConstants;
import com.dworld.core.Engine;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.ui.BuildingPalette;
import com.dworld.ui.DWorldWindowListener;
import com.dworld.ui.DrawWorld;
import com.dworld.ui.InfoScreen;
import com.dworld.ui.Map;
import com.dworld.units.ControlledUnit;

public class DWorldLauncher implements KeyListener, FocusListener,
		MouseListener, MouseMotionListener {
	private static final String SAVE_FILE = "/save.dat";
	private static final String BACKUP_FILE = "/backup.dat";
	private static final String TEST_FILE = "/test.dat";
	
	private static final int BRUSH = 1;
	private static final int LINE = 2;
	private static final int RECTANGLE = 3;
	
	private static int draw_mode = BRUSH;
	
	public static DWorldLauncher launcher;
	private static ControlledUnit controlledUnit = null;
	private JFrame window;
	private static String path;
	private JPanel panel = null;
	private int selectedElement = Land.Brick;
	private boolean buildMode = false;
	public static final String TITLE = "D World";
	public static final String MODIFYED_TITLE = "*D World";
	
	private static boolean attack_mode = false;
	
	private Engine engine;

	public DWorldLauncher() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launcher = new DWorldLauncher();

		launcher.init();
		
		launcher.start();
	}
	
	public static String getPath(){
		return path;
	}

	private void init() {
		File jar = new File(DWorldLauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		if(jar.exists()){
			path = jar.getParent();
		}else{
			path = "";
		}
		
		window = new JFrame();
		engine = new Engine(window);
		DWorldWindowListener.getDefault().addMainWindow(window);
		initMenu();
		initWindow();
		initElements();
	}

	private void start() {
		Land.load(SAVE_FILE, window);
		engine.init();
		engine.run();
	}

	private void initWindow() {

		window.setTitle(TITLE);
		window.setIconImage(new ImageIcon("resources/land/patriot.gif")
				.getImage());
		panel = new JPanel() {
			static final long serialVersionUID = 12;

			public void paint(Graphics g) {
				DrawWorld.draw(g);
			}
		};
		
		panel.setSize(DWorldConstants.UI_WIDTH * DWorldConstants.UI_IMAGE_WIDTH, DWorldConstants.UI_HEIGHT * DWorldConstants.UI_IMAGE_HEIGHT);
		window.setLayout(new BorderLayout());
		window.add(panel, BorderLayout.CENTER);
		initToolBar();
		window.pack();
		window.setSize(DWorldConstants.UI_WIDTH * DWorldConstants.UI_IMAGE_WIDTH + 8,
				DWorldConstants.UI_HEIGHT * DWorldConstants.UI_IMAGE_HEIGHT + 48);
		window.setLocation(500, 10);
		window.addKeyListener(launcher);
		panel.addMouseListener(launcher);
		panel.addMouseMotionListener(launcher);
		window.addFocusListener(launcher);
		window.setResizable(false);
		window.setVisible(true);
		window.setFocusable(true);
		window.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent arg0) {
			}

			public void windowClosed(WindowEvent arg0) {
			}

			public void windowClosing(WindowEvent arg0) {
				exitConfirmation();
				System.exit(0);
			}

			public void windowDeactivated(WindowEvent arg0) {
			}

			public void windowDeiconified(WindowEvent arg0) {
			}

			public void windowIconified(WindowEvent arg0) {
			}

			public void windowOpened(WindowEvent arg0) {
			}
		});
		
		Map.switchMinimap();
	}
	
	private boolean exitConfirmation(){
		if (Land.isDirty()) {
			int n = JOptionPane.showConfirmDialog(window,
					"Do you want to save the game?", "Question",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				engine.saveAndExit(SAVE_FILE);
				return false;
			}
		}
		return true;
	}

	private void initMenu() {
		JCheckBoxMenuItem cbMenuItem;
		JMenuItem menuItem;

		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");

		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.load(SAVE_FILE);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.save(SAVE_FILE);
			}
		});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Load Backup");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.load(BACKUP_FILE);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save Backup");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.save(BACKUP_FILE);
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();

		menuItem = new JMenuItem("Load Test");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.load(TEST_FILE);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save Test");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.save(TEST_FILE);
			}
		});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(exitConfirmation())
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
				Map.showMap();
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Mini Map");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map.showMinimap();
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();

		menuItem = new JMenuItem("Pause");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.pause(true);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Go");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.pause(false);
			}
		});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Info");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfoScreen(window, controlledUnit);
			}
		});
		menu.add(menuItem);

		menu = new JMenu("Build");
		cbMenuItem = new JCheckBoxMenuItem("Build mode");
		cbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildMode = !buildMode;
				if(buildMode){
					BuildingPalette.showPalette();
				}else{
					BuildingPalette.hidePalette();
				}
			}
		});
		menu.add(cbMenuItem);
		menu.addSeparator();
		
		ButtonGroup buildGroup = new ButtonGroup();
		
		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Brush");
		//rbMenuItem.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		rbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw_mode = BRUSH;
			}
		});
		rbMenuItem.setSelected(true);
		buildGroup.add(rbMenuItem);
		menu.add(rbMenuItem);
		
		rbMenuItem = new JRadioButtonMenuItem("Line");
		//rbMenuItem.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		rbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw_mode = LINE;
			}
		});
		rbMenuItem.setSelected(true);
		buildGroup.add(rbMenuItem);
		menu.add(rbMenuItem);
		
		rbMenuItem = new JRadioButtonMenuItem("Rectangle");
		//rbMenuItem.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		rbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw_mode = RECTANGLE;
			}
		});
		rbMenuItem.setSelected(true);
		buildGroup.add(rbMenuItem);
		menu.add(rbMenuItem);
		
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
		createBuildMenuItem(subMenu, group, "Grass", Land.Grass);
		
		createBuildMenuItem(subMenu, group, "Water", Land.Water);
		createBuildMenuItem(subMenu, group, "Sand", Land.Sand);
		subMenu.addSeparator();
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

		createBuildMenuItem(subMenu, group, "General", Land.Gray_General);
		createBuildMenuItem(subMenu, group, "General", Land.Gray_General_Grass);
		createBuildMenuItem(subMenu, group, "General", Land.Gray_General_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Officer", Land.Gray_Officer);
		createBuildMenuItem(subMenu, group, "Officer", Land.Gray_Officer_Grass);
		createBuildMenuItem(subMenu, group, "Officer", Land.Gray_Officer_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Robot", Land.Robot);
		createBuildMenuItem(subMenu, group, "Robot", Land.Robot_Grass);
		createBuildMenuItem(subMenu, group, "Robot", Land.Robot_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Tank", Land.Tank);
		createBuildMenuItem(subMenu, group, "Tank", Land.Tank_Grass);
		createBuildMenuItem(subMenu, group, "Tank", Land.Tank_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Bunker", Land.Bunker);
		createBuildMenuItem(subMenu, group, "Bunker", Land.Bunker_Grass);
		createBuildMenuItem(subMenu, group, "Bunker", Land.Bunker_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Radar", Land.Radar);
		createBuildMenuItem(subMenu, group, "Radar", Land.Radar_Grass);
		createBuildMenuItem(subMenu, group, "Radar", Land.Radar_Sand);

		menu.add(subMenu);

		subMenu = new JMenu("Citizens");

		createBuildMenuItem(subMenu, group, "General", Land.General);
		createBuildMenuItem(subMenu, group, "General", Land.General_Grass);
		createBuildMenuItem(subMenu, group, "General", Land.General_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Officer", Land.Officer);
		createBuildMenuItem(subMenu, group, "Officer", Land.Officer_Grass);
		createBuildMenuItem(subMenu, group, "Officer", Land.Officer_Sand);
		subMenu.addSeparator();
		createBuildMenuItem(subMenu, group, "Soldier", Land.Soldier);
		createBuildMenuItem(subMenu, group, "Soldier", Land.Soldier_Grass);
		createBuildMenuItem(subMenu, group, "Soldier", Land.Soldier_Sand);
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
		createBuildMenuItem(subMenu, group, "Home", Land.Teleport2);
		createBuildMenuItem(subMenu, group, "Scientific Center", Land.Teleport3);
		createBuildMenuItem(subMenu, group, "Capital", Land.Teleport4);
		createBuildMenuItem(subMenu, group, "Jail", Land.Teleport6);
		createBuildMenuItem(subMenu, group, "Grand Hotel", Land.Teleport7);
		createBuildMenuItem(subMenu, group, "Bunker", Land.Teleport8);
		createBuildMenuItem(subMenu, group, "Palace", Land.Teleport9);

		menu.add(subMenu);

		menuBar.add(menu);
		
		menu = new JMenu("Hero");
		
		createHeroMenuItem(menu, "Hero", Land.Man);
		createHeroMenuItem(menu, "Peasant", Land.Peasant);
		createHeroMenuItem(menu, "Soldier", Land.Soldier);
		createHeroMenuItem(menu, "Officer", Land.Officer);
		createHeroMenuItem(menu, "General", Land.General);
		createHeroMenuItem(menu, "Robot", Land.Robot);
		createHeroMenuItem(menu, "Tank", Land.GoodTank);
		createHeroMenuItem(menu, "Gray Officer", Land.Gray_Officer);
		createHeroMenuItem(menu, "Gray General", Land.Gray_General);
		createHeroMenuItem(menu, "Gray Tank", Land.Tank);
		createHeroMenuItem(menu, "Dark Knight", Land.Dark_Knight);
		
		menu.addSeparator();
		
		menuItem = new JCheckBoxMenuItem("Fight");
		if(controlledUnit != null)
			menuItem.setSelected(controlledUnit.isFight());
		else
			menuItem.setSelected(false);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlledUnit.setFight(!controlledUnit.isFight());
			}
		});
		menu.add(menuItem);
		
		menuItem = new JCheckBoxMenuItem("Defense");
		if(controlledUnit != null)
			menuItem.setSelected(controlledUnit.isDefense());
		else
			menuItem.setSelected(true);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlledUnit.setDefense(!controlledUnit.isDefense());
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu = new JMenu("Commands");
		
		ButtonGroup group2 = new ButtonGroup();
		
		rbMenuItem = new JRadioButtonMenuItem("Attack");
		rbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack_mode = true;
			}
		});
		rbMenuItem.setSelected(false);
		group2.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Move to");
		rbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack_mode = false;
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

		window.setJMenuBar(menuBar);
	}
	
	private void initToolBar(){
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
		
		window.add(toolBar, BorderLayout.NORTH);
	}
	
	private void createHeroButton(JToolBar toolBar, final int code){
		JButton button = new JButton();
		button.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		button.addActionListener(new ChangeManCodeAction(code));
		
		toolBar.add(button);
	}
	
	private void createHeroMenuItem(JMenu menu, String name, int code){
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		menuItem.addActionListener(new ChangeManCodeAction(code));
		menu.add(menuItem);
	}

	private void createBuildMenuItem(JMenu menu, ButtonGroup group,
			String name, int code) {
		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(name);
		rbMenuItem.setIcon(new ImageIcon(DrawWorld.getImage(code)));
		rbMenuItem.addActionListener(new SelectElementAction(code));
		if (selectedElement == code)
			rbMenuItem.setSelected(true);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);
	}

	private void initElements() {
	}

	@SuppressWarnings("unused")
	private void initControl() {
		controlledUnit = new ControlledUnit(1, 1, Land.Man);
		DrawWorld.setUnit(controlledUnit);
	}

	public void focusGained(FocusEvent arg0) {
	}

	public void focusLost(FocusEvent arg0) {
		//Engine.pause(true);
	}
	
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == 0)
			return;
		else if (event.getKeyCode() == 27) {
			if(exitConfirmation())
				System.exit(0);
		}else if (event.getKeyCode() == 77) { // m
			Map.showMap();
		}else if (event.getKeyCode() == 78) { // n
			Map.switchMinimap();
		}
		
		if (controlledUnit != null)
			controlledUnit.control(event.getKeyCode(), event.getModifiers());
		// System.out.println("keyPressed");
		// System.out.println("Char - "+event.getKeyChar());
		// System.out.println("Code - "+event.getKeyCode());
		// System.out.println("Modifiers - "+event.getModifiers());
		// System.out.println("ModifiersExt - "+event.getModifiersEx());
	}

	public void keyReleased(KeyEvent event) {

	}

	public void keyTyped(KeyEvent event) {

	}

	public void mouseClicked(MouseEvent e) {
//		if (e.getButton() != MouseEvent.BUTTON1) {
//			SelectionManager.clearSelection();
//			Point location = getLocation(e.getX(), e.getY());
//			SelectionManager.setSelectedArea(new Rectangle(location, new Dimension(1, 1)));
//			SelectionManager.sendCommand(SelectionManager.DEFAULT_COMMAND, null);
//		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	int button;

	Point selectedPoint = null;
	
	private Point getLocation(int mouseX, int mouseY){
		return new Point(
			DrawWorld.getX() + (mouseX - 3) / DWorldConstants.UI_IMAGE_WIDTH,
			DrawWorld.getY() + mouseY / DWorldConstants.UI_IMAGE_HEIGHT
		);
	}

	public void mousePressed(MouseEvent e) {
		button = e.getButton();
		
		Point location = getLocation(e.getX(), e.getY());

		if (!buildMode) { // selection mode
			if (e.getButton() == MouseEvent.BUTTON1) {
				SelectionManager.clearSelection();
				selectedPoint = location;
				SelectionManager.setSelectedArea(new Rectangle(selectedPoint,
						new Dimension(1, 1)));
			} else {
				if(!SelectionManager.sendDefaultCommand(location)){
					if(attack_mode){
						SelectionManager.<Point>sendCommand(SelectionManager.ATTACK_COMMAND, location);
					}else{
						SelectionManager.<Point>sendCommand(SelectionManager.MOVE_TO_COMMAND, location);
					}
				}
				
				selectedPoint = null;
				SelectionManager.setSelectedArea(null);
			}
			return;
		}else if(draw_mode == RECTANGLE){
			SelectionManager.clearSelection();
			selectedPoint = location;
			SelectionManager.setSelectedArea(new Rectangle(selectedPoint,
					new Dimension(1, 1)));
		}else if(draw_mode == LINE){
				SelectionManager.clearSelection();
				selectedPoint = location;
				SelectionManager.setSelectedLine(selectedPoint, selectedPoint);
		}else{
			if (e.getButton() == MouseEvent.BUTTON1)
				Land.setLand(location, selectedElement);
			else
				Land.setLand(location, Land.Empty);
	
			Land.modified(window);
		}
	}

	public void mouseReleased(MouseEvent e) {
		button = e.getButton();
		if(buildMode){
			if(draw_mode == RECTANGLE){
				Rectangle rectangle = SelectionManager.getSelectedArea();
				if(rectangle == null)
					return;
				for(int x = rectangle.x; x < rectangle.x+rectangle.width; x++){
					for(int y = rectangle.y; y < rectangle.y+rectangle.height; y++){
						if (e.getButton() == MouseEvent.BUTTON1)
							Land.setLand(new Point(x, y), selectedElement);
						else
							Land.setLand(new Point(x, y), Land.Empty);
					}
				}
				Land.modified(window);
			}else if(draw_mode == LINE){
				ArrayList<Point> points = SelectionManager.getSelectedLine();
				if(points != null){
					for(Point point : points){
						if (e.getButton() == MouseEvent.BUTTON1)
							Land.setLand(point, selectedElement);
						else
							Land.setLand(point, Land.Empty);
						
					}
				}
				Land.modified(window);
			}
		}
		if(!buildMode){
			SelectionManager.select();
		}
	}

	class SelectElementAction implements ActionListener {
		int code;

		public SelectElementAction(int code) {
			this.code = code;
		}

		public void actionPerformed(ActionEvent e) {
			setSelectedElement(code);
		}
	}
	
	public void setSelectedElement(int code){
		selectedElement = code;
	}

	class ChangeManCodeAction implements ActionListener {
		int code;

		public ChangeManCodeAction(int code) {
			this.code = code;
		}

		public void actionPerformed(ActionEvent e) {
			engine.changeManCode(code);
			window.requestFocus();
		}
	}

	public static ControlledUnit getControlledUnit() {
		return controlledUnit;
	}

	public static void setControlledUnit(ControlledUnit controlledUnit) {
		DWorldLauncher.controlledUnit = controlledUnit;
	}

	public boolean isBuildMode() {
		return buildMode;
	}

	public void setBuildMode(boolean buildMode) {
		this.buildMode = buildMode;
	}

	int lastX = 0, lastY = 0;

	public void mouseDragged(MouseEvent e) {

		Point location = getLocation(e.getX(), e.getY());
		if (lastX == location.x && lastY == location.y)
			return;
		lastX = location.x;
		lastY = location.y;
		if (!buildMode || draw_mode == RECTANGLE) {
			if (selectedPoint != null) {
				int topX, topY, width, height;

				if (location.x < selectedPoint.x) {
					topX = location.x;
					width = selectedPoint.x - location.x + 1;
				} else if (location.x == selectedPoint.x) {
					topX = location.x;
					width = 1;
				} else {
					topX = selectedPoint.x;
					width = location.x - selectedPoint.x + 1;
				}

				if (location.y < selectedPoint.y) {
					topY = location.y;
					height = selectedPoint.y - location.y + 1;
				} else if (location.y == selectedPoint.y) {
					topY = location.y;
					height = 1;
				} else {
					topY = selectedPoint.y;
					height = location.y - selectedPoint.y + 1;
				}

				SelectionManager.setSelectedArea(new Rectangle(topX, topY,
						width, height));
			}
			return;
		}else if(buildMode && draw_mode == LINE){
			SelectionManager.setSelectedLine(selectedPoint, location);
			return;
		}

		if(draw_mode == BRUSH){
			if (button == MouseEvent.BUTTON1)
				Land.setLand(location, selectedElement);
			else
				Land.setLand(location, Land.Empty);
		}
	}

	public void mouseMoved(MouseEvent arg0) {
	}
}
