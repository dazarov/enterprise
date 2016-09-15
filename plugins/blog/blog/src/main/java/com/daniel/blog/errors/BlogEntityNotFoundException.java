package com.daniel.blog.errors;

public class BlogEntityNotFoundException extends PhotoBlogException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public BlogEntityNotFoundException(String message) {
		super(ENTITY_NOT_FOUND, message);
	}
	
	public BlogEntityNotFoundException(int id, String message) {
		super(id, message);
	}
}
