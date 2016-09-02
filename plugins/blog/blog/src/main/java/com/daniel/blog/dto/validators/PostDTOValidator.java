package com.daniel.blog.dto.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.dto.PostDTO;

public class PostDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PostDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "subject", "subject.empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");
		ValidationUtils.rejectIfEmpty(errors, "body", "body.empty");
		
		PostDTO post = (PostDTO)target;
		if(post.getSubject().length() > PhotoBlogConstants.MAX_POST_SUBJECT_LENGTH){
			errors.rejectValue("subject", "subject.tooLong");
		}
		if(post.getDescription().length() > PhotoBlogConstants.MAX_POST_DESCRIPTION_LENGTH){
			errors.rejectValue("description", "description.tooLong");
		}
		if(post.getBody().length() > PhotoBlogConstants.MAX_POST_BODY_LENGTH){
			errors.rejectValue("body", "body.tooLong");
		}
	}

}
