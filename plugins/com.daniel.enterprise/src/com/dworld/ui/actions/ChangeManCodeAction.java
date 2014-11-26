package com.dworld.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.dworld.core.Engine;

public class ChangeManCodeAction implements ActionListener {
	private int code;
	private JFrame window;
	
	public ChangeManCodeAction(JFrame window, int code) {
		this.code = code;
		this.window = window;
	}

	public void actionPerformed(ActionEvent e) {
		Engine.getEngine().changeManCode(code);
		window.requestFocus();
	}
}