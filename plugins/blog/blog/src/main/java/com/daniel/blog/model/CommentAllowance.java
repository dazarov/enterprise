package com.daniel.blog.model;

public enum CommentAllowance {

	COMMENTS_NOT_ALLOWED(0),
	COMMENTS_ALLOWED(1),
	COMMENTS_MODERATED(2)

	;

	private int id;
	
	CommentAllowance(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public static CommentAllowance byId(int id){
		for(CommentAllowance a: CommentAllowance.values()){
			if(a.getId() == id){
				return a;
			}
		}
		return null;
	}
}
