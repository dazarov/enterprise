package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="POST")
public class Post extends CommentableBlogEntry{
	
	// Fields
	
	@Column(name="SUBJECT_EN")
	private String subjectEn;
	
	@Column(name="SUBJECT_RU")
	private String subjectRu;
	
	@Column(name="SHORT_EN")
	private String shortEn;
	
	@Column(name="SHORT_RU")
	private String shortRu;
	
	@Column(name="BODY_EN")
	private String bodyEn;
	
	@Column(name="BODY_RU")
	private String bodyRu;
	
	@ManyToOne
    @JoinColumn(name="USER_ID", nullable=true)
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "POST_PHOTO",
		joinColumns = {@JoinColumn(name="POST_ID")},
		inverseJoinColumns = {@JoinColumn(name="PHOTO_ID")}
	)
	private List<Photo> photos = new ArrayList<>();
	
	// Methods
	
	public String getSubjectEn(){
		return subjectEn;
	}
	
	public void setSubjectEn(String subjectEn){
		this.subjectEn = subjectEn;
	}

	public String getSubjectRu(){
		return subjectRu;
	}
	
	public void setSubjectRu(String subjectRu){
		this.subjectRu = subjectRu;
	}
	
	public String getShortEn(){
		return shortEn;
	}
	
	public void setShortEn(String shortEn){
		this.shortEn = shortEn;
	}

	public String getShortRu(){
		return shortRu;
	}
	
	public void setShortRu(String shortRu){
		this.shortRu = shortRu;
	}

	public String getBodyEn(){
		return bodyEn;
	}
	
	public void setBodyEn(String bodyEn){
		this.bodyEn = bodyEn;
	}

	public String getBodyRu(){
		return bodyRu;
	}
	
	public void setBodyRu(String bodyRu){
		this.bodyRu = bodyRu;
	}
	
	public List<Photo> getPotos(){
		return photos;
	}
}
