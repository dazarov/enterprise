package com.daniel.blog.model;

public enum Status {
	// Statuses for Posts, Photos and Comments
	ENTRY_REMOVED(0),
	ENTRY_NOTPUBLISHED(1),
	ENTRY_PUBLIC(2),
	ENTRY_PRIVATE(3),
	ENTRY_NOT_VISIBLE(4),
	
	// Statuses for Users
	USER_REMOVED(105),
	USER_ACTIVE(106),
	USER_BLOCKED(107),
	
	// Statuses for Blogs
	BLOG_REMOVED(505),
	BLOG_ACTIVE(506),
	BLOG_BLOCKED(507)
	
	;

	private int id;
	
	Status(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public static Status byId(int id){
		for(Status s: Status.values()){
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
}
