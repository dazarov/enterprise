package com.daniel.blog.dto;

public abstract class PhotoBlogDTO {
	private long id;
	private String dateTime;
	private String status;
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}
	
	public String getDateTime(){
		return dateTime;
	}
	
	public void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
}
