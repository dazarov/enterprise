package com.daniel.blog.errors;

public class CommentsNotAllowedException extends PhotoBlogException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public CommentsNotAllowedException(String message) {
		super(COMMENTS_NOT_ALLOWED, message);
	}
}
