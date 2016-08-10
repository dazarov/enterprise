package com.daniel.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="COMMENT")
public class Comment extends CommentableBlogEntry{
	
	// Fields
	
	@ManyToOne
	private CommentableBlogEntry blogEntry; 
	
	@Column(name="BODY")
	@Length(max = 3000)
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
