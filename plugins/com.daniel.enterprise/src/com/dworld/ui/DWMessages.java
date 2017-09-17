package com.dworld.ui;

import java.util.ResourceBundle;

import com.dworld.core.DWConfiguration;

public class DWMessages {
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("com.dworld.ui.messages", DWConfiguration.getInstance().getLocale());
	
	public static final String FILE = "FILE";
	
	public static String getMessage(String key) {
		return resourceBundle.getString(key);
	}
}
