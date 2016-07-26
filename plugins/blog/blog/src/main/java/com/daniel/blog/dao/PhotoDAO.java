package com.daniel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniel.blog.model.Photo;

@Component
public class PhotoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public void save(Photo photo) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(photo);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Photo> list(int start, int limit) {
		Session session = this.sessionFactory.openSession();
		Query<Photo> query = session.createQuery("from Photo");
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Photo> postList = query.getResultList();
		session.close();
		return postList;
	}
	
	public Photo getPost(long id){
		Session session = this.sessionFactory.openSession();
		Photo photo = null;
		try{
			photo = (Photo)session.get(Photo.class, id);
		}catch (Exception e){
			e.printStackTrace();
		}
		session.close();
		return photo;
	}
	
	public boolean update(Photo p){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Photo photo = null;
		try{
			photo = (Photo)session.get(Photo.class, p.getId());
		}catch (Exception e){
			e.printStackTrace();
		}
		if(photo == null){
			session.getTransaction().rollback();
			session.close();
			return false;
		}
		
		photo.setImage(p.getImage());
		
		photo.getComments().clear();
		photo.getComments().addAll(p.getComments());
		
		photo.setUser(p.getUser());
		
		photo.setCreationTime(p.getCreationTime());
		
		photo.setVisited(p.getVisited());
		
		photo.setStatus(p.getStatus());
		
		photo.setLanguage(p.getLanguage());
		
		photo.setDescription(p.getDescription());

		photo.setLocation(p.getLocation());
        
        session.getTransaction().commit();
		
		session.close();
		return false;
	}
	
	public boolean delete(long id){
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Photo photo = (Photo)session.load(Photo.class, id);
		if(photo == null){
			session.getTransaction().rollback();
			session.close();
			return false;
		}
		session.delete(photo);
		System.out.println("Deleted Successfully");
		session.getTransaction().commit();
		session.close();
		return true;
	}
}
