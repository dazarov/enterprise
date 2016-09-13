package com.daniel.blog.services;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.daniel.blog.annotations.Loggable;
import com.daniel.blog.errors.PhotoBlogException;
import com.daniel.blog.model.Blog;

@Service
public class PhotoBlogPublisherService {
	@Autowired
	private Logger logger;
	
	@Autowired
    private PhotoBlogService blogService;
	
	@Loggable
	//@Scheduled(fixedRate = 5000) // for test
	@Scheduled(fixedRate = 3600000) // run each hour
	public void doSchedual(){
		logger.info("Publisher started");
		try {
			List<Blog> allBlogs = blogService.getAllBlogs();
		} catch (PhotoBlogException e1) {
			logger.trace("an exception was thrown", e1);
		}
		try {
			
			Thread.currentThread().sleep(10000); // simulate the work
		} catch (InterruptedException e) {
			logger.trace("an exception was thrown", e);
		}
		logger.info("Publisher stoped");
	}
}
