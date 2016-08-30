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
import com.daniel.blog.dto.UserDTO;
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.User;
import com.daniel.blog.requests.validators.UserRequestValidator;
import com.daniel.blog.services.PhotoBlogService;

//GET /users															- Retrieves a list of all users
//GET /users?page={page_number}											- Retrieves a page of users
//GET /users/{user_id}													- Retrieves a specific user

//POST /users															- Creates a new user
//PUT /users/{user_id}													- Updates a specific user (more then one property)
//DELETE /users/{user_id}												- Deletes a specific user
 
@RestController
public class UsersRestController {
	
	@Autowired
    private PhotoBlogService blogService;
	
	@Loggable
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserRequestValidator());
    }
	
	//GET /users															- Retrieves a list of all users
	//GET /users?page={page_number}											- Retrieves a page of users
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/users", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) throws BlogEntityNotFoundException {
		List<User> users;
		if(pageNumber == 0){
			users = blogService.getAllUsers();
		}else{
			users = blogService.getUsers(pageNumber);
		}
		
		if(users.isEmpty()){
            throw new BlogEntityNotFoundException("Blogs not found!");
        }
		
		List<UserDTO> userDTOs = new ArrayList<>();
		users.forEach(p -> userDTOs.add(new UserDTO(p)));
		
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
	
	//GET /users/{user_id}													- Retrieves a specific user
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long userId) throws BlogEntityNotFoundException {
		User user =  blogService.getUserById(userId);
		
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	//POST /users															- Creates a new user
	@Loggable
	@RequestMapping(value = "/users/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserDTO userRequest, UriComponentsBuilder ucBuilder) throws BlogEntityNotFoundException {
        User user = blogService.createUser(userRequest);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/blogs/{id}").buildAndExpand(user.getId()).toUri());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
	
	//PUT /users/{user_id}													- Updates a specific user (more then one property)
	@Loggable
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") long id, @RequestBody @Valid UserDTO blogRequest) throws BlogEntityNotFoundException {
        User user = blogService.updateUser(id, blogRequest);
        
       	return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
	
	//DELETE /users/{user_id}												- Deletes a specific user
	@Loggable
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") long id) throws BlogEntityNotFoundException {
        boolean deleted = blogService.deleteUser(id);
        if(!deleted){
        	throw new BlogEntityNotFoundException("User not found!");
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}