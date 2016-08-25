package com.daniel.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.daniel.blog.model.Comment;
import com.daniel.blog.model.CommentableBlogEntry;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>{
	List<Comment> findByBlogEntry(CommentableBlogEntry blogEntry, Pageable pageble);
	
}
