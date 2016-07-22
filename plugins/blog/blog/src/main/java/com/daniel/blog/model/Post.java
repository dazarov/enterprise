package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="POST")
public class Post extends CommentableBlogEntry{
	
	// Fields
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="BODY")
	private String body;
	
	@ManyToOne
    @JoinColumn(name="USER_ID", nullable=true)
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "POST_IMAGE",
		joinColumns = {@JoinColumn(name="POST_ID")},
		inverseJoinColumns = {@JoinColumn(name="IMAGE_ID")}
	)
	private List<PhotoImage> images = new ArrayList<>();
	
	// Methods
	
	
	public String getSubject(){
		return subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}

	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public String getBody(){
		return body;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}

	public List<PhotoImage> getImages(){
		return images;
	}
	
}
