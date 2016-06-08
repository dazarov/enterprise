package com.dworld.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dworld.core.DWConfiguration;

public class SelectElementAction implements ActionListener {
	int code;

	public SelectElementAction(int code) {
		this.code = code;
	}

	public void actionPerformed(ActionEvent e) {
		DWConfiguration.getInstance().setSelectedMenu(code);
	}
}