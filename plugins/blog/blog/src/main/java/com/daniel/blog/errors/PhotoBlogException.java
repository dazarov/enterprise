package com.daniel.blog.errors;

public class PhotoBlogException extends Exception{
	public static final int ENTITY_NOT_FOUND 		= 100;
	public static final int COMMENTS_NOT_ALLOWED 	= 101;
	public static final int BLOG_NAME_NOT_UNIQUE 	= 102;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int id;
	
	public PhotoBlogException(int id, String message){
		super(message);
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
}
