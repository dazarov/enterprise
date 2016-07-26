package com.daniel.blog.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="IMAGE")
public class PhotoImage extends AbstractEntity{
	
	// Fields
	
	@Column(name="IMAGE")
	@Lob
	private int[] image;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Photo> photos = new TreeSet<>();
	
	// Methods

	public void setImage(int[] image){
		this.image = image;
	}
	
	public int[] getImage(){
		return image;
	}
	
	public Set<Photo> getPhotos(){
		return photos;
	}
	
}
