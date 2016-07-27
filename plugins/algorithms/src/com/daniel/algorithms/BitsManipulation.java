package com.daniel.algorithms;

public class BitsManipulation {
	
	static long swapBits(long x, int i, int j){
		if(((x >> i) & 1) != ((x >> j) & 1)){
			long bitMask = (1l << i) | (1l << j);
			x ^= bitMask;
		}
		return x;
	}
	
	static short getParity(long x){
		x ^= x >> 32;
		x ^= x >> 16;
		x ^= x >> 8;
		x ^= x >> 4;
		x ^= x >> 2;
		x ^= x >> 1;
		return (short)(x & 0x1);
	}
	
	static short getParity2(long x){
		return (short)(x & 0x1);
	}

	public static void main(String[] args){
		
		long x = 0b01010101;
		System.out.println("before x - "+Long.toBinaryString(x));
		x = swapBits(x, 0, 1);
		
		System.out.println(" after x - "+Long.toBinaryString(x));
		
		x = 0b01010101;
		System.out.println("x - "+Long.toBinaryString(x));
		System.out.println("x - "+x);
		
		long parity = getParity(x);
		
		System.out.println("parity - "+Long.toBinaryString(parity));
		
		parity = getParity2(x);
		
		System.out.println("parity2 - "+Long.toBinaryString(parity));
		
		x = 0b01010100;
		System.out.println("x - "+Long.toBinaryString(x));
		System.out.println("x - "+x);
		
		parity = getParity(x);
		
		System.out.println("parity - "+Long.toBinaryString(parity));

		parity = getParity2(x);
		
		System.out.println("parity2 - "+Long.toBinaryString(parity));
		
		x = 0b00000000;
		System.out.println("x - "+Long.toBinaryString(x));
		System.out.println("x - "+x);
		
		parity = getParity(x);
		
		System.out.println("parity - "+Long.toBinaryString(parity));

		parity = getParity2(x);
		
		System.out.println("parity2 - "+Long.toBinaryString(parity));
		
		x = 0b00000001;
		System.out.println("x - "+Long.toBinaryString(x));
		System.out.println("x - "+x);
		
		parity = getParity(x);
		
		System.out.println("parity - "+Long.toBinaryString(parity));

		parity = getParity2(x);
		
		System.out.println("parity2 - "+Long.toBinaryString(parity));
		
		x = 0b11111111;
		System.out.println("x - "+Long.toBinaryString(x));
		System.out.println("x - "+x);
		
		parity = getParity(x);
		
		System.out.println("parity - "+Long.toBinaryString(parity));

		parity = getParity2(x);
		
		System.out.println("parity2 - "+Long.toBinaryString(parity));
		
		x = 0b11111110;
		System.out.println("x - "+Long.toBinaryString(x));
		System.out.println("x - "+x);
		
		parity = getParity(x);
		
		System.out.println("parity - "+Long.toBinaryString(parity));

		parity = getParity2(x);
		
		System.out.println("parity2 - "+Long.toBinaryString(parity));

	}
}
