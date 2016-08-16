package com.daniel.blog.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.daniel.blog.model.Comment;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>{

}
