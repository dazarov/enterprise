package com.daniel.algorithms.sort;

public abstract class MergeSort {
	protected Comparable[] aux;
	
	protected void merge(Comparable[] a, int lo, int mid, int hi){
		int i = lo;
		int j = mid+1;
		
		for(int k = lo; k <= hi; k++){
			aux[k] = a[k];
		}
		
		for(int k = lo; k <= hi; k++){
			
		}
	}
}
