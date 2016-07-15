package com.daniel.blog.model;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class BlogEntry extends AbstractEntity {
	@OneToMany(fetch = FetchType.LAZY, mappedBy="blogentry")
	List<Comment> comments;

}
