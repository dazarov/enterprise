package com.daniel.blog.errors;

public class UserNotFoundException extends BlogEntityNotFoundException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(long userId){
		super(USER_NOT_FOUND, "User <id:"+userId+"> not found!");
	}
	
	public UserNotFoundException(String userName){
		super(USER_NOT_FOUND, "User <"+userName+"> not found!");
	}

}
