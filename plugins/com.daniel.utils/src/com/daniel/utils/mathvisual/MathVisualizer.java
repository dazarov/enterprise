package com.daniel.utils.mathvisual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MathVisualizer {
	private int number = 1000;
	private double startValue = 0.0;
	private double step = 0.005;
	
	private VisualPanel visualPanel;
	
	public MathVisualizer(){
		JFrame frame = new JFrame();
		JTabbedPane tabPane = new JTabbedPane();
		JPanel configPanel = new JPanel();
		visualPanel = new VisualPanel();
		initView();
		
		tabPane.addTab("Options", new JPanel());
		tabPane.addTab("Configuration", configPanel);
		tabPane.addTab("Visual View", visualPanel);
		
		frame.add(tabPane);
		frame.pack();
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
	
	private void initView(){
		double[] x = new double[number];
		double[] y = new double[number];
		for(int index = 0; index < number; index++){
			if(index == 0){
				x[index] = startValue;
			}else{
				x[index] = x[index - 1] + step;
			}
			
			y[index] = Math.tan(x[index]);
					//Math.pow(x[index], 5)*Math.sin(x[index]);
		}
		
		visualPanel.init(x, y);
	}
	
	
	public static void main(String[] args){
		new MathVisualizer();
	}
}
