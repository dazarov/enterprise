package com.example.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.hibernate.dao.CountryDAO;


@Configuration
@ComponentScan({"com.example.hibernate", "com.example.hibernate.model", "com.example.hibernate.dao"})
@EnableAutoConfiguration
@SpringBootApplication
public class SpringHibernateExampleApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateExampleApplication.class, args);
	}
	
	@Bean
	public CountryDAO countryDAO(){
		return new CountryDAO();
	}
	
	@Bean
	public Logger logger(){
		return LoggerFactory.getLogger(SpringHibernateExampleApplication.class);
	}
}