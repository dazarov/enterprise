package com.daniel.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.daniel.blog.model.Post;

public class PostDAO{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public void save(Post post) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(post);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Post> list(int start, int limit) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from POSTS");
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Post> postList = query.list();
		session.close();
		return postList;
	}
	
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
		post.setSubjectEn(p.getSubjectEn());
        post.setSubjectRu(p.getSubjectRu());

        post.setShortEn(p.getShortEn());
        post.setShortRu(p.getShortRu());

        post.setBodyEn(p.getBodyEn());
        post.setBodyRu(p.getBodyRu());
        
        session.getTransaction().commit();
		
		session.close();
		return false;
	}
	
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

}
