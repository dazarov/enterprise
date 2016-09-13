package com.daniel.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.daniel.blog.PhotoBlogConstants;

@Entity
@Table(name="COMMENT")
public class Comment extends CommentableBlogEntry{
	
	// Fields
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="BLOG_ENTRY_ID")
	private CommentableBlogEntry blogEntry; 
	
	@Column(name="BODY")
	@Length(max = PhotoBlogConstants.MAX_COMMENT_BODY_LENGTH)
	private String body;
	
	// Methods
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
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
