package com.daniel.OCP;

import java.util.ArrayList;
import java.util.List;

public final class Animal {
	private final String species;
	private final int age;
	private final List<String> favoriteFoods;
	
	public Animal(String species, int age, List<String> favoriteFood){
		this.species = species;
		this.age = age;
		if(favoriteFood == null){
			throw new RuntimeException("favoriteFood is required");
		}
		this.favoriteFoods = new ArrayList<>(favoriteFood);
	}
	
	public String getSpecies(){
		return species;
	}
	
	public int getAge(){
		return age;
	}
	
	public int getFavoriteFoodCount(){
		return favoriteFoods.size();
	}
	
	public String getFavoriteFood(int index){
		return favoriteFoods.get(index);
	}

}
