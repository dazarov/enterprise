package com.daniel.blog.model;

public enum Status {
	
	Removed(0),
	NotPublished(1),
	Public(2),
	Private(3),
	NotVisible(4);
	
	private int id;
	
	Status(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}
