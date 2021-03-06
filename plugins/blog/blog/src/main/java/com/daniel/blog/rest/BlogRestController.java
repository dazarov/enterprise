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
import com.daniel.blog.dto.BlogDTO;
import com.daniel.blog.dto.DTOConverter;
import com.daniel.blog.dto.validators.BlogDTOValidator;
import com.daniel.blog.errors.BlogNotFoundException;
import com.daniel.blog.errors.PhotoBlogException;
import com.daniel.blog.model.Blog;
import com.daniel.blog.services.PhotoBlogService;

// ------------------------ BlogRestController:
//GET /blogs/init														- Creates some test entities

//GET /blogs															- Retrieves a list of all blogs
//GET /blogs?page={page_number}											- Retrieves a page of blogs
//GET /blogs/{blog_id}													- Retrieves a specific blog

//POST /blogs															- Creates a new blog
//PUT /blogs/{blog_id}													- Updates a specific blog
//DELETE /blogs/{blog_id}												- Deletes a specific blog

//------------------------ UserRestController:
//GET /users															- Retrieves a list of all users
//GET /users?page={page_number}											- Retrieves a page of users
//GET /users/{user_id}													- Retrieves a specific user

//POST /users															- Creates a new user
//PUT /users/{user_id}													- Updates a specific user (more then one property)
//----PATCH /users/{user_id}												- Partially updates a specific user (one property)
//DELETE /users/{user_id}												- Deletes a specific user

//------------------------ PostsRestController:
//----GET	/{blog_name}/posts       										- Retrieves a list of posts
//GET	/{blog_name}/posts?page={page_number}							- Retrieves a page of posts
//GET	/posts/{post_id}			    								- Retrieves a specific post

//POST	/{blog_name}/posts      										- Creates a new post in the blog
//PUT	/posts/{post_id}    											- Updates a specific post (more then one property)
//----PATCH	/posts/{post_id}			  									- Partially updates a specific post (one property)
//DELETE /posts/{post_id} 												- Deletes a specific post

//------------------------ PhotosRestController:
//----GET	/{blog_name}/photos       										- Retrieves a list of photos
//GET	/{blog_name}/photos?page={page_number}							- Retrieves a page of photos
//GET	/photos/{photo_id} 				   								- Retrieves a specific photo

//POST	/{blog_name}/photos      										- Creates a new photo in the blog
//PUT	/photos/{photo_id}			    								- Updates a specific photo (more then one property)
//----PATCH	/photos/{photo_id}  											- Partially updates a specific photo (one property)
//DELETE /photos/{photo_id}												- Deletes a specific photo

//------------------------ CommentsRestController:
//GET	/posts/{post_id}/comments?page={page_number}   					- Retrieves a page of comments for specific post
//GET	/photos/{photo_id}/comments?page={page_number} 					- Retrieves a page of comments for specific photo
//GET	/comments/{parent_comment_id}?page={page_number} 				- Retrieves a page of child comments for specific comment

//POST /posts/{post_id}/comments										- Creates a new comment for specific post
//POST /photos/{photo_id}/comments										- Creates a new comment for specific photo
//POST /comments/{parent_comment_id}									- Creates a new child comment for specific comment

//PUT /comments/{comment_id}											- Updates a specific comment
//DELETE /comments/{comment_id}											- Deletes a specific comment

@RestController
@RequestMapping(value = "/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogRestController {
	
	@Autowired
    private PhotoBlogService blogService;
	
	@Loggable
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new BlogDTOValidator());
    }
	
	//GET /blogs															- Retrieves a list of all blogs
	//GET /blogs?page={page_number}											- Retrieves a page of blogs
	@Loggable
	@RequestMapping(method = RequestMethod.GET) 
    public ResponseEntity<List<BlogDTO>> getBlogs(@RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) throws PhotoBlogException {
		List<Blog> blogs;
		if(pageNumber == 0){
			blogs = blogService.getAllBlogs();
		}else{
			blogs = blogService.getBlogs(pageNumber);
		}
		if(blogs.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		
		List<BlogDTO> blogDTOs = new ArrayList<>();
		blogs.forEach(b -> blogDTOs.add(DTOConverter.convert(b)));
		
        return new ResponseEntity<>(blogDTOs, HttpStatus.OK);
	}
	
	//GET /blogs/{blog_id}													- Retrieves a specific blog
	//GET /blogs/{blog_name}
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{blogName}") 
    public ResponseEntity<BlogDTO> getBlog(@PathVariable("blogName") String blogName) throws PhotoBlogException {
		Blog blog =  blogService.getBlogByName(blogName);
		
        return new ResponseEntity<>(DTOConverter.convert(blog), HttpStatus.OK);
	}

	//POST /blogs															- Creates a new blog
	@Loggable
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBlog(@RequestBody @Valid BlogDTO blogRequest, UriComponentsBuilder ucBuilder) throws PhotoBlogException {
        Blog blog = blogService.createBlog(blogRequest);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/blogs/{id}").buildAndExpand(blog.getId()).toUri());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

	//PUT /blogs/{blog_id}													- Updates a specific blog
	//PUT /blogs/{blog_name}
	@Loggable
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable("id") String blogName, @RequestBody @Valid BlogDTO blogRequest) throws PhotoBlogException {
        Blog blog = blogService.updateBlog(blogName, blogRequest);
        
       	return new ResponseEntity<>(DTOConverter.convert(blog), HttpStatus.OK);
    }
    
	//DELETE /blogs/{blog_id}												- Deletes a specific blog
	//DELETE /blogs/{blog_name}
	@Loggable
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BlogDTO> deleteBlog(@PathVariable("id") String blogName) throws PhotoBlogException {
        boolean deleted = blogService.deleteBlog(blogName);
        if(!deleted){
        	throw new BlogNotFoundException(blogName);
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
