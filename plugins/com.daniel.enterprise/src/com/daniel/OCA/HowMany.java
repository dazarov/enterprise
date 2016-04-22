package com.daniel.OCA;

interface Climb {
	boolean isTooHigh(int hight, int limit);
}

public class HowMany{
	public static void main(String[] args){
		check((h, l) -> h > l, 5);
	}
	private static void check(Climb climb, int height){
		if(climb.isTooHigh(height, 10))
			System.out.println("too high");
		else
			System.out.println("ok");
	}
}