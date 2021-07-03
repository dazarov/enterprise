package com.daniel.utils.circular;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Circular {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 1000, 1000);
		frame.setTitle("Circular graph");
		frame.setBackground(Color.BLUE);
		Configuration configuration = new Configuration();
		
		VisualPanel visualPanel = new VisualPanel(configuration);
		visualPanel.setBounds(10, 10, 1000, 1000);
		
		JPanel configurationPanel = new ConfigurationPanel(configuration, visualPanel);
		configurationPanel.setBounds(10, 10, 1000, 1000);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, visualPanel, configurationPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

	//Provide minimum sizes for the two components in the split pane
	Dimension minimumSize = new Dimension(100, 50);
	visualPanel.setMinimumSize(minimumSize);
	configurationPanel.setMinimumSize(minimumSize);
		frame.add(splitPane);
		
		frame.setVisible(true);
	}
}
