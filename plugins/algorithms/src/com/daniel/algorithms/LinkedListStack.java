package com.daniel.algorithms;

import java.util.Iterator;

public class LinkedListStack<E> implements Iterable<E> {
	private Node first;
	private int n;
	
	private class Node{
		E item;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public void push(E item){
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		n++;
	}
	
	public E pop(){
		E item = first.item;
		first = first.next;
		n--;
		return item;
	}
	
	public Iterator<E> iterator(){
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<E>{
		Node node = first;
		
		@Override
		public boolean hasNext() {
			return node.next != null;
		}

		@Override
		public E next() {
			E item = node.item;
			node = node.next;
			return item;
		}
		
	}
}
