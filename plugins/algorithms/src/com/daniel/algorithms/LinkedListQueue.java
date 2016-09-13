package com.daniel.algorithms;

import java.util.Iterator;


public class LinkedListQueue<E> implements Iterable<E> {
	private Node first;
	private Node last;
	private int n;
	
	private class Node{
		E item;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return n;
	}
	
	public void enqueue(E item){
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()){
			first = last;
		}else{
			oldLast.next = last;
		}
		n++;
	}
	
	public E dequeue(){
		E item = first.item;
		first = first.next;
		if(isEmpty()){
			last = null;
		}
		n--;
		return item;
	}

	@Override
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
