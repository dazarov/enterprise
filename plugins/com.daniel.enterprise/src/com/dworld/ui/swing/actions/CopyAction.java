package com.dworld.ui.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dworld.core.SelectionManager;

public class CopyAction implements ActionListener {
	public CopyAction() {
	}

	public void actionPerformed(ActionEvent e) {
		SelectionManager.copy();
	}
}