package com.dworld.core;

import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import com.dworld.DWLauncher;
import com.dworld.ui.DWDraw;
import com.dworld.ui.DWMessageDialog;
import com.dworld.ui.DWProgressMonitor;
import com.dworld.units.ControlledUnit;
import com.dworld.units.MovableUnit;
import com.dworld.units.weapon.Bullet;

public class Land {
	private static final int FILE_KEY = 200;
	
	/*******************************************************************************************************************************
	 * Land codes section begin
	 */
	
	public static final int Vacuum = -1;
	public static final int Empty = 0;
	public static final int Wall = 1;
	public static final int Brick = 2;
	public static final int Grenada = 3;
	public static final int Ammo = 4;
	public static final int BadSoldier = 5;
	public static final int BadTank = 6;
	public static final int Hero = 7;
	public static final int Bomb = 8;
	public static final int Bullet = 9;
	public static final int Patron = 10;
	public static final int Grave = 11;
	public static final int Enemy = 12;
	public static final int RocketNorth = 13;
	public static final int RocketSouth = 14;
	public static final int RocketEast = 15;
	public static final int RocketWest = 16;
	public static final int RocketNorthEast = 17;
	public static final int RocketNorthWest = 18;
	public static final int RocketSouthEast = 19;
	public static final int RocketSouthWest = 20;
	public static final int Rocket = 22;
	public static final int Food = 21;
	public static final int OpenedDoor = 23;
	public static final int ClosedDoor = 24;
	public static final int RobotGrave = 25;
	public static final int TankGrave = 26;
	public static final int BadBunker = 27;
	public static final int BadRadar = 28;

	public static final int OpenedHorizontalWoodGate = 29;
	public static final int ClosedHorizontalWoodGate = 30;
	public static final int OpenedVerticalWoodGate = 31;
	public static final int ClosedVerticalWoodGate = 32;
	public static final int OpenedHorizontalSteelGate = 33;
	public static final int ClosedHorizontalSteelGate = 34;
	public static final int OpenedVerticalSteelGate = 35;
	public static final int ClosedVerticalSteelGate = 36;

	public static final int OpenedVerticalConcreteGate = 37;
	public static final int ClosedVerticalConcreteGate = 38;
	public static final int OpenedHorizontalConcreteGate = 39;
	public static final int ClosedHorizontalConcreteGate = 40;
	public static final int OpenedVerticalBrickGate = 41;
	public static final int ClosedVerticalBrickGate = 42;
	public static final int OpenedHorizontalBrickGate = 43;
	public static final int ClosedHorizontalBrickGate = 44;

	public static final int Grass = 45;
	public static final int Hero_Grass = 46;
	public static final int Bullet_Grass = 47;
	public static final int Bomb_Grass = 48;
	public static final int BadSoldier_Grass = 49;
	public static final int BadTank_Grass = 50;
	public static final int RocketNorth_Grass = 51;
	public static final int RocketSouth_Grass = 52;
	public static final int RocketEast_Grass = 53;
	public static final int RocketWest_Grass = 54;
	public static final int RocketNorthEast_Grass = 55;
	public static final int RocketNorthWest_Grass = 56;
	public static final int RocketSouthEast_Grass = 57;
	public static final int RocketSouthWest_Grass = 58;

	public static final int Water = 59;
	public static final int Bullet_Water = 60;
	public static final int Bomb_Water = 61;
	public static final int RocketNorth_Water = 62;
	public static final int RocketSouth_Water = 63;
	public static final int RocketEast_Water = 64;
	public static final int RocketWest_Water = 65;
	public static final int RocketNorthEast_Water = 66;
	public static final int RocketNorthWest_Water = 67;
	public static final int RocketSouthEast_Water = 68;
	public static final int RocketSouthWest_Water = 69;

	public static final int GoodSoldier = 70;
	public static final int GoodSoldier_Grass = 71;
	public static final int GoodTank = 72;
	public static final int GoodTank_Grass = 73;
	public static final int GoodBunker = 74;
	public static final int GoodRadar = 75;

	public static final int Mine = 76;
	public static final int Mine_Grass = 77;

	public static final int Teleport1 = 78;
	public static final int Teleport2 = 79;
	public static final int Teleport3 = 80;
	public static final int Teleport4 = 81;
	public static final int Teleport5 = 82;

	public static final int BadBunker_Grass = 83;
	public static final int BadRadar_Grass = 84;
	public static final int GoodBunker_Grass = 85;
	public static final int GoodRadar_Grass = 86;

	public static final int Sand = 87;
	public static final int Bullet_Sand = 88;
	public static final int Bomb_Sand = 89;
	public static final int RocketNorth_Sand = 90;
	public static final int RocketSouth_Sand = 91;
	public static final int RocketEast_Sand = 92;
	public static final int RocketWest_Sand = 93;
	public static final int RocketNorthEast_Sand = 94;
	public static final int RocketNorthWest_Sand = 95;
	public static final int RocketSouthEast_Sand = 96;
	public static final int RocketSouthWest_Sand = 97;
	public static final int Hero_Sand = 98;
	public static final int GoodSoldier_Sand = 99;
	public static final int GoodTank_Sand = 100;
	public static final int GoodBunker_Sand = 101;
	public static final int GoodRadar_Sand = 102;
	public static final int BadSoldier_Sand = 103;
	public static final int BadTank_Sand = 104;
	public static final int BadBunker_Sand = 105;
	public static final int BadRadar_Sand = 106;
	public static final int Mine_Sand = 107;

	public static final int Stone = 108;

	public static final int Grave_Grass = 109;
	public static final int Grave_Sand = 110;
	public static final int BadSoldierGrave_Grass = 111;
	public static final int BadSoldierGrave_Sand = 112;
	public static final int BadTankGrave_Grass = 113;
	public static final int BadTankGrave_Sand = 114;
	
	public static final int WhiteBrick = 115;
	public static final int BlackStone = 116;
	public static final int Wood1 = 117;
	public static final int Wood2 = 118;
	public static final int Wood3 = 119;
	public static final int Wood4 = 120;
	
	public static final int CannonBall = 121;
	public static final int CannonBall_Grass = 122;
	public static final int CannonBall_Sand = 123;
	public static final int CannonBall_Water = 124;
	
	public static final int GoodOfficer = 125;
	public static final int GoodOfficer_Grass = 126;
	public static final int GoodOfficer_Sand = 127;
	
	public static final int GoodGeneral = 128;
	public static final int GoodGeneral_Grass = 129;
	public static final int GoodGeneral_Sand = 130;
	
	public static final int Teleport6 = 131;
	public static final int Teleport7 = 132;
	public static final int Teleport8 = 133;
	public static final int Teleport9 = 134;
	
	public static final int Tree1 = 135;
	public static final int Tree2 = 136;
	public static final int Tree3 = 137;
	
	public static final int Dark_Knight = 138;
	public static final int Dark_Knight_Grass = 139;
	public static final int Dark_Knight_Sand = 140;

	public static final int BadOfficer = 141;
	public static final int BadOfficer_Grass = 142;
	public static final int BadOfficer_Sand = 143;

	public static final int BadGeneral = 144;
	public static final int BadGeneral_Grass = 145;
	public static final int BadGeneral_Sand = 146;

	public static final int Peasant = 147;
	public static final int Peasant_Grass = 148;
	public static final int Peasant_Sand = 149;
	
	public static final int Rail_Vertical = 150;
	public static final int Rail_Horizontal = 151;
	public static final int Rail_Diagonal_Up = 152;
	public static final int Rail_Diagonal_Down = 153;
	public static final int Rail_Up_Right = 154;
	public static final int Rail_Up_Left = 155;
	public static final int Rail_Down_Right = 156;
	public static final int Rail_Down_Left = 157;
	public static final int Rail_Right_Up = 158;
	public static final int Rail_Right_Down = 159;
	public static final int Rail_Left_Up = 160;
	public static final int Rail_Left_Down = 161;
	
	public static final int Train_Vertical = 162;
	public static final int Train_Horizontal = 163;
	public static final int Train_Diagonal_Up = 164;
	public static final int Train_Diagonal_Down = 165;
	public static final int Train_Up_Right = 166;
	public static final int Train_Up_Left = 167;
	public static final int Train_Down_Right = 168;
	public static final int Train_Down_Left = 169;
	public static final int Train_Right_Up = 170;
	public static final int Train_Right_Down = 171;
	public static final int Train_Left_Up = 172;
	public static final int Train_Left_Down = 173;
	
	public static final int Rail_Vertical_Cross = 174;
	public static final int Rail_Diagonal_Cross = 175;
	
	public static final int Train_Vertical_Cross = 176;
	public static final int Train_Horizontal_Cross = 177;
	public static final int Train_Diagonal_Up_Cross = 178;
	public static final int Train_Diagonal_Down_Cross = 179;
	
	public static final int Station_Vertical = 186;
	public static final int Station_Horizontal = 187;
	
	public static final int WarTrain_Vertical = 188;
	public static final int WarTrain_Horizontal = 189;
	public static final int WarTrain_Diagonal_Up = 190;
	public static final int WarTrain_Diagonal_Down = 191;
	public static final int WarTrain_Up_Right = 192;
	public static final int WarTrain_Up_Left = 193;
	public static final int WarTrain_Down_Right = 194;
	public static final int WarTrain_Down_Left = 195;
	public static final int WarTrain_Right_Up = 196;
	public static final int WarTrain_Right_Down = 197;
	public static final int WarTrain_Left_Up = 198;
	public static final int WarTrain_Left_Down = 199;
	
	public static final int WarTrain_Vertical_Cross = 200;
	public static final int WarTrain_Horizontal_Cross = 201;
	public static final int WarTrain_Diagonal_Up_Cross = 202;
	public static final int WarTrain_Diagonal_Down_Cross = 203;
	
	public static final int Mountain = 204;
	
	public static final int Teleport10 = 205;
	public static final int Teleport11 = 206;
	public static final int Teleport12 = 207;
	public static final int Teleport13 = 208;
	public static final int Teleport14 = 209;
	public static final int Teleport15 = 210;
	
	/**
	 * Land codes section end
	 ****************************************************************************************************/

	/**
	 * Map itself
	 */
	private static int[][] landMap = new int[DWConstants.MAX_X][DWConstants.MAX_Y];
	
	/**
	 * Land lists section start
	 */
	
	public static final Set<Integer> heroList = new HashSet<>();//Collections.unmodifiableList(Arrays.asList(Hero, Hero_Grass, Hero_Sand));
	static{
		heroList.add(Hero);
		heroList.add(Hero_Grass);
		heroList.add(Hero_Sand);
	}

	public static Set<Integer> rocketList = new HashSet<>();
	static {
		rocketList.add(RocketNorth);
		rocketList.add(RocketSouth);
		rocketList.add(RocketEast);
		rocketList.add(new Integer(RocketWest));
		rocketList.add(new Integer(RocketNorthEast));
		rocketList.add(new Integer(RocketNorthWest));
		rocketList.add(new Integer(RocketSouthEast));
		rocketList.add(new Integer(RocketSouthWest));

		rocketList.add(new Integer(RocketNorth_Grass));
		rocketList.add(new Integer(RocketSouth_Grass));
		rocketList.add(new Integer(RocketEast_Grass));
		rocketList.add(new Integer(RocketWest_Grass));
		rocketList.add(new Integer(RocketNorthEast_Grass));
		rocketList.add(new Integer(RocketNorthWest_Grass));
		rocketList.add(new Integer(RocketSouthEast_Grass));
		rocketList.add(new Integer(RocketSouthWest_Grass));

		rocketList.add(new Integer(RocketNorth_Water));
		rocketList.add(new Integer(RocketSouth_Water));
		rocketList.add(new Integer(RocketEast_Water));
		rocketList.add(new Integer(RocketWest_Water));
		rocketList.add(new Integer(RocketNorthEast_Water));
		rocketList.add(new Integer(RocketNorthWest_Water));
		rocketList.add(new Integer(RocketSouthEast_Water));
		rocketList.add(new Integer(RocketSouthWest_Water));

		rocketList.add(new Integer(RocketNorth_Sand));
		rocketList.add(new Integer(RocketSouth_Sand));
		rocketList.add(new Integer(RocketEast_Sand));
		rocketList.add(new Integer(RocketWest_Sand));
		rocketList.add(new Integer(RocketNorthEast_Sand));
		rocketList.add(new Integer(RocketNorthWest_Sand));
		rocketList.add(new Integer(RocketSouthEast_Sand));
		rocketList.add(new Integer(RocketSouthWest_Sand));
	}

	public static Set<Integer> bulletList = new HashSet<>();
	static {
		bulletList.add(new Integer(Bullet));
		bulletList.add(new Integer(Bullet_Sand));
		bulletList.add(new Integer(Bullet_Grass));
		bulletList.add(new Integer(Bullet_Water));
		bulletList.add(new Integer(CannonBall));
		bulletList.add(new Integer(CannonBall_Sand));
		bulletList.add(new Integer(CannonBall_Grass));
		bulletList.add(new Integer(CannonBall_Water));
	}

	private static Set<Integer> bombList = new HashSet<>();
	static {
		bombList.add(new Integer(Bomb));
		bombList.add(new Integer(Bomb_Sand));
		bombList.add(new Integer(Bomb_Grass));
		bombList.add(new Integer(Bomb_Water));
	}
	
	public static Set<Integer> railList = new HashSet<>();
	static {
		railList.add(new Integer(Rail_Diagonal_Down));
		railList.add(new Integer(Rail_Diagonal_Up));
		railList.add(new Integer(Rail_Down_Left));
		railList.add(new Integer(Rail_Down_Right));
		railList.add(new Integer(Rail_Horizontal));
		railList.add(new Integer(Rail_Left_Down));
		railList.add(new Integer(Rail_Left_Up));
		railList.add(new Integer(Rail_Right_Down));
		railList.add(new Integer(Rail_Right_Up));
		railList.add(new Integer(Rail_Up_Left));
		railList.add(new Integer(Rail_Up_Right));
		railList.add(new Integer(Rail_Vertical));
		railList.add(new Integer(Rail_Vertical_Cross));
		railList.add(new Integer(Rail_Diagonal_Cross));
	}

	public static Set<Integer> wartrainList = new HashSet<>();
	static {
		wartrainList.add(new Integer(WarTrain_Diagonal_Down));
		wartrainList.add(new Integer(WarTrain_Diagonal_Up));
		wartrainList.add(new Integer(WarTrain_Down_Left));
		wartrainList.add(new Integer(WarTrain_Down_Right));
		wartrainList.add(new Integer(WarTrain_Horizontal));
		wartrainList.add(new Integer(WarTrain_Left_Down));
		wartrainList.add(new Integer(WarTrain_Left_Up));
		wartrainList.add(new Integer(WarTrain_Right_Down));
		wartrainList.add(new Integer(WarTrain_Right_Up));
		wartrainList.add(new Integer(WarTrain_Up_Left));
		wartrainList.add(new Integer(WarTrain_Up_Right));
		wartrainList.add(new Integer(WarTrain_Vertical));
		wartrainList.add(new Integer(WarTrain_Vertical_Cross));
		wartrainList.add(new Integer(WarTrain_Horizontal_Cross));
		wartrainList.add(new Integer(WarTrain_Diagonal_Up_Cross));
		wartrainList.add(new Integer(WarTrain_Diagonal_Down_Cross));
	}

	public static Set<Integer> trainList = new HashSet<>();
	static {
		trainList.add(new Integer(Train_Diagonal_Down));
		trainList.add(new Integer(Train_Diagonal_Up));
		trainList.add(new Integer(Train_Down_Left));
		trainList.add(new Integer(Train_Down_Right));
		trainList.add(new Integer(Train_Horizontal));
		trainList.add(new Integer(Train_Left_Down));
		trainList.add(new Integer(Train_Left_Up));
		trainList.add(new Integer(Train_Right_Down));
		trainList.add(new Integer(Train_Right_Up));
		trainList.add(new Integer(Train_Up_Left));
		trainList.add(new Integer(Train_Up_Right));
		trainList.add(new Integer(Train_Vertical));
		trainList.add(new Integer(Train_Vertical_Cross));
		trainList.add(new Integer(Train_Horizontal_Cross));
		trainList.add(new Integer(Train_Diagonal_Up_Cross));
		trainList.add(new Integer(Train_Diagonal_Down_Cross));
		trainList.addAll(wartrainList);
	}
	

	public static Set<Integer> walkList = new HashSet<>();
	static {
		walkList.add(new Integer(Empty));
		walkList.add(new Integer(Grenada));
		walkList.add(new Integer(Ammo));
		walkList.add(new Integer(Rocket));
		walkList.add(new Integer(Food));
		walkList.add(new Integer(Bullet));
		walkList.add(new Integer(Bullet_Grass));
		walkList.add(new Integer(Bomb));
		walkList.add(new Integer(Bomb_Grass));
		walkList.add(new Integer(RobotGrave));
		walkList.add(new Integer(OpenedHorizontalWoodGate));
		walkList.add(new Integer(OpenedVerticalWoodGate));
		walkList.add(new Integer(OpenedHorizontalSteelGate));
		walkList.add(new Integer(OpenedVerticalSteelGate));
		walkList.add(new Integer(OpenedHorizontalConcreteGate));
		walkList.add(new Integer(OpenedVerticalConcreteGate));
		walkList.add(new Integer(OpenedHorizontalBrickGate));
		walkList.add(new Integer(OpenedVerticalBrickGate));
		walkList.add(new Integer(Grass));
		walkList.add(new Integer(Mine));
		walkList.add(new Integer(Mine_Grass));
		walkList.add(new Integer(Sand));
		walkList.add(new Integer(Mine_Sand));
		walkList.add(new Integer(Bullet_Sand));
		walkList.add(new Integer(Bomb_Sand));
		walkList.add(new Integer(CannonBall));
		walkList.add(new Integer(CannonBall_Sand));
		walkList.add(new Integer(CannonBall_Grass));
		walkList.add(new Integer(CannonBall_Water));
		walkList.addAll(railList);
	}

	public static Set<Integer> flyAndFindList = new HashSet<>();
	static {
		flyAndFindList.addAll(walkList);
		flyAndFindList.add(new Integer(Water));
		flyAndFindList.add(new Integer(Bullet_Water));
		flyAndFindList.add(new Integer(CannonBall_Water));
		flyAndFindList.add(new Integer(Bomb_Water));
	}

	private static Set<Integer> unexplosiveList = new HashSet<>();
	static {
		unexplosiveList.add(new Integer(Vacuum));
		unexplosiveList.add(new Integer(Wall));
		unexplosiveList.add(new Integer(OpenedHorizontalSteelGate));
		unexplosiveList.add(new Integer(ClosedHorizontalSteelGate));
		unexplosiveList.add(new Integer(OpenedVerticalSteelGate));
		unexplosiveList.add(new Integer(ClosedVerticalSteelGate));
		unexplosiveList.add(new Integer(OpenedHorizontalConcreteGate));
		unexplosiveList.add(new Integer(ClosedHorizontalConcreteGate));
		unexplosiveList.add(new Integer(OpenedVerticalConcreteGate));
		unexplosiveList.add(new Integer(ClosedVerticalConcreteGate));
		unexplosiveList.add(new Integer(Water));
		unexplosiveList.add(new Integer(Sand));
	}

	/**
	 * list of codes which should be replaced by grass code during the save procedure
	 * they are codes of flying objects above the grass
	 */
	private static Set<Integer> grassList = new HashSet<>();
	static {
		grassList.add(new Integer(Bullet_Grass));
		grassList.add(new Integer(CannonBall_Grass));
		grassList.add(new Integer(Bomb_Grass));
		grassList.add(new Integer(RocketNorth_Grass));
		grassList.add(new Integer(RocketSouth_Grass));
		grassList.add(new Integer(RocketEast_Grass));
		grassList.add(new Integer(RocketWest_Grass));
		grassList.add(new Integer(RocketNorthEast_Grass));
		grassList.add(new Integer(RocketNorthWest_Grass));
		grassList.add(new Integer(RocketSouthEast_Grass));
		grassList.add(new Integer(RocketSouthWest_Grass));
	}

	/**
	 * list of codes which should be replaced by water code during the save procedure
	 * they are codes of flying objects above the water
	 */
	private static Set<Integer> waterList = new HashSet<>();
	static {
		waterList.add(new Integer(Bullet_Water));
		waterList.add(new Integer(CannonBall_Water));
		waterList.add(new Integer(Bomb_Water));
		waterList.add(new Integer(RocketNorth_Water));
		waterList.add(new Integer(RocketSouth_Water));
		waterList.add(new Integer(RocketEast_Water));
		waterList.add(new Integer(RocketWest_Water));
		waterList.add(new Integer(RocketNorthEast_Water));
		waterList.add(new Integer(RocketNorthWest_Water));
		waterList.add(new Integer(RocketSouthEast_Water));
		waterList.add(new Integer(RocketSouthWest_Water));
	}
	
	/**
	 * list of codes which should be replaced by Sand code during the save procedure
	 * they are codes of flying objects above the sand
	 */
	private static Set<Integer> sandList = new HashSet<>();
	static {
		sandList.add(new Integer(Sand));
		sandList.add(new Integer(Bullet_Sand));
		sandList.add(new Integer(CannonBall_Sand));
		sandList.add(new Integer(Bomb_Sand));
		sandList.add(new Integer(RocketNorth_Sand));
		sandList.add(new Integer(RocketSouth_Sand));
		sandList.add(new Integer(RocketEast_Sand));
		sandList.add(new Integer(RocketWest_Sand));
		sandList.add(new Integer(RocketNorthEast_Sand));
		sandList.add(new Integer(RocketNorthWest_Sand));
		sandList.add(new Integer(RocketSouthEast_Sand));
		sandList.add(new Integer(RocketSouthWest_Sand));
	}
	
	private static Set<Integer> sandExplList = new HashSet<>();
	static {
		sandExplList.add(new Integer(Hero_Sand));
		sandExplList.add(new Integer(Mine_Sand));
		sandExplList.add(new Integer(GoodSoldier_Sand));
		sandExplList.add(new Integer(GoodOfficer_Sand));
		sandExplList.add(new Integer(GoodGeneral_Sand));
		sandExplList.add(new Integer(GoodTank_Sand));
		sandExplList.add(new Integer(GoodBunker_Sand));
		sandExplList.add(new Integer(GoodRadar_Sand));
		sandExplList.add(new Integer(BadSoldier_Sand));
		sandExplList.add(new Integer(BadTank_Sand));
		sandExplList.add(new Integer(BadBunker_Sand));
		sandExplList.add(new Integer(BadRadar_Sand));
		sandExplList.add(new Integer(BadOfficer_Sand));
		sandExplList.add(new Integer(BadGeneral_Sand));
		sandExplList.add(new Integer(Dark_Knight_Sand));
		sandExplList.add(new Integer(Peasant_Sand));
	}

	private static Set<Integer> unsaveList = new HashSet<>();
	static {
		unsaveList.addAll(bulletList);

		unsaveList.addAll(bombList);

		unsaveList.addAll(rocketList);
	}

	private static Set<Integer> forBulletList = new HashSet<>();
	static {
		forBulletList.addAll(rocketList);
		forBulletList.add(new Integer(Grenada));
	}

	private static Set<Integer> forRocketList = new HashSet<>();
	static {
		forRocketList.addAll(forBulletList);
		forRocketList.addAll(bulletList);
	}
	
	public final static Set<Integer> armoredEnemyList = new HashSet<>();
	static {
		armoredEnemyList.add(new Integer(BadTank));
		armoredEnemyList.add(new Integer(BadTank_Grass));
		armoredEnemyList.add(new Integer(BadTank_Sand));
		
		armoredEnemyList.add(new Integer(BadBunker));
		armoredEnemyList.add(new Integer(BadBunker_Grass));
		armoredEnemyList.add(new Integer(BadBunker_Sand));
		
		armoredEnemyList.add(new Integer(BadRadar));
		armoredEnemyList.add(new Integer(BadRadar_Grass));
		armoredEnemyList.add(new Integer(BadRadar_Sand));
	}

	public final static Set<Integer> enemyList = new HashSet<>();
	static {
		enemyList.addAll(armoredEnemyList);
		enemyList.add(new Integer(BadSoldier));
		enemyList.add(new Integer(BadSoldier_Grass));
		enemyList.add(new Integer(BadSoldier_Sand));
		enemyList.add(new Integer(BadOfficer));
		enemyList.add(new Integer(BadOfficer_Grass));
		enemyList.add(new Integer(BadOfficer_Sand));
		enemyList.add(new Integer(BadGeneral));
		enemyList.add(new Integer(BadGeneral_Grass));
		enemyList.add(new Integer(BadGeneral_Sand));
		enemyList.add(new Integer(Dark_Knight));
		enemyList.add(new Integer(Dark_Knight_Grass));
		enemyList.add(new Integer(Dark_Knight_Sand));
	}
	
	public static final Set<Integer> armoredCitizenList = new HashSet<>();
	static {
		armoredCitizenList.add(new Integer(GoodTank));
		armoredCitizenList.add(new Integer(GoodTank_Grass));
		armoredCitizenList.add(new Integer(GoodTank_Sand));
		
		armoredCitizenList.add(new Integer(GoodRadar));
		armoredCitizenList.add(new Integer(GoodRadar_Grass));
		armoredCitizenList.add(new Integer(GoodRadar_Sand));
		
		armoredCitizenList.add(new Integer(GoodBunker));
		armoredCitizenList.add(new Integer(GoodBunker_Grass));
		armoredCitizenList.add(new Integer(GoodBunker_Sand));
		
		armoredCitizenList.addAll(trainList);
	}

	public static final Set<Integer> citizenList = new HashSet<>();
	static {
		citizenList.addAll(armoredCitizenList);
		
		citizenList.addAll(heroList);
		
		citizenList.add(new Integer(GoodSoldier));
		citizenList.add(new Integer(GoodSoldier_Grass));
		citizenList.add(new Integer(GoodSoldier_Sand));

		citizenList.add(new Integer(GoodOfficer));
		citizenList.add(new Integer(GoodOfficer_Grass));
		citizenList.add(new Integer(GoodOfficer_Sand));

		citizenList.add(new Integer(GoodGeneral));
		citizenList.add(new Integer(GoodGeneral_Grass));
		citizenList.add(new Integer(GoodGeneral_Sand));
		
		citizenList.add(new Integer(Peasant));
		citizenList.add(new Integer(Peasant_Grass));
		citizenList.add(new Integer(Peasant_Sand));
	}
	
	public static final Set<Integer> gateList = new HashSet<>();
	static {
		gateList.add(new Integer(OpenedHorizontalSteelGate));
		gateList.add(new Integer(ClosedHorizontalSteelGate));
		gateList.add(new Integer(OpenedVerticalSteelGate));
		gateList.add(new Integer(ClosedVerticalSteelGate));
		gateList.add(new Integer(OpenedHorizontalConcreteGate));
		gateList.add(new Integer(ClosedHorizontalConcreteGate));
		gateList.add(new Integer(OpenedVerticalConcreteGate));
		gateList.add(new Integer(ClosedVerticalConcreteGate));

		gateList.add(new Integer(OpenedHorizontalWoodGate));
		gateList.add(new Integer(ClosedHorizontalWoodGate));
		gateList.add(new Integer(OpenedVerticalWoodGate));
		gateList.add(new Integer(ClosedVerticalWoodGate));
		gateList.add(new Integer(OpenedHorizontalBrickGate));
		gateList.add(new Integer(ClosedHorizontalBrickGate));
		gateList.add(new Integer(OpenedVerticalBrickGate));
		gateList.add(new Integer(ClosedVerticalBrickGate));
	}

	public static final Set<Integer> saveList = new HashSet<>();
	static {
		saveList.addAll(enemyList);
		saveList.addAll(citizenList);
		saveList.add(Mine);
		saveList.add(Mine_Grass);
		saveList.add(Mine_Sand);
		saveList.addAll(gateList);
	}
	
	public static int getLand(Location location) {
		return getLand(location.getX(), location.getY());
	}

	public static int getTurnedLand(Location location) {
		int code = getLand(location.getX(), location.getY());
		if(gateList.contains(code)){
			switch(code){
			case ClosedHorizontalSteelGate:
				return ClosedVerticalSteelGate;
			case ClosedVerticalSteelGate:
				return ClosedHorizontalSteelGate;
			case ClosedHorizontalConcreteGate:
				return ClosedVerticalConcreteGate;
			case ClosedVerticalConcreteGate:
				return ClosedHorizontalConcreteGate;
			case ClosedHorizontalWoodGate:
				return ClosedVerticalWoodGate;
			case ClosedVerticalWoodGate:
				return ClosedHorizontalWoodGate;
			case ClosedHorizontalBrickGate:
				return ClosedVerticalBrickGate;
			case ClosedVerticalBrickGate:
				return ClosedHorizontalBrickGate;
			}
		}
		return code;
	}

	public static int getLand(int x, int y) {
		if (x > DWConstants.MAX_X - 1 || y > DWConstants.MAX_Y - 1) {
			return Land.Vacuum;
		}
		if (x < DWConstants.MIN_X || y < DWConstants.MIN_Y) {
			return Land.Vacuum;
		}
		int code;
		synchronized(landMap){
			code = landMap[x][y];
		}
		
		return code;
	}

	public static int setLand(Location location, int code) {
		return setLand(location.getX(), location.getY(), code);
	}
	
	public static int setLand(int x, int y, int code) {
		int oldCode = getLand(x, y);
		synchronized(landMap){
			landMap[x][y] = code;
		}
		return oldCode;
	}

	public static int setLand(Location location, MovableUnit unit) {
		int oldCode = getLand(location);
		int newCode = unit.getCode(oldCode);
		synchronized(landMap){
			landMap[location.getX()][location.getY()] = newCode;
		}
		return oldCode;
	}

	public static void initLand(Location location, int beneath, MovableUnit unit) {
		int newCode = unit.getCode(beneath);
		synchronized(landMap){
			landMap[location.getX()][location.getY()] = newCode;
		}
	}

	public static Location getNewLocation(Location location, Direction direction) {
		return direction.getNewLocation(location);
	}

	public static int getLand(Location location, Direction direction) {
		location = getNewLocation(location, direction);
		if (location.getX() > DWConstants.MAX_X - 1 || location.getY() > DWConstants.MAX_Y - 1)
			return Vacuum;
		if (location.getX() < DWConstants.MIN_X || location.getY() < DWConstants.MIN_Y)
			return Vacuum;
		return getLand(location);
	}

	public static boolean canIWalk(Location location, Direction direction,
			Set<Integer> list) {
		int land = getLand(location, direction);
		if (land == Vacuum)
			return false;
		if (list.contains(new Integer(land)))
			return true;
		return false;
	}

	public static int getWalkStop(Location location, Direction direction) {
		return getLand(location, direction);
	}

	public static boolean bulletListContains(int code) {
		return bulletList.contains(new Integer(code));
	}

	public static boolean forBulletListContains(int code) {
		return forBulletList.contains(new Integer(code));
	}
	
	public static boolean rocketListContains(int code) {
		return forRocketList.contains(new Integer(code));
	}

	public static boolean unsaveListContains(int code) {
		return unsaveList.contains(new Integer(code));
	}
	
//	public static int findUnit(Point location, Direction direction, List<Integer> list) {
//		return findUnit(location, direction, list, DWConstants.VISIBLE_DISTANCE);
//	}
//
//	public static int findUnit(final Point location, final Direction direction, final List<Integer> list, final int maxDistance) {
//		Point point = getNewLocation((Point) location, direction);
//
//		int land = Vacuum;
//		int distance = 1;
//		while (true) {
//			land = getLand(point);
//			if (list.contains(new Integer(land)))
//				return distance;
//			if (land == Vacuum)
//				return -1;
//			if (!flyAndFindList.contains(new Integer(land)))
//				return -1;
//			distance++;
//			if (distance > maxDistance)
//				return -1;
//			point = getNewLocation(point, direction);
//		}
//	}
	
	public static SearchResult search(final Location location, final Direction direction, final Set<Integer> list) {
		return search(location, direction, list, DWConstants.VISIBLE_DISTANCE);
	}
	
	public static SearchResult search(final Location location, final Direction direction, final Set<Integer> list, final int maxDistance) {
		Location point = getNewLocation((Location) location, direction);

		int land = Vacuum;
		int distance = 1;
		while (true) {
			land = getLand(point);
			if (list.contains(new Integer(land)))
				return new SearchResult(land, distance, direction, point);
			if (land == Vacuum)
				return null;
			if (!flyAndFindList.contains(new Integer(land)))
				return null;
			distance++;
			if (distance > maxDistance)
				return null;
			point = getNewLocation(point, direction);
		}
	}

	public static int getMaxX() {
		return DWConstants.MAX_X;
	}

	public static int getMaxY() {
		return DWConstants.MAX_Y;
	}

	public static void explode(final Location location) {
		for (int x = location.getX() - 1; x < location.getX() + 2; x++) {
			for (int y = location.getY() - 1; y < location.getY() + 2; y++) {
				int code = getLand(x, y);
				if (!unexplosiveList.contains(new Integer(code))) {
					if (waterList.contains(new Integer(code)))
						setLand(new Location(x, y), Water);
					else if (sandExplList.contains(new Integer(code)))
						setLand(new Location(x, y), Sand);
					else
						setLand(new Location(x, y), Empty);
				}
			}
		}
		Direction dir = Direction.NORTH;
		for (int i = 0; i < 8; i++) {
			new Bullet(location.getX(), location.getY(), dir);
			dir = dir.getClockwiseDirection();
		}
	}

	private static void loadMan(final int x, final int y, int code,
			InputStream stream) throws IOException {
		ControlledUnit unit = new ControlledUnit(x, y, code);
		DWLauncher.setControlledUnit(unit);
		unit.load(stream);
		DWDraw.setUnit(unit);
	}

	
	private static boolean loadUnit(int code, int x, int y,	InputStream stream) throws IOException {
		IUnit unit = DWUnitFactory.createUnit(code, x, y);
		if(unit != null){
			unit.load(stream);
			return true;
		}
		return false;
	}

	private static boolean dirty = false;
	
	public static boolean isDirty(){
		return dirty;
	}
	
	private static void saved(Frame panel){
		dirty = false;
		panel.setTitle(DWLauncher.TITLE);
	}
	
	public static void modified(Frame panel){
		dirty = true;
		panel.setTitle(DWLauncher.MODIFYED_TITLE);
	}

	public static void load(String fileName, Frame panel) {
		for (int x = 0; x < DWConstants.MAX_X; x++) {
			for (int y = 0; y < DWConstants.MAX_Y; y++) {
				synchronized(landMap){
					landMap[x][y] = Empty;
				}
			}
		}
		File file = new File(DWLauncher.getPath()+fileName);
		if (!file.exists()){
			new DWMessageDialog(panel, "Error", "File "+file.getAbsolutePath()+" not found");
			return;
		}
		
		DWProgressMonitor progressMonitor = new DWProgressMonitor(panel, "Loading "+fileName+" file...", DWConstants.MAX_X);
		int progress = 0;
		try(FileInputStream fs = new FileInputStream(file);
				BufferedInputStream stream = new BufferedInputStream(fs);) {
			
			
			int heroX = readInt(stream);
			int heroY = readInt(stream);
			
			for (int x = 0; x < DWConstants.MAX_X; x++) {
				progress++;
				progressMonitor.setProgress(progress);

				for (int y = 0; y < DWConstants.MAX_Y; y++) {
					int code = stream.read();
					
					if(x == heroX && y == heroY){
						loadMan(x, y, code, stream);
						continue;
					}
					
					if((x != heroX || y != heroY) &&
							loadUnit(code, x, y, stream)){
						continue;
					}
					landMap[x][y] = code;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		progressMonitor.close();
		saved(panel);
	}

	public static void save(String fileName, Frame panel) {
		File file = new File(DWLauncher.getPath()+fileName);
		DWProgressMonitor progressMonitor = new DWProgressMonitor(panel, "Saving "+fileName+" file...", DWConstants.MAX_X);
		int progress = 0;
		try(FileOutputStream fs = new FileOutputStream(file);
				BufferedOutputStream stream = new BufferedOutputStream(fs);) {
			
			ControlledUnit hero = DWLauncher.getControlledUnit();
			
			writeInt(stream, hero.getLocation().getX());
			writeInt(stream, hero.getLocation().getY());
			
			for (int x = 0; x < DWConstants.MAX_X; x++) {
				progress++;
				progressMonitor.setProgress(progress);
				for (int y = 0; y < DWConstants.MAX_Y; y++) {
					int code;
					synchronized(landMap){
						code = landMap[x][y];
					}
					Integer intCode = new Integer(code);
					if (unsaveList.contains(intCode)) {
						if (waterList.contains(intCode))
							code = Water;
						else if (grassList.contains(intCode))
							code = Grass;
						else if (sandList.contains(intCode))
							code = Sand;
						else
							code = Empty;
					}
					stream.write(code);
					if (saveList.contains(code)) {
						IUnit unit = DWEngine.getEngine().findUnit(new Location(x, y));
						if (unit != null)
							unit.save(stream);
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		progressMonitor.close();
		saved(panel);
	}
	
	public static void writeInt(BufferedOutputStream stream, int value) throws IOException{
		int value1 = value/FILE_KEY;
		int value2 = value - value1*FILE_KEY;
		
		stream.write(value1);
		stream.write(value2);
	}
	
	public static int readInt(BufferedInputStream stream) throws IOException{
		int value1 = stream.read();
		int value2 = stream.read();
		
		return value1*FILE_KEY+value2;
	}
}
