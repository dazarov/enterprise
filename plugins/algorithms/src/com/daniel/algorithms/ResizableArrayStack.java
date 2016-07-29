package com.daniel.algorithms;

import java.util.Iterator;

public class ResizableArrayStack<E> implements Iterable<E>{
	private E[] a;
	private int n;
	
	@SuppressWarnings("unchecked")
	public ResizableArrayStack(int size){
		a = (E[]) new Object[size];
	}
	
	public boolean isEmpty(){
		return n == 0;
	}
	
	public int size(){
		return n;
	}
	
	public void push(E item){
		if(n == a.length){
			resize(2*a.length);
		}
		a[n++] = item;
	}
	
	public E pop(){
		if(n == 0)
			return null;
		
		E item = a[--n];
		a[n] = null;
		if(n > 0 && n == a.length/4) resize(a.length/2);
		
		return item;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int max){
		E[] temp = (E[]) new Object[max];
		for(int i = 0; i < n; i++){
			temp[i] = a[i];
		}
		a = temp;
	}
	
	@Override
	public Iterator<E> iterator(){
		return new ReverseIterator();
	}
	
	private class ReverseIterator implements Iterator<E>{
		private int i = n;

		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public E next() {
			return a[--i];
		}
		
	}
}
