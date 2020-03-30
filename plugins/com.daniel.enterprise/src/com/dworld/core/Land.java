package com.dworld.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Set;

import com.dworld.pathfinding.TileBasedMap;
import com.dworld.ui.DWMessage;
import com.dworld.ui.DWSounds;
import com.dworld.ui.IProgressMonitor;
import com.dworld.units.ControlledUnit;
import com.dworld.units.MovableUnit;
import com.dworld.units.weapon.Bullet;

public enum Land implements TileBasedMap{
	
	
	/*******************************************************************************************************************************
	 * Land codes section begin
	 */
	
	Vacuum,
	Empty,
	Wall,
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
	
	Station_Vertical,
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
	private static final Land[][][] landMap = new Land[DWConstants.MAX_X][DWConstants.MAX_Y][2];
	
	/**
	 * Land lists section start
	 */
	
	public static final Set<Land> heroList = EnumSet.of(
		Hero,
		Hero_Grass,
		Hero_Sand
	);

	public static final Set<Land> rocketList = EnumSet.of(
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

	public static final Set<Land> bulletList = EnumSet.of(
		Bullet,
		Bullet_Sand,
		Bullet_Grass,
		Bullet_Water,
		CannonBall,
		CannonBall_Sand,
		CannonBall_Grass,
		CannonBall_Water
	);

	private static final Set<Land> bombList = EnumSet.of(
		Bomb,
		Bomb_Sand,
		Bomb_Grass,
		Bomb_Water
	);
	
	public static final Set<Land> railList = EnumSet.of(
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

	public static final Set<Land> wartrainList = EnumSet.range(WarTrain_Vertical, WarTrain_Diagonal_Down_Cross);

	public static final Set<Land> trainList = EnumSet.of(
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
	

	// Only Backgrounds
	public static final Set<Land> walkBackgroundList = EnumSet.of(
		Empty,
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
		Sand
	);
	static{
		walkBackgroundList.addAll(railList);
	}
	
	// Only Backgrounds
	public static final Set<Land> walkForegroundList = EnumSet.of(
		Empty,
		Grenade,
		Ammo,
		Rocket,
		Food,
		Bullet,
		Bomb,
		CannonBall,
		Mine
	);

	// Only foregrounds
	public static final Set<Land> flyAndFindList = EnumSet.of(
		Empty,
		Bullet,
		Bomb,
		Rocket,
		CannonBall
	);
	

	private static final Set<Land> unexplosiveList = EnumSet.of(
		Vacuum,
		Wall,
		ClosedHorizontalSteelGate,
		ClosedVerticalSteelGate,
		ClosedHorizontalConcreteGate,
		ClosedVerticalConcreteGate,
		Water,
		Sand
	);

	/**
	 * list of codes which should be replaced by grass code during the save procedure
	 * they are codes of flying objects above the grass
	 */
	public static final Set<Land> saveGrassList = EnumSet.of(
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
	
	public static final Set<Land> allGrassList = EnumSet.of(
			Grass,
			Hero_Grass,
			GoodSoldier_Grass,
			GoodTank_Grass,
			GoodOfficer_Grass,
			GoodGeneral_Grass,
			GoodBunker_Grass,
			GoodRadar_Grass,
			BadSoldier_Grass,
			BadTank_Grass,
			BadOfficer_Grass,
			BadGeneral_Grass,
			BadBunker_Grass,
			BadRadar_Grass,
			Dark_Knight_Grass
			
	);
	static{
		allGrassList.addAll(saveGrassList);
	}

	/**
	 * list of codes which should be replaced by water code during the save procedure
	 * they are codes of flying objects above the water
	 */
	public static final Set<Land> waterList = EnumSet.of(
		Water,
		Bullet_Water,
		CannonBall_Water,
		Bomb_Water,
		RocketNorth_Water,
		RocketSouth_Water,
		RocketEast_Water,
		RocketWest_Water,
		RocketNorthEast_Water,
		RocketNorthWest_Water,
		RocketSouthEast_Water,
		RocketSouthWest_Water
	);
	
	/**
	 * list of codes which should be replaced by Sand code during the save procedure
	 * they are codes of flying objects above the sand
	 */
	public static final Set<Land> saveSandList = EnumSet.of(
		Sand,
		Bullet_Sand,
		CannonBall_Sand,
		Bomb_Sand,
		RocketNorth_Sand,
		RocketSouth_Sand,
		RocketEast_Sand,
		RocketWest_Sand,
		RocketNorthEast_Sand,
		RocketNorthWest_Sand,
		RocketSouthEast_Sand,
		RocketSouthWest_Sand
	);
	
	public static final Set<Land> allSandList = EnumSet.of(
			Hero_Sand,
			GoodSoldier_Sand,
			GoodTank_Sand,
			GoodOfficer_Sand,
			GoodGeneral_Sand,
			GoodBunker_Sand,
			GoodRadar_Sand,
			BadSoldier_Sand,
			BadTank_Sand,
			BadOfficer_Sand,
			BadGeneral_Sand,
			BadBunker_Sand,
			BadRadar_Sand,
			Dark_Knight_Sand
			
	);
	static{
		allSandList.addAll(saveSandList);
	}
	
	private static final Set<Land> sandExplList = EnumSet.of(
		Hero_Sand,
		Mine_Sand,
		GoodSoldier_Sand,
		GoodOfficer_Sand,
		GoodGeneral_Sand,
		GoodTank_Sand,
		GoodBunker_Sand,
		GoodRadar_Sand,
		BadSoldier_Sand,
		BadTank_Sand,
		BadBunker_Sand,
		BadRadar_Sand,
		BadOfficer_Sand,
		BadGeneral_Sand,
		Dark_Knight_Sand,
		Peasant_Sand
	);

	private static final Set<Land> unsaveList = EnumSet.noneOf(Land.class);
	static {
		unsaveList.addAll(bulletList);

		unsaveList.addAll(bombList);

		unsaveList.addAll(rocketList);
	}

	private static final Set<Land> forBulletList = EnumSet.of(Grenade);
	static {
		forBulletList.addAll(rocketList);
	}

	private static final Set<Land> forRocketList = EnumSet.noneOf(Land.class);
	static {
		forRocketList.addAll(forBulletList);
		forRocketList.addAll(bulletList);
	}
	
	public static final Set<Land> armoredEnemyList = EnumSet.of(
		BadTank,
		BadTank_Grass,
		BadTank_Sand,
		
		BadBunker,
		BadBunker_Grass,
		BadBunker_Sand,
		
		BadRadar,
		BadRadar_Grass,
		BadRadar_Sand
	);

	public static final Set<Land> enemyList = EnumSet.of(
		BadSoldier,
		BadSoldier_Grass,
		BadSoldier_Sand,
		BadOfficer,
		BadOfficer_Grass,
		BadOfficer_Sand,
		BadGeneral,
		BadGeneral_Grass,
		BadGeneral_Sand,
		Dark_Knight,
		Dark_Knight_Grass,
		Dark_Knight_Sand
	);
	static {
		enemyList.addAll(armoredEnemyList);
	}
	
	public static final Set<Land> armoredCitizenList = EnumSet.of(
		GoodTank,
		GoodTank_Grass,
		GoodTank_Sand,
		
		GoodRadar,
		GoodRadar_Grass,
		GoodRadar_Sand,
		
		GoodBunker,
		GoodBunker_Grass,
		GoodBunker_Sand
	);
	static {
		armoredCitizenList.addAll(trainList);
	}

	public static final Set<Land> citizenList = EnumSet.of(
		GoodSoldier,
		GoodSoldier_Grass,
		GoodSoldier_Sand,

		GoodOfficer,
		GoodOfficer_Grass,
		GoodOfficer_Sand,

		GoodGeneral,
		GoodGeneral_Grass,
		GoodGeneral_Sand,
			
		Peasant,
		Peasant_Grass,
		Peasant_Sand
	);
	static {
		citizenList.addAll(armoredCitizenList);
		
		citizenList.addAll(heroList);
	}
	
	public static final Set<Land> gateList = EnumSet.of(
		OpenedHorizontalSteelGate,
		ClosedHorizontalSteelGate,
		OpenedVerticalSteelGate,
		ClosedVerticalSteelGate,
		OpenedHorizontalConcreteGate,
		ClosedHorizontalConcreteGate,
		OpenedVerticalConcreteGate,
		ClosedVerticalConcreteGate,

		OpenedHorizontalWoodGate,
		ClosedHorizontalWoodGate,
		OpenedVerticalWoodGate,
		ClosedVerticalWoodGate,
		OpenedHorizontalBrickGate,
		ClosedHorizontalBrickGate,
		OpenedVerticalBrickGate,
		ClosedVerticalBrickGate
	);

	public static final Set<Land> saveList = EnumSet.of(
		Mine,
		Mine_Grass,
		Mine_Sand
	);
	static {
		saveList.addAll(enemyList);
		saveList.addAll(citizenList);
		saveList.addAll(gateList);
	}
	
	public static final Set<Land> backgroundList = EnumSet.noneOf(Land.class);
		static {
			backgroundList.addAll(allGrassList);
			backgroundList.addAll(allSandList);
			backgroundList.addAll(waterList);
			backgroundList.addAll(railList);
			//backgroundList.addAll(gateList);
		}
	
	public static Land getLand(Location location) {
		return getLand(location.getX(), location.getY());
	}

	@SuppressWarnings("incomplete-switch")
	public static Land getTurnedLand(Location location) {
		Land land = getLand(location.getX(), location.getY());
		if(gateList.contains(land)){
			switch(land){
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
		return land;
	}

	public static Land getLand(int x, int y) {
		if (x > DWConstants.MAX_X - 1 || y > DWConstants.MAX_Y - 1) {
			return Vacuum;
		}
		if (x < DWConstants.MIN_X || y < DWConstants.MIN_Y) {
			return Vacuum;
		}
		int code;
		synchronized(Land.class){
			return landMap[x][y][1];
		}
		//code = (code << 16) >>> 16;
		//return Land.values()[code];
	}

	public static Land setLand(Location location, Land land) {
		return setLand(location.getX(), location.getY(), land);
	}
	
	public static Land setLand(int x, int y, Land land) {
		Land oldLand = getLand(x, y);
		if(backgroundList.contains(land)){
			setBackground(x, y, land);
		}else{
			setForeground(x, y, land);
		}
		return oldLand;
	}
	
	public static void setLand(int x, int y, Land foreground, Land background) {
		synchronized(Land.class){
			landMap[x][y][0] = background;
			landMap[x][y][1] = foreground;
			//landMap[x][y] = (background << 16) + foreground;
		}
	}
	
	public static Land setForeground(Location location, Land foreground) {
		return setForeground(location.getX(), location.getY(), foreground);
	}
	
	public static Land setForeground(int x, int y, Land foreground) {
		Land oldForeground = getForeground(x, y);
		synchronized(Land.class){
			landMap[x][y][1] = foreground;
		}
		return oldForeground;
	}
	
	public static Land setBackground(Location location, Land background) {
		
		return setBackground(location.getX(), location.getY(), background);
	}
	
	public static Land setBackground(int x, int y, Land background) {
		Land oldBackground = getBackground(x, y);
		synchronized(Land.class){
			landMap[x][y][0] = background;
		}
		return oldBackground;
	}
	
	public static Land getBackground(Location location) {
		return getBackground(location.getX(), location.getY());
	}
	
	public static Land getBackground(int x, int y) {
		synchronized(Land.class){
			return landMap[x][y][0];
		}
	}
	
	public static Land getForeground(Location location) {
		return getForeground(location.getX(), location.getY());
	}
	
	public static Land getForeground(Location location, Direction direction) {
		location = getNewLocation(location, direction);
		if (location.getX() > DWConstants.MAX_X - 1 || location.getY() > DWConstants.MAX_Y - 1)
			return Vacuum;
		if (location.getX() < DWConstants.MIN_X || location.getY() < DWConstants.MIN_Y)
			return Vacuum;
		return getForeground(location);
	}
	
	public static Land getBackground(Location location, Direction direction) {
		location = getNewLocation(location, direction);
		if (location.getX() > DWConstants.MAX_X - 1 || location.getY() > DWConstants.MAX_Y - 1)
			return Vacuum;
		if (location.getX() < DWConstants.MIN_X || location.getY() < DWConstants.MIN_Y)
			return Vacuum;
		return getBackground(location);
	}
	
	public static Land getForeground(int x, int y) {
		synchronized(Land.class){
			return landMap[x][y][1];
		}
	}

	public static Land setLand(Location location, MovableUnit unit) {
		Land oldLand = getLand(location);
		Land newLand = unit.getLand();
		setForeground(location.getX(), location.getY(), newLand);
		return oldLand;
	}

	public static Land initLand(Location location, Land beneath, MovableUnit unit) {
		Land newLand = unit.getLand();
		return setForeground(location.getX(), location.getY(), newLand);
	}

	public static Location getNewLocation(Location location, Direction direction) {
		return direction.getNewLocation(location);
	}
	
	public static Direction getNewDirection(Location source, Location target) {
		return Direction.findDirection(source, target);
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
	
	public static boolean canFly(Location location, Direction direction){
		Land land = getForeground(location, direction);
		if (land == Vacuum)
			return false;
		if (flyAndFindList.contains(land))
			return true;
		return false;
	}
	
	public static boolean canWalk(Location location, Direction direction){
		Land land = getBackground(location, direction);
		if (land == Vacuum)
			return false;
		if (!walkBackgroundList.contains(land))
			return false;
		
		land = getForeground(location, direction);
		if (land == Vacuum)
			return false;
		if (walkForegroundList.contains(land))
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
				Land land = getForeground(x, y);
				if (!unexplosiveList.contains(land)) {
					setForeground(x, y, Empty);
				}
			}
		}
		Direction dir = Direction.NORTH;
		for (int i = 0; i < 8; i++) {
			new Bullet(location.getX(), location.getY(), dir);
			dir = dir.getClockwiseDirection();
		}
		DWSounds.EXPLOSION.playSound();
	}

	private static void loadMan(final int x, final int y, Land land,
			InputStream stream) throws IOException {
		ControlledUnit unit = new ControlledUnit(x, y, land);
		DWConfiguration.getInstance().setControlledUnit(unit);
		unit.load(stream);
	}

	
	private static boolean loadUnit(Land land, int x, int y,	InputStream stream) throws IOException {
		IUnit unit = DWUnitFactory.createUnit(land, x, y);
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
		DWConfiguration.getInstance().getUI().setTitle(DWMessage.TITLE.get());
	}
	
	public static void modified(){
		dirty = true;
		DWConfiguration.getInstance().getUI().setTitle(DWConfiguration.MODIFYED_TITLE);
	}

	static void load(String fileName, IProgressMonitor progressMonitor) {
		for (int x = 0; x < DWConstants.MAX_X; x++) {
			for (int y = 0; y < DWConstants.MAX_Y; y++) {
				synchronized(Land.class){
					landMap[x][y][0] = Empty;
					landMap[x][y][1] = Empty;
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
					Land foreground = Land.values()[code];
					if(code == -1){
						throw new RuntimeException("End of File reached");
					}
					
					
					Land background = Vacuum;
					if(allGrassList.contains(foreground)) {
						background = Grass;
					}else if(allSandList.contains(foreground)){
						background = Sand;
					}else if(waterList.contains(foreground)){
						background = Water;
					}
					
					if(background != Vacuum){
						setBackground(x, y, background);
					}
					
					if(x == heroX && y == heroY){
						loadMan(x, y, foreground, stream);
						continue;
					}
					
					if((x != heroX || y != heroY) &&
						loadUnit(foreground, x, y, stream)){
						continue;
					}
					//Land foreground = land;
					if(background == Vacuum){
						setForeground(x, y, foreground);
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
//		File file = new File(DWConfiguration.getInstance().getPathName()+fileName);
//		int progress = 0;
//		try(FileOutputStream fs = new FileOutputStream(file);
//				BufferedOutputStream stream = new BufferedOutputStream(fs);) {
//			
//			ControlledUnit hero = DWConfiguration.getInstance().getControlledUnit();
//			
//			writeInt(stream, hero.getLocation().getX());
//			writeInt(stream, hero.getLocation().getY());
//			
//			for (int x = 0; x < DWConstants.MAX_X; x++) {
//				if(progress != x*100/DWConstants.MAX_X){
//					progress = x*100/DWConstants.MAX_X;
//					progressMonitor.progress(progress);	
//				}
//				for (int y = 0; y < DWConstants.MAX_Y; y++) {
//					int land;
//					synchronized(Land.class){
//						land = landMap[x][y];
//					}
//					if (unsaveList.contains(land)) {
//						if (waterList.contains(land))
//							land = Water;
//						else if (saveGrassList.contains(land))
//							land = Grass;
//						else if (saveSandList.contains(land))
//							land = Sand;
//						else
//							land = Empty;
//					}
//					stream.write(land);
//					if (saveList.contains(land)) {
//						List<IUnit> list = DWConfiguration.getInstance().getEngine().findUnit(new Location(x, y));
//						if (list != null){
//							for(IUnit unit : list){
//								unit.save(stream);
//							}
//						}
//					}
//				}
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		progressMonitor.close();
//		saved();
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
	
	@Override
	public String toString(){
		return name();
	}

	@Override
	public int getWidthInTiles() {
		return getMaxX();
	}

	@Override
	public int getHeightInTiles() {
		return getMaxY();
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		
	}

	@Override
	public boolean blocked(MovableUnit mover, int x, int y) {
		Land land = getBackground(x, y);
		if (land == Vacuum)
			return true;
		if (!walkBackgroundList.contains(land))
			return true;
		
		land = getForeground(x, y);
		if (land == Vacuum)
			return true;
		if (!walkForegroundList.contains(land))
			return true;
		if(citizenList.contains(mover.getLand()) && gateList.contains(land)){
			return false;
		}
		return true;
	}

	@Override
	public float getCost(MovableUnit mover, int sx, int sy, int tx, int ty) {
		return 1;
	}
}
