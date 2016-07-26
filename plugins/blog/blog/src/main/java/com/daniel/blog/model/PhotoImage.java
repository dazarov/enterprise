package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

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
	private List<Photo> photos = new ArrayList<>();
	
	// Methods

	public void setImage(int[] image){
		this.image = image;
	}
	
	public int[] getImage(){
		return image;
	}
	
}
