package com.daniel.blog.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.daniel.blog.model.Photo;

public interface PhotoRepository extends PagingAndSortingRepository<Photo, Long>{

}
