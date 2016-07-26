package com.daniel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniel.blog.model.User;

@Component
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(){
		Session session = this.sessionFactory.openSession();
		Query<User> query = session.createQuery("from User");
		List<User> userList = query.getResultList();
		session.close();
		return userList;
	}
	
	public User getUser(long id){
		Session session = this.sessionFactory.openSession();
		User user = null;
		try{
			user = (User)session.get(User.class, id);
		}catch (Exception e){
			e.printStackTrace();
		}
		session.close();
		return user;
	}
	
	public boolean isUserExist(User user){
		return false;
	}
	
	public void saveUser(User user){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}
	
	public void updateUser(User user){
		
	}
	
	public void deleteUserById(long id){
		
	}
	
	public void deleteAllUsers(){
		
	}
}
