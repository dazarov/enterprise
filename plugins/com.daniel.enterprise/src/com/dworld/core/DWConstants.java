package com.dworld.core;

public interface DWConstants {
	int COMMON_TARGET_MAX_NUMBER = 20;
	int COMMON_TARGET_FRAME_NUMBER = 5;
	int COMMON_TARGET_FRAME_CLEAR = 50;
	
	// Size
	
	int MAX_X = 3500;
	int MAX_Y = 2200;
	
	int TEST_MAX_X = 100;
	int TEST_MAX_Y = 100;
	
	int MIN_X = 0;
	int MIN_Y = 0;
	
	int UI_WIDTH = 49;
	int UI_HEIGHT = 49;
	int UI_IMAGE_WIDTH = 16;
	int UI_IMAGE_HEIGHT = 16;
	
	// Distance
	
	int COMMON_TARGET_DISTANCE = 120;
	
	int VISIBLE_DISTANCE = 30;
	
	int ROCKET_VISIBLE_DISTANCE = 60;
	
	int BUNKER_VISIBLE_DISTANCE = 50;
	
	int RADAR_VISIBLE_DISTANCE = 100;
	
	int TANK_NOTARMORED_DISTANCE = 40;
	
	int TANK_ARMORED_DISTANCE = 60;
	
	
	// Range
	
	int MIN_RANGE = 7;
	
	int BULLET_RANGE = 50;
	
	int CANNONBALL_RANGE = 75;
	
	int ROCKET_RANGE = 150;
	
	// Speed
	
	double MAX_SPEED = 1.0;
	
	double STOP_SPEED = 0.0;
	
	double WALK_SPEED = 0.5;
	
	double BULLET_SPEED = MAX_SPEED;
	
	double CANNONBALL_SPEED = MAX_SPEED;
	
	double BOMB_SPEED = 0.4;
	
	double ROCKET_SPEED = 0.5;
	
	double SOLDIER_SPEED = 0.2;
	
	double PEASANT_SPEED = 0.2;
	
	double TANK_SPEED = 0.1;
	
	double TRAIN_SPEED = 0.1;
}
