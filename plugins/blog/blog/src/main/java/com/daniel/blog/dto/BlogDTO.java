package com.daniel.blog.dto;

public class BlogDTO {
	private long id;
	private String name;
	
	public BlogDTO(String name){
		this.name = name;
	}
	
	public BlogDTO(){
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
