package com.dworld.ui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JProgressBar;


public class DWProgressMonitor extends JDialog implements IProgressMonitor{
	public static final long serialVersionUID = 1;
	
	private JProgressBar progressBar;
	
	public DWProgressMonitor(Frame parent, String title, int max){
		super(parent);
		setResizable(false);
		setTitle(title);
		progressBar = new JProgressBar(0, max);
		progressBar.setPreferredSize(new Dimension(200, 20));
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		add(progressBar);
		pack();
		setLocation(parent.getLocation().x+parent.getSize().width/2-getSize().width/2, parent.getLocation().y+parent.getSize().height/2-getSize().height/2);
		setVisible(true);
	}
	
	@Override
	public void setProgress(int value){
		progressBar.setValue(value);
	}
	
	@Override
	public void close(){
		setVisible(false);
	}
}
