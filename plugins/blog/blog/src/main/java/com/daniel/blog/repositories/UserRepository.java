package com.daniel.blog.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.daniel.blog.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

}
