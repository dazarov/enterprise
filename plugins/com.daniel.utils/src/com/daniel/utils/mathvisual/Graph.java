package com.daniel.utils.mathvisual;

import java.awt.Color;

public final class Graph {
	private final Color color;
	private final double[] x;
	private final double[] y;
	
	public Graph(double[] x, double[] y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public double[] getX(){
		return x;
	}
	
	public double[] getY(){
		return y;
	}
	
	public Color getColor(){
		return color;
	}
}
