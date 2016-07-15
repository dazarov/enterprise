package com.daniel.blog.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Posts")
public class Post extends BlogEntry{
	
	
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
	
	private List<Photo> photos;
	
	
	
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
}
