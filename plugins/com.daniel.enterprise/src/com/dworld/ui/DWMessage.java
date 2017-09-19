package com.dworld.ui;

import java.util.ResourceBundle;

import com.dworld.core.DWConfiguration;

public enum DWMessage {
		FILE,
		SAVE,
		LOAD,
		LOAD_BACKUP,
		SAVE_BACKUP,
		LOAD_TEST,
		SAVE_TEST,
		EXIT,
		MAP,
		MINI_MAP,
		PAUSE,
		GO,
		INFO,
		BUILD,
		BUILD_MODE,
		COPY,
		PASTE,
		LANDSCAPE,
		WALL,
		BRICK,
		WHITE_BRICK,
		WHITE_STONE,
		BLACK_STONE,
		WOOD,
		MOUNTAIIN,
		WATER,
		SAND,
		GRASS,
		TREE,
		RAILROAD,
		TRAIN,
		STATION,
		RAIL,
		DOORS,
		VERTICAL_STEEL_DOOR,
		HORIZONTAL_STEEL_DOOR,
		VERTICAL_WOOD_DOOR,
		HORIZONTAL_WOOD_DOOR,
		VERTICAL_CONCREET_DOOR,
		HORIZONTAL_CONCREET_DOOR,
		VETICAL_BRICK_DOOR,
		HORIZONTAL_BRICK_DOOR,
		AMMUNITION,
		AMMO,
		GRENADE,
		ROCKET,
		MINE,
		ENEMIES,
		BAD_GENERAL,
		BAD_OFFICER,
		BAD_SOLDIER,
		BAD_TANK,
		BAD_BUNKER,
		BAD_RADAR,
		CITIZENS,
		GOOD_GENERAL,
		GOOD_OFFICER,
		GOOD_SOLDIER,
		GOOD_TANK,
		GOOD_BUNKER,
		GOOD_RADAR,
		PEASANT
	;
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("com.dworld.ui.messages",
				DWConfiguration.getInstance().getLocale());


	public String get() {
		return resourceBundle.getString(name());
	}
}