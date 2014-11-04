package com.dworld.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;

import com.dworld.DWorldLauncher;
import com.dworld.core.DWorldConstants;
import com.dworld.core.Land;
import com.dworld.core.SelectionManager;
import com.dworld.units.ControlledUnit;

public class DrawWorld {
	private static int startX = 0, startY = 0;
	private static HashMap<Integer, Image> images = new HashMap<Integer, Image>();
	private static ControlledUnit unit = null;

	static {
		Image image;

		image = loadImage("resources/land/none.gif");
		images.put(new Integer(Land.Empty), image);

		image = loadImage("resources/land/wall.gif");
		images.put(new Integer(Land.Wall), image);

		image = loadImage("resources/land/brick.gif");
		images.put(new Integer(Land.Brick), image);

		image = loadImage("resources/land/grenada.gif");
		images.put(new Integer(Land.Grenada), image);

		image = loadImage("resources/land/ammo.gif");
		images.put(new Integer(Land.Ammo), image);

		image = loadImage("resources/land/robot.gif");
		images.put(new Integer(Land.Robot), image);

		image = loadImage("resources/land/tank.gif");
		images.put(new Integer(Land.Tank), image);

		image = loadImage("resources/land/hiro.gif");
		images.put(new Integer(Land.Man), image);

		image = loadImage("resources/land/bomb.gif");
		images.put(new Integer(Land.Bomb), image);

		image = loadImage("resources/land/bullet.gif");
		images.put(new Integer(Land.Bullet), image);

		image = loadImage("resources/land/patron.gif");
		images.put(new Integer(Land.Patron), image);

		image = loadImage("resources/land/grave.gif");
		images.put(new Integer(Land.Grave), image);

		image = loadImage("resources/land/enemy.gif");
		images.put(new Integer(Land.Enemy), image);

		image = loadImage("resources/land/r_u.gif");
		images.put(new Integer(Land.RocketNorth), image);

		image = loadImage("resources/land/r_d.gif");
		images.put(new Integer(Land.RocketSouth), image);

		image = loadImage("resources/land/r_r.gif");
		images.put(new Integer(Land.RocketEast), image);

		image = loadImage("resources/land/r_l.gif");
		images.put(new Integer(Land.RocketWest), image);

		image = loadImage("resources/land/r_ur.gif");
		images.put(new Integer(Land.RocketNorthEast), image);

		image = loadImage("resources/land/r_ul.gif");
		images.put(new Integer(Land.RocketNorthWest), image);

		image = loadImage("resources/land/r_dr.gif");
		images.put(new Integer(Land.RocketSouthEast), image);

		image = loadImage("resources/land/r_dl.gif");
		images.put(new Integer(Land.RocketSouthWest), image);

		image = loadImage("resources/land/rocket.gif");
		images.put(new Integer(Land.Rocket), image);

		image = loadImage("resources/land/food.gif");
		images.put(new Integer(Land.Food), image);

		image = loadImage("resources/land/opdoor.gif");
		images.put(new Integer(Land.OpenedDoor), image);

		image = loadImage("resources/land/cldoor.gif");
		images.put(new Integer(Land.ClosedDoor), image);

		image = loadImage("resources/land/graver.gif");
		images.put(new Integer(Land.RobotGrave), image);

		image = loadImage("resources/land/gravet.gif");
		images.put(new Integer(Land.TankGrave), image);

		image = loadImage("resources/land/bunker.gif");
		images.put(new Integer(Land.Bunker), image);

		image = loadImage("resources/land/patriot.gif");
		images.put(new Integer(Land.Radar), image);

		image = loadImage("resources/land/hwgo.gif");
		images.put(new Integer(Land.OpenedHorizontalWoodGate), image);

		image = loadImage("resources/land/hwgc.gif");
		images.put(new Integer(Land.ClosedHorizontalWoodGate), image);

		image = loadImage("resources/land/vwgo.gif");
		images.put(new Integer(Land.OpenedVerticalWoodGate), image);

		image = loadImage("resources/land/vwgc.gif");
		images.put(new Integer(Land.ClosedVerticalWoodGate), image);

		image = loadImage("resources/land/hsgo.gif");
		images.put(new Integer(Land.OpenedHorizontalSteelGate), image);

		image = loadImage("resources/land/hsgc.gif");
		images.put(new Integer(Land.ClosedHorizontalSteelGate), image);

		image = loadImage("resources/land/vsgo.gif");
		images.put(new Integer(Land.OpenedVerticalSteelGate), image);

		image = loadImage("resources/land/vsgc.gif");
		images.put(new Integer(Land.ClosedVerticalSteelGate), image);
		
		image = loadImage("resources/land/wall_gate.gif");
		images.put(new Integer(Land.ClosedHorizontalConcreteGate), image);
		images.put(new Integer(Land.ClosedVerticalConcreteGate), image);
		
		image = loadImage("resources/land/wall_gate_h_open.gif");
		images.put(new Integer(Land.OpenedHorizontalConcreteGate), image);
		
		image = loadImage("resources/land/wall_gate_v_open.gif");
		images.put(new Integer(Land.OpenedVerticalConcreteGate), image);
		
		image = loadImage("resources/land/brick_gate.gif");
		images.put(new Integer(Land.ClosedHorizontalBrickGate), image);
		images.put(new Integer(Land.ClosedVerticalBrickGate), image);
		
		image = loadImage("resources/land/brick_gate_h_open.gif");
		images.put(new Integer(Land.OpenedHorizontalBrickGate), image);
		
		image = loadImage("resources/land/brick_gate_v_open.gif");
		images.put(new Integer(Land.OpenedVerticalBrickGate), image);
		
		image = loadImage("resources/land/grass.gif");
		images.put(new Integer(Land.Grass), image);
		
		image = loadImage("resources/land/hiro_grass.gif");
		images.put(new Integer(Land.Man_Grass), image);

		image = loadImage("resources/land/bullet_grass.gif");
		images.put(new Integer(Land.Bullet_Grass), image);

		image = loadImage("resources/land/bomb_grass.gif");
		images.put(new Integer(Land.Bomb_Grass), image);

		image = loadImage("resources/land/robot_grass.gif");
		images.put(new Integer(Land.Robot_Grass), image);

		image = loadImage("resources/land/tank_grass.gif");
		images.put(new Integer(Land.Tank_Grass), image);

		image = loadImage("resources/land/r_u_g.gif");
		images.put(new Integer(Land.RocketNorth_Grass), image);

		image = loadImage("resources/land/r_d_g.gif");
		images.put(new Integer(Land.RocketSouth_Grass), image);

		image = loadImage("resources/land/r_r_g.gif");
		images.put(new Integer(Land.RocketEast_Grass), image);

		image = loadImage("resources/land/r_l_g.gif");
		images.put(new Integer(Land.RocketWest_Grass), image);

		image = loadImage("resources/land/r_ur_g.gif");
		images.put(new Integer(Land.RocketNorthEast_Grass), image);

		image = loadImage("resources/land/r_ul_g.gif");
		images.put(new Integer(Land.RocketNorthWest_Grass), image);

		image = loadImage("resources/land/r_dr_g.gif");
		images.put(new Integer(Land.RocketSouthEast_Grass), image);

		image = loadImage("resources/land/r_dl_g.gif");
		images.put(new Integer(Land.RocketSouthWest_Grass), image);

		image = loadImage("resources/land/water.gif");
		images.put(new Integer(Land.Water), image);
		
		image = loadImage("resources/land/bullet_water.gif");
		images.put(new Integer(Land.Bullet_Water), image);

		image = loadImage("resources/land/bomb_water.gif");
		images.put(new Integer(Land.Bomb_Water), image);

		image = loadImage("resources/land/r_u_w.gif");
		images.put(new Integer(Land.RocketNorth_Water), image);

		image = loadImage("resources/land/r_d_w.gif");
		images.put(new Integer(Land.RocketSouth_Water), image);

		image = loadImage("resources/land/r_r_w.gif");
		images.put(new Integer(Land.RocketEast_Water), image);

		image = loadImage("resources/land/r_l_w.gif");
		images.put(new Integer(Land.RocketWest_Water), image);

		image = loadImage("resources/land/r_ur_w.gif");
		images.put(new Integer(Land.RocketNorthEast_Water), image);

		image = loadImage("resources/land/r_ul_w.gif");
		images.put(new Integer(Land.RocketNorthWest_Water), image);

		image = loadImage("resources/land/r_dr_w.gif");
		images.put(new Integer(Land.RocketSouthEast_Water), image);

		image = loadImage("resources/land/r_dl_w.gif");
		images.put(new Integer(Land.RocketSouthWest_Water), image);

		image = loadImage("resources/land/soldier.gif");
		images.put(new Integer(Land.Soldier), image);

		image = loadImage("resources/land/soldier_grass.gif");
		images.put(new Integer(Land.Soldier_Grass), image);

		image = loadImage("resources/land/tank2.gif");
		images.put(new Integer(Land.GoodTank), image);

		image = loadImage("resources/land/tank2_grass.gif");
		images.put(new Integer(Land.GoodTank_Grass), image);

		image = loadImage("resources/land/radar.gif");
		images.put(new Integer(Land.GoodRadar), image);

		image = loadImage("resources/land/bunker2.gif");
		images.put(new Integer(Land.GoodBunker), image);

		image = loadImage("resources/land/mine.gif");
		images.put(new Integer(Land.Mine), image);

		image = loadImage("resources/land/mine_grass.gif");
		images.put(new Integer(Land.Mine_Grass), image);

		image = loadImage("resources/land/teleport.gif");
		images.put(new Integer(Land.Teleport1), image);
		images.put(new Integer(Land.Teleport2), image);
		images.put(new Integer(Land.Teleport3), image);
		images.put(new Integer(Land.Teleport4), image);
		images.put(new Integer(Land.Teleport5), image);
		images.put(new Integer(Land.Teleport6), image);
		images.put(new Integer(Land.Teleport7), image);
		images.put(new Integer(Land.Teleport8), image);
		images.put(new Integer(Land.Teleport9), image);
		
		image = loadImage("resources/land/bunker_grass.gif");
		images.put(new Integer(Land.Bunker_Grass), image);

		image = loadImage("resources/land/patriot_grass.gif");
		images.put(new Integer(Land.Radar_Grass), image);
		
		image = loadImage("resources/land/bunker2_grass.gif");
		images.put(new Integer(Land.GoodBunker_Grass), image);

		image = loadImage("resources/land/radar_grass.gif");
		images.put(new Integer(Land.GoodRadar_Grass), image);
		
		image = loadImage("resources/land/sand.gif");
		images.put(new Integer(Land.Sand), image);

		image = loadImage("resources/land/hiro_sand.gif");
		images.put(new Integer(Land.Man_Sand), image);

		image = loadImage("resources/land/bullet_sand.gif");
		images.put(new Integer(Land.Bullet_Sand), image);

		image = loadImage("resources/land/bomb_sand.gif");
		images.put(new Integer(Land.Bomb_Sand), image);

		image = loadImage("resources/land/mine_sand.gif");
		images.put(new Integer(Land.Mine_Sand), image);

		image = loadImage("resources/land/r_u_s.gif");
		images.put(new Integer(Land.RocketNorth_Sand), image);

		image = loadImage("resources/land/r_d_s.gif");
		images.put(new Integer(Land.RocketSouth_Sand), image);

		image = loadImage("resources/land/r_r_s.gif");
		images.put(new Integer(Land.RocketEast_Sand), image);

		image = loadImage("resources/land/r_l_s.gif");
		images.put(new Integer(Land.RocketWest_Sand), image);

		image = loadImage("resources/land/r_ur_s.gif");
		images.put(new Integer(Land.RocketNorthEast_Sand), image);

		image = loadImage("resources/land/r_ul_s.gif");
		images.put(new Integer(Land.RocketNorthWest_Sand), image);

		image = loadImage("resources/land/r_dr_s.gif");
		images.put(new Integer(Land.RocketSouthEast_Sand), image);

		image = loadImage("resources/land/r_dl_s.gif");
		images.put(new Integer(Land.RocketSouthWest_Sand), image);
		
		image = loadImage("resources/land/robot_sand.gif");
		images.put(new Integer(Land.Robot_Sand), image);

		image = loadImage("resources/land/tank_sand.gif");
		images.put(new Integer(Land.Tank_Sand), image);
		
		image = loadImage("resources/land/bunker_sand.gif");
		images.put(new Integer(Land.Bunker_Sand), image);

		image = loadImage("resources/land/patriot_sand.gif");
		images.put(new Integer(Land.Radar_Sand), image);

		image = loadImage("resources/land/soldier_sand.gif");
		images.put(new Integer(Land.Soldier_Sand), image);

		image = loadImage("resources/land/tank2_sand.gif");
		images.put(new Integer(Land.GoodTank_Sand), image);
		
		image = loadImage("resources/land/bunker2_sand.gif");
		images.put(new Integer(Land.GoodBunker_Sand), image);

		image = loadImage("resources/land/radar_sand.gif");
		images.put(new Integer(Land.GoodRadar_Sand), image);
		
		image = loadImage("resources/land/stone.gif");
		images.put(new Integer(Land.Stone), image);
		
		image = loadImage("resources/land/grave_grass.gif");
		images.put(new Integer(Land.Grave_Grass), image);
		
		image = loadImage("resources/land/grave_sand.gif");
		images.put(new Integer(Land.Grave_Sand), image);

		image = loadImage("resources/land/graver_grass.gif");
		images.put(new Integer(Land.RobotGrave_Grass), image);

		image = loadImage("resources/land/graver_sand.gif");
		images.put(new Integer(Land.RobotGrave_Sand), image);
		
		image = loadImage("resources/land/white_brick.gif");
		images.put(new Integer(Land.WhiteBrick), image);

		image = loadImage("resources/land/black_stone.gif");
		images.put(new Integer(Land.BlackStone), image);
		
		image = loadImage("resources/land/wood1.gif");
		images.put(new Integer(Land.Wood1), image);
		
		image = loadImage("resources/land/wood2.gif");
		images.put(new Integer(Land.Wood2), image);
		
		image = loadImage("resources/land/wood3.gif");
		images.put(new Integer(Land.Wood3), image);
		
		image = loadImage("resources/land/wood4.gif");
		images.put(new Integer(Land.Wood4), image);
		
		image = loadImage("resources/land/cannon_ball.gif");
		images.put(new Integer(Land.CannonBall), image);

		image = loadImage("resources/land/cannon_ball_sand.gif");
		images.put(new Integer(Land.CannonBall_Sand), image);

		image = loadImage("resources/land/cannon_ball_grass.gif");
		images.put(new Integer(Land.CannonBall_Grass), image);

		image = loadImage("resources/land/cannon_ball_water.gif");
		images.put(new Integer(Land.CannonBall_Water), image);
		
		image = loadImage("resources/land/officer.gif");
		images.put(new Integer(Land.Officer), image);
		
		image = loadImage("resources/land/officer_grass.gif");
		images.put(new Integer(Land.Officer_Grass), image);
		
		image = loadImage("resources/land/officer_sand.gif");
		images.put(new Integer(Land.Officer_Sand), image);

		image = loadImage("resources/land/general.gif");
		images.put(new Integer(Land.General), image);
		
		image = loadImage("resources/land/general_grass.gif");
		images.put(new Integer(Land.General_Grass), image);
		
		image = loadImage("resources/land/general_sand.gif");
		images.put(new Integer(Land.General_Sand), image);
		
		image = loadImage("resources/land/tree1.gif");
		images.put(new Integer(Land.Tree1), image);
		image = loadImage("resources/land/tree2.gif");
		images.put(new Integer(Land.Tree2), image);
		image = loadImage("resources/land/tree3.gif");
		images.put(new Integer(Land.Tree3), image);
		
		image = loadImage("resources/land/dark_knight.gif");
		images.put(new Integer(Land.Dark_Knight), image);
		
		image = loadImage("resources/land/dark_knight_grass.gif");
		images.put(new Integer(Land.Dark_Knight_Grass), image);
		
		image = loadImage("resources/land/dark_knight_sand.gif");
		images.put(new Integer(Land.Dark_Knight_Sand), image);

		image = loadImage("resources/land/gray_officer.gif");
		images.put(new Integer(Land.Gray_Officer), image);
		
		image = loadImage("resources/land/gray_officer_grass.gif");
		images.put(new Integer(Land.Gray_Officer_Grass), image);
		
		image = loadImage("resources/land/gray_officer_sand.gif");
		images.put(new Integer(Land.Gray_Officer_Sand), image);

		image = loadImage("resources/land/gray_general.gif");
		images.put(new Integer(Land.Gray_General), image);
		
		image = loadImage("resources/land/gray_general_grass.gif");
		images.put(new Integer(Land.Gray_General_Grass), image);
		
		image = loadImage("resources/land/gray_general_sand.gif");
		images.put(new Integer(Land.Gray_General_Sand), image);

		image = loadImage("resources/land/peasant.gif");
		images.put(new Integer(Land.Peasant), image);
		
		image = loadImage("resources/land/peasant_grass.gif");
		images.put(new Integer(Land.Peasant_Grass), image);
		
		image = loadImage("resources/land/peasant_sand.gif");
		images.put(new Integer(Land.Peasant_Sand), image);
		
		image = loadImage("resources/land/rail_v.gif");
		images.put(new Integer(Land.Rail_Vertical), image);
		
		image = loadImage("resources/land/rail_h.gif");
		images.put(new Integer(Land.Rail_Horizontal), image);

		image = loadImage("resources/land/rail_d_u.gif");
		images.put(new Integer(Land.Rail_Diagonal_Up), image);

		image = loadImage("resources/land/rail_d_d.gif");
		images.put(new Integer(Land.Rail_Diagonal_Down), image);

		image = loadImage("resources/land/rail_up_r.gif");
		images.put(new Integer(Land.Rail_Up_Right), image);

		image = loadImage("resources/land/rail_up_l.gif");
		images.put(new Integer(Land.Rail_Up_Left), image);

		image = loadImage("resources/land/rail_down_r.gif");
		images.put(new Integer(Land.Rail_Down_Right), image);

		image = loadImage("resources/land/rail_down_l.gif");
		images.put(new Integer(Land.Rail_Down_Left), image);

		image = loadImage("resources/land/rail_r_up.gif");
		images.put(new Integer(Land.Rail_Right_Up), image);

		image = loadImage("resources/land/rail_r_down.gif");
		images.put(new Integer(Land.Rail_Right_Down), image);

		image = loadImage("resources/land/rail_l_up.gif");
		images.put(new Integer(Land.Rail_Left_Up), image);

		image = loadImage("resources/land/rail_l_down.gif");
		images.put(new Integer(Land.Rail_Left_Down), image);

		image = loadImage("resources/land/rail_v_cross.gif");
		images.put(new Integer(Land.Rail_Vertical_Cross), image);

		image = loadImage("resources/land/rail_d_cross.gif");
		images.put(new Integer(Land.Rail_Diagonal_Cross), image);
		
		image = loadImage("resources/land/train_v.gif");
		images.put(new Integer(Land.Train_Vertical), image);
		
		image = loadImage("resources/land/train_h.gif");
		images.put(new Integer(Land.Train_Horizontal), image);

		image = loadImage("resources/land/train_d_u.gif");
		images.put(new Integer(Land.Train_Diagonal_Up), image);

		image = loadImage("resources/land/train_d_d.gif");
		images.put(new Integer(Land.Train_Diagonal_Down), image);

		image = loadImage("resources/land/train_up_r.gif");
		images.put(new Integer(Land.Train_Up_Right), image);

		image = loadImage("resources/land/train_up_l.gif");
		images.put(new Integer(Land.Train_Up_Left), image);

		image = loadImage("resources/land/train_down_r.gif");
		images.put(new Integer(Land.Train_Down_Right), image);

		image = loadImage("resources/land/train_down_l.gif");
		images.put(new Integer(Land.Train_Down_Left), image);

		image = loadImage("resources/land/train_r_up.gif");
		images.put(new Integer(Land.Train_Right_Up), image);

		image = loadImage("resources/land/train_r_down.gif");
		images.put(new Integer(Land.Train_Right_Down), image);

		image = loadImage("resources/land/train_l_up.gif");
		images.put(new Integer(Land.Train_Left_Up), image);

		image = loadImage("resources/land/train_l_down.gif");
		images.put(new Integer(Land.Train_Left_Down), image);
		
		image = loadImage("resources/land/train_v_cross.gif");
		images.put(new Integer(Land.Train_Vertical_Cross), image);
		
		image = loadImage("resources/land/train_h_cross.gif");
		images.put(new Integer(Land.Train_Horizontal_Cross), image);
		
		image = loadImage("resources/land/train_d_u_cross.gif");
		images.put(new Integer(Land.Train_Diagonal_Up_Cross), image);
		
		image = loadImage("resources/land/train_d_d_cross.gif");
		images.put(new Integer(Land.Train_Diagonal_Down_Cross), image);

		image = loadImage("resources/land/station_v.gif");
		images.put(new Integer(Land.Station_Vertical), image);

		image = loadImage("resources/land/station_h.gif");
		images.put(new Integer(Land.Station_Horizontal), image);

	}

	public static Image getImage(int code) {
		return images.get(new Integer(code));
	}

	private static Image loadImage(String path) {
		URL url = DWorldLauncher.class.getClassLoader().getResource(path);
		if(url != null){
			return new ImageIcon(url).getImage();
		}else{
			return new ImageIcon(path).getImage();
		}
	}

	private static void calculateLocation(Point location) {
		if (location.x < 24)
			startX = 0;
		else if (location.x > Land.getMaxX() - 26)
			startX = Land.getMaxX() - 49;
		else
			startX = location.x - 23;

		if (location.y < 24)
			startY = 0;
		else if (location.y > Land.getMaxY() - 26)
			startY = Land.getMaxY() - 49;
		else
			startY = location.y - 23;
	}
	
	private static int code;
	private static Image image;
	
	public static void draw(Graphics g) {
		if (unit != null)
			calculateLocation(unit.getDrawPosition());
		
		for (int x = 0; x < DWorldConstants.UI_WIDTH; x++) {
			for (int y = 0; y < DWorldConstants.UI_HEIGHT; y++) {
				code = Land.getLand(startX + x, startY + y);
				
				image = getImage(code);
				g.drawImage(image, x * DWorldConstants.UI_IMAGE_WIDTH, y * DWorldConstants.UI_IMAGE_HEIGHT, DWorldConstants.UI_IMAGE_WIDTH,	DWorldConstants.UI_IMAGE_HEIGHT, null);
			}
		}
		Rectangle area = SelectionManager.getSelectedArea();
		if(area != null){
			g.setColor(Color.yellow);
			g.drawRect((area.x-startX)*DWorldConstants.UI_IMAGE_WIDTH, (area.y-startY)*DWorldConstants.UI_IMAGE_HEIGHT, area.width*DWorldConstants.UI_IMAGE_WIDTH-1, area.height*DWorldConstants.UI_IMAGE_HEIGHT-1);
		}
	}

	public static int getX() {
		return startX;
	}

	public static void setX(int x) {
		DrawWorld.startX = x;
	}

	public static int getY() {
		return startY;
	}

	public static void setY(int y) {
		DrawWorld.startY = y;
	}

	public static void setUnit(ControlledUnit unit) {
		DrawWorld.unit = unit;
	}
}
