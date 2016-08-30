package com.daniel.blog.dto;

import com.daniel.blog.model.Blog;

public class BlogDTO {
	private long id;
	private String name;
	
	public BlogDTO(String name){
		this.name = name;
	}
	
	public BlogDTO(Blog blog){
		this.name = blog.getName();
		this.id = blog.getId();
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
