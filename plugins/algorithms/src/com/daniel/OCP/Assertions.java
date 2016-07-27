package com.daniel.OCP;

public class Assertions {
	public static void main(String[] args){
		int numGuest = -5;
		
		assert numGuest > 0: "Number of guest must be more or equals zero";
		
		System.out.println("Number guests - "+numGuest);
	}
	
	public void a(String...strings){
		int s = v(1,3,4);
	}
	
	private int v(int...is){
		return 0;
	}
}
