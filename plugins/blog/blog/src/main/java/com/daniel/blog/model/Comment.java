package com.daniel.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENTS")
public class Comment extends BlogEntry{
	@Column(name="USER_ID")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="ENTRY_ID", nullable=false)
    public Entity entry;
	
}
