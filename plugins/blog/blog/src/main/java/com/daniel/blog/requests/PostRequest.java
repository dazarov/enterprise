package com.daniel.blog.requests;

public class PostRequest {
	private String subject;
	
	private String description;
	
	private String body;
	
	public PostRequest(String subject, String description, String body){
		this.subject = subject;
		this.description = description;
		this.body = body;
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
