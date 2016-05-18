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
		
		List<? extends Integer> l45 = new ArrayList<Integer>();
		
		List<? super Integer> l4 = new ArrayList<Integer>();
		
		//l.add(13);
		
	}
}