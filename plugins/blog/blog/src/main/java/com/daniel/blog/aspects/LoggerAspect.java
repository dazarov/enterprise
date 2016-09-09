package com.daniel.blog.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class LoggerAspect {
	
	@Autowired
	private Logger logger;
	
	@Before("@annotation(com.daniel.blog.annotations.Loggable)")
	public void loggingAdvice(JoinPoint joinPoint){
		logger.info("#### Before "+joinPoint.toString()+" Arguments: "+Arrays.toString(joinPoint.getArgs()));
	}
}
