package com.example.hibernate;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/CountryDB");
        dataSource.setUsername("testUser");
        dataSource.setPassword("testPassword");
        return dataSource;
    }
    
//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory2(DataSource dataSource) {
//            LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//            
//            sessionBuilder.scanPackages("com.daniel.blog.model");
//            
//            sessionBuilder.setProperty("hibernate.current_session_context_class", "thread");
//            sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//            sessionBuilder.setProperty("hibernate.show_sql", "true");
//         
//            return sessionBuilder.buildSessionFactory();
//    }
    
    @Bean
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
     
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        
    	sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.example.hibernate.model" });
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }
    
    
    private Properties getHibernateProperties(){
    	Properties properties = new Properties();
    	
    	properties.put("hibernate.current_session_context_class", "thread");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.show_sql", "true");
    	
    	return properties;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
 }