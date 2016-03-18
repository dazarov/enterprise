package com.daniel.algorithms;

import java.util.Arrays;

public class Sorting {

	// The algorithm works by comparing each item in the list with the item next
	// to it, and swapping them if required.
	// In other words, the largest element has bubbled to the top of the array.
	// The algorithm repeats this process until it makes a pass all the way
	// through the list without swapping any items.
	// The worst-case runtime complexity is O(n*n).
	public static void bubbleSort(int[] array) {
		for (int i = array.length - 1; i >= 0; i--) {
			for (int j = 1; j <= i; j++) {
				if (array[j - 1] > array[j]) {
					int temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	// The algorithm works by selecting the smallest unsorted item and then
	// swapping it with the item in the next position to be filled.
	// The selection sort works as follows: you look through the entire array
	// for the smallest element,
	// once you find it you swap it (the smallest element) with the first
	// element of the array.
	// Then you look for the smallest element in the remaining array (an array
	// without the first element) and swap it with the second element.
	// Then you look for the smallest element in the remaining array (an array
	// without first and second elements) and swap it with the third element,
	// and so on.
	// The worst-case runtime complexity is O(n*n).
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			int temp = array[i];
			array[i] = array[min];
			array[min] = temp;
		}
	}

	// To sort unordered list of elements, we remove its entries one at a time
	// and then insert each of them into a sorted part (initially empty)
	public static void insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int index = array[i];
			int j = i;
			while (j > 0 && array[j - 1] > index) {
				array[j] = array[j - 1];
				j--;
			}
			array[j] = index;
		}
	}
	
	public static void bucketSort(int[] array){
		// searching for max
		int max=0;
		for(int i = 0; i < array.length; i++){
			if(array[i] > max){
				max = array[i];
			}
		}
		
		// sorting
		int[] bucket = new int[max+1];
		
		Arrays.fill(bucket, 0);
		
		for(int i = 0; i < array.length; i++){
			int index = array[i];
			bucket[index]++;
		}
		
		// getting the resulting array
		int index = 0; // index in array
		for(int i = 0; i < bucket.length; i++){
			for(int j = 0; j < bucket[i]; j++){
				array[index++] = i;
			}
		}
	}
	
	public static void main(String[] args){
		int[] array1 = new int[]{3,11,2,9,1,5};
		
		System.out.println("Array to sort - "+Arrays.toString(array1));
		bubbleSort(array1);
		System.out.println("Sorted array - "+Arrays.toString(array1));

		int[] array2 = new int[]{3,11,2,9,1,5};
		
		System.out.println("Array to sort - "+Arrays.toString(array2));
		selectionSort(array2);
		System.out.println("Sorted array - "+Arrays.toString(array2));

		int[] array3 = new int[]{3,11,2,9,1,5};
		
		System.out.println("Array to sort - "+Arrays.toString(array3));
		insertionSort(array3);
		System.out.println("Sorted array - "+Arrays.toString(array3));

		int[] array4 = new int[]{3,11,2,9,1,5};
		
		System.out.println("Array to sort - "+Arrays.toString(array4));
		bucketSort(array4);
		System.out.println("Sorted array - "+Arrays.toString(array4));
	}
}
