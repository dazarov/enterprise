package com.daniel.blog.dto;

import com.daniel.blog.model.Comment;

public class CommentDTO {
	private String body;
	
	public CommentDTO(String body){
		this.body = body;
	}
	
	public CommentDTO(Comment comment){
		this.body = comment.getBody();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
