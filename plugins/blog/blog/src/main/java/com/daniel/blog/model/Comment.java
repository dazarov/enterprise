package com.daniel.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT")
public class Comment extends CommentableBlogEntry{
	
	// Fields
	
	@ManyToOne
	private CommentableBlogEntry blogEntry; 
	
	@Column(name="BODY")
	private String body;
	
	
	// Methods
	
	public void setBlogEntry(CommentableBlogEntry blogEntry){
		this.blogEntry = blogEntry;
	}
	
	public CommentableBlogEntry getBlogEntry(){
		return blogEntry;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public String getBody(){
		return body;
	}
}
