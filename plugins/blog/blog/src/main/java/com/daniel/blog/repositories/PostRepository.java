package com.daniel.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>{
	
	//@Query("select p from Post p where p.blog = ?1 and p.status = ")
	List<Post> findByBlog(Blog blog, Pageable pageble);
}
