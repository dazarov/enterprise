package com.daniel.blog.errors;

public class PhotoBlogError {
	private final int id;
	
	private final String message;
	
	public PhotoBlogError(PhotoBlogException exception){
		this.id = exception.getId();
		this.message = exception.getMessage();
	}
	
	public PhotoBlogError(int id, Exception exception){
		this.id = id;
		this.message = exception.getMessage();
	}
	
	public int getId(){
		return id;
	}
	
	public String getMessage(){
		return message;
	}
}
