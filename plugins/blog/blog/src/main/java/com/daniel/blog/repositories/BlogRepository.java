package com.daniel.blog.repositories;

import org.springframework.data.repository.CrudRepository;

import com.daniel.blog.model.Blog;

public interface BlogRepository extends CrudRepository<Blog, Long>{
	Blog findOneByName(String name);
}
