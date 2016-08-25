package com.daniel.blog.requests;

public class BlogRequest {
	private String name;
	
	public BlogRequest(String name){
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
