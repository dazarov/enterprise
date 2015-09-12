package com.dworld.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dworld.core.SelectionManager;

public class PasteAction implements ActionListener {
	public PasteAction() {
	}

	public void actionPerformed(ActionEvent e) {
		SelectionManager.paste();
	}
}