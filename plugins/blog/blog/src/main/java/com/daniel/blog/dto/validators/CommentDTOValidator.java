package com.daniel.blog.dto.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.dto.CommentDTO;

public class CommentDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CommentDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "body", "body.empty");
	}

}
