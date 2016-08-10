package com.daniel.blog.model.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.model.Comment;

public class CommentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Comment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "body", "body.empty");
	}

}
