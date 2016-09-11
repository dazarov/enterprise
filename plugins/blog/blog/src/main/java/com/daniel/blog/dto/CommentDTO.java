package com.daniel.blog.dto;

public class CommentDTO extends CommentableDTO{
	private String body;
	
	public CommentDTO(String body){
		this.body = body;
	}
	
	public CommentDTO(){
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
