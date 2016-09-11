package com.daniel.blog.dto;

public abstract class CommentableDTO extends PhotoBlogDTO{
	private String commentAllowance;
	
	public void setCommentAllowance(String commentAllowance){
		this.commentAllowance = commentAllowance;
	}
	
	public String getCommentAllowance(){
		return commentAllowance;
	}

}
