package com.daniel.blog.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.daniel.blog.model.Blog;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long>{
	Blog findOneByName(String name);
}
