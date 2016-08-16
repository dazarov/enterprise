package com.daniel.blog;


import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages={"com.daniel.blog.repositories"})
public class PersistenceConfig {

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/PhotoBlog");
        dataSource.setUsername("testUser");
        dataSource.setPassword("testPassword");

        return dataSource;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
     
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        
        sessionBuilder.scanPackages("com.daniel.blog.model");
        
        sessionBuilder.setProperty("hibernate.current_session_context_class", "thread");
        sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionBuilder.setProperty("hibernate.show_sql", "true");
     
        return sessionBuilder.buildSessionFactory();
    }
    
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//
//      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//      vendorAdapter.setGenerateDdl(true);
//
//      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//      factory.setJpaVendorAdapter(vendorAdapter);
//      factory.setPackagesToScan("com.daniel.blog.model");
//      factory.setDataSource(dataSource());
//      factory.afterPropertiesSet();
//
//      return factory.getObject();
//    }
    
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//
//      JpaTransactionManager txManager = new JpaTransactionManager();
//      txManager.setEntityManagerFactory(entityManagerFactory());
//      return txManager;
//    }

 }