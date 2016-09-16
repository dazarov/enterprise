package com.daniel.blog.dto;

public class PhotoDTO extends CommentableDTO{
	private String location;
	private String description;
	
	public PhotoDTO(){
		
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return "PhotoDTO description: "+description;
	}
}
