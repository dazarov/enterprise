package com.example.model.dao;

import java.util.List;

import com.example.model.Country;

public interface CountryDAO {
	public void addCountry(Country country);
	public void updateCountry(Country country);
	public List<Country> listCountries();
	public Country getCountryById(long id);
	public void removeCountry(long id);
}
