package com.daniel.blog.services;

import java.util.List;

import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.Post;
import com.daniel.blog.requests.PostRequest;

public interface PhotoBlogService {
	public List<Post> getPostsByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException;
	
	public List<Comment> getCommentsByPostId(Long postId, int pageNUmber);
	
	public Post createPost(String blogName, PostRequest postRequest);
	
	@Deprecated
	public List<Post> getPosts(int offset, int number);
	
	public Post getPost(long id);
	
	@Deprecated
	public Post createPost(PostRequest postRequest);
	
	public Post updatePost(PostRequest postRequest, long id);

	public Post createPost(Post post);
	
	public Post updatePost(Post post, long id);
	
	public boolean deletePost(long id);
	
}
