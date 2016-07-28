package com.daniel.algorithms;

public class FixedArrayStack<E> {
	private E[] a;
	private int n;
	
	@SuppressWarnings("unchecked")
	public FixedArrayStack(int size){
		a = (E[]) new Object[size];
	}
	
	public boolean isEmpty(){
		return n == 0;
	}
	
	public int size(){
		return n;
	}
	
	public void push(E item){
		if(n < a.length-1){
			a[n++] = item;
		}
	}
	
	public E pop(){
		if(n == 0)
			return null;
		E item = a[--n];
		a[n] = null;
		return item;
	}
}
