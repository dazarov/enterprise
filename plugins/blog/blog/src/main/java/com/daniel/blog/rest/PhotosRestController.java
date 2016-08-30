package com.daniel.blog.rest;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.daniel.blog.dto.validators.PhotoDTOValidator;

//GET	/{blog_name}/photos       										- Retrieves a list of photos
//GET	/{blog_name}/photos?page={page_number}							- Retrieves a page of photos
//GET	/photos/{photo_id} 				   								- Retrieves a specific photo

//POST	/{blog_name}/photos      										- Creates a new photo in the blog
//PUT	/photos/{photo_id}			    								- Updates a specific photo (more then one property)
//DELETE /photos/{photo_id}												- Deletes a specific photo

public class PhotosRestController {
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PhotoDTOValidator());
    }
}
