package com.daniel.blog.test.configs;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.daniel.blog.services.PhotoBlogService;
import com.daniel.blog.services.PhotoBlogServiceImpl;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.daniel.blog.repositories"})
public class PersistenceTestConfig {

    @Bean
    public DataSource dataSource() {
    	//EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        //return builder.setType(EmbeddedDatabaseType.HSQL).addDefaultScripts().build();
    	BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/PhotoBlog");
        dataSource.setUsername("testUser");
        dataSource.setPassword("testPassword");

        return dataSource;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {

      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
      factory.setPackagesToScan("com.daniel.blog.model");
      factory.setDataSource(dataSource());
      factory.setJpaProperties(hibernateProperties());
      factory.afterPropertiesSet();

      return factory.getObject();
    }
    
    private Properties hibernateProperties(){
    	Properties properties = new Properties();
    	
    	properties.put("hibernate.current_session_context_class", "thread");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.show_sql", "true");
    	
    	return properties;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {

      JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(entityManagerFactory());
      return txManager;
    }
    
    @Bean
    public PhotoBlogService photoBlogService(){
    	return new PhotoBlogServiceImpl();
    }

 }