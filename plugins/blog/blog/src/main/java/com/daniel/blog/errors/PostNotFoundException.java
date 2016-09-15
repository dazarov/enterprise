package com.daniel.blog.errors;

public class PostNotFoundException extends BlogEntityNotFoundException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(long userId){
		super(POST_NOT_FOUND, "Post <id:"+userId+"> not found!");
	}
	
	public PostNotFoundException(String userName){
		super(POST_NOT_FOUND, "Post <"+userName+"> not found!");
	}

}
