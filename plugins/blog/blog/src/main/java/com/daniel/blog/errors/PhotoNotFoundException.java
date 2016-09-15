package com.daniel.blog.errors;

public class PhotoNotFoundException extends BlogEntityNotFoundException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhotoNotFoundException(long userId){
		super(PHOTO_NOT_FOUND, "Photo <id:"+userId+"> not found!");
	}
	
	public PhotoNotFoundException(String userName){
		super(PHOTO_NOT_FOUND, "Photo <"+userName+"> not found!");
	}

}
