package com.daniel.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.daniel.blog.aspects.LoggerAspect;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfiguration {

	@Bean
	public LoggerAspect loggerAspect(){
		return new LoggerAspect();
	}
}
