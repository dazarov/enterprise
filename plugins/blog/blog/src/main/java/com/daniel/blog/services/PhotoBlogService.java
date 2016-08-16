package com.daniel.blog.services;

import java.util.List;

import com.daniel.blog.model.Post;
import com.daniel.blog.requests.PostRequest;

public interface PhotoBlogService {
	public List<Post> getPosts(int offset, int number);
	
	public Post getPost(long id);
	
	public Post createPost(PostRequest postRequest);
	
	public Post updatePost(PostRequest postRequest, long id);

	public Post createPost(Post post);
	
	public Post updatePost(Post post, long id);
	
	public boolean deletePost(long id);
	
}
