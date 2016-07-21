package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User extends AbstractEntity{
	
	// Fields
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Photo> photos = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	// Methods
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public List<Post> getPosts(){
		return posts;
	}
	
	public List<Photo> getPhotos(){
		return photos;
	}

	public List<Comment> getComments(){
		return comments;
	}
	
}
