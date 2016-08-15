package com.daniel.blog.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.daniel.blog.aspects.LoggerAspect;

@Configuration
@EnableAspectJAutoProxy
public class AOPTestConfiguration {

	@Bean
	public LoggerAspect loggerAspect(){
		return new LoggerAspect();
	}
}
