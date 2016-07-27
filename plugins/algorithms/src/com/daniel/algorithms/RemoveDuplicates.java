package com.daniel.algorithms;

import java.util.Arrays;

public class RemoveDuplicates {
	public static void main(String[] args){
		int[] input1 = {2,5,6,7,7,8,9,10,10,10,14,16,18,18,18};
		
		System.out.println("Array - "+Arrays.toString(input1));
		
		int newLength = removeDuplicates(input1);
		
		System.out.println("Array - "+Arrays.toString(input1));
		System.out.println("New Length - "+newLength);
		
		int[] input2 = {2,5,6,7,7,8,9,10,10,10,14,16,18,18,18};
		
		System.out.println("Array - "+Arrays.toString(input2));
		
		newLength = removeDuplicates2(input2);
		
		System.out.println("Array - "+Arrays.toString(input2));
		System.out.println("New Length - "+newLength);
	}
	
	// O(n^2)
	static int removeDuplicates3(int[] array){
		int times = 0;
		int newLength = array.length;
		for(int i = 0; i < newLength-1; i++){
			times++;
			if(array[i] == array[i+1]){
				newLength--;
				for(int j = i; j < array.length-1; j++){
					times++;
					array[j] = array[j+1];
				}
				i--;
			}
		}
		System.out.println("Times - "+times);
		return newLength;
	}

	// O(n^2)
	static int removeDuplicates2(int[] array){
		int times=0;
		int newLength = 1;
		int j;
		for(int i = 1; i < array.length; i++){
			times++;
			for(j = 0; j < newLength; j++){
				times++;
				if(array[i] == array[j]){
					break;
				}
			}
			if(j == newLength){
				array[newLength++] = array[i];
			}
		}
		System.out.println("Times - "+times);
		return newLength;
	}
	
	// O(n)
	static int removeDuplicates(int[] array){
		if(array.length == 0){
			return 0;
		}
		
		int writeIndex = 1;
		for(int i = 1; i < array.length; i++){
			if(array[writeIndex - 1] != array[i]){
				array[writeIndex++] = array[i];
			}
		}
		return writeIndex;
	}
	
}
