package com.daniel.blog.model.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.model.Photo;

public class PhotoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Photo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "location", "location.empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");
	}

}
