package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.daniel.blog.model.converters.LanguageConverter;

@MappedSuperclass
public abstract class CommentableBlogEntry extends AbstractEntity {
	
	// Fields
	
	@OneToMany(fetch = FetchType.LAZY)
	List<Comment> comments = new ArrayList<>();
	
	@Column(name="LANGUAGE")
	@Basic
	@Convert(converter = LanguageConverter.class)
	private Language language;
	
	// Methods
	
	public List<Comment> getComments(){
		return comments;
	}
	
	public void setLanguage(Language language){
		this.language = language;
	}
	
	public Language getLanguage(){
		return language;
	}

}
