package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Configuration implements Serializable {
	private static final long serialVersionUID = 5L;
	private Color backgroundColor = Color.LIGHT_GRAY;
	private Color gridColor = Color.BLACK;
	private Color textColor = Color.BLACK;
	private double start = -100.0;
	private double end = 100.0;
	private double minY = 0.0;
	private double maxY = 0.0;
	private int number = 10_000;
	
	private List<GraphConfiguration> graphs = new ArrayList<>();

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getGridColor() {
		return gridColor;
	}

	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<GraphConfiguration> getGraphs() {
		return graphs;
	}

	public void setGraphs(List<GraphConfiguration> graphs) {
		this.graphs = graphs;
	}

	public List<GraphConfiguration> getGraphConfigs() {
		return graphs;
	}

	public GraphConfiguration addGraph() {
		GraphConfiguration graph = new GraphConfiguration();
		graphs.add(graph);
		return graph;
	}

	public void removeGraph(GraphConfiguration graph) {
		graphs.remove(graph);
	}

	public class GraphConfiguration implements Serializable {
		private static final long serialVersionUID = 5L;
		private boolean visible = true;
		private String formula = "";
		private String comment = "";
		private Color color = Color.RED;

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public boolean isVisible() {
			return visible;
		}

		public void setVisible(boolean visible) {
			this.visible = visible;
		}

		public String getFormula() {
			return formula;
		}

		public void setFormula(String formula) {
			this.formula = formula;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof GraphConfiguration)) {
				return false;
			}
			return ((GraphConfiguration) o).getFormula().equals(this.formula);
		}
	}
}
