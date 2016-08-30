package com.daniel.blog.dto;

import com.daniel.blog.model.Photo;

public class PhotoDTO {
	private long id;
	private String location;
	private String description;
	
	public PhotoDTO(){
		
	}
	
	public PhotoDTO(Photo photo){
		this.id = photo.getId();
		this.location = photo.getLocation();
		this.description = photo.getDescription();
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
