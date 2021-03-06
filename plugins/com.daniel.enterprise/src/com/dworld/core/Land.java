package com.dworld.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.List;
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
	
	// Backgrounds
	Sand,
	Grass,
	Water,
	
	Background1,
	Background2,
	Background3,
	Background4,
	Background5,
	Background6,
	
	// Walls
	Wall,
	Brick,
	Stone,
	WhiteBrick,
	BlackStone,
	Mountain,
	Wood1,
	Wood2,
	Wood3,
	Wood4,
	Tree1,
	Tree2,
	Tree3,
	
	Grave,
	RobotGrave,
	TankGrave,
	Food,
	Grenade,
	Ammo,
	Patron,
	
	// Weaponds
	Bullet,
	Bomb,
	CannonBall,
	Rocket,
	Mine,
	RocketNorth,
	RocketSouth,
	RocketEast,
	RocketWest,
	RocketNorthEast,
	RocketNorthWest,
	RocketSouthEast,
	RocketSouthWest,
	
	// Friends
	Hero,
	Peasant,
	GoodSoldier,
	GoodOfficer,
	GoodGeneral,
	GoodTank,
	GoodBunker,
	GoodRadar,
	
	// Enemies
	Enemy,
	BadSoldier,
	BadTank,
	Dark_Knight,
	BadOfficer,
	BadGeneral,	
	BadBunker,
	BadRadar,
	
	// Gates
	OpenedDoor,
	ClosedDoor,

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

	// Teleports
	Teleport1,
	Teleport2,
	Teleport3,
	Teleport4,
	Teleport5,
	Teleport6,
	Teleport7,
	Teleport8,
	Teleport9,
	Teleport10,
	Teleport11,
	Teleport12,
	Teleport13,
	Teleport14,
	Teleport15,
	
	// Trains
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
	Train_Vertical_Cross,
	Train_Horizontal_Cross,
	Train_Diagonal_Up_Cross,
	Train_Diagonal_Down_Cross,
	
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
	
	// Rails
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
	Rail_Vertical_Cross,
	Rail_Diagonal_Cross,
	
	Station_Vertical,
	Station_Horizontal;
	
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
	// backgrounds
	public static final Set<Land> openedGateList = EnumSet.of(
		OpenedHorizontalSteelGate,
		OpenedVerticalSteelGate,
		OpenedHorizontalConcreteGate,
		OpenedVerticalConcreteGate,
		OpenedHorizontalWoodGate,
		OpenedVerticalWoodGate,
		OpenedHorizontalBrickGate,
		OpenedVerticalBrickGate
	);
		
	// foregrounds
	public static final Set<Land> closedGateList = EnumSet.of(
		ClosedHorizontalSteelGate,
		ClosedVerticalSteelGate,
		ClosedHorizontalConcreteGate,
		ClosedVerticalConcreteGate,
		ClosedHorizontalWoodGate,
		ClosedVerticalWoodGate,
		ClosedHorizontalBrickGate,
		ClosedVerticalBrickGate
	);
	
	public static final Set<Land> gateList = EnumSet.noneOf(Land.class);
	static {
		gateList.addAll(openedGateList);
		gateList.addAll(closedGateList);
	}

	public static final Set<Land> rocketList = EnumSet.of(
		RocketNorth,
		RocketSouth,
		RocketEast,
		RocketWest,
		RocketNorthEast,
		RocketNorthWest,
		RocketSouthEast,
		RocketSouthWest
	);

	public static final Set<Land> bulletList = EnumSet.of(
		Bullet,
		CannonBall
	);

	private static final Set<Land> bombList = EnumSet.of(
		Bomb
	);
	
	// backgrounds
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

	public static final Set<Land> trainList = EnumSet.range(Train_Vertical, Train_Diagonal_Down_Cross);

	static{
		trainList.addAll(wartrainList);
	}	

	// Only Backgrounds
	public static final Set<Land> walkBackgroundList = EnumSet.of(
		Empty,
		Grass,
		Sand
	);
	static{
		walkBackgroundList.addAll(openedGateList);
		walkBackgroundList.addAll(railList);
	}
	
	// Only foregrounds
	public static final Set<Land> walkForegroundList = EnumSet.of(
		Empty,
		Grenade,
		Ammo,
		Rocket,
		Patron,
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
		Patron,
		Bomb,
		Grenade,
		Ammo,
		Rocket,
		CannonBall,
		Mine
	);
	
	private static final Set<Land> unexplosiveList = EnumSet.of(
		Vacuum,
		Wall,
		ClosedHorizontalSteelGate,
		ClosedVerticalSteelGate,
		ClosedHorizontalConcreteGate,
		ClosedVerticalConcreteGate
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
		BadBunker,
		BadRadar
	);

	public static final Set<Land> enemyList = EnumSet.of(
		BadSoldier,
		BadOfficer,
		BadGeneral,
		Dark_Knight
	);
	static {
		enemyList.addAll(armoredEnemyList);
	}
	
	public static final Set<Land> armoredCitizenList = EnumSet.of(
		GoodTank,
		GoodRadar,
		GoodBunker
	);
	static {
		armoredCitizenList.addAll(trainList);
	}

	public static final Set<Land> citizenList = EnumSet.of(
		Hero,
		GoodSoldier,
		GoodOfficer,
		GoodGeneral,
		Peasant
	);
	static {
		citizenList.addAll(armoredCitizenList);
	}
	
	public static final Set<Land> saveList = EnumSet.of(
		Mine
	);
	static {
		saveList.addAll(enemyList);
		saveList.addAll(citizenList);
		saveList.addAll(gateList);
	}
	
	public static final Set<Land> backgroundList = EnumSet.of(
		Sand,
		Grass,
		Water,
		Background1,
		Background2,
		Background3,
		Background4,
		Background5,
		Background6
	);
	static{
		backgroundList.addAll(openedGateList);
		backgroundList.addAll(railList);
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
		synchronized(Land.class){
			return landMap[x][y][1];
		}
	}

	public static void setLand(int x, int y, Land foreground, Land background) {
		synchronized(Land.class){
			landMap[x][y][0] = background;
			landMap[x][y][1] = foreground;
		}
	}
	
	public static Land setLand(Location location, Land foreground) {
		return setLand(location.getX(), location.getY(), foreground);
	}
	
	public static Land setLand(int x, int y, Land land) {
		if(backgroundList.contains(land)){
			return setBackground(x, y, land);
		}else{
			return setForeground(x, y, land);
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
	
	public static boolean canWalk(MovableUnit mover, int x, int y){
		Land land = getBackground(x, y);
		if (land == Vacuum)
			return false;
		if (!walkBackgroundList.contains(land))
			return false;
		
		land = getForeground(x, y);
		if (land == Vacuum)
			return false;
		if (walkForegroundList.contains(land))
			return true;
		return false;
	}
	
	public static boolean canRoll(Location location, Direction direction){
		Land land = getBackground(location, direction);
		if (land == Vacuum)
			return false;
		if (!railList.contains(land))
			return false;
		
		land = getForeground(location, direction);
		if (land == Vacuum)
			return false;
		if (land == Empty)
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
			int backgroundCode, foregroundCode;
			Land background, foreground;
			
			for (int x = 0; x < DWConstants.MAX_X; x++) {
				if(progressMonitor.isCancelled()){
					return;
				}
				if(progress != x*100/DWConstants.MAX_X){
					progress = x*100/DWConstants.MAX_X;
					progressMonitor.progress(progress);	
				}
				for (int y = 0; y < DWConstants.MAX_Y; y++) {
					backgroundCode = stream.read();
					foregroundCode = stream.read();
					if(backgroundCode < 0){
						background = Land.Empty;
					}else{
						background = Land.values()[backgroundCode];
					}
					if(foregroundCode < 0){
						foreground = Land.Empty;
					}else{
						foreground = Land.values()[foregroundCode];
					}
					
					foreground = Land.values()[foregroundCode];
					
					setBackground(x, y, background);
					
					if(x == heroX && y == heroY){
						loadMan(x, y, foreground, stream);
						continue;
					}
					
					if((x != heroX || y != heroY) &&
						loadUnit(foreground, x, y, stream)){
						continue;
					}
					setForeground(x, y, foreground);
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
					Land background, foreground;
					synchronized(Land.class){
						background = landMap[x][y][0];
						foreground = landMap[x][y][1];
					}
					
					if (unsaveList.contains(foreground)) {
						foreground = Land.Empty;
					}
					stream.write(background.ordinal());
					stream.write(foreground.ordinal());
					if (saveList.contains(foreground)) {
						List<IUnit> list = DWConfiguration.getInstance().getEngine().findUnit(new Location(x, y));
						if (list != null){
							for(IUnit unit : list){
								unit.save(stream);
							}
						}
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
		Land land = getForeground(x, y);
		if(citizenList.contains(mover.getLand()) && gateList.contains(land)){
			return false;
		}
		return !canWalk(mover, x, y);
	}

	@Override
	public float getCost(MovableUnit mover, int sx, int sy, int tx, int ty) {
		return 1;
	}
}
