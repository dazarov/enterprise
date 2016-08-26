package com.daniel.blog.requests.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.requests.BlogRequest;

public class BlogRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BlogRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
	}

}
