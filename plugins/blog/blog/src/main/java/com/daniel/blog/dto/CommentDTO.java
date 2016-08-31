package com.daniel.blog.dto;

public class CommentDTO {
	private long id;
	private String body;
	
	public CommentDTO(String body){
		this.body = body;
	}
	
	public CommentDTO(){
	}
	
	public void setId(long id){
		this.id = id;
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
