package com.daniel.blog;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "net.petrikainulainen.spring.testmvc.todo.repository")
public class PersistenceContext {

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

     
//        sessionBuilder.addAnnotatedClasses(AbstractEntity.class);
//        sessionBuilder.addAnnotatedClasses(User.class);
//        sessionBuilder.addAnnotatedClasses(PhotoImage.class);
//        sessionBuilder.addAnnotatedClasses(CommentableBlogEntry.class);
//        sessionBuilder.addAnnotatedClasses(Post.class);
//        sessionBuilder.addAnnotatedClasses(Photo.class);
//        sessionBuilder.addAnnotatedClasses(Comment.class);
        
        return sessionBuilder.buildSessionFactory();
    }

 }