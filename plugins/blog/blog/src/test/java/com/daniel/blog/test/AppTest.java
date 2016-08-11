package com.daniel.blog.test;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daniel.blog.dao.PostDAO;
import com.daniel.blog.model.Language;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
public class AppTest {
	
	@Autowired
    private PostDAO postDAO;
	
	public void setPostDAO(PostDAO postDAO){
		this.postDAO = postDAO;
		System.out.println("######################## com.daniel.blog.rest.PostsRestController postDAO SET!");
	}

	@Test
    public void testPostDAO() {
    	//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		//PostDAO postDAO = context.getBean(PostDAO.class);
		
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
		
		Assert.assertEquals("Unexpected number of posts", 2, list.size());
		
		postDAO.delete(post1);
		postDAO.delete(post2);
		
		list = postDAO.list(0,100);
		
		Assert.assertEquals("Unexpected number of posts", 0, list.size());
		
		//close resources
		//context.close();	
    }
    
    public void testUserDAO(){
    	
    }
    
    public void testPhotoDAO(){
    	
    }
}
