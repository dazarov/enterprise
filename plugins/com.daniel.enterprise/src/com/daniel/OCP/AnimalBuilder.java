package com.daniel.OCP;

import java.util.ArrayList;
import java.util.List;

public class AnimalBuilder {
	private String species;
	private int age;
	private List<String> favoriteFoods;
	
	public AnimalBuilder setAge(int age){
		this.age = age;
		return this;
	}
	
	public AnimalBuilder setSpecies(String species){
		this.species = species;
		return this;
	}
	
	public AnimalBuilder addFavoriteFood(String food){
		if(favoriteFoods == null){
			favoriteFoods = new ArrayList<>();
		}
		favoriteFoods.add(food);
		return this;
	}
	
	public AnimalBuilder setFavoriteFoods(List<String> favoriteFoods){
		this.favoriteFoods = new ArrayList<>(favoriteFoods);
		return this;
	}
	
	public Animal build(){
		return new Animal(species, age, favoriteFoods);
	}
}
