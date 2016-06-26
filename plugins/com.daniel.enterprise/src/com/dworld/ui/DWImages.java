package com.dworld.ui;

import java.util.HashMap;
import java.util.Map;

import com.dworld.core.Land;
import com.dworld.core.Location;

public abstract class DWImages<T> {
	protected int startX = 0, startY = 0;
	private Map<Integer, T> images = new HashMap<>();

	public DWImages() {
		T image;

		image = loadImage("resources/land/none.gif");
		images.put(Land.Empty, image);

		image = loadImage("resources/land/wall.gif");
		images.put(Land.Wall, image);

		image = loadImage("resources/land/brick.gif");
		images.put(Land.Brick, image);

		image = loadImage("resources/land/grenada.gif");
		images.put(Land.Grenada, image);

		image = loadImage("resources/land/ammo.gif");
		images.put(Land.Ammo, image);

		image = loadImage("resources/land/robot.gif");
		images.put(Land.BadSoldier, image);

		image = loadImage("resources/land/tank.gif");
		images.put(Land.BadTank, image);

		image = loadImage("resources/land/hiro.gif");
		images.put(Land.Hero, image);

		image = loadImage("resources/land/bomb.gif");
		images.put(Land.Bomb, image);

		image = loadImage("resources/land/bullet.gif");
		images.put(Land.Bullet, image);

		image = loadImage("resources/land/patron.gif");
		images.put(Land.Patron, image);

		image = loadImage("resources/land/grave.gif");
		images.put(Land.Grave, image);

		image = loadImage("resources/land/enemy.gif");
		images.put(Land.Enemy, image);

		image = loadImage("resources/land/r_u.gif");
		images.put(Land.RocketNorth, image);

		image = loadImage("resources/land/r_d.gif");
		images.put(Land.RocketSouth, image);

		image = loadImage("resources/land/r_r.gif");
		images.put(Land.RocketEast, image);

		image = loadImage("resources/land/r_l.gif");
		images.put(Land.RocketWest, image);

		image = loadImage("resources/land/r_ur.gif");
		images.put(Land.RocketNorthEast, image);

		image = loadImage("resources/land/r_ul.gif");
		images.put(Land.RocketNorthWest, image);

		image = loadImage("resources/land/r_dr.gif");
		images.put(Land.RocketSouthEast, image);

		image = loadImage("resources/land/r_dl.gif");
		images.put(Land.RocketSouthWest, image);

		image = loadImage("resources/land/rocket.gif");
		images.put(Land.Rocket, image);

		image = loadImage("resources/land/food.gif");
		images.put(Land.Food, image);

		image = loadImage("resources/land/opdoor.gif");
		images.put(Land.OpenedDoor, image);

		image = loadImage("resources/land/cldoor.gif");
		images.put(Land.ClosedDoor, image);

		image = loadImage("resources/land/graver.gif");
		images.put(Land.RobotGrave, image);

		image = loadImage("resources/land/gravet.gif");
		images.put(Land.TankGrave, image);

		image = loadImage("resources/land/bunker.gif");
		images.put(Land.BadBunker, image);

		image = loadImage("resources/land/patriot.gif");
		images.put(Land.BadRadar, image);

		image = loadImage("resources/land/hwgo.gif");
		images.put(Land.OpenedHorizontalWoodGate, image);

		image = loadImage("resources/land/hwgc.gif");
		images.put(Land.ClosedHorizontalWoodGate, image);

		image = loadImage("resources/land/vwgo.gif");
		images.put(Land.OpenedVerticalWoodGate, image);

		image = loadImage("resources/land/vwgc.gif");
		images.put(Land.ClosedVerticalWoodGate, image);

		image = loadImage("resources/land/hsgo.gif");
		images.put(Land.OpenedHorizontalSteelGate, image);

		image = loadImage("resources/land/hsgc.gif");
		images.put(Land.ClosedHorizontalSteelGate, image);

		image = loadImage("resources/land/vsgo.gif");
		images.put(Land.OpenedVerticalSteelGate, image);

		image = loadImage("resources/land/vsgc.gif");
		images.put(Land.ClosedVerticalSteelGate, image);
		
		image = loadImage("resources/land/wall_gate.gif");
		images.put(Land.ClosedHorizontalConcreteGate, image);
		images.put(Land.ClosedVerticalConcreteGate, image);
		
		image = loadImage("resources/land/wall_gate_h_open.gif");
		images.put(Land.OpenedHorizontalConcreteGate, image);
		
		image = loadImage("resources/land/wall_gate_v_open.gif");
		images.put(Land.OpenedVerticalConcreteGate, image);
		
		image = loadImage("resources/land/brick_gate.gif");
		images.put(Land.ClosedHorizontalBrickGate, image);
		images.put(Land.ClosedVerticalBrickGate, image);
		
		image = loadImage("resources/land/brick_gate_h_open.gif");
		images.put(Land.OpenedHorizontalBrickGate, image);
		
		image = loadImage("resources/land/brick_gate_v_open.gif");
		images.put(Land.OpenedVerticalBrickGate, image);
		
		image = loadImage("resources/land/grass.gif");
		images.put(Land.Grass, image);
		
		image = loadImage("resources/land/hiro_grass.gif");
		images.put(Land.Hero_Grass, image);

		image = loadImage("resources/land/bullet_grass.gif");
		images.put(Land.Bullet_Grass, image);

		image = loadImage("resources/land/bomb_grass.gif");
		images.put(Land.Bomb_Grass, image);

		image = loadImage("resources/land/robot_grass.gif");
		images.put(Land.BadSoldier_Grass, image);

		image = loadImage("resources/land/tank_grass.gif");
		images.put(Land.BadTank_Grass, image);

		image = loadImage("resources/land/r_u_g.gif");
		images.put(Land.RocketNorth_Grass, image);

		image = loadImage("resources/land/r_d_g.gif");
		images.put(Land.RocketSouth_Grass, image);

		image = loadImage("resources/land/r_r_g.gif");
		images.put(Land.RocketEast_Grass, image);

		image = loadImage("resources/land/r_l_g.gif");
		images.put(Land.RocketWest_Grass, image);

		image = loadImage("resources/land/r_ur_g.gif");
		images.put(Land.RocketNorthEast_Grass, image);

		image = loadImage("resources/land/r_ul_g.gif");
		images.put(Land.RocketNorthWest_Grass, image);

		image = loadImage("resources/land/r_dr_g.gif");
		images.put(Land.RocketSouthEast_Grass, image);

		image = loadImage("resources/land/r_dl_g.gif");
		images.put(Land.RocketSouthWest_Grass, image);

		image = loadImage("resources/land/water.gif");
		images.put(Land.Water, image);
		
		image = loadImage("resources/land/bullet_water.gif");
		images.put(Land.Bullet_Water, image);

		image = loadImage("resources/land/bomb_water.gif");
		images.put(Land.Bomb_Water, image);

		image = loadImage("resources/land/r_u_w.gif");
		images.put(Land.RocketNorth_Water, image);

		image = loadImage("resources/land/r_d_w.gif");
		images.put(Land.RocketSouth_Water, image);

		image = loadImage("resources/land/r_r_w.gif");
		images.put(Land.RocketEast_Water, image);

		image = loadImage("resources/land/r_l_w.gif");
		images.put(Land.RocketWest_Water, image);

		image = loadImage("resources/land/r_ur_w.gif");
		images.put(Land.RocketNorthEast_Water, image);

		image = loadImage("resources/land/r_ul_w.gif");
		images.put(Land.RocketNorthWest_Water, image);

		image = loadImage("resources/land/r_dr_w.gif");
		images.put(Land.RocketSouthEast_Water, image);

		image = loadImage("resources/land/r_dl_w.gif");
		images.put(Land.RocketSouthWest_Water, image);

		image = loadImage("resources/land/soldier.gif");
		images.put(Land.GoodSoldier, image);

		image = loadImage("resources/land/soldier_grass.gif");
		images.put(Land.GoodSoldier_Grass, image);

		image = loadImage("resources/land/tank2.gif");
		images.put(Land.GoodTank, image);

		image = loadImage("resources/land/tank2_grass.gif");
		images.put(Land.GoodTank_Grass, image);

		image = loadImage("resources/land/radar.gif");
		images.put(Land.GoodRadar, image);

		image = loadImage("resources/land/bunker2.gif");
		images.put(Land.GoodBunker, image);

		image = loadImage("resources/land/mine.gif");
		images.put(Land.Mine, image);

		image = loadImage("resources/land/mine_grass.gif");
		images.put(Land.Mine_Grass, image);

		image = loadImage("resources/land/teleport.gif");
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
		
		image = loadImage("resources/land/bunker_grass.gif");
		images.put(Land.BadBunker_Grass, image);

		image = loadImage("resources/land/patriot_grass.gif");
		images.put(Land.BadRadar_Grass, image);
		
		image = loadImage("resources/land/bunker2_grass.gif");
		images.put(Land.GoodBunker_Grass, image);

		image = loadImage("resources/land/radar_grass.gif");
		images.put(Land.GoodRadar_Grass, image);
		
		image = loadImage("resources/land/sand.gif");
		images.put(Land.Sand, image);

		image = loadImage("resources/land/hiro_sand.gif");
		images.put(Land.Hero_Sand, image);

		image = loadImage("resources/land/bullet_sand.gif");
		images.put(Land.Bullet_Sand, image);

		image = loadImage("resources/land/bomb_sand.gif");
		images.put(Land.Bomb_Sand, image);

		image = loadImage("resources/land/mine_sand.gif");
		images.put(Land.Mine_Sand, image);

		image = loadImage("resources/land/r_u_s.gif");
		images.put(Land.RocketNorth_Sand, image);

		image = loadImage("resources/land/r_d_s.gif");
		images.put(Land.RocketSouth_Sand, image);

		image = loadImage("resources/land/r_r_s.gif");
		images.put(Land.RocketEast_Sand, image);

		image = loadImage("resources/land/r_l_s.gif");
		images.put(Land.RocketWest_Sand, image);

		image = loadImage("resources/land/r_ur_s.gif");
		images.put(Land.RocketNorthEast_Sand, image);

		image = loadImage("resources/land/r_ul_s.gif");
		images.put(Land.RocketNorthWest_Sand, image);

		image = loadImage("resources/land/r_dr_s.gif");
		images.put(Land.RocketSouthEast_Sand, image);

		image = loadImage("resources/land/r_dl_s.gif");
		images.put(Land.RocketSouthWest_Sand, image);
		
		image = loadImage("resources/land/robot_sand.gif");
		images.put(Land.BadSoldier_Sand, image);

		image = loadImage("resources/land/tank_sand.gif");
		images.put(Land.BadTank_Sand, image);
		
		image = loadImage("resources/land/bunker_sand.gif");
		images.put(Land.BadBunker_Sand, image);

		image = loadImage("resources/land/patriot_sand.gif");
		images.put(Land.BadRadar_Sand, image);

		image = loadImage("resources/land/soldier_sand.gif");
		images.put(Land.GoodSoldier_Sand, image);

		image = loadImage("resources/land/tank2_sand.gif");
		images.put(Land.GoodTank_Sand, image);
		
		image = loadImage("resources/land/bunker2_sand.gif");
		images.put(Land.GoodBunker_Sand, image);

		image = loadImage("resources/land/radar_sand.gif");
		images.put(Land.GoodRadar_Sand, image);
		
		image = loadImage("resources/land/stone.gif");
		images.put(Land.Stone, image);
		
		image = loadImage("resources/land/grave_grass.gif");
		images.put(Land.Grave_Grass, image);
		
		image = loadImage("resources/land/grave_sand.gif");
		images.put(Land.Grave_Sand, image);

		image = loadImage("resources/land/graver_grass.gif");
		images.put(Land.BadSoldierGrave_Grass, image);

		image = loadImage("resources/land/graver_sand.gif");
		images.put(Land.BadSoldierGrave_Sand, image);
		
		image = loadImage("resources/land/white_brick.gif");
		images.put(Land.WhiteBrick, image);

		image = loadImage("resources/land/black_stone.gif");
		images.put(Land.BlackStone, image);
		
		image = loadImage("resources/land/wood1.gif");
		images.put(Land.Wood1, image);
		
		image = loadImage("resources/land/wood2.gif");
		images.put(Land.Wood2, image);
		
		image = loadImage("resources/land/wood3.gif");
		images.put(Land.Wood3, image);
		
		image = loadImage("resources/land/wood4.gif");
		images.put(Land.Wood4, image);
		
		image = loadImage("resources/land/cannon_ball.gif");
		images.put(Land.CannonBall, image);

		image = loadImage("resources/land/cannon_ball_sand.gif");
		images.put(Land.CannonBall_Sand, image);

		image = loadImage("resources/land/cannon_ball_grass.gif");
		images.put(Land.CannonBall_Grass, image);

		image = loadImage("resources/land/cannon_ball_water.gif");
		images.put(Land.CannonBall_Water, image);
		
		image = loadImage("resources/land/officer.gif");
		images.put(Land.GoodOfficer, image);
		
		image = loadImage("resources/land/officer_grass.gif");
		images.put(Land.GoodOfficer_Grass, image);
		
		image = loadImage("resources/land/officer_sand.gif");
		images.put(Land.GoodOfficer_Sand, image);

		image = loadImage("resources/land/general.gif");
		images.put(Land.GoodGeneral, image);
		
		image = loadImage("resources/land/general_grass.gif");
		images.put(Land.GoodGeneral_Grass, image);
		
		image = loadImage("resources/land/general_sand.gif");
		images.put(Land.GoodGeneral_Sand, image);
		
		image = loadImage("resources/land/tree1.gif");
		images.put(Land.Tree1, image);
		image = loadImage("resources/land/tree2.gif");
		images.put(Land.Tree2, image);
		image = loadImage("resources/land/tree3.gif");
		images.put(Land.Tree3, image);
		
		image = loadImage("resources/land/dark_knight.gif");
		images.put(Land.Dark_Knight, image);
		
		image = loadImage("resources/land/dark_knight_grass.gif");
		images.put(Land.Dark_Knight_Grass, image);
		
		image = loadImage("resources/land/dark_knight_sand.gif");
		images.put(Land.Dark_Knight_Sand, image);

		image = loadImage("resources/land/gray_officer.gif");
		images.put(Land.BadOfficer, image);
		
		image = loadImage("resources/land/gray_officer_grass.gif");
		images.put(Land.BadOfficer_Grass, image);
		
		image = loadImage("resources/land/gray_officer_sand.gif");
		images.put(Land.BadOfficer_Sand, image);

		image = loadImage("resources/land/gray_general.gif");
		images.put(Land.BadGeneral, image);
		
		image = loadImage("resources/land/gray_general_grass.gif");
		images.put(Land.BadGeneral_Grass, image);
		
		image = loadImage("resources/land/gray_general_sand.gif");
		images.put(Land.BadGeneral_Sand, image);

		image = loadImage("resources/land/peasant.gif");
		images.put(Land.Peasant, image);
		
		image = loadImage("resources/land/peasant_grass.gif");
		images.put(Land.Peasant_Grass, image);
		
		image = loadImage("resources/land/peasant_sand.gif");
		images.put(Land.Peasant_Sand, image);
		
		image = loadImage("resources/land/rail_v.gif");
		images.put(Land.Rail_Vertical, image);
		
		image = loadImage("resources/land/rail_h.gif");
		images.put(Land.Rail_Horizontal, image);

		image = loadImage("resources/land/rail_d_u.gif");
		images.put(Land.Rail_Diagonal_Up, image);

		image = loadImage("resources/land/rail_d_d.gif");
		images.put(Land.Rail_Diagonal_Down, image);

		image = loadImage("resources/land/rail_up_r.gif");
		images.put(Land.Rail_Up_Right, image);

		image = loadImage("resources/land/rail_up_l.gif");
		images.put(Land.Rail_Up_Left, image);

		image = loadImage("resources/land/rail_down_r.gif");
		images.put(Land.Rail_Down_Right, image);

		image = loadImage("resources/land/rail_down_l.gif");
		images.put(Land.Rail_Down_Left, image);

		image = loadImage("resources/land/rail_r_up.gif");
		images.put(Land.Rail_Right_Up, image);

		image = loadImage("resources/land/rail_r_down.gif");
		images.put(Land.Rail_Right_Down, image);

		image = loadImage("resources/land/rail_l_up.gif");
		images.put(Land.Rail_Left_Up, image);

		image = loadImage("resources/land/rail_l_down.gif");
		images.put(Land.Rail_Left_Down, image);

		image = loadImage("resources/land/rail_v_cross.gif");
		images.put(Land.Rail_Vertical_Cross, image);

		image = loadImage("resources/land/rail_d_cross.gif");
		images.put(Land.Rail_Diagonal_Cross, image);
		
		image = loadImage("resources/land/train_v.gif");
		images.put(Land.Train_Vertical, image);
		
		image = loadImage("resources/land/train_h.gif");
		images.put(Land.Train_Horizontal, image);

		image = loadImage("resources/land/train_d_u.gif");
		images.put(Land.Train_Diagonal_Up, image);

		image = loadImage("resources/land/train_d_d.gif");
		images.put(Land.Train_Diagonal_Down, image);

		image = loadImage("resources/land/train_up_r.gif");
		images.put(Land.Train_Up_Right, image);

		image = loadImage("resources/land/train_up_l.gif");
		images.put(Land.Train_Up_Left, image);

		image = loadImage("resources/land/train_down_r.gif");
		images.put(Land.Train_Down_Right, image);

		image = loadImage("resources/land/train_down_l.gif");
		images.put(Land.Train_Down_Left, image);

		image = loadImage("resources/land/train_r_up.gif");
		images.put(Land.Train_Right_Up, image);

		image = loadImage("resources/land/train_r_down.gif");
		images.put(Land.Train_Right_Down, image);

		image = loadImage("resources/land/train_l_up.gif");
		images.put(Land.Train_Left_Up, image);

		image = loadImage("resources/land/train_l_down.gif");
		images.put(Land.Train_Left_Down, image);
		
		image = loadImage("resources/land/train_v_cross.gif");
		images.put(Land.Train_Vertical_Cross, image);
		
		image = loadImage("resources/land/train_h_cross.gif");
		images.put(Land.Train_Horizontal_Cross, image);
		
		image = loadImage("resources/land/train_d_u_cross.gif");
		images.put(Land.Train_Diagonal_Up_Cross, image);
		
		image = loadImage("resources/land/train_d_d_cross.gif");
		images.put(Land.Train_Diagonal_Down_Cross, image);
		
		image = loadImage("resources/land/wtrain_v.gif");
		images.put(Land.WarTrain_Vertical, image);
		
		image = loadImage("resources/land/wtrain_h.gif");
		images.put(Land.WarTrain_Horizontal, image);

		image = loadImage("resources/land/wtrain_d_u.gif");
		images.put(Land.WarTrain_Diagonal_Up, image);

		image = loadImage("resources/land/wtrain_d_d.gif");
		images.put(Land.WarTrain_Diagonal_Down, image);

		image = loadImage("resources/land/wtrain_up_r.gif");
		images.put(Land.WarTrain_Up_Right, image);

		image = loadImage("resources/land/wtrain_up_l.gif");
		images.put(Land.WarTrain_Up_Left, image);

		image = loadImage("resources/land/wtrain_down_r.gif");
		images.put(Land.WarTrain_Down_Right, image);

		image = loadImage("resources/land/wtrain_down_l.gif");
		images.put(Land.WarTrain_Down_Left, image);

		image = loadImage("resources/land/wtrain_r_up.gif");
		images.put(Land.WarTrain_Right_Up, image);

		image = loadImage("resources/land/wtrain_r_down.gif");
		images.put(Land.WarTrain_Right_Down, image);

		image = loadImage("resources/land/wtrain_l_up.gif");
		images.put(Land.WarTrain_Left_Up, image);

		image = loadImage("resources/land/wtrain_l_down.gif");
		images.put(Land.WarTrain_Left_Down, image);
		
		image = loadImage("resources/land/wtrain_v_cross.gif");
		images.put(Land.WarTrain_Vertical_Cross, image);
		
		image = loadImage("resources/land/wtrain_h_cross.gif");
		images.put(Land.WarTrain_Horizontal_Cross, image);
		
		image = loadImage("resources/land/wtrain_d_u_cross.gif");
		images.put(Land.WarTrain_Diagonal_Up_Cross, image);
		
		image = loadImage("resources/land/wtrain_d_d_cross.gif");
		images.put(Land.WarTrain_Diagonal_Down_Cross, image);

		image = loadImage("resources/land/station_v.gif");
		images.put(Land.Station_Vertical, image);

		image = loadImage("resources/land/station_h.gif");
		images.put(Land.Station_Horizontal, image);

		image = loadImage("resources/land/mountain.gif");
		images.put(Land.Mountain, image);
	}

	public T getImage(int code) {
		return images.get(code);
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
