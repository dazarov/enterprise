package com.daniel.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.daniel.blog.model.converters.StatusConverter;

@MappedSuperclass
public abstract class AbstractEntity {
	
	// Fields
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CREATION_TIME")
	private LocalDateTime creationTime;
	
	@Column(name="STATUS")
	@Basic
	@Convert(converter = StatusConverter.class)
	private Status status;
	
	@Column(name="VISITED")
	private long visited;
	
	// Methods
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public LocalDateTime getCreationTime(){
		return creationTime;
	}
	
	public void setCreationTime(LocalDateTime creationTime){
		this.creationTime = creationTime;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setVisited(long visited){
		this.visited = visited;
	}
	
	public long getVisited(){
		return visited;
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
