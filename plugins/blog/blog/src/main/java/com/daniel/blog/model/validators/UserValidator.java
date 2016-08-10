package com.daniel.blog.model.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.model.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
	}

}
