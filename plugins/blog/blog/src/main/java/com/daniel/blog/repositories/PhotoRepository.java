package com.daniel.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Photo;

public interface PhotoRepository extends PagingAndSortingRepository<Photo, Long>{
	List<Photo> findByBlog(Blog blog, Pageable pageble);
}
