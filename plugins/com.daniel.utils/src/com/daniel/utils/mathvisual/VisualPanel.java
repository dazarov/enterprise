package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class VisualPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GraphList list;
	
	public VisualPanel(){
		super();
		
		setPreferredSize(new Dimension(800, 800));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void init(GraphList list){
		this.list = list;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		g.setColor(Color.BLACK);
		
		g.drawRect(5,  5, getWidth() - 10, getHeight() - 10);
		
		g.drawString("" + round(list.getMinX()), 20, getHeight() - 20);
		g.drawString("X " + round(list.getMaxX()), getWidth() - 50, getHeight() - 20);
		g.drawString("" + round(list.getMinY()), 10, getHeight() - 50);
		g.drawString("Y " + round(list.getMaxY()), 10, 20);
		
		if(list.getMinX() < 0 && list.getMaxX() > 0){
			g.drawLine(translateX(0), 5, translateX(0), getHeight() - 5);
			g.drawString("0", translateX(0), 5);
		}
		
		if(list.getMinY() < 0 && list.getMaxY() > 0){
			g.drawLine(5, translateY(0), getWidth() - 5, translateY(0));
			g.drawString("0", 10, translateY(0) - 5);
		}
		
		for(Graph graph : list.getGraphs()){
			g.setColor(graph.getColor());
		
			for(int index = 1; index < graph.getX().length; index++){
				g.drawLine(translateX(graph.getX()[index - 1]), translateY(graph.getY()[index - 1]),
						translateX(graph.getX()[index]), translateY(graph.getY()[index]));
			}
		}
	}
	
	private int translateX(double x){
		return 10 + (int) ((getWidth() - 20) / ((list.getMaxX() - list.getMinX()) / (x - list.getMinX())));
	}
	
	private int translateY(double y){
		return 10 + (int)
				(getHeight() - 20 - ((getHeight() - 20) /
						((list.getMaxY() - list.getMinY()) / (y - list.getMinY()))));
	}
	
	private double round(double a){
		return Math.round(a * 100.0) / 100.0;
	}

}
