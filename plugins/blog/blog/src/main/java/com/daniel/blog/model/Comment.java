package com.daniel.blog.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT")
public class Comment extends CommentableBlogEntry{
	
	// Fields
	
	@ManyToOne
    @JoinColumn(name="USER_ID", nullable=true)
	private User user;
	
	@ManyToOne
    @JoinColumn(name="POST_ID", nullable=true)
	private Post post;
	
	@ManyToOne
    @JoinColumn(name="PHOTO_ID", nullable=true)
	private Photo photo;
	
	@ManyToOne
    @JoinColumn(name="COMMENT_ID", nullable=true)
	private Comment parentComment;
	
	// Methods
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setPost(Post post){
		this.post = post;
	}
	
	public Post getPost(){
		return post;
	}
	
	public void setPhoto(Photo photo){
		this.photo = photo;
	}
	
	public Photo getPhoto(){
		return photo;
	}
	
	public void setParentComment(Comment parentComment){
		this.parentComment = parentComment;
	}
	
	public Comment getParentComment(){
		return parentComment;
	}
	
	public CommentableBlogEntry getParent(){
		if(post != null){
			return post;
		}else if(photo != null){
			return photo;
		}else if(parentComment != null){
			return parentComment;
		}
		return null;
	}
	
}
