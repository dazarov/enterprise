package com.dworld.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import com.dworld.ui.IProgressMonitor;
import com.dworld.units.ControlledUnit;
import com.dworld.units.MovableUnit;
import com.dworld.units.weapon.Bullet;

public enum Land {
	
	
	/*******************************************************************************************************************************
	 * Land codes section begin
	 */
	
	Vacuum, // -1 -> 0
	Empty,	// 0 -> 1
	Wall,	// 1 -> 2
	Brick,
	Grenade,
	Ammo,
	BadSoldier,
	BadTank,
	Hero,
	Bomb,
	Bullet,
	Patron,
	Grave,
	Enemy,
	RocketNorth,
	RocketSouth,
	RocketEast,
	RocketWest,
	RocketNorthEast,
	RocketNorthWest,
	RocketSouthEast,
	RocketSouthWest,
	Food,
	Rocket,
	OpenedDoor,
	ClosedDoor,
	RobotGrave,
	TankGrave,
	BadBunker,
	BadRadar,

	OpenedHorizontalWoodGate,
	ClosedHorizontalWoodGate,
	OpenedVerticalWoodGate,
	ClosedVerticalWoodGate,
	OpenedHorizontalSteelGate,
	ClosedHorizontalSteelGate,
	OpenedVerticalSteelGate,
	ClosedVerticalSteelGate,

	OpenedVerticalConcreteGate,
	ClosedVerticalConcreteGate,
	OpenedHorizontalConcreteGate,
	ClosedHorizontalConcreteGate,
	OpenedVerticalBrickGate,
	ClosedVerticalBrickGate,
	OpenedHorizontalBrickGate,
	ClosedHorizontalBrickGate,

	Grass,
	Hero_Grass,
	Bullet_Grass,
	Bomb_Grass,
	BadSoldier_Grass,
	BadTank_Grass,
	RocketNorth_Grass,
	RocketSouth_Grass,
	RocketEast_Grass,
	RocketWest_Grass,
	RocketNorthEast_Grass,
	RocketNorthWest_Grass,
	RocketSouthEast_Grass,
	RocketSouthWest_Grass,

	Water,
	Bullet_Water,
	Bomb_Water,
	RocketNorth_Water,
	RocketSouth_Water,
	RocketEast_Water,
	RocketWest_Water,
	RocketNorthEast_Water,
	RocketNorthWest_Water,
	RocketSouthEast_Water,
	RocketSouthWest_Water,

	GoodSoldier,
	GoodSoldier_Grass,
	GoodTank,
	GoodTank_Grass,
	GoodBunker,
	GoodRadar,

	Mine,
	Mine_Grass,

	Teleport1,
	Teleport2,
	Teleport3,
	Teleport4,
	Teleport5,

	BadBunker_Grass,
	BadRadar_Grass,
	GoodBunker_Grass,
	GoodRadar_Grass,

	Sand,
	Bullet_Sand,
	Bomb_Sand,
	RocketNorth_Sand,
	RocketSouth_Sand,
	RocketEast_Sand,
	RocketWest_Sand,
	RocketNorthEast_Sand,
	RocketNorthWest_Sand,
	RocketSouthEast_Sand,
	RocketSouthWest_Sand,
	Hero_Sand,
	GoodSoldier_Sand,
	GoodTank_Sand,
	GoodBunker_Sand,
	GoodRadar_Sand,
	BadSoldier_Sand,
	BadTank_Sand,
	BadBunker_Sand,
	BadRadar_Sand,
	Mine_Sand,

	Stone,

	Grave_Grass,
	Grave_Sand,
	BadSoldierGrave_Grass,
	BadSoldierGrave_Sand,
	BadTankGrave_Grass,
	BadTankGrave_Sand,
	
	WhiteBrick,
	BlackStone,
	Wood1,
	Wood2,
	Wood3,
	Wood4,
	
	CannonBall,
	CannonBall_Grass,
	CannonBall_Sand,
	CannonBall_Water,
	
	GoodOfficer,
	GoodOfficer_Grass,
	GoodOfficer_Sand,
	
	GoodGeneral,
	GoodGeneral_Grass,
	GoodGeneral_Sand,
	
	Teleport6,
	Teleport7,
	Teleport8,
	Teleport9,
	
	Tree1,
	Tree2,
	Tree3,
	
	Dark_Knight,
	Dark_Knight_Grass,
	Dark_Knight_Sand,

	BadOfficer,
	BadOfficer_Grass,
	BadOfficer_Sand,

	BadGeneral,
	BadGeneral_Grass,
	BadGeneral_Sand,

	Peasant,
	Peasant_Grass,
	Peasant_Sand,
	
	Rail_Vertical,
	Rail_Horizontal,
	Rail_Diagonal_Up,
	Rail_Diagonal_Down,
	Rail_Up_Right,
	Rail_Up_Left,
	Rail_Down_Right,
	Rail_Down_Left,
	Rail_Right_Up,
	Rail_Right_Down,
	Rail_Left_Up,
	Rail_Left_Down,
	
	Train_Vertical,
	Train_Horizontal,
	Train_Diagonal_Up,
	Train_Diagonal_Down,
	Train_Up_Right,
	Train_Up_Left,
	Train_Down_Right,
	Train_Down_Left,
	Train_Right_Up,
	Train_Right_Down,
	Train_Left_Up,
	Train_Left_Down,
	
	Rail_Vertical_Cross,
	Rail_Diagonal_Cross,
	
	Train_Vertical_Cross,
	Train_Horizontal_Cross,
	Train_Diagonal_Up_Cross,
	Train_Diagonal_Down_Cross,
	
	Station_Vertical, // 186+ -> 180
	Station_Horizontal,
	
	WarTrain_Vertical,
	WarTrain_Horizontal,
	WarTrain_Diagonal_Up,
	WarTrain_Diagonal_Down,
	WarTrain_Up_Right,
	WarTrain_Up_Left,
	WarTrain_Down_Right,
	WarTrain_Down_Left,
	WarTrain_Right_Up,
	WarTrain_Right_Down,
	WarTrain_Left_Up,
	WarTrain_Left_Down,
	
	WarTrain_Vertical_Cross,
	WarTrain_Horizontal_Cross,
	WarTrain_Diagonal_Up_Cross,
	WarTrain_Diagonal_Down_Cross,
	
	Mountain,
	
	Teleport10,
	Teleport11,
	Teleport12,
	Teleport13,
	Teleport14,
	Teleport15;
	
	private static final int FILE_KEY = 200;
	
	/**
	 * Land codes section end
	 ****************************************************************************************************/

	/**
	 * Map itself
	 */
	private static Land[][] landMap = new Land[DWConstants.MAX_X][DWConstants.MAX_Y];
	
	/**
	 * Land lists section start
	 */
	
	public static final Set<Land> heroList = EnumSet.of(
		Hero,
		Hero_Grass,
		Hero_Sand
	);

	public static Set<Land> rocketList = EnumSet.of(
		RocketNorth,
		RocketSouth,
		RocketEast,
		RocketWest,
		RocketNorthEast,
		RocketNorthWest,
		RocketSouthEast,
		RocketSouthWest,

		RocketNorth_Grass,
		RocketSouth_Grass,
		RocketEast_Grass,
		RocketWest_Grass,
		RocketNorthEast_Grass,
		RocketNorthWest_Grass,
		RocketSouthEast_Grass,
		RocketSouthWest_Grass,

		RocketNorth_Water,
		RocketSouth_Water,
		RocketEast_Water,
		RocketWest_Water,
		RocketNorthEast_Water,
		RocketNorthWest_Water,
		RocketSouthEast_Water,
		RocketSouthWest_Water,

		RocketNorth_Sand,
		RocketSouth_Sand,
		RocketEast_Sand,
		RocketWest_Sand,
		RocketNorthEast_Sand,
		RocketNorthWest_Sand,
		RocketSouthEast_Sand,
		RocketSouthWest_Sand
	);

	public static Set<Land> bulletList = EnumSet.of(
		Bullet,
		Bullet_Sand,
		Bullet_Grass,
		Bullet_Water,
		CannonBall,
		CannonBall_Sand,
		CannonBall_Grass,
		CannonBall_Water
	);

	private static Set<Land> bombList = EnumSet.of(
		Bomb,
		Bomb_Sand,
		Bomb_Grass,
		Bomb_Water
	);
	
	public static Set<Land> railList = EnumSet.of(
		Rail_Diagonal_Down,
		Rail_Diagonal_Up,
		Rail_Down_Left,
		Rail_Down_Right,
		Rail_Horizontal,
		Rail_Left_Down,
		Rail_Left_Up,
		Rail_Right_Down,
		Rail_Right_Up,
		Rail_Up_Left,
		Rail_Up_Right,
		Rail_Vertical,
		Rail_Vertical_Cross,
		Rail_Diagonal_Cross
	);

	public static Set<Land> wartrainList = EnumSet.range(WarTrain_Vertical, WarTrain_Diagonal_Down_Cross);

	public static Set<Land> trainList = EnumSet.of(
		Train_Diagonal_Down,
		Train_Diagonal_Up,
		Train_Down_Left,
		Train_Down_Right,
		Train_Horizontal,
		Train_Left_Down,
		Train_Left_Up,
		Train_Right_Down,
		Train_Right_Up,
		Train_Up_Left,
		Train_Up_Right,
		Train_Vertical,
		Train_Vertical_Cross,
		Train_Horizontal_Cross,
		Train_Diagonal_Up_Cross,
		Train_Diagonal_Down_Cross
	);
	static{
		trainList.addAll(wartrainList);
	}
	

	public static Set<Land> walkList = EnumSet.of(
		Empty,
		Grenade,
		Ammo,
		Rocket,
		Food,
		Bullet,
		Bullet_Grass,
		Bomb,
		Bomb_Grass,
		RobotGrave,
		OpenedHorizontalWoodGate,
		OpenedVerticalWoodGate,
		OpenedHorizontalSteelGate,
		OpenedVerticalSteelGate,
		OpenedHorizontalConcreteGate,
		OpenedVerticalConcreteGate,
		OpenedHorizontalBrickGate,
		OpenedVerticalBrickGate,
		Grass,
		Mine,
		Mine_Grass,
		Sand,
		Mine_Sand,
		Bullet_Sand,
		Bomb_Sand,
		CannonBall,
		CannonBall_Sand,
		CannonBall_Grass,
		CannonBall_Water
	);
	static{
		walkList.addAll(railList);
	}

	public static Set<Land> flyAndFindList = EnumSet.of(
		Water,
		Bullet_Water,
		CannonBall_Water,
		Bomb_Water
	);
	static {
		flyAndFindList.addAll(walkList);
	}

	private static Set<Land> unexplosiveList = EnumSet.of(
		Vacuum,
		Wall,
		OpenedHorizontalSteelGate,
		ClosedHorizontalSteelGate,
		OpenedVerticalSteelGate,
		ClosedVerticalSteelGate,
		OpenedHorizontalConcreteGate,
		ClosedHorizontalConcreteGate,
		OpenedVerticalConcreteGate,
		ClosedVerticalConcreteGate,
		Water,
		Sand
	);

	/**
	 * list of codes which should be replaced by grass code during the save procedure
	 * they are codes of flying objects above the grass
	 */
	private static Set<Land> grassList = EnumSet.of(
		Bullet_Grass,
		CannonBall_Grass,
		Bomb_Grass,
		RocketNorth_Grass,
		RocketSouth_Grass,
		RocketEast_Grass,
		RocketWest_Grass,
		RocketNorthEast_Grass,
		RocketNorthWest_Grass,
		RocketSouthEast_Grass,
		RocketSouthWest_Grass
	);

	/**
	 * list of codes which should be replaced by water code during the save procedure
	 * they are codes of flying objects above the water
	 */
	private static Set<Land> waterList = new HashSet<>();
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
	private static Set<Land> sandList = new HashSet<>();
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
	
	private static Set<Land> sandExplList = new HashSet<>();
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

	private static Set<Land> unsaveList = new HashSet<>();
	static {
		unsaveList.addAll(bulletList);

		unsaveList.addAll(bombList);

		unsaveList.addAll(rocketList);
	}

	private static Set<Land> forBulletList = new HashSet<>();
	static {
		forBulletList.addAll(rocketList);
		forBulletList.add(Grenade);
	}

	private static Set<Land> forRocketList = new HashSet<>();
	static {
		forRocketList.addAll(forBulletList);
		forRocketList.addAll(bulletList);
	}
	
	public final static Set<Land> armoredEnemyList = new HashSet<>();
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

	public final static Set<Land> enemyList = new HashSet<>();
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
	
	public static final Set<Land> armoredCitizenList = new HashSet<>();
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

	public static final Set<Land> citizenList = new HashSet<>();
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
	
	public static final Set<Land> gateList = new HashSet<>();
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

	public static final Set<Land> saveList = new HashSet<>();
	static {
		saveList.addAll(enemyList);
		saveList.addAll(citizenList);
		saveList.add(Mine);
		saveList.add(Mine_Grass);
		saveList.add(Mine_Sand);
		saveList.addAll(gateList);
	}
	
	public static Land getLand(Location location) {
		return getLand(location.getX(), location.getY());
	}

	@SuppressWarnings("incomplete-switch")
	public static Land getTurnedLand(Location location) {
		Land code = getLand(location.getX(), location.getY());
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

	public static Land getLand(int x, int y) {
		if (x > DWConstants.MAX_X - 1 || y > DWConstants.MAX_Y - 1) {
			return Vacuum;
		}
		if (x < DWConstants.MIN_X || y < DWConstants.MIN_Y) {
			return Vacuum;
		}
		Land code;
		synchronized(Land.class){
			code = landMap[x][y];
		}
		
		return code;
	}

	public static Land setLand(Location location, Land code) {
		return setLand(location.getX(), location.getY(), code);
	}
	
	public static Land setLand(int x, int y, Land code) {
		Land oldCode = getLand(x, y);
		synchronized(Land.class){
			landMap[x][y] = code;
		}
		return oldCode;
	}

	public static Land setLand(Location location, MovableUnit unit) {
		Land oldLand = getLand(location);
		Land newLand = unit.getLand(oldLand);
		synchronized(Land.class){
			landMap[location.getX()][location.getY()] = newLand;
		}
		return oldLand;
	}

	public static void initLand(Location location, Land beneath, MovableUnit unit) {
		Land newCode = unit.getLand(beneath);
		synchronized(Land.class){
			landMap[location.getX()][location.getY()] = newCode;
		}
	}

	public static Location getNewLocation(Location location, Direction direction) {
		return direction.getNewLocation(location);
	}

	public static Land getLand(Location location, Direction direction) {
		location = getNewLocation(location, direction);
		if (location.getX() > DWConstants.MAX_X - 1 || location.getY() > DWConstants.MAX_Y - 1)
			return Vacuum;
		if (location.getX() < DWConstants.MIN_X || location.getY() < DWConstants.MIN_Y)
			return Vacuum;
		return getLand(location);
	}

	public static boolean canIWalk(Location location, Direction direction,
			Set<Land> list) {
		Land land = getLand(location, direction);
		if (land == Vacuum)
			return false;
		if (list.contains(land))
			return true;
		return false;
	}

	public static Land getWalkStop(Location location, Direction direction) {
		return getLand(location, direction);
	}

	public static boolean bulletListContains(Land land) {
		return bulletList.contains(land);
	}

	public static boolean forBulletListContains(Land land) {
		return forBulletList.contains(land);
	}
	
	public static boolean rocketListContains(Land land) {
		return forRocketList.contains(land);
	}

	public static boolean unsaveListContains(Land land) {
		return unsaveList.contains(land);
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
	
	public static SearchResult search(final Location location, final Direction direction, final Set<Land> list) {
		return search(location, direction, list, DWConstants.VISIBLE_DISTANCE);
	}
	
	public static SearchResult search(final Location location, final Direction direction, final Set<Land> list, final int maxDistance) {
		Location point = getNewLocation((Location) location, direction);

		Land land = Vacuum;
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
				Land code = getLand(x, y);
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

	private static void loadMan(final int x, final int y, Land land,
			InputStream stream) throws IOException {
		ControlledUnit unit = new ControlledUnit(x, y, land);
		DWConfiguration.getInstance().setControlledUnit(unit);
		unit.load(stream);
	}

	
	private static boolean loadUnit(Land code, int x, int y,	InputStream stream) throws IOException {
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

	static void load(String fileName, IProgressMonitor progressMonitor) {
		for (int x = 0; x < DWConstants.MAX_X; x++) {
			for (int y = 0; y < DWConstants.MAX_Y; y++) {
				synchronized(Land.class){
					landMap[x][y] = Empty;
				}
			}
		}
		File file = new File(DWConfiguration.getInstance().getPathName()+fileName);
		if (!file.exists()){
			DWConfiguration.getInstance().getUI().showMessageDialog("Error", "File "+file.getAbsolutePath()+" not found");
			return;
		}
		
		int progress = 0;
		try(FileInputStream fs = new FileInputStream(file);
				BufferedInputStream stream = new BufferedInputStream(fs);) {
			
			
			int heroX = readInt(stream);
			int heroY = readInt(stream);
			
			for (int x = 0; x < DWConstants.MAX_X; x++) {
				if(progressMonitor.isCancelled()){
					return;
				}
				if(progress != x*100/DWConstants.MAX_X){
					progress = x*100/DWConstants.MAX_X;
					progressMonitor.progress(progress);	
				}
				for (int y = 0; y < DWConstants.MAX_Y; y++) {
					int code = stream.read();
					if(code == -1){
						throw new RuntimeException("End of Stream reached");
					}
					if(code >= 186){
						code -= 6;
					}
					code++;
					
					
					if(x == heroX && y == heroY){
						loadMan(x, y, Land.values()[code], stream);
						continue;
					}
					
					if((x != heroX || y != heroY) &&
						loadUnit(Land.values()[code], x, y, stream)){
						continue;
					}
					synchronized(Land.class){
						landMap[x][y] = Land.values()[code];
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		progressMonitor.close();
		saved();
	}

	public static void save(String fileName, IProgressMonitor progressMonitor) {
		File file = new File(DWConfiguration.getInstance().getPathName()+fileName);
		int progress = 0;
		try(FileOutputStream fs = new FileOutputStream(file);
				BufferedOutputStream stream = new BufferedOutputStream(fs);) {
			
			ControlledUnit hero = DWConfiguration.getInstance().getControlledUnit();
			
			writeInt(stream, hero.getLocation().getX());
			writeInt(stream, hero.getLocation().getY());
			
			for (int x = 0; x < DWConstants.MAX_X; x++) {
				if(progress != x*100/DWConstants.MAX_X){
					progress = x*100/DWConstants.MAX_X;
					progressMonitor.progress(progress);	
				}
				for (int y = 0; y < DWConstants.MAX_Y; y++) {
					Land code;
					synchronized(Land.class){
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
					stream.write(code.ordinal());
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
