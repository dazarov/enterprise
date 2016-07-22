package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PHOTO_IMAGE")
public class PhotoImage extends AbstractEntity{
	
	// Fields
	
	@Column(name="IMAGE")
	@Lob
	private int[] image;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Photo> photos = new ArrayList<>();
	
	// Methods

	public void setImage(int[] image){
		this.image = image;
	}
	
	public int[] getImage(){
		return image;
	}
	
	public List<Post> getPosts(){
		return posts;
	}
	
	public List<Photo> getPhotos(){
		return photos;
	}
	
}
