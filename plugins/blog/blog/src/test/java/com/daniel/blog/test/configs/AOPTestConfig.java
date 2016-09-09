package com.daniel.blog.test.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.daniel.blog.aspects.LoggerAspect;

@Configuration
@EnableAspectJAutoProxy
public class AOPTestConfig {

	@Bean
	public LoggerAspect loggerAspect(){
		return new LoggerAspect();
	}
	
	@Bean
	public Logger logger(){
		return LoggerFactory.getLogger("PhotoBlog");
	}
}
