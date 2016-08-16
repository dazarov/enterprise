package com.daniel.blog.rest;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.daniel.blog.model.validators.PhotoRequestValidator;

public class PhotosRestController {
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PhotoRequestValidator());
    }
}
