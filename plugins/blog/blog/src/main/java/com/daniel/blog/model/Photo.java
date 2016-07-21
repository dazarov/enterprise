package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PHOTO")
public class Photo extends CommentableBlogEntry{
	
	// Fields
	
	@Column(name="DESCRIPTION_EN")
	private String descriptionEn;

	@Column(name="DESCRIPTION_RU")
	private String descriptionRu;
	
	@Column(name="IMAGE")
	@Lob
	private int[] image;
	
	@ManyToOne
    @JoinColumn(name="USER_ID", nullable=true)
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	// Methods
	
	public void setDescriptionEn(String descriptionEn){
		this.descriptionEn = descriptionEn;
	}

	public void setDescriptionRu(String descriptionRu){
		this.descriptionRu = descriptionRu;
	}
	
	public void setImage(int[] image){
		this.image = image;
	}
	
	public int[] getImage(){
		return image;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public List<Post> getPosts(){
		return posts;
	}
}
