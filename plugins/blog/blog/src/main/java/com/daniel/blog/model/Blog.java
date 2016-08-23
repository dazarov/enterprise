package com.daniel.blog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="BLOG")
public class Blog extends AbstractEntity{
	@Column(name="NAME")
	@Length(max = 100)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "USER_BLOG",
		joinColumns = {@JoinColumn(name="BLOG_ID")},
		inverseJoinColumns = {@JoinColumn(name="USER_ID")}
	)
	private Set<User> users = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "BLOCKED_USERS",
		joinColumns = {@JoinColumn(name="BLOG_ID")},
		inverseJoinColumns = {@JoinColumn(name="USER_ID")}
	)
	private Set<User> blockedUsers = new HashSet<>();
	
	@Column(name="MODERATED")
	private Boolean moderated;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public Set<User> getUsers(){
		return users;
	}

	public Set<User> getBlockedUsers(){
		return blockedUsers;
	}
	
	public boolean isModerated(){
		return moderated;
	}
	
	public void setModerated(boolean moderated){
		this.moderated = moderated;
	}

}
