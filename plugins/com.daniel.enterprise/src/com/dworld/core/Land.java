package com.dworld.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import com.dworld.ui.IProgressMonitor;
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
		rocketList.add(RocketWest);
		rocketList.add(RocketNorthEast);
		rocketList.add(RocketNorthWest);
		rocketList.add(RocketSouthEast);
		rocketList.add(RocketSouthWest);

		rocketList.add(RocketNorth_Grass);
		rocketList.add(RocketSouth_Grass);
		rocketList.add(RocketEast_Grass);
		rocketList.add(RocketWest_Grass);
		rocketList.add(RocketNorthEast_Grass);
		rocketList.add(RocketNorthWest_Grass);
		rocketList.add(RocketSouthEast_Grass);
		rocketList.add(RocketSouthWest_Grass);

		rocketList.add(RocketNorth_Water);
		rocketList.add(RocketSouth_Water);
		rocketList.add(RocketEast_Water);
		rocketList.add(RocketWest_Water);
		rocketList.add(RocketNorthEast_Water);
		rocketList.add(RocketNorthWest_Water);
		rocketList.add(RocketSouthEast_Water);
		rocketList.add(RocketSouthWest_Water);

		rocketList.add(RocketNorth_Sand);
		rocketList.add(RocketSouth_Sand);
		rocketList.add(RocketEast_Sand);
		rocketList.add(RocketWest_Sand);
		rocketList.add(RocketNorthEast_Sand);
		rocketList.add(RocketNorthWest_Sand);
		rocketList.add(RocketSouthEast_Sand);
		rocketList.add(RocketSouthWest_Sand);
	}

	public static Set<Integer> bulletList = new HashSet<>();
	static {
		bulletList.add(Bullet);
		bulletList.add(Bullet_Sand);
		bulletList.add(Bullet_Grass);
		bulletList.add(Bullet_Water);
		bulletList.add(CannonBall);
		bulletList.add(CannonBall_Sand);
		bulletList.add(CannonBall_Grass);
		bulletList.add(CannonBall_Water);
	}

	private static Set<Integer> bombList = new HashSet<>();
	static {
		bombList.add(Bomb);
		bombList.add(Bomb_Sand);
		bombList.add(Bomb_Grass);
		bombList.add(Bomb_Water);
	}
	
	public static Set<Integer> railList = new HashSet<>();
	static {
		railList.add(Rail_Diagonal_Down);
		railList.add(Rail_Diagonal_Up);
		railList.add(Rail_Down_Left);
		railList.add(Rail_Down_Right);
		railList.add(Rail_Horizontal);
		railList.add(Rail_Left_Down);
		railList.add(Rail_Left_Up);
		railList.add(Rail_Right_Down);
		railList.add(Rail_Right_Up);
		railList.add(Rail_Up_Left);
		railList.add(Rail_Up_Right);
		railList.add(Rail_Vertical);
		railList.add(Rail_Vertical_Cross);
		railList.add(Rail_Diagonal_Cross);
	}

	public static Set<Integer> wartrainList = new HashSet<>();
	static {
		wartrainList.add(WarTrain_Diagonal_Down);
		wartrainList.add(WarTrain_Diagonal_Up);
		wartrainList.add(WarTrain_Down_Left);
		wartrainList.add(WarTrain_Down_Right);
		wartrainList.add(WarTrain_Horizontal);
		wartrainList.add(WarTrain_Left_Down);
		wartrainList.add(WarTrain_Left_Up);
		wartrainList.add(WarTrain_Right_Down);
		wartrainList.add(WarTrain_Right_Up);
		wartrainList.add(WarTrain_Up_Left);
		wartrainList.add(WarTrain_Up_Right);
		wartrainList.add(WarTrain_Vertical);
		wartrainList.add(WarTrain_Vertical_Cross);
		wartrainList.add(WarTrain_Horizontal_Cross);
		wartrainList.add(WarTrain_Diagonal_Up_Cross);
		wartrainList.add(WarTrain_Diagonal_Down_Cross);
	}

	public static Set<Integer> trainList = new HashSet<>();
	static {
		trainList.add(Train_Diagonal_Down);
		trainList.add(Train_Diagonal_Up);
		trainList.add(Train_Down_Left);
		trainList.add(Train_Down_Right);
		trainList.add(Train_Horizontal);
		trainList.add(Train_Left_Down);
		trainList.add(Train_Left_Up);
		trainList.add(Train_Right_Down);
		trainList.add(Train_Right_Up);
		trainList.add(Train_Up_Left);
		trainList.add(Train_Up_Right);
		trainList.add(Train_Vertical);
		trainList.add(Train_Vertical_Cross);
		trainList.add(Train_Horizontal_Cross);
		trainList.add(Train_Diagonal_Up_Cross);
		trainList.add(Train_Diagonal_Down_Cross);
		trainList.addAll(wartrainList);
	}
	

	public static Set<Integer> walkList = new HashSet<>();
	static {
		walkList.add(Empty);
		walkList.add(Grenada);
		walkList.add(Ammo);
		walkList.add(Rocket);
		walkList.add(Food);
		walkList.add(Bullet);
		walkList.add(Bullet_Grass);
		walkList.add(Bomb);
		walkList.add(Bomb_Grass);
		walkList.add(RobotGrave);
		walkList.add(OpenedHorizontalWoodGate);
		walkList.add(OpenedVerticalWoodGate);
		walkList.add(OpenedHorizontalSteelGate);
		walkList.add(OpenedVerticalSteelGate);
		walkList.add(OpenedHorizontalConcreteGate);
		walkList.add(OpenedVerticalConcreteGate);
		walkList.add(OpenedHorizontalBrickGate);
		walkList.add(OpenedVerticalBrickGate);
		walkList.add(Grass);
		walkList.add(Mine);
		walkList.add(Mine_Grass);
		walkList.add(Sand);
		walkList.add(Mine_Sand);
		walkList.add(Bullet_Sand);
		walkList.add(Bomb_Sand);
		walkList.add(CannonBall);
		walkList.add(CannonBall_Sand);
		walkList.add(CannonBall_Grass);
		walkList.add(CannonBall_Water);
		walkList.addAll(railList);
	}

	public static Set<Integer> flyAndFindList = new HashSet<>();
	static {
		flyAndFindList.addAll(walkList);
		flyAndFindList.add(Water);
		flyAndFindList.add(Bullet_Water);
		flyAndFindList.add(CannonBall_Water);
		flyAndFindList.add(Bomb_Water);
	}

	private static Set<Integer> unexplosiveList = new HashSet<>();
	static {
		unexplosiveList.add(Vacuum);
		unexplosiveList.add(Wall);
		unexplosiveList.add(OpenedHorizontalSteelGate);
		unexplosiveList.add(ClosedHorizontalSteelGate);
		unexplosiveList.add(OpenedVerticalSteelGate);
		unexplosiveList.add(ClosedVerticalSteelGate);
		unexplosiveList.add(OpenedHorizontalConcreteGate);
		unexplosiveList.add(ClosedHorizontalConcreteGate);
		unexplosiveList.add(OpenedVerticalConcreteGate);
		unexplosiveList.add(ClosedVerticalConcreteGate);
		unexplosiveList.add(Water);
		unexplosiveList.add(Sand);
	}

	/**
	 * list of codes which should be replaced by grass code during the save procedure
	 * they are codes of flying objects above the grass
	 */
	private static Set<Integer> grassList = new HashSet<>();
	static {
		grassList.add(Bullet_Grass);
		grassList.add(CannonBall_Grass);
		grassList.add(Bomb_Grass);
		grassList.add(RocketNorth_Grass);
		grassList.add(RocketSouth_Grass);
		grassList.add(RocketEast_Grass);
		grassList.add(RocketWest_Grass);
		grassList.add(RocketNorthEast_Grass);
		grassList.add(RocketNorthWest_Grass);
		grassList.add(RocketSouthEast_Grass);
		grassList.add(RocketSouthWest_Grass);
	}

	/**
	 * list of codes which should be replaced by water code during the save procedure
	 * they are codes of flying objects above the water
	 */
	private static Set<Integer> waterList = new HashSet<>();
	static {
		waterList.add(Bullet_Water);
		waterList.add(CannonBall_Water);
		waterList.add(Bomb_Water);
		waterList.add(RocketNorth_Water);
		waterList.add(RocketSouth_Water);
		waterList.add(RocketEast_Water);
		waterList.add(RocketWest_Water);
		waterList.add(RocketNorthEast_Water);
		waterList.add(RocketNorthWest_Water);
		waterList.add(RocketSouthEast_Water);
		waterList.add(RocketSouthWest_Water);
	}
	
	/**
	 * list of codes which should be replaced by Sand code during the save procedure
	 * they are codes of flying objects above the sand
	 */
	private static Set<Integer> sandList = new HashSet<>();
	static {
		sandList.add(Sand);
		sandList.add(Bullet_Sand);
		sandList.add(CannonBall_Sand);
		sandList.add(Bomb_Sand);
		sandList.add(RocketNorth_Sand);
		sandList.add(RocketSouth_Sand);
		sandList.add(RocketEast_Sand);
		sandList.add(RocketWest_Sand);
		sandList.add(RocketNorthEast_Sand);
		sandList.add(RocketNorthWest_Sand);
		sandList.add(RocketSouthEast_Sand);
		sandList.add(RocketSouthWest_Sand);
	}
	
	private static Set<Integer> sandExplList = new HashSet<>();
	static {
		sandExplList.add(Hero_Sand);
		sandExplList.add(Mine_Sand);
		sandExplList.add(GoodSoldier_Sand);
		sandExplList.add(GoodOfficer_Sand);
		sandExplList.add(GoodGeneral_Sand);
		sandExplList.add(GoodTank_Sand);
		sandExplList.add(GoodBunker_Sand);
		sandExplList.add(GoodRadar_Sand);
		sandExplList.add(BadSoldier_Sand);
		sandExplList.add(BadTank_Sand);
		sandExplList.add(BadBunker_Sand);
		sandExplList.add(BadRadar_Sand);
		sandExplList.add(BadOfficer_Sand);
		sandExplList.add(BadGeneral_Sand);
		sandExplList.add(Dark_Knight_Sand);
		sandExplList.add(Peasant_Sand);
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
		forBulletList.add(Grenada);
	}

	private static Set<Integer> forRocketList = new HashSet<>();
	static {
		forRocketList.addAll(forBulletList);
		forRocketList.addAll(bulletList);
	}
	
	public final static Set<Integer> armoredEnemyList = new HashSet<>();
	static {
		armoredEnemyList.add(BadTank);
		armoredEnemyList.add(BadTank_Grass);
		armoredEnemyList.add(BadTank_Sand);
		
		armoredEnemyList.add(BadBunker);
		armoredEnemyList.add(BadBunker_Grass);
		armoredEnemyList.add(BadBunker_Sand);
		
		armoredEnemyList.add(BadRadar);
		armoredEnemyList.add(BadRadar_Grass);
		armoredEnemyList.add(BadRadar_Sand);
	}

	public final static Set<Integer> enemyList = new HashSet<>();
	static {
		enemyList.addAll(armoredEnemyList);
		enemyList.add(BadSoldier);
		enemyList.add(BadSoldier_Grass);
		enemyList.add(BadSoldier_Sand);
		enemyList.add(BadOfficer);
		enemyList.add(BadOfficer_Grass);
		enemyList.add(BadOfficer_Sand);
		enemyList.add(BadGeneral);
		enemyList.add(BadGeneral_Grass);
		enemyList.add(BadGeneral_Sand);
		enemyList.add(Dark_Knight);
		enemyList.add(Dark_Knight_Grass);
		enemyList.add(Dark_Knight_Sand);
	}
	
	public static final Set<Integer> armoredCitizenList = new HashSet<>();
	static {
		armoredCitizenList.add(GoodTank);
		armoredCitizenList.add(GoodTank_Grass);
		armoredCitizenList.add(GoodTank_Sand);
		
		armoredCitizenList.add(GoodRadar);
		armoredCitizenList.add(GoodRadar_Grass);
		armoredCitizenList.add(GoodRadar_Sand);
		
		armoredCitizenList.add(GoodBunker);
		armoredCitizenList.add(GoodBunker_Grass);
		armoredCitizenList.add(GoodBunker_Sand);
		
		armoredCitizenList.addAll(trainList);
	}

	public static final Set<Integer> citizenList = new HashSet<>();
	static {
		citizenList.addAll(armoredCitizenList);
		
		citizenList.addAll(heroList);
		
		citizenList.add(GoodSoldier);
		citizenList.add(GoodSoldier_Grass);
		citizenList.add(GoodSoldier_Sand);

		citizenList.add(GoodOfficer);
		citizenList.add(GoodOfficer_Grass);
		citizenList.add(GoodOfficer_Sand);

		citizenList.add(GoodGeneral);
		citizenList.add(GoodGeneral_Grass);
		citizenList.add(GoodGeneral_Sand);
		
		citizenList.add(Peasant);
		citizenList.add(Peasant_Grass);
		citizenList.add(Peasant_Sand);
	}
	
	public static final Set<Integer> gateList = new HashSet<>();
	static {
		gateList.add(OpenedHorizontalSteelGate);
		gateList.add(ClosedHorizontalSteelGate);
		gateList.add(OpenedVerticalSteelGate);
		gateList.add(ClosedVerticalSteelGate);
		gateList.add(OpenedHorizontalConcreteGate);
		gateList.add(ClosedHorizontalConcreteGate);
		gateList.add(OpenedVerticalConcreteGate);
		gateList.add(ClosedVerticalConcreteGate);

		gateList.add(OpenedHorizontalWoodGate);
		gateList.add(ClosedHorizontalWoodGate);
		gateList.add(OpenedVerticalWoodGate);
		gateList.add(ClosedVerticalWoodGate);
		gateList.add(OpenedHorizontalBrickGate);
		gateList.add(ClosedHorizontalBrickGate);
		gateList.add(OpenedVerticalBrickGate);
		gateList.add(ClosedVerticalBrickGate);
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
		if (list.contains(land))
			return true;
		return false;
	}

	public static int getWalkStop(Location location, Direction direction) {
		return getLand(location, direction);
	}

	public static boolean bulletListContains(int code) {
		return bulletList.contains(code);
	}

	public static boolean forBulletListContains(int code) {
		return forBulletList.contains(code);
	}
	
	public static boolean rocketListContains(int code) {
		return forRocketList.contains(code);
	}

	public static boolean unsaveListContains(int code) {
		return unsaveList.contains(code);
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
//			if (list.contains(land)))
//				return distance;
//			if (land == Vacuum)
//				return -1;
//			if (!flyAndFindList.contains(land)))
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
			if (list.contains(land))
				return new SearchResult(land, distance, direction, point);
			if (land == Vacuum)
				return null;
			if (!flyAndFindList.contains(land))
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
				if (!unexplosiveList.contains(code)) {
					if (waterList.contains(code))
						setLand(new Location(x, y), Water);
					else if (sandExplList.contains(code))
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
		DWConfiguration.getInstance().setControlledUnit(unit);
		unit.load(stream);
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
	
	private static void saved(){
		dirty = false;
		DWConfiguration.getInstance().getUI().setTitle(DWConfiguration.TITLE);
	}
	
	public static void modified(){
		dirty = true;
		DWConfiguration.getInstance().getUI().setTitle(DWConfiguration.MODIFYED_TITLE);
	}

	public static void load(String fileName) {
		for (int x = 0; x < DWConstants.MAX_X; x++) {
			for (int y = 0; y < DWConstants.MAX_Y; y++) {
				synchronized(landMap){
					landMap[x][y] = Empty;
				}
			}
		}
		File file = new File(DWConfiguration.getInstance().getPathName()+fileName);
		if (!file.exists()){
			DWConfiguration.getInstance().getUI().showMessageDialog("Error", "File "+file.getAbsolutePath()+" not found");
			return;
		}
		
		IProgressMonitor progressMonitor = DWConfiguration.getInstance().getUI().getProgressMonitor("Loading "+fileName+" file...", DWConstants.MAX_X);
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
		saved();
	}

	public static void save(String fileName) {
		File file = new File(DWConfiguration.getInstance().getPathName()+fileName);
		IProgressMonitor progressMonitor = DWConfiguration.getInstance().getUI().getProgressMonitor("Saving "+fileName+" file...", DWConstants.MAX_X);
		int progress = 0;
		try(FileOutputStream fs = new FileOutputStream(file);
				BufferedOutputStream stream = new BufferedOutputStream(fs);) {
			
			ControlledUnit hero = DWConfiguration.getInstance().getControlledUnit();
			
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
					if (unsaveList.contains(code)) {
						if (waterList.contains(code))
							code = Water;
						else if (grassList.contains(code))
							code = Grass;
						else if (sandList.contains(code))
							code = Sand;
						else
							code = Empty;
					}
					stream.write(code);
					if (saveList.contains(code)) {
						IUnit unit = DWConfiguration.getInstance().getEngine().findUnit(new Location(x, y));
						if (unit != null)
							unit.save(stream);
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		progressMonitor.close();
		saved();
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
