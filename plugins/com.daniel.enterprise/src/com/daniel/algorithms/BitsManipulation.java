package com.daniel.algorithms;

public class BitsManipulation {
	
	static long swapBits(long x, int i, int j){
		if(((x >> i) & 1) != ((x >> j) & 1)){
			long bitMask = (1l << i) | (1l << j);
			x ^= bitMask;
		}
		return x;
	}

	public static void main(String[] args){
		
		long x = 0b01010101;
		System.out.println("before x - "+Long.toBinaryString(x));
		x = swapBits(x, 0, 1);
		
		System.out.println(" after x - "+Long.toBinaryString(x));
	}
}
