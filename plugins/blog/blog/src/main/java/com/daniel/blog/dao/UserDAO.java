package com.daniel.blog.dao;

import java.util.ArrayList;
import java.util.List;

import com.daniel.blog.model.User;

public class UserDAO {
	public List<User> findAllUsers(){
		return new ArrayList<User>();
	}
	
	public User findById(long id){
		return new User();
	}
	
	public boolean isUserExist(User user){
		return false;
	}
	
	public void saveUser(User user){
		
	}
	
	public void updateUser(User user){
		
	}
	
	public void deleteUserById(long id){
		
	}
	
	public void deleteAllUsers(){
		
	}
}
