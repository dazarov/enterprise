package com.daniel.blog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.daniel.blog.model.converters.CommentAllowanceConverter;

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
	
	// Default value for all commentables in the blog
	@Column(name="COMMENT_ALLOWANCE")
	@Basic
	@Convert(converter = CommentAllowanceConverter.class)
	private CommentAllowance commentAllowance;
	
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
	
	public CommentAllowance getCommentAllowance(){
		return commentAllowance;
	}
	
	public void setCommentAllowance(CommentAllowance commentAllowance){
		this.commentAllowance = commentAllowance;
	}

}
