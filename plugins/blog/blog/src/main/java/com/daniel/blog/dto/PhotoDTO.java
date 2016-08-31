package com.daniel.blog.dto;

public class PhotoDTO {
	private long id;
	private String location;
	private String description;
	
	public PhotoDTO(){
		
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
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
}
