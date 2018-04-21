package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class GraphList {
	private double minX, maxX, minY, maxY;
	private List<Graph> graphs = new ArrayList<Graph>();

	public void addGraph(String formula, double[] x, double[] y, Color color) {
		if (graphs.size() == 0) {
			minX = DoubleStream.of(x).min().getAsDouble();
			maxX = DoubleStream.of(x).max().getAsDouble();
			minY = DoubleStream.of(y).min().getAsDouble();
			maxY = DoubleStream.of(y).max().getAsDouble();
		} else {
			double mnX = DoubleStream.of(x).min().getAsDouble();
			if (mnX < minX) {
				minX = mnX;
			}
			double mxX = DoubleStream.of(x).max().getAsDouble();
			if (mxX > maxX) {
				maxX = mxX;
			}
			double mnY = DoubleStream.of(y).min().getAsDouble();
			if (mnY < minY) {
				minY = mnY;
			}
			double mxY = DoubleStream.of(y).max().getAsDouble();
			if (mxY > maxY) {
				maxY = mxY;
			}
		}
		graphs.add(new Graph(x, y, color));

	}

	public List<Graph> getGraphs() {
		return graphs;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMinX() {
		return minX;
	}

	public double getMaxY() {
		return maxY;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}
}
