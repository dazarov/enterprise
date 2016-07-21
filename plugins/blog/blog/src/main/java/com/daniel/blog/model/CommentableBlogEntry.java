package com.daniel.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class CommentableBlogEntry extends AbstractEntity {
	
	// Fields
	
	@OneToMany(fetch = FetchType.LAZY)
	List<Comment> comments = new ArrayList<>();
	
	// Methods
	
	public List<Comment> getComments(){
		return comments;
	}

}
