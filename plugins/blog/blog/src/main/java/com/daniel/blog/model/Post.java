package com.daniel.blog.model;

import java.util.Set;
import java.util.TreeSet;

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

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.model.converters.CommentAllowanceConverter;

@Entity
@Table(name="POST")
public class Post extends CommentableBlogEntry{
	
	// Fields
	
	@Column(name="SUBJECT")
	@Length(max = PhotoBlogConstants.MAX_POST_SUBJECT_LENGTH)
	private String subject;
	
	@Column(name="DESCRIPTION")
	@Length(max = PhotoBlogConstants.MAX_POST_DESCRIPTION_LENGTH)
	private String description;
	
	@Column(name="BODY")
	@Length(max = PhotoBlogConstants.MAX_POST_BODY_LENGTH)
	private String body;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "POST_IMAGE",
		joinColumns = {@JoinColumn(name="POST_ID")},
		inverseJoinColumns = {@JoinColumn(name="IMAGE_ID")}
	)
	private Set<PhotoImage> images = new TreeSet<>();
	
	// Methods
	
	
	public String getSubject(){
		return subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}

	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public String getBody(){
		return body;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	

	public Set<PhotoImage> getImages(){
		return images;
	}
	
	@Override
	public String toString(){
		return "Post [Subject:"+subject+", Description:"+description+", Body:"+body+"]";
	}
	
}
