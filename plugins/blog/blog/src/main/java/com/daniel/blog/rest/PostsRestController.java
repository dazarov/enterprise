package com.daniel.blog.rest;

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
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Post;
import com.daniel.blog.requests.PostRequest;
import com.daniel.blog.requests.validators.PostRequestValidator;
import com.daniel.blog.services.PhotoBlogService;

//GET	/{blog_name}/posts       										- Retrieves a list of posts
//GET	/{blog_name}/posts?page={page_number}							- Retrieves a page of posts
//GET	/{blog_name}/posts/{post_id}    								- Retrieves a specific post
//GET	/{blog_name}/posts/{post_id}/comments?page={page_number}   	- Retrieves a specific post with page of comments
//POST	/{blog_name}/posts      										- Creates a new post in the blog
//PUT	/{blog_name}/posts/{post_id}    								- Updates a specific post (more then one field)
//PATCH	/{blog_name}/posts/{post_id}  								- Partially updates a specific post (one field)
//DELETE /{blog_name}/posts/12 										- Deletes a specific post

//GET /photos      - Retrieves a list of photos
//GET /photos/12   - Retrieves a specific photo
//POST /photos     - Creates/Upload a new photo
//PUT /photos/12   - Updates a specific photo
//PATCH /photos/12 - Partially updates (one field) a specific photo
//DELETE /photos/12- Deletes a specific photo


@RestController
public class PostsRestController {
	
	@Autowired
    private PhotoBlogService blogService;
	
	@Loggable
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PostRequestValidator());
    }
	
	//-------------------Retrieve Page of Posts--------------------------------------------------------
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<Post>> getPosts(@RequestParam("_start") int start, @RequestParam("_number") int number) throws BlogEntityNotFoundException {
		List<Post> posts =  blogService.getPosts(start, number);
		
		if(posts.isEmpty()){
            throw new BlogEntityNotFoundException("Posts not found!");
        }
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	//-------------------Retrieve Single Post--------------------------------------------------------
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<Post> getPost(@PathVariable("id") long id) throws BlogEntityNotFoundException {
		Post post = blogService.getPost(id);
        if (post == null) {
            System.out.println("User with id " + id + " not found");
            throw new BlogEntityNotFoundException("Post not found for id - "+id);
        }
        return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	//-------------------Create a Post--------------------------------------------------------

	@Loggable
	@RequestMapping(value = "/posts/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPost(@RequestBody @Valid PostRequest post, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Post " + post.getSubject());
 
        blogService.createPost(post);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
    //------------------- Update a Post --------------------------------------------------------
    
	@Loggable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> updatePost(@PathVariable("id") long id, @RequestBody @Valid PostRequest postRequest) {
        System.out.println("Updating Post " + id);
         
        Post post = blogService.updatePost(postRequest, postRequest.getId());
        if(post != null){
        	return new ResponseEntity<Post>(post, HttpStatus.OK);
        }else{
            System.out.println("Post with id " + id + " not found");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        	
        }
    }
    
  //------------------- Delete a Post --------------------------------------------------------
    
	@Loggable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Post> deletePost(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Post with id " + id);
        
        boolean deleted = blogService.deletePost(id);
        if(deleted){
        	return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
        }
        System.out.println("Unable to delete. Post with id " + id + " not found");
        return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
    }

}
