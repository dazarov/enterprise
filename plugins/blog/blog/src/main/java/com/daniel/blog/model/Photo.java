package com.daniel.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="PHOTO")
public class Photo extends CommentableBlogEntry{
	
	// Fields
	
	@Column(name="LOCATION")
	@Length(max = 255)
	private String location;

	@Column(name="DESCRIPTION")
	@Length(max = 255)
	private String description;
	
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
	
	public void setImage(PhotoImage image){
		this.photoImage = image;
	}

	public PhotoImage getImage(){
		return photoImage;
	}
	
	@Override
	public String toString(){
		return "Photo [Description:"+description+", Location:"+location+"]";
	}
}
