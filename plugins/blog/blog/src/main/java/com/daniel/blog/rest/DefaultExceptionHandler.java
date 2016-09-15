package com.daniel.blog.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.daniel.blog.annotations.Loggable;
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.errors.PhotoBlogErrorInfo;
import com.daniel.blog.errors.PhotoBlogException;

@ControllerAdvice
public class DefaultExceptionHandler {
	@Autowired
	private Logger logger;
	
	/**
	 * General purpose exception handler. Returns HTTP 500
	 */
	@Loggable
	@ExceptionHandler(value={Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public PhotoBlogErrorInfo handleException(Exception exception, HttpServletRequest request) throws IOException {
		deal(exception);
		return new PhotoBlogErrorInfo(PhotoBlogException.EXCEPTION_ID, request.getRequestURL().toString(), exception);
	}
	
	@Loggable
	@ExceptionHandler(value={PhotoBlogException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public PhotoBlogErrorInfo handlePhotoBlogException(PhotoBlogException exception, HttpServletRequest request) throws IOException {
		deal(exception);
		return new PhotoBlogErrorInfo(request.getRequestURL().toString(), exception);
	}

	@Loggable
	@ExceptionHandler(value={BlogEntityNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public PhotoBlogErrorInfo handleBlogEntityNotFoundException(BlogEntityNotFoundException exception, HttpServletRequest request) throws IOException {
		deal(exception);
		return new PhotoBlogErrorInfo(request.getRequestURL().toString(), exception);
	}
	
	/**
	* Wrong input parameters. Return Http 400 
	*/
	@Loggable
	@ExceptionHandler(value={IllegalArgumentException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public PhotoBlogErrorInfo handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) throws IOException {
		deal(exception);
		return new PhotoBlogErrorInfo(PhotoBlogException.ILLEGAL_ARGUMENT_ID, request.getRequestURL().toString(), exception);
	}
	
	private void deal(Exception ex){
		logger.error(ex.getMessage(), ex);
	}

}
