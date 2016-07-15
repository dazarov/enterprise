package com.daniel.blog.dao;

import java.util.List;

import org.hibernate.Query;
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
	public List<Post> list(int start, int limit) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from POSTS");
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Post> postList = query.list();
		session.close();
		return postList;
	}

}
