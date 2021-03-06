package com.daniel.blog.model;

import java.time.Instant;
import java.time.ZonedDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.daniel.blog.model.converters.StatusConverter;

@MappedSuperclass
public abstract class AbstractEntity implements Comparable<AbstractEntity>{
	
	// Fields
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name="CREATION_TIME")
	private Instant creationTime;
	
	@Column(name="STATUS")
	@Basic
	@Convert(converter = StatusConverter.class)
	private Status status;
	
	// Methods
	
	public Long getId(){
		return id;
	}
	
	public Instant getCreationTime(){
		return creationTime;
	}
	
	public void setCreationTime(Instant creationTime){
		this.creationTime = creationTime;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
	
	public Status getStatus(){
		return status;
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
		if ( obj == null || getClass() != obj.getClass() ) {
            return false;
        }
		AbstractEntity other = (AbstractEntity)obj;
		return this.id == other.id;
	}
	
	@Override
	public int compareTo(AbstractEntity other){
		return (int)(this.id - other.id);
	}
}
