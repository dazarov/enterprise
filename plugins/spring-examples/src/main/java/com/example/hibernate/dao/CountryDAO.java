package com.example.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.hibernate.model.CountryEntity;


public class CountryDAO{
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addCountry(CountryEntity country) {
		try(Session session = sessionFactory.getCurrentSession()){
			Transaction tx = session.beginTransaction();
			session.persist(country);
			tx.commit();
			logger.info("Country saved successfully, Country Details="+country);
		}
	}

	public void updateCountry(CountryEntity country) {
		try(Session session = sessionFactory.getCurrentSession()){
			Transaction tx = session.beginTransaction();
			session.update(country);
			tx.commit();
			logger.info("Country updated successfully, Country Details="+country);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CountryEntity> listCountries() {
		try(Session session = sessionFactory.getCurrentSession()){
			Transaction tx = session.beginTransaction();
			// CountryEntity - class name, not table name
			List<CountryEntity> countryList = session.createQuery("from CountryEntity").getResultList();
			tx.commit();
			countryList.forEach(c -> logger.info("Country List::"+c));
			return countryList;
		}
	}

	public CountryEntity getCountryById(long id) {
		try(Session session = sessionFactory.getCurrentSession()){
			Transaction tx = session.beginTransaction();		
			CountryEntity country = session.load(CountryEntity.class, new Long(id));
			tx.commit();
			logger.info("Country loaded successfully, Country details="+country);
			return country;
		}
	}

	public void removeCountry(long id) {
		try(Session session = sessionFactory.getCurrentSession()){
			Transaction tx = session.beginTransaction();
			CountryEntity country = session.load(CountryEntity.class, new Long(id));
			if(null != country){
				session.delete(country);
			}
			tx.commit();
			logger.info("Country deleted successfully, Country details="+country);
		}
	}


}
