package com.daniel.blog.dto;

import com.daniel.blog.model.Blog;

public class BlogDTO {
	private String name;
	
	public BlogDTO(String name){
		this.name = name;
	}
	
	public BlogDTO(Blog blog){
		this.name = blog.getName();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
