package com.daniel.utils.mathvisual;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class IntervalAnalyzer {
	private static final int NUMBER_OF_INTERVALS = 9;
	private static final double TRESHOLD = 100;

	public static void analyze(GraphList list) {
		double min = 0, max = 0;
		MinMax minmax;
		for(Graph graph : list.getGraphs()){
			minmax = analize(graph);
			if(minmax.min < min){
				min = minmax.min;
			}
			if(minmax.max > max){
				max = minmax.max;
			}
		}
		list.setMinY(min);
		list.setMaxY(max);
	}
	
	private static MinMax analize(Graph graph) {
		double min = 0, max = 0;
		double[] mins = new double[NUMBER_OF_INTERVALS];
		double[] maxs = new double[NUMBER_OF_INTERVALS];
		
		int number = graph.getX().length;
		int start = 0, end = number / NUMBER_OF_INTERVALS;
		
		for(int i = 0; i < NUMBER_OF_INTERVALS; i++){
			double[] y = Arrays.copyOfRange(graph.getX(), start, end);
			mins[i] = DoubleStream.of(y).min().getAsDouble();
			maxs[i] = DoubleStream.of(y).max().getAsDouble();
			start += number / NUMBER_OF_INTERVALS + 1;
			end += number / NUMBER_OF_INTERVALS;
		}
		
		int selected = -1;
		
		for(int i = 0; i < NUMBER_OF_INTERVALS; i++){
			int s = i;
			double aMin = DoubleStream.of(mins).filter(d -> !(d == mins[s])).min().getAsDouble();
			double aMax = DoubleStream.of(maxs).filter(d -> !(d == maxs[s])).max().getAsDouble();
			if(maxs[i]/TRESHOLD > aMax){
				selected = i;
				break;
			}
			if(mins[i]/TRESHOLD < aMin){
				selected = i;
				break;
			}
		}
		
		final int sel = selected;
		
		min = DoubleStream.of(mins).filter(d -> sel < 0 || !(d == mins[sel])).min().getAsDouble();
		max = DoubleStream.of(maxs).filter(d -> sel < 0 || !(d == maxs[sel])).max().getAsDouble();
		
		return new MinMax(min, max);
	}

	static class MinMax{
		double min;
		double max;
		
		public MinMax(double min, double max){
			this.min = min;
			this.max = max;
		}
	}

}
