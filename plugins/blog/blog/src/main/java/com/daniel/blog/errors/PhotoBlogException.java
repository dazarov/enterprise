package com.daniel.blog.errors;

public class PhotoBlogException extends Exception{
	public static final int EXCEPTION_ID 			= 100;
	public static final int ILLEGAL_ARGUMENT_ID		= 101;
	
	public static final int ENTITY_NOT_FOUND 		= 200;
	public static final int USER_NOT_FOUND 			= 201;
	public static final int BLOG_NOT_FOUND 			= 202;
	public static final int POST_NOT_FOUND 			= 203;
	public static final int PHOTO_NOT_FOUND			= 204;
	public static final int IMAGE_NOT_FOUND			= 205;
	public static final int COMMENT_NOT_FOUND		= 206;
	
	public static final int COMMENTS_NOT_ALLOWED 	= 301;
	public static final int BLOG_NAME_NOT_UNIQUE 	= 302;

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
