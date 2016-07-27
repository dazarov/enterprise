package com.daniel.OCP;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class CountNumbers extends RecursiveAction{
	private int start;
	private int end;
	public CountNumbers(int start, int end){
		this.start = start;
		this.end = end;
	}

	protected void compute() {
		if(start < 0) return;
		else{
			int middle = start + ((end-start)/2);
			invokeAll(new CountNumbers(start, middle), new CountNumbers(middle, end));
		}
	}
	
	public static void main(String...strings){
		ForkJoinTask<?> task = new CountNumbers(0, 4);
		ForkJoinPool pool = new ForkJoinPool();
		Object result = pool.invoke(task);
	}

}