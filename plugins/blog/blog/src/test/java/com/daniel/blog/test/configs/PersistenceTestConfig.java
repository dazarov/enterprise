package com.daniel.blog.test.configs;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.daniel.blog.dao.PostDAO;
import com.daniel.blog.dao.impl.PostDAOImpl;

@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "net.petrikainulainen.spring.testmvc.todo.repository")
//@ComponentScan({"com.daniel.blog.dao.impl"})
public class PersistenceTestConfig {

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
    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
     
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        
        sessionBuilder.scanPackages("com.daniel.blog.model");
        
        sessionBuilder.setProperty("hibernate.current_session_context_class", "thread");
        sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionBuilder.setProperty("hibernate.show_sql", "true");
     
        return sessionBuilder.buildSessionFactory();
    }
    
    @Bean
    public PostDAO postDAOImpl(){
    	return new PostDAOImpl();
    }

 }