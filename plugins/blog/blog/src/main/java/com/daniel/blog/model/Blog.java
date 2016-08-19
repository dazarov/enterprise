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

@Entity
@Table(name="BLOG")
public class Blog extends AbstractEntity{
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "USER_BLOG",
		joinColumns = {@JoinColumn(name="BLOG_ID")},
		inverseJoinColumns = {@JoinColumn(name="USER_ID")}
	)
	private Set<User> users = new HashSet<>();
	
	@Column(name="MODERATED")
	private Boolean moderated;
	
	public Set<User> getUsers(){
		return users;
	}

}
