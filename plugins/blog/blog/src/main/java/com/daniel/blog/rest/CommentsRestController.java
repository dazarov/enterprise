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
import com.daniel.blog.dto.CommentDTO;
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Comment;
import com.daniel.blog.requests.validators.CommentRequestValidator;
import com.daniel.blog.services.PhotoBlogService;

//GET	/posts/{post_id}/comments?page={page_number}   					- Retrieves a page of comments for specific post
//GET	/photos/{photo_id}/comments?page={page_number} 					- Retrieves a page of comments for specific photo
//GET	/comments/{parent_comment_id}?page={page_number} 				- Retrieves a page of child comments for specific comment

//POST /posts/{post_id}/comments										- Creates a new comment for specific post
//POST /photos/{photo_id}/comments										- Creates a new comment for specific photo
//POST /comments/{parent_comment_id}									- Creates a new child comment for specific comment

//PUT /comments/{comment_id}											- Updates a specific comment
//DELETE /comments/{comment_id}											- Deletes a specific comment

@RestController
public class CommentsRestController {
	
	@Autowired
    private PhotoBlogService blogService;
	
	@Loggable
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CommentRequestValidator());
    }
	
	//GET	/posts/{post_id}/comments?page={page_number}   					- Retrieves a page of comments for specific post
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/posts/{post_id}/comments", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<CommentDTO>> getCommentsForPost(@PathVariable("post_id") long postId, @RequestParam(value = "page", required = false, defaultValue="0") Integer pageNumber) throws BlogEntityNotFoundException {
		List<Comment> comments =  blogService.getCommentsByPostId(postId, pageNumber);
		
		if(comments.isEmpty()){
            throw new BlogEntityNotFoundException("Comments not found!");
        }
		
		List<CommentDTO> commentDTOs = new ArrayList<>();
		comments.forEach(c -> commentDTOs.add(new CommentDTO(c)));
		
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
	}
	
	//GET	/photos/{photo_id}/comments?page={page_number} 					- Retrieves a page of comments for specific photo
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/photos/{photo_id}/comments", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<CommentDTO>> getCommentsForPhoto(@PathVariable("photo_id") long photoId, @RequestParam(value = "page", required = false, defaultValue="0") Integer pageNumber) throws BlogEntityNotFoundException {
		List<Comment> comments =  blogService.getCommentsByPhotoId(photoId, pageNumber);
		
		if(comments.isEmpty()){
            throw new BlogEntityNotFoundException("Comments not found!");
        }
		
		List<CommentDTO> commentDTOs = new ArrayList<>();
		comments.forEach(c -> commentDTOs.add(new CommentDTO(c)));
		
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
	}
	
	//GET	/comments/{parent_comment_id}?page={page_number} 				- Retrieves a page of child comments for specific comment
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/comments/{parent_id}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<CommentDTO>> getCommentsForParentComment(@PathVariable("parent_id") long parentId, @RequestParam(value = "page", required = false, defaultValue="0") Integer pageNumber) throws BlogEntityNotFoundException {
		List<Comment> comments =  blogService.getCommentsByParentCommentId(parentId, pageNumber);
		
		if(comments.isEmpty()){
            throw new BlogEntityNotFoundException("Comments not found!");
        }
		
		List<CommentDTO> commentDTOs = new ArrayList<>();
		comments.forEach(c -> commentDTOs.add(new CommentDTO(c)));
		
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
	}

	//POST /posts/{post_id}/comments										- Creates a new comment for specific post
	@Loggable
	@RequestMapping(value = "/posts/{post_id}/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCommentForPost(@PathVariable("post_id") long postId, @RequestBody @Valid CommentDTO commentRequest, UriComponentsBuilder ucBuilder) throws BlogEntityNotFoundException {
        blogService.createCommentForPost(postId, commentRequest);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/posts/{id}").buildAndExpand(postId).toUri());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
	
	//POST /photos/{photo_id}/comments										- Creates a new comment for specific photo
	@Loggable
	@RequestMapping(value = "/photos/{photo_id}/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCommentForPhoto(@PathVariable("photo_id") long photoId, @RequestBody @Valid CommentDTO commentRequest, UriComponentsBuilder ucBuilder) throws BlogEntityNotFoundException {
        blogService.createCommentForPhoto(photoId, commentRequest);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/photos/{id}").buildAndExpand(photoId).toUri());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

	//POST /comments/{parent_comment_id}									- Creates a new child comment for specific comment
	@Loggable
	@RequestMapping(value = "/comments/{parent_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCommentForParentComment(@PathVariable("parent_id") long parentId, @RequestBody @Valid CommentDTO commentRequest, UriComponentsBuilder ucBuilder) throws BlogEntityNotFoundException {
        blogService.createCommentForParentComment(parentId, commentRequest);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/blogs").build().toUri());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

	//PUT /comments/{comment_id}											- Updates a specific comment
	@Loggable
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("id") long id, @RequestBody @Valid CommentDTO commentRequest) throws BlogEntityNotFoundException {
        Comment comment = blogService.updateComment(id, commentRequest);
        
       	return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.OK);
    }
	
	//DELETE /comments/{comment_id}											- Deletes a specific comment
	@Loggable
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable("id") long id) throws BlogEntityNotFoundException {
        boolean deleted = blogService.deleteComment(id);
        if(!deleted){
        	throw new BlogEntityNotFoundException("Comment with id "+id+" not found!");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
