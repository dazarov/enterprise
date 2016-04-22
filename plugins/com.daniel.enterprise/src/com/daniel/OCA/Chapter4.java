package com.daniel.OCA;

import java.util.Arrays;

public class Chapter4 {
	
	public static void main(String[] args){
		//new BR();
		//char value = 'c';
		//do
		//	System.out.print(value++);
		
		//while(value <= 'f');
		
//		int[] nums = new int[]{1, 4, 6};
//		Object p = nums;
//		
//		String letters = "abcde";
//		String answer = letters.substring(2,3);
//		System.out.println(answer);
//		String numbers = "2468";
//		int total = 0;
//		total += numbers.indexOf("6");
		int x = 9;
		long y = x * (long) (++x);
		System.out.println(y);
	}
	
	private static void check(Climb climb, int hight){
		
		if(climb.isTooHigh(hight, 10)){
			System.out.println("too hight");
		}else{
			System.out.println("ok");
		}
	}
}

//interface Climb{
//	boolean isTooHigh(int hight, int limit);
//}

class Rh{
	public Rh(){
		System.out.println("1");
	}
}

class BR extends Rh{
	public BR(int age){
		System.out.println("2");
	}
	
	public BR(){
		this(5);
		System.out.println("3");
	}
}
