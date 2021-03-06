package com.daniel.blog.dto;

public class PostDTO extends CommentableDTO{
	private String subject;
	
	private String description;
	
	private String body;
	
	public PostDTO(String subject, String description, String body){
		this.subject = subject;
		this.description = description;
		this.body = body;
	}
	
	public PostDTO(){
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
	
	@Override
	public String toString(){
		return "PostDTO subject: "+subject;
	}
}
