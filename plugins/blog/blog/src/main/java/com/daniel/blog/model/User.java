package com.daniel.blog.model;

public class User extends AbstractEntity{
	private String nick;
	
	private String name;
	
	private String passwordHash;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
