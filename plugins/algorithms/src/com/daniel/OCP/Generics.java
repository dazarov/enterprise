package com.daniel.OCP;

import java.util.ArrayList;
import java.util.List;

class Aa{
	
}

class Bb extends Aa{
	
}

class Cc extends Bb{
	
}

class Container<T>{
	final private T t;
	
	public Container(T t){
		this.t = t;
	}
	
	public T getElement(){
		return t;
	}
}

public class Generics {
	public static void main(String[] args){
		Container<Aa> container = new Container<>(new Aa());
		
		List<Aa> list = new ArrayList<>();
		list.add(new Aa());
		list.add(new Bb());
		list.add(new Cc());
		
		List<? extends Number> l = new ArrayList<Integer>();
		//l.add(3);
		
		List<? extends Integer> l45 = new ArrayList<Integer>();
		
		List<? super Integer> l4 = new ArrayList<Integer>();
		
		//l.add(13);
		
	}
	
	void printNumbers1(List<? extends Number> list){
		list.forEach(System.out::println);
		
		//list.add(3);
		
	}
	
	void printNumbers2(List<? super Integer> list){
		list.forEach(System.out::println);
		
		list.add(4);
	}
	
	void printNumbers3(List<? extends Integer> list){
		list.forEach(System.out::println);
		
		//list.add(45);
	}
	
	void printNumbers4(List<?> list){
		list.forEach(System.out::println);
		
		//list.add(45);
	}
}