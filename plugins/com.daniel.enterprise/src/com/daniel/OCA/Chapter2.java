package com.daniel.OCA;

import java.util.ArrayList;

public class Chapter2 {

	void a(){
		int i = 2;
		i = i++; // i still 2
	}
	
	void arrays(){
		
		// Arrays
		int[] array1;
		int array2[];
		int [] array3 = new int[]{1,2,3,4};
		int array4 [] = {1,2,3,4,5,6}; // anonymous array
		
		int[] array5, array6;
		
		int array7[], not_array;
		
		int[][] array11; // 2D array
		int array12[][]; // 2D array
		int[] array10[]; // 2D array
	}
	
	void arrayLists(){
		ArrayList<String> list = new ArrayList<>();
	}
	
	void wrappers(){
		Integer.parseInt("1");
	}
}
