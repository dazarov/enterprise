package com.daniel.blog.errors;

public class BlogNotFoundException extends BlogEntityNotFoundException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlogNotFoundException(long userId){
		super(BLOG_NOT_FOUND, "Blog <id:"+userId+"> not found!");
	}
	
	public BlogNotFoundException(String userName){
		super(BLOG_NOT_FOUND, "Blog <"+userName+"> not found!");
	}

}
