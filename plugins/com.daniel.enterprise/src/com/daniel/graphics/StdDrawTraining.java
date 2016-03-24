package com.daniel.graphics;

public class StdDrawTraining {
	public static void main(String[] args){
		int N = 100;
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N*N);
		for(int i = 1; i <= N; i++){
			StdDraw.point(i, i);
			StdDraw.point(i, i*i);
			StdDraw.point(i, i*Math.log(i));
		}
	}
}
