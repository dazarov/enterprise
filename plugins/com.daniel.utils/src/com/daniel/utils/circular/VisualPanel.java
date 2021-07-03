package com.daniel.utils.circular;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class VisualPanel extends JPanel {
	private static final long serialVersionUID = 971836177433607117L;

	private Configuration configuration;
	
	public VisualPanel(Configuration configuration) {
		super();
		this.configuration = configuration;
		setBackground(Color.BLUE);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		for (int grade = 0; grade < configuration.getAngle(); grade += configuration.getStep())  {
			g.drawLine(
					(int) (configuration.getBaseX() - Math.cos(Math.toRadians(grade)) * configuration.getRateX()), (int)(configuration.getBaseY() + Math.sin(Math.toRadians(grade)) * configuration.getRateY()),
					(int) (configuration.getBaseX() - Math.cos(Math.toRadians(grade + configuration.getOppositAngle())) * configuration.getRateX()), (int)(configuration.getBaseY() + Math.sin(Math.toRadians(grade + configuration.getOppositAngle())) * configuration.getRateY()));
		}
	}
}
