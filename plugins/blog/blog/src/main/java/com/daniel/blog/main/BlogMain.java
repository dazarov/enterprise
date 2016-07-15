package com.daniel.blog.main;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daniel.blog.dao.PostDAO;
import com.daniel.blog.model.Post;

public class BlogMain {
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		PostDAO postDAO = context.getBean(PostDAO.class);
		
		Post post = new Post();
		
		postDAO.save(post);
		
		System.out.println("Post::"+post);
		
		List<Post> list = postDAO.list(1,1);
		
		for(Post p : list){
			System.out.println("Post List::"+p);
		}
		
		context.close();
	}
}
