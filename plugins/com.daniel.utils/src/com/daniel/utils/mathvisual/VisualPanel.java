package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.stream.DoubleStream;

import javax.swing.JPanel;

public class VisualPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double x[];
	private double y[];
	private double minX, maxX, minY, maxY;
	
	public VisualPanel(){
		super();
		
		setPreferredSize(new Dimension(800, 800));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void init(double[] x, double[] y){
		this.x = x;
		this.y = y;
		
		minX = DoubleStream.of(x).min().getAsDouble();
		maxX = DoubleStream.of(x).max().getAsDouble();
		minY = DoubleStream.of(y).min().getAsDouble();
		maxY = DoubleStream.of(y).max().getAsDouble();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		g.setColor(Color.BLACK);
		
		g.drawRect(5,  5, getWidth() - 10, getHeight() - 10);
		
		g.drawString("" + round(minX), 20, getHeight() - 20);
		g.drawString("X " + round(maxX), getWidth() - 50, getHeight() - 20);
		g.drawString("" + round(minY), 10, getHeight() - 50);
		g.drawString("Y " + round(maxY), 10, 20);
		
		if(minX < 0 && maxX > 0){
			g.drawLine(translateX(0), 5, translateX(0), getHeight() - 5);
			g.drawString("0", translateX(0), 5);
		}
		
		if(minY < 0 && maxY > 0){
			g.drawLine(5, translateY(0), getWidth() - 5, translateY(0));
			g.drawString("0", 10, translateY(0) - 5);
		}
		
		g.setColor(Color.BLUE);
		
		for(int index = 1; index < x.length; index++){
			g.drawLine(translateX(x[index - 1]), translateY(y[index - 1]),
					translateX(x[index]), translateY(y[index]));
		}
	}
	
	private int translateX(double x){
		return 10 + (int) ((getWidth() - 20) / ((maxX - minX) / (x - minX)));
	}
	
	private int translateY(double y){
		return 10 + (int)
				(getHeight() - 20 - ((getHeight() - 20) /
						((maxY - minY) / (y - minY))));
	}
	
	private double round(double a){
		return Math.round(a * 100.0) / 100.0;
	}

}
