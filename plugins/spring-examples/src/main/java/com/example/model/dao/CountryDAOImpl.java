package com.example.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Country;


public class CountryDAOImpl implements CountryDAO{
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addCountry(Country country) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(country);
		logger.info("Country saved successfully, Country Details="+country);
	}

	@Override
	public void updateCountry(Country country) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(country);
		logger.info("Country updated successfully, Country Details="+country);
	}

	@Override
	public List<Country> listCountries() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Country> countryList = session.createQuery("from Country").list();
		
		countryList.forEach(c -> logger.info("Country List::"+c));
		
		return countryList;
	}

	@Override
	public Country getCountryById(long id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Country country = (Country) session.load(Country.class, new Long(id));
		logger.info("Country loaded successfully, Country details="+country);
		return country;
	}

	@Override
	public void removeCountry(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Country country = (Country) session.load(Country.class, new Long(id));
		if(null != country){
			session.delete(country);
		}
		logger.info("Country deleted successfully, Country details="+country);
	}


}
