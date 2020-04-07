package com.dworld.ui;

import java.util.HashMap;
import java.util.Map;

import com.dworld.core.Land;
import com.dworld.core.Location;

public abstract class DWImages<T> {
	protected int startX = 0, startY = 0;
	private Map<Land, T> images = new HashMap<>();

	public DWImages() {
		T image;

		image = loadImage("resources/land/none.png");
		images.put(Land.Empty, image);

		image = loadImage("resources/land/wall.png");
		images.put(Land.Wall, image);

		image = loadImage("resources/land/brick.png");
		images.put(Land.Brick, image);

		image = loadImage("resources/land/grenada.png");
		images.put(Land.Grenade, image);

		image = loadImage("resources/land/ammo.png");
		images.put(Land.Ammo, image);

		image = loadImage("resources/land/robot.png");
		images.put(Land.BadSoldier, image);

		image = loadImage("resources/land/tank.png");
		images.put(Land.BadTank, image);

		image = loadImage("resources/land/hiro.png");
		images.put(Land.Hero, image);

		image = loadImage("resources/land/bomb.png");
		images.put(Land.Bomb, image);

		image = loadImage("resources/land/bullet.png");
		images.put(Land.Bullet, image);

		image = loadImage("resources/land/patron.png");
		images.put(Land.Patron, image);

		image = loadImage("resources/land/grave.png");
		images.put(Land.Grave, image);

		image = loadImage("resources/land/enemy.png");
		images.put(Land.Enemy, image);

		image = loadImage("resources/land/r_u.png");
		images.put(Land.RocketNorth, image);

		image = loadImage("resources/land/r_d.png");
		images.put(Land.RocketSouth, image);

		image = loadImage("resources/land/r_r.png");
		images.put(Land.RocketEast, image);

		image = loadImage("resources/land/r_l.png");
		images.put(Land.RocketWest, image);

		image = loadImage("resources/land/r_ur.png");
		images.put(Land.RocketNorthEast, image);

		image = loadImage("resources/land/r_ul.png");
		images.put(Land.RocketNorthWest, image);

		image = loadImage("resources/land/r_dr.png");
		images.put(Land.RocketSouthEast, image);

		image = loadImage("resources/land/r_dl.png");
		images.put(Land.RocketSouthWest, image);

		image = loadImage("resources/land/rocket.png");
		images.put(Land.Rocket, image);

		image = loadImage("resources/land/food.png");
		images.put(Land.Food, image);

		image = loadImage("resources/land/opdoor.png");
		images.put(Land.OpenedDoor, image);

		image = loadImage("resources/land/cldoor.png");
		images.put(Land.ClosedDoor, image);

		image = loadImage("resources/land/graver.png");
		images.put(Land.RobotGrave, image);

		image = loadImage("resources/land/gravet.png");
		images.put(Land.TankGrave, image);

		image = loadImage("resources/land/bunker.png");
		images.put(Land.BadBunker, image);

		image = loadImage("resources/land/patriot.png");
		images.put(Land.BadRadar, image);

		image = loadImage("resources/land/hwgo.png");
		images.put(Land.OpenedHorizontalWoodGate, image);

		image = loadImage("resources/land/hwgc.png");
		images.put(Land.ClosedHorizontalWoodGate, image);

		image = loadImage("resources/land/vwgo.png");
		images.put(Land.OpenedVerticalWoodGate, image);

		image = loadImage("resources/land/vwgc.png");
		images.put(Land.ClosedVerticalWoodGate, image);

		image = loadImage("resources/land/hsgo.png");
		images.put(Land.OpenedHorizontalSteelGate, image);

		image = loadImage("resources/land/hsgc.png");
		images.put(Land.ClosedHorizontalSteelGate, image);

		image = loadImage("resources/land/vsgo.png");
		images.put(Land.OpenedVerticalSteelGate, image);

		image = loadImage("resources/land/vsgc.png");
		images.put(Land.ClosedVerticalSteelGate, image);
		
		image = loadImage("resources/land/wall_gate.png");
		images.put(Land.ClosedHorizontalConcreteGate, image);
		images.put(Land.ClosedVerticalConcreteGate, image);
		
		image = loadImage("resources/land/wall_gate_h_open.png");
		images.put(Land.OpenedHorizontalConcreteGate, image);
		
		image = loadImage("resources/land/wall_gate_v_open.png");
		images.put(Land.OpenedVerticalConcreteGate, image);
		
		image = loadImage("resources/land/brick_gate.png");
		images.put(Land.ClosedHorizontalBrickGate, image);
		images.put(Land.ClosedVerticalBrickGate, image);
		
		image = loadImage("resources/land/brick_gate_h_open.png");
		images.put(Land.OpenedHorizontalBrickGate, image);
		
		image = loadImage("resources/land/brick_gate_v_open.png");
		images.put(Land.OpenedVerticalBrickGate, image);
		
		image = loadImage("resources/land/grass.png");
		images.put(Land.Grass, image);
		

		image = loadImage("resources/land/water.png");
		images.put(Land.Water, image);
		
		image = loadImage("resources/land/soldier.png");
		images.put(Land.GoodSoldier, image);

		image = loadImage("resources/land/tank2.png");
		images.put(Land.GoodTank, image);

		image = loadImage("resources/land/radar.png");
		images.put(Land.GoodRadar, image);

		image = loadImage("resources/land/bunker2.png");
		images.put(Land.GoodBunker, image);

		image = loadImage("resources/land/mine.png");
		images.put(Land.Mine, image);

		image = loadImage("resources/land/teleport.png");
		images.put(Land.Teleport1, image);
		images.put(Land.Teleport2, image);
		images.put(Land.Teleport3, image);
		images.put(Land.Teleport4, image);
		images.put(Land.Teleport5, image);
		images.put(Land.Teleport6, image);
		images.put(Land.Teleport7, image);
		images.put(Land.Teleport8, image);
		images.put(Land.Teleport9, image);
		images.put(Land.Teleport10, image);
		images.put(Land.Teleport11, image);
		images.put(Land.Teleport12, image);
		images.put(Land.Teleport13, image);
		images.put(Land.Teleport14, image);
		images.put(Land.Teleport15, image);
		
		image = loadImage("resources/land/sand.png");
		images.put(Land.Sand, image);

		image = loadImage("resources/land/stone.png");
		images.put(Land.Stone, image);
		
		image = loadImage("resources/land/white_brick.png");
		images.put(Land.WhiteBrick, image);

		image = loadImage("resources/land/black_stone.png");
		images.put(Land.BlackStone, image);
		
		image = loadImage("resources/land/wood1.png");
		images.put(Land.Wood1, image);
		
		image = loadImage("resources/land/wood2.png");
		images.put(Land.Wood2, image);
		
		image = loadImage("resources/land/wood3.png");
		images.put(Land.Wood3, image);
		
		image = loadImage("resources/land/wood4.png");
		images.put(Land.Wood4, image);
		
		image = loadImage("resources/land/cannon_ball.png");
		images.put(Land.CannonBall, image);

		image = loadImage("resources/land/officer.png");
		images.put(Land.GoodOfficer, image);
		
		image = loadImage("resources/land/general.png");
		images.put(Land.GoodGeneral, image);
		
		image = loadImage("resources/land/tree1.png");
		images.put(Land.Tree1, image);
		image = loadImage("resources/land/tree2.png");
		images.put(Land.Tree2, image);
		image = loadImage("resources/land/tree3.png");
		images.put(Land.Tree3, image);
		
		image = loadImage("resources/land/dark_knight.png");
		images.put(Land.Dark_Knight, image);
		
		image = loadImage("resources/land/gray_officer.png");
		images.put(Land.BadOfficer, image);
		
		image = loadImage("resources/land/gray_general.png");
		images.put(Land.BadGeneral, image);
		
		image = loadImage("resources/land/peasant.png");
		images.put(Land.Peasant, image);
		
		image = loadImage("resources/land/rail_v.png");
		images.put(Land.Rail_Vertical, image);
		
		image = loadImage("resources/land/rail_h.png");
		images.put(Land.Rail_Horizontal, image);

		image = loadImage("resources/land/rail_d_u.png");
		images.put(Land.Rail_Diagonal_Up, image);

		image = loadImage("resources/land/rail_d_d.png");
		images.put(Land.Rail_Diagonal_Down, image);

		image = loadImage("resources/land/rail_up_r.png");
		images.put(Land.Rail_Up_Right, image);

		image = loadImage("resources/land/rail_up_l.png");
		images.put(Land.Rail_Up_Left, image);

		image = loadImage("resources/land/rail_down_r.png");
		images.put(Land.Rail_Down_Right, image);

		image = loadImage("resources/land/rail_down_l.png");
		images.put(Land.Rail_Down_Left, image);

		image = loadImage("resources/land/rail_r_up.png");
		images.put(Land.Rail_Right_Up, image);

		image = loadImage("resources/land/rail_r_down.png");
		images.put(Land.Rail_Right_Down, image);

		image = loadImage("resources/land/rail_l_up.png");
		images.put(Land.Rail_Left_Up, image);

		image = loadImage("resources/land/rail_l_down.png");
		images.put(Land.Rail_Left_Down, image);

		image = loadImage("resources/land/rail_v_cross.png");
		images.put(Land.Rail_Vertical_Cross, image);

		image = loadImage("resources/land/rail_d_cross.png");
		images.put(Land.Rail_Diagonal_Cross, image);
		
		image = loadImage("resources/land/train_v.png");
		images.put(Land.Train_Vertical, image);
		
		image = loadImage("resources/land/train_h.png");
		images.put(Land.Train_Horizontal, image);

		image = loadImage("resources/land/train_d_u.png");
		images.put(Land.Train_Diagonal_Up, image);

		image = loadImage("resources/land/train_d_d.png");
		images.put(Land.Train_Diagonal_Down, image);

		image = loadImage("resources/land/train_up_r.png");
		images.put(Land.Train_Up_Right, image);

		image = loadImage("resources/land/train_up_l.png");
		images.put(Land.Train_Up_Left, image);

		image = loadImage("resources/land/train_down_r.png");
		images.put(Land.Train_Down_Right, image);

		image = loadImage("resources/land/train_down_l.png");
		images.put(Land.Train_Down_Left, image);

		image = loadImage("resources/land/train_r_up.png");
		images.put(Land.Train_Right_Up, image);

		image = loadImage("resources/land/train_r_down.png");
		images.put(Land.Train_Right_Down, image);

		image = loadImage("resources/land/train_l_up.png");
		images.put(Land.Train_Left_Up, image);

		image = loadImage("resources/land/train_l_down.png");
		images.put(Land.Train_Left_Down, image);
		
		image = loadImage("resources/land/train_v_cross.png");
		images.put(Land.Train_Vertical_Cross, image);
		
		image = loadImage("resources/land/train_h_cross.png");
		images.put(Land.Train_Horizontal_Cross, image);
		
		image = loadImage("resources/land/train_d_u_cross.png");
		images.put(Land.Train_Diagonal_Up_Cross, image);
		
		image = loadImage("resources/land/train_d_d_cross.png");
		images.put(Land.Train_Diagonal_Down_Cross, image);
		
		image = loadImage("resources/land/wtrain_v.png");
		images.put(Land.WarTrain_Vertical, image);
		
		image = loadImage("resources/land/wtrain_h.png");
		images.put(Land.WarTrain_Horizontal, image);

		image = loadImage("resources/land/wtrain_d_u.png");
		images.put(Land.WarTrain_Diagonal_Up, image);

		image = loadImage("resources/land/wtrain_d_d.png");
		images.put(Land.WarTrain_Diagonal_Down, image);

		image = loadImage("resources/land/wtrain_up_r.png");
		images.put(Land.WarTrain_Up_Right, image);

		image = loadImage("resources/land/wtrain_up_l.png");
		images.put(Land.WarTrain_Up_Left, image);

		image = loadImage("resources/land/wtrain_down_r.png");
		images.put(Land.WarTrain_Down_Right, image);

		image = loadImage("resources/land/wtrain_down_l.png");
		images.put(Land.WarTrain_Down_Left, image);

		image = loadImage("resources/land/wtrain_r_up.png");
		images.put(Land.WarTrain_Right_Up, image);

		image = loadImage("resources/land/wtrain_r_down.png");
		images.put(Land.WarTrain_Right_Down, image);

		image = loadImage("resources/land/wtrain_l_up.png");
		images.put(Land.WarTrain_Left_Up, image);

		image = loadImage("resources/land/wtrain_l_down.png");
		images.put(Land.WarTrain_Left_Down, image);
		
		image = loadImage("resources/land/wtrain_v_cross.png");
		images.put(Land.WarTrain_Vertical_Cross, image);
		
		image = loadImage("resources/land/wtrain_h_cross.png");
		images.put(Land.WarTrain_Horizontal_Cross, image);
		
		image = loadImage("resources/land/wtrain_d_u_cross.png");
		images.put(Land.WarTrain_Diagonal_Up_Cross, image);
		
		image = loadImage("resources/land/wtrain_d_d_cross.png");
		images.put(Land.WarTrain_Diagonal_Down_Cross, image);

		image = loadImage("resources/land/station_v.png");
		images.put(Land.Station_Vertical, image);

		image = loadImage("resources/land/station_h.png");
		images.put(Land.Station_Horizontal, image);

		image = loadImage("resources/land/mountain.png");
		images.put(Land.Mountain, image);
	}

	public T getImage(Land land) {
		return images.get(land);
	}

	abstract protected T loadImage(String path);

	protected void calculateLocation(Location location) {
		if (location.getX() < 24)
			startX = 0;
		else if (location.getX() > Land.getMaxX() - 26)
			startX = Land.getMaxX() - 49;
		else
			startX = location.getX() - 23;

		if (location.getY() < 24)
			startY = 0;
		else if (location.getY() > Land.getMaxY() - 26)
			startY = Land.getMaxY() - 49;
		else
			startY = location.getY() - 23;
	}
	
	

	public int getX() {
		return startX;
	}

	public void setX(int x) {
		startX = x;
	}

	public int getY() {
		return startY;
	}

	public void setY(int y) {
		startY = y;
	}

}
