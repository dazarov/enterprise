package com.dworld.ui;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;


public class MessageDialog extends JDialog {
	public static final long serialVersionUID = 1;
	
	public MessageDialog(Frame parent, String title, String message){
		super(parent);
		setResizable(false);
		setTitle(title);
		JLabel label = new JLabel(message);
		add(label);
		pack();
		setLocation(parent.getLocation().x+parent.getSize().width/2-getSize().width/2, parent.getLocation().y+parent.getSize().height/2-getSize().height/2);
		setVisible(true);
	}
	
	public void close(){
		setVisible(false);
	}
}
