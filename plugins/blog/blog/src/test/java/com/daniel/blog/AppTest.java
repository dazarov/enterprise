package com.daniel.blog;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daniel.blog.dao.PostDAO;
import com.daniel.blog.model.Language;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.Status;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testPostDAO() {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		PostDAO personDAO = context.getBean(PostDAO.class);
		
		Post post = new Post();
		post.setCreationTime(LocalDateTime.of(1980, 1, 12, 20, 30));
		post.setStatus(Status.ENTRY_NOTPUBLISHED);
		post.setVisited(256);
		post.setSubject("Subject 1");
		post.setDescription("Description 1");
		post.setBody("Body 1");
		post.setLanguage(Language.ENGLISH);
		
		personDAO.save(post);
		System.out.println("Post::"+post);
		
		post = new Post();
		post.setCreationTime(LocalDateTime.of(2006, 6, 24, 15, 15));
		post.setStatus(Status.ENTRY_PUBLIC);
		post.setVisited(348);
		post.setSubject("Subject 2");
		post.setDescription("Description 2");
		post.setBody("Body 2");
		post.setLanguage(Language.FRENCH);
		
		personDAO.save(post);
		
		System.out.println("Post::"+post);
		
		List<Post> list = personDAO.list(0,100);
		
		for(Post p : list){
			System.out.println("Post List::"+p);
		}
		//close resources
		context.close();	
    }
    
    public void testUserDAO(){
    	
    }
    
    public void testPhotoDAO(){
    	
    }
}
