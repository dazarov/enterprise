package com.dworld.ui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.DWMessage;
import com.dworld.ui.IProgressMonitor;


public class DWSwingProgressMonitor extends JDialog implements IProgressMonitor{
	public static final long serialVersionUID = 1;
	
	private JProgressBar progressBar;
	private volatile boolean canceled = false;
	
	
	public DWSwingProgressMonitor(String title){
		super(DWConfiguration.getInstance().getUI(DWSwingUI.class).getWindow());
		setResizable(false);
		setTitle(title);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		progressBar = new JProgressBar(0, 100);
		progressBar.setPreferredSize(new Dimension(310, 30));
		progressBar.setForeground(Color.blue);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(progressBar);
		JButton button = new JButton(DWMessage.CANCEL.get());
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.addActionListener(e -> canceled = true);
		add(button);
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

	@Override
	public boolean isCancelled() {
		return canceled;
	}
}
