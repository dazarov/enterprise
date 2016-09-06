package com.example.model;

import java.util.Objects;

//@Entity
public class Country {
	private Long id;
	
	private String name;
	
	public Country(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return "Country "+id+" "+name;
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Country)){
			return false;
		}
		Country that = (Country)obj;
		return this.id == that.id;
	}
}
