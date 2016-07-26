package com.daniel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniel.blog.model.Comment;

@Component
public class CommentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public void save(Comment photo) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(photo);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> list(long blogEntryId, int start, int number) {
		Session session = this.sessionFactory.openSession();
		Query<Comment> query = session.createQuery("select c from Comment c where c.blogEntry.id = :blogEntryId")
				.setParameter("blogEntryId", blogEntryId);
		
		query.setFirstResult(start);
		query.setMaxResults(number);
		List<Comment> postList = query.getResultList();
		session.close();
		return postList;
	}

	public Comment getComment(long id){
		Session session = this.sessionFactory.openSession();
		Comment comment = null;
		try{
			comment = (Comment)session.get(Comment.class, id);
		}catch (Exception e){
			e.printStackTrace();
		}
		session.close();
		return comment;
	}
	
	public boolean update(Comment c){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Comment comment = null;
		try{
			comment = (Comment)session.get(Comment.class, c.getId());
		}catch (Exception e){
			e.printStackTrace();
		}
		if(comment == null){
			session.getTransaction().rollback();
			session.close();
			return false;
		}
		
		comment.setUser(c.getUser());
		
		comment.setCreationTime(c.getCreationTime());
		
		comment.setVisited(c.getVisited());
		
		comment.setStatus(c.getStatus());
		
		comment.setLanguage(c.getLanguage());
		
		comment.setBody(c.getBody());

        
        session.getTransaction().commit();
		
		session.close();
		return false;
	}
	
	public boolean delete(long id){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Comment comment = (Comment)session.load(Comment.class, id);
		if(comment == null){
			session.getTransaction().rollback();
			session.close();
			return false;
		}
		session.delete(comment);
		System.out.println("Deleted Successfully");
		session.getTransaction().commit();
		session.close();
		return true;
	}
}
