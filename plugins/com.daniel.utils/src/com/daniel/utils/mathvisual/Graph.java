package com.daniel.utils.mathvisual;

import java.awt.Color;

public final class Graph {
	private final Color color;
	private final String formula;
	private final double[] x;
	private final double[] y;
	
	public Graph(String formula, double[] x, double[] y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
		this.formula = formula;
	}
	
	public double[] getX(){
		return x;
	}
	
	public double[] getY(){
		return y;
	}
	
	public String getFormula(){
		return formula;
	}
	
	public Color getColor(){
		return color;
	}
}
