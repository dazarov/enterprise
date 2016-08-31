package com.daniel.blog.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.daniel.blog.annotations.Loggable;
import com.daniel.blog.dto.DTOConverter;
import com.daniel.blog.dto.PostDTO;
import com.daniel.blog.dto.validators.PostDTOValidator;
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Post;
import com.daniel.blog.services.PhotoBlogService;

//GET	/{blog_name}/posts?page={page_number}							- Retrieves a page of posts
//GET	/posts/{post_id}			    								- Retrieves a specific post

//POST	/{blog_name}/posts      										- Creates a new post in the blog
//PUT	/posts/{post_id}    											- Updates a specific post
//DELETE /posts/{post_id} 												- Deletes a specific post

@RestController
public class PostsRestController {
	
	@Autowired
    private PhotoBlogService blogService;
	
	@Loggable
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PostDTOValidator());
    }
	
	//GET	/{blog_name}/posts?page={page_number}							- Retrieves a page of posts
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{blogName}/posts", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<PostDTO>> getPosts(@PathVariable("blogName") String blogName, @RequestParam(value = "page", required = false, defaultValue="0") Integer pageNumber) throws BlogEntityNotFoundException {
		List<Post> posts =  blogService.getPostsByBlogName(blogName, pageNumber);
		
		if(posts.isEmpty()){
            throw new BlogEntityNotFoundException("Posts not found!");
        }
		
		List<PostDTO> postDTOs = new ArrayList<>();
		posts.forEach(p -> postDTOs.add(DTOConverter.convert(p)));
		
        return new ResponseEntity<>(postDTOs, HttpStatus.OK);
	}
	
	//GET	/posts/{post_id}			    								- Retrieves a specific post
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") long id) throws BlogEntityNotFoundException {
		Post post = blogService.getPost(id);
		
        return new ResponseEntity<>(DTOConverter.convert(post), HttpStatus.OK);
	}
	
	//POST	/{blog_name}/posts      										- Creates a new post in the blog
	@Loggable
	@RequestMapping(value = "/{blogName}/posts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPost(@PathVariable("blogName") String blogName, @RequestBody @Valid PostDTO postRequest, UriComponentsBuilder ucBuilder) throws BlogEntityNotFoundException {
        Post post = blogService.createPost(blogName, postRequest);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
	
	//PUT	/posts/{post_id}    											- Updates a specific post
	@Loggable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") long id, @RequestBody @Valid PostDTO postRequest) throws BlogEntityNotFoundException {
        System.out.println("Updating Post " + id);
         
        Post post = blogService.updatePost(id, postRequest);
        
       	return new ResponseEntity<>(DTOConverter.convert(post), HttpStatus.OK);
    }
    
	//DELETE /posts/{post_id} 												- Deletes a specific post
	@Loggable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PostDTO> deletePost(@PathVariable("id") long id) throws BlogEntityNotFoundException {
        boolean deleted = blogService.deletePost(id);
        if(!deleted){
        	throw new BlogEntityNotFoundException("Post with id "+id+" not found!");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    }

}
