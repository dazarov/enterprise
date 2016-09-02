package com.daniel.blog.dto.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.dto.BlogDTO;

public class BlogDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BlogDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		
		BlogDTO blog = (BlogDTO)target;
		if(blog.getName().length() > PhotoBlogConstants.MAX_BLOG_NAME_LENGTH){
			errors.rejectValue("name", "name.tooLong");
		}
	}

}
