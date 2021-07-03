package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Circular {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 1000, 1000);
		frame.setTitle("Circular graph");
		frame.setBackground(Color.BLUE);
		//JTabbedPane tabPane = new JTabbedPane();
		
		JPanel visualPanel = new JPanel() {
			public void paint(Graphics g) {
				g.setColor(Color.BLACK);
				int baseX = 500;
				int baseY = 350;
				int rateX = 300;
				int rateY = 300;
				for (int grade = 0; grade < 45;grade += 1)  {
					g.drawLine(
							(int)(baseX - Math.cos(Math.toRadians(grade)) * rateX), (int)(baseY + Math.sin(Math.toRadians(grade)) * rateY),
							(int)(baseX - Math.cos(Math.toRadians(grade + 180)) * rateX), (int)(baseY + Math.sin(Math.toRadians(grade + 180)) * rateY));
				}
			}
		};
		visualPanel.setBounds(10, 10, 1000, 1000);
		

		

		frame.add(visualPanel);
		//frame.pack();
		
		frame.setVisible(true);
	}
}
