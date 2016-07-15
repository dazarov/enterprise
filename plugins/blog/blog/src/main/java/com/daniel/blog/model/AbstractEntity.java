package com.daniel.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CREATION_DATE")
	private LocalDateTime dateTime;
	
	@Column(name="STATUS")
	private Status status;
	
	@Column(name="NUMBER_OF_VISITS")
	private long visited;

	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public LocalDateTime getdateTime(){
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime){
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode(){
		if(id == null){
			return super.hashCode();
		}
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(id == null){
			return super.equals(obj);
		}
		if(!(obj instanceof AbstractEntity)){
			return false;
		}
		AbstractEntity other = (AbstractEntity)obj;
		return this.id == other.id;
	}
}
