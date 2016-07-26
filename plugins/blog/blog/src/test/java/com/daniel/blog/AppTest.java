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
		
		PostDAO postDAO = context.getBean(PostDAO.class);
		
		//postDAO.deleteAllPosts();
		
		Post post1 = new Post();
		post1.setCreationTime(LocalDateTime.of(1980, 1, 12, 20, 30));
		post1.setStatus(Status.ENTRY_NOTPUBLISHED);
		post1.setVisited(256);
		post1.setSubject("Subject 1");
		post1.setDescription("Description 1");
		post1.setBody("Body 1");
		post1.setLanguage(Language.ENGLISH);
		
		postDAO.save(post1);
		
		System.out.println("Post::"+post1);
		
		Post post2 = new Post();
		post2.setCreationTime(LocalDateTime.of(2006, 6, 24, 15, 15));
		post2.setStatus(Status.ENTRY_PUBLIC);
		post2.setVisited(348);
		post2.setSubject("Subject 2");
		post2.setDescription("Description 2");
		post2.setBody("Body 2");
		post2.setLanguage(Language.FRENCH);
		
		postDAO.save(post2);
		
		System.out.println("Post::"+post2);
		
		List<Post> list = postDAO.list(0,100);
		
		for(Post p : list){
			System.out.println("Post List::"+p);
		}
		
		assertEquals("Unexpected number of posts", 2, list.size());
		
		postDAO.delete(post1);
		postDAO.delete(post2);
		
		list = postDAO.list(0,100);
		
		assertEquals("Unexpected number of posts", 0, list.size());
		
		//close resources
		context.close();	
    }
    
    public void testUserDAO(){
    	
    }
    
    public void testPhotoDAO(){
    	
    }
}
