package com.daniel.blog.dto;

import com.daniel.blog.model.Post;

public class PostDTO {
	private long id;
	
	private String subject;
	
	private String description;
	
	private String body;
	
	public PostDTO(String subject, String description, String body){
		this.subject = subject;
		this.description = description;
		this.body = body;
	}
	
	public PostDTO(Post post){
		this.id = post.getId();
		this.subject = post.getSubject();
		this.description = post.getDescription();
		this.body = post.getBody();
	}
	
	public long getId(){
		return id;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
