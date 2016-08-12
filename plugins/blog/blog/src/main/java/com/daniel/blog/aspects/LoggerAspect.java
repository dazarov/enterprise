package com.daniel.blog.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggerAspect {
	
	@Before("@annotation(com.daniel.blog.annotations.Loggable)")
	public void loggingAdvice(JoinPoint joinPoint){
		System.out.println("#### Before "+joinPoint.toString()+" Arguments: "+Arrays.toString(joinPoint.getArgs()));
	}
}
