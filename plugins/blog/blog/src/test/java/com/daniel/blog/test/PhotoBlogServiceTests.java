package com.daniel.blog.test;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daniel.blog.model.Language;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.Status;
import com.daniel.blog.services.PhotoBlogService;
import com.daniel.blog.test.configs.AOPTestConfig;
import com.daniel.blog.test.configs.PersistenceTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class, AOPTestConfig.class})
public class PhotoBlogServiceTests {
	
	@Autowired
    private PhotoBlogService blogService;
	
	@Test
    public void testPostRepository() {
		Post post1 = new Post();
		post1.setCreationTime(LocalDateTime.of(1980, 1, 12, 20, 30));
		post1.setStatus(Status.ENTRY_NOTPUBLISHED);
		post1.setVisited(256l);
		post1.setSubject("Subject 1");
		post1.setDescription("Description 1");
		post1.setBody("Body 1");
		post1.setLanguage(Language.ENGLISH);
		
		blogService.createPost(post1);
		
		System.out.println("Post::"+post1);
		
		Post post2 = new Post();
		post2.setCreationTime(LocalDateTime.of(2006, 6, 24, 15, 15));
		post2.setStatus(Status.ENTRY_PUBLIC);
		post2.setVisited(348l);
		post2.setSubject("Subject 2");
		post2.setDescription("Description 2");
		post2.setBody("Body 2");
		post2.setLanguage(Language.FRENCH);
		
		blogService.createPost(post2);
		
		System.out.println("Post::"+post2);
		
		List<Post> list = blogService.getPosts(0,100);
		
		for(Post p : list){
			System.out.println("Post List::"+p);
		}
		
		Assert.assertEquals("Unexpected number of posts", 2, list.size());
		
		blogService.deletePost(post1.getId());
		blogService.deletePost(post2.getId());
		
		list = blogService.getPosts(0,100);
		
		Assert.assertEquals("Unexpected number of posts", 0, list.size());
    }
    
    public void testUserDAO(){
    	
    }
    
    public void testPhotoDAO(){
    	
    }
}
