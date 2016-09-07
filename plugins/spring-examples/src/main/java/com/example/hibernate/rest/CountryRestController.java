package com.example.hibernate.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernate.dao.CountryDAO;
import com.example.hibernate.model.CountryEntity;



@RestController
@RequestMapping(value = "/hibernate/api")
public class CountryRestController {
	
	@Autowired
	private CountryDAO countryDAO;
	
	@RequestMapping(method = RequestMethod.GET, value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<String>> getCountries(){
		
		List<CountryEntity> countries = countryDAO.listCountries();
		
		List<String> names = countries.stream().map(c -> c.getName()).collect(Collectors.toList());
				
        return new ResponseEntity<>(names, HttpStatus.OK);
	}

}
