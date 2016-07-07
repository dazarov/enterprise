package com.dworld.ui.swing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.IProgressMonitor;


public class DWProgressMonitor extends JDialog implements IProgressMonitor{
	public static final long serialVersionUID = 1;
	
	private JProgressBar progressBar;
	
	
	public DWProgressMonitor(String title){
		super(DWConfiguration.getInstance().getUI().getWindow());
		setResizable(false);
		setTitle(title);
		progressBar = new JProgressBar(0, 100);
		progressBar.setPreferredSize(new Dimension(300, 20));
		progressBar.setForeground(Color.blue);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		add(progressBar);
		pack();
		setLocation(getOwner().getLocation().x+getOwner().getSize().width/2-getSize().width/2, getOwner().getLocation().y+getOwner().getSize().height/2-getSize().height/2);
		setVisible(true);
	}
	
	@Override
	public void progress(int value){
		progressBar.setValue(value);
	}
	
	@Override
	public void close(){
		setVisible(false);
		dispose();
	}
}
