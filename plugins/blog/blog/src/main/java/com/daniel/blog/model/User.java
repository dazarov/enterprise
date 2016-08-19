package com.daniel.blog.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="USER")
public class User extends AbstractEntity{
	
	// Fields
	@Column(name="LOGGED_IN")
	private Integer logged_id;
	
	@Column(name="NAME")
	@Length(max = 100)
	private String name;
	
	@Column(name="EMAIL")
	@Length(max = 100)
	private String email;
	
	@Column(name="PASSWORD")
	@Length(max = 100)
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<CommentableBlogEntry> blogEntries = new TreeSet<>();
	
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
	
	public Set<CommentableBlogEntry> getBlogEntries(){
		return blogEntries;
	}
	
}
