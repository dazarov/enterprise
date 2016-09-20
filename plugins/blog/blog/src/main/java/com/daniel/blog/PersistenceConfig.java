package com.daniel.blog;

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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.daniel.blog.repositories"})
public class PersistenceConfig {

    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/PhotoBlog?autoReconnect=true&amp;useUnicode=true&amp;createDatabaseIfNotExist=true&amp;characterEncoding=UTF-8");
        dataSource.setUsername("testUser");
        dataSource.setPassword("testPassword");

        return dataSource;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {

    	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true); 
        vendorAdapter.setShowSql(true);
        //if (Arrays.asList(environment.getActiveProfiles()).contains("prod"))
            vendorAdapter.setDatabasePlatform(CustomMysqlDialect.class.getName());

      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      //factory.setJpaVendorAdapter(vendorAdapter);
      factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
      factory.setPackagesToScan("com.daniel.blog.model");
      factory.setDataSource(dataSource());
      factory.setJpaProperties(hibernateProperties());
      factory.afterPropertiesSet();
      factory.setJpaVendorAdapter(vendorAdapter);

      return factory.getObject();
    }
    
    private Properties hibernateProperties(){
    	Properties properties = new Properties();
    	
    	properties.put("hibernate.current_session_context_class", "thread");
    	properties.put("hibernate.dialect", "com.daniel.blog.CustomMysqlDialect");
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	
    	properties.put("hibernate.connection.CharSet", "utf-8");
    	properties.put("hibernate.connection.characterEncoding", "utf-8");
    	properties.put("hibernate.connection.useUnicode", "true");
    	
    	return properties;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {

      JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(entityManagerFactory());
      return txManager;
    }

 }