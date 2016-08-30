package com.daniel.blog.requests.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.dto.PhotoDTO;

public class PhotoRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PhotoDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "location", "location.empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");
	}

}
