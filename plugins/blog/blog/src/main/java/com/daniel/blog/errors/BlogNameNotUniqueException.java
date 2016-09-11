package com.daniel.blog.errors;

public class BlogNameNotUniqueException extends PhotoBlogException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public BlogNameNotUniqueException(String message) {
		super(BLOG_NAME_NOT_UNIQUE, message);
	}
}
