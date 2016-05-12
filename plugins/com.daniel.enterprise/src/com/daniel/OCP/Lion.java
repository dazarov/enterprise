package com.daniel.OCP;

public class Lion {
	int id;
	
	public Lion(int id){
		this.id = id;
	}
	
	public boolean equals(Lion other){
		System.out.println("Lion check for equality...");
		return this.id == other.id;
	}
	
	@Override
	public boolean equals(Object obj){
		System.out.println("Object check for equality...");
		if(!(obj instanceof Lion)) return false;
		Lion other = (Lion)obj;
		return this.id == other.id;
	}
	
	public static void main(String...strings){
		Lion lion1 = new Lion(1);
		Lion lion2 = new Lion(2);
		
		System.out.println(lion1.equals(lion2));
		
		Object lion3 = lion1;
		
		System.out.println(lion3.equals(lion2));
	}
}
