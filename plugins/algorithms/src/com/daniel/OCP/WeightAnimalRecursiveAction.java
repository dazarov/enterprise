package com.daniel.OCP;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class WeightAnimalRecursiveAction extends RecursiveAction {
	private int start;
	private int end;
	private Double[] weights;
	
	public WeightAnimalRecursiveAction(Double[] weights, int start, int end) {
		this.start = start;
		this.end = end;
		this.weights = weights;
		//System.out.println("new WeightAnimalRecursiveAction start - "+start+" end - "+end);
	}

	@Override
	protected void compute() {
		if(end-start <= 3){
			for(int i = start; i < end; i++){
				weights[i] = (double)new Random().nextInt(100);
				System.out.println("Animal weighted: "+i);
			}
		}else{
			int middle = start+((end-start)/2);
			System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
			invokeAll(new WeightAnimalRecursiveAction(weights, start, middle),
					new WeightAnimalRecursiveAction(weights, middle, end));
		}
	}
	
	public static void main(String[] args){
		Double[] weights = new Double[100];
		
		ForkJoinTask<?> task = new WeightAnimalRecursiveAction(weights, 0, weights.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);
		
		// Print results
		System.out.println();
		System.out.println("Weights:");
		Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue()+" "));
	}

}
