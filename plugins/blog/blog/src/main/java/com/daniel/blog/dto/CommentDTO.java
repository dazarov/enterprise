package com.daniel.blog.dto;

import com.daniel.blog.model.Comment;

public class CommentDTO {
	private long id;
	private String body;
	
	public CommentDTO(String body){
		this.body = body;
	}
	
	public CommentDTO(Comment comment){
		this.id = comment.getId();
		this.body = comment.getBody();
	}
	
	public long getId(){
		return id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
