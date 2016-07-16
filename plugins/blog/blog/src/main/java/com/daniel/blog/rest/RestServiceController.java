package com.daniel.blog.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.blog.dao.PostDAO;
import com.daniel.blog.dao.PostDAOImpl;
import com.daniel.blog.model.Post;

//GET /posts       - Retrieves a list of posts
//GET /posts?_start=20,_number=10
//GET /posts/12    - Retrieves a specific post
//POST /posts      - Creates a new post
//PUT /posts/12    - Updates a specific post (more then one field)
//PATCH /posts/12  - Partially updates a specific post (one field)
//DELETE /posts/12 - Deletes a specific post

//GET /photos      - Retrieves a list of photos
//GET /photos/12   - Retrieves a specific photo
//POST /photos     - Creates/Upload a new photo
//PUT /photos/12   - Updates a specific photo
//PATCH /photos/12 - Partially updates (one field) a specific photo
//DELETE /photos/12- Deletes a specific photo

@Component
@RestController
public class RestServiceController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/posts", produces = "application/json") 
    public @ResponseBody List<Post> getPosts(@RequestParam("_start") int start, @RequestParam("_number") int number, HttpServletRequest request, HttpServletResponse response){
		PostDAO postDAO = new PostDAOImpl();
		
		return postDAO.list(start, number);
	}

}
