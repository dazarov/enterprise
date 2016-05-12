package com.daniel.OCP;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Duck implements Comparable<Duck>{
	private String name;
	private int weight;
	
	public Duck(String name, int weight){
		this.name = name;
		this.weight = weight;
	}
	
	public String getName(){
		return name;
	}
	
	public int getWeight(){
		return weight;
	}
	
	@Override
	public String toString(){
		return name+" ("+weight+")";
	}
	

	@Override
	public int compareTo(Duck o) {
		return name.compareTo(o.name);
	}
	
	public static void main(String... args){
		Comparator<Duck> byWeight = new Comparator<Duck>(){
			@Override
			public int compare(Duck d1, Duck d2){
				return d1.getWeight() - d2.getWeight();
			}
		};
		
		Comparator<Duck> chainig2 = new Comparator<Duck>(){
			@Override
			public int compare(Duck d1, Duck d2){
				Comparator<Duck> c = Comparator.comparing(d -> d.getName());
				c = c.thenComparingInt(d -> d.getWeight());
				return c.compare(d1, d2);
			}
		};
		
		Comparator<Duck> chainig = new Comparator<Duck>(){
			@Override
			public int compare(Duck d1, Duck d2){
				return Comparator.comparing((Duck d) -> d.getName())
						.thenComparingInt(d -> d.getWeight())
						.compare(d1, d2);
			}
		};
		
		List<Duck> ducks = Arrays.asList(new Duck("A", 10), new Duck("B", 20), new Duck("C", 4), new Duck("A", 50));
		Collections.sort(ducks, byWeight);
		System.out.println("Ducks - "+ducks);
		
		Collections.sort(ducks, (d1, d2) -> d1.getWeight() - d2.getWeight());
		System.out.println("Ducks - "+ducks);
		
		Collections.sort(ducks, (d1, d2) -> d2.getWeight() - d1.getWeight());
		System.out.println("Ducks - "+ducks);
		
		Collections.sort(ducks, chainig);
		System.out.println("Ducks - "+ducks);
		
		Collections.sort(ducks, (d1, d2) -> Comparator.comparing((Duck d) -> d.getName()).thenComparingInt(d -> d.getWeight()).compare(d1, d2));
		System.out.println("Ducks - "+ducks);
	}

}
