package com.daniel.blog.dto.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.dto.CommentDTO;

public class CommentDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CommentDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "body", "body.empty");
		
		CommentDTO comment = (CommentDTO)target;
		if(comment.getBody().length() > PhotoBlogConstants.MAX_COMMENT_BODY_LENGTH){
			errors.rejectValue("body", "body.tooLong");
		}
	}

}
