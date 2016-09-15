package com.daniel.blog.errors;

public class CommentNotFoundException extends BlogEntityNotFoundException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentNotFoundException(long userId){
		super(COMMENT_NOT_FOUND, "Comment <id:"+userId+"> not found!");
	}
	
	public CommentNotFoundException(String userName){
		super(COMMENT_NOT_FOUND, "Comment <"+userName+"> not found!");
	}

}
