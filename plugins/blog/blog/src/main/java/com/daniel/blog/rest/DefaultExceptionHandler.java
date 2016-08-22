package com.daniel.blog.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daniel.blog.errors.PhotoBlogError;
import com.daniel.blog.errors.PhotoBlogException;

@Controller
public class DefaultExceptionHandler {
	
	/**
	 * General purpose exception handler. Returns HTTP 500
	 */
	@ExceptionHandler(value={Exception.class})
	@ResponseBody
	public ResponseEntity<PhotoBlogError> handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PhotoBlogError error = new PhotoBlogError(1, exception);
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
	      
	}
	
	/**
	 * General purpose exception handler. Returns HTTP 500
	 */
	@ExceptionHandler(value={PhotoBlogException.class})
	@ResponseBody
	public ResponseEntity<PhotoBlogError> handlePhotoBlogException(PhotoBlogException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PhotoBlogError error = new PhotoBlogError(exception);
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
	      
	}
	
	/**
	* Wrong input parameters. Return Http 400 
	*/
	@ExceptionHandler(value={IllegalArgumentException.class})
	@ResponseBody
	public ResponseEntity<PhotoBlogError> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PhotoBlogError error = new PhotoBlogError(2, exception);
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); 
	      
	}

}
