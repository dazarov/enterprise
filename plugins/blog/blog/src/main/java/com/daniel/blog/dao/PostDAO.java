package com.daniel.blog.dao;

import java.util.List;

import com.daniel.blog.model.Post;

public interface PostDAO {
	public void save(Post post);

	public List<Post> list(int start, int number);

	public List<Post> list();
	
	public Post getPost(long id);
	
	public boolean update(Post p);
	
	public boolean delete(long id);
	
	public boolean delete(Post post);
	
	public void deleteAllPosts();

}
