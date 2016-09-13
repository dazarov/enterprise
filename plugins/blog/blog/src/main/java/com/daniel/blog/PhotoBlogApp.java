package com.daniel.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@ComponentScan({"com.daniel.blog", "com.daniel.blog.model", "com.daniel.blog.services"})
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class PhotoBlogApp {


	public static void main(String[] args) {
		SpringApplication.run(PhotoBlogApp.class, args);
	}
	
	@Bean
	public Logger logger(){
		return LoggerFactory.getLogger("PhotoBlog");
	}
}