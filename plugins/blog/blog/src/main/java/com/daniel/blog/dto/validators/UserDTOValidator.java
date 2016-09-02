package com.daniel.blog.dto.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.dto.UserDTO;

public class UserDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
		
		UserDTO user = (UserDTO)target;
		if(user.getName().length() > PhotoBlogConstants.MAX_USER_NAME_LENGTH){
			errors.rejectValue("name", "name.tooLong");
		}
		if(user.getEmail().length() > PhotoBlogConstants.MAX_USER_EMAIL_LENGTH){
			errors.rejectValue("email", "email.tooLong");
		}
		if(user.getPassword().length() > PhotoBlogConstants.MAX_USER_PASSWORD_LENGTH){
			errors.rejectValue("password", "password.tooLong");
		}
	}

}
