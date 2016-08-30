package com.daniel.blog.dto.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
	}

}
