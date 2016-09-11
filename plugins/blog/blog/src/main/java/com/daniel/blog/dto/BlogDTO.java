package com.daniel.blog.dto;

public class BlogDTO extends CommentableDTO{
	
	private String name;
	
	public BlogDTO(String name){
		this.name = name;
	}
	
	public BlogDTO(){
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
