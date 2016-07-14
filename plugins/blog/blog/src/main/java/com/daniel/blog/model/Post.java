package com.daniel.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Posts")
public class Post {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="date")
	private LocalDateTime dateTime;
	
	@Column(name="visible")
	private boolean visible;
	
	@Column(name="number_of_visits")
	private long visited;
	
	@Column(name="subject_en")
	private String subjectEn;
	
	@Column(name="subject_ru")
	private String subjectRu;
	
	@Column(name="short_en")
	private String shortEn;
	
	@Column(name="short_ru")
	private String shortRu;
	
	@Column(name="body_en")
	private String bodyEn;
	
	@Column(name="body_ru")
	private String bodyRu;
	
	private List<Photo> photos;
	
	private List<Comment> comments;

	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public LocalDateTime getdateTime(){
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime){
		this.dateTime = dateTime;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
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
