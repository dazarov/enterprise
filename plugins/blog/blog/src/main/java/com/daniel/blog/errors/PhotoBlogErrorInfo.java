package com.daniel.blog.errors;

public class PhotoBlogErrorInfo {
	private final int id;
	
	private final String url;
	
	private final String message;
	
	public PhotoBlogErrorInfo(String url, PhotoBlogException exception){
		this.id = exception.getId();
		this.url = url;
		this.message = exception.getLocalizedMessage();
	}
	
	public PhotoBlogErrorInfo(int id, String url, Exception exception){
		this.id = id;
		this.url = url;
		this.message = exception.getLocalizedMessage();
	}
	
	public int getId(){
		return id;
	}
	
	public String getURL(){
		return url;
	}
	
	public String getMessage(){
		return message;
	}
}
