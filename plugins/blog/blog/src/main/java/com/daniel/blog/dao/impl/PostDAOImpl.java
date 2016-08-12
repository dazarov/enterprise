package com.daniel.blog.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.daniel.blog.annotations.Loggable;
import com.daniel.blog.dao.PostDAO;
import com.daniel.blog.model.Post;

@Component
public class PostDAOImpl implements PostDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Loggable
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Loggable
	@Override
	public void save(Post post) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(post);
		tx.commit();
		session.close();
	}

	@Loggable
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> list(int start, int number) {
		Session session = this.sessionFactory.openSession();
		
		Query<Post> query = session.createQuery("FROM Post")
			.setFirstResult(start)
			.setMaxResults(number);
		
		List<Post> postList = query.getResultList();
		session.close();
		return postList;
	}
	
	@Loggable
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> list() {
		Session session = this.sessionFactory.openSession();
		Query<Post> query = session.createQuery("FROM Post");
		List<Post> postList = query.getResultList();
		session.close();
		return postList;
	}

	@Loggable
	@Override
	public Post getPost(long id){
		Session session = this.sessionFactory.openSession();
		Post post = null;
		try{
			post = (Post)session.get(Post.class, id);
		}catch (Exception e){
			e.printStackTrace();
		}
		session.close();
		return post;
	}
	
	@Loggable
	@Override
	public boolean update(Post p){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Post post = null;
		try{
			post = (Post)session.get(Post.class, p.getId());
		}catch (Exception e){
			e.printStackTrace();
		}
		if(post == null){
			session.getTransaction().rollback();
			session.close();
			return false;
		}
		
		post.getImages().clear();
		post.getImages().addAll(p.getImages());
		
		post.getComments().clear();
		post.getComments().addAll(p.getComments());
		
		post.setUser(p.getUser());
		
		post.setCreationTime(p.getCreationTime());
		
		post.setVisited(p.getVisited());
		
		post.setStatus(p.getStatus());
		
		post.setLanguage(p.getLanguage());
		
		post.setSubject(p.getSubject());

        post.setDescription(p.getDescription());

        post.setBody(p.getBody());
        
        session.getTransaction().commit();
		
		session.close();
		return false;
	}
	
	@Loggable
	@Override
	public boolean delete(long id){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Post post = (Post)session.load(Post.class, id);
		if(post == null){
			session.getTransaction().rollback();
			session.close();
			return false;
		}
		session.delete(post);
		System.out.println("Deleted Successfully");
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	@Loggable
	@Override
	public boolean delete(Post post){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(post);
		System.out.println("Deleted Successfully");
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	@Loggable
	@Override
	public void deleteAllPosts(){
		List<Post> posts = list();
		for(Post post : posts){
			delete(post);
		}
	}

}
