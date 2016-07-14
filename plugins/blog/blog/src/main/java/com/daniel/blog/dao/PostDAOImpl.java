package com.daniel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.daniel.blog.model.Post;

public class PostDAOImpl implements PostDAO{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Post post) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(post);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> list() {
		Session session = this.sessionFactory.openSession();
		List<Post> postList = session.createQuery("from Post").list();
		session.close();
		return postList;
	}

}
