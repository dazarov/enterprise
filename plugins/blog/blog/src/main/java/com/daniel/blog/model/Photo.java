package com.daniel.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PHOTO")
public class Photo extends CommentableBlogEntry{
	
	// Fields
	
	@Column(name="LOCATION")
	private String location;

	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne
    @JoinColumn(name="USER_ID", nullable=true)
	private User user;

	@ManyToOne
    @JoinColumn(name="IMAGE_ID", nullable=true)
	private PhotoImage photoImage;
	
	// Methods
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public String getLocation(){
		return location;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setImage(PhotoImage image){
		this.photoImage = image;
	}

	public PhotoImage getImage(){
		return photoImage;
	}
	
}
