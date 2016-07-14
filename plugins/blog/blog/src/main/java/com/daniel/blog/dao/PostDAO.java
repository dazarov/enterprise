package com.daniel.blog.dao;

import java.util.List;

import com.daniel.blog.model.Post;

public interface PostDAO {
	void save(Post post);
	List<Post> list();
}
