package com.daniel.blog.services;

import java.util.List;

import com.daniel.blog.dto.BlogDTO;
import com.daniel.blog.dto.CommentDTO;
import com.daniel.blog.dto.PhotoDTO;
import com.daniel.blog.dto.PostDTO;
import com.daniel.blog.dto.UserDTO;
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.Photo;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.User;

public interface PhotoBlogService {
	// test
	public void init();
	
	// Blog operations
	
	public List<Blog> getAllBlogs() throws BlogEntityNotFoundException;
	
	public List<Blog> getBlogs(int pageNumber) throws BlogEntityNotFoundException;
	
	public Blog getBlogById(long blogId) throws BlogEntityNotFoundException;
	
	public Blog getBlogByName(String blogName) throws BlogEntityNotFoundException;
	
	public Blog createBlog(BlogDTO blogRequest);
	
	public Blog updateBlog(long blogId, BlogDTO blogRequest) throws BlogEntityNotFoundException;
	
	public boolean deleteBlog(long blogId);
	
	// User operations
	
	public List<User> getAllUsers() throws BlogEntityNotFoundException;
	
	public List<User> getUsers(int pageNumber) throws BlogEntityNotFoundException;
	
	public User getUserById(long userId) throws BlogEntityNotFoundException;
	
	public User createUser(UserDTO userRequest);
	
	public User updateUser(long userId, UserDTO userRequest) throws BlogEntityNotFoundException;
	
	public boolean deleteUser(long userId);
	
	// Post operations
	
	public List<Post> getPostsByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException;
	
	public Post getPost(long postId) throws BlogEntityNotFoundException;
	
	public Post createPost(String blogName, PostDTO postRequest) throws BlogEntityNotFoundException;
	
	public Post updatePost(long postId, PostDTO postRequest) throws BlogEntityNotFoundException;
	
	public boolean deletePost(long postId);
	
	// Photo operations

	public List<Photo> getPhotosByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException;
	
	public Photo getPhoto(long photoId) throws BlogEntityNotFoundException;
	
	public Photo createPhoto(String blogName, PhotoDTO photoRequest) throws BlogEntityNotFoundException;
	
	public Photo updatePhoto(long photoId, PhotoDTO photoRequest) throws BlogEntityNotFoundException;
	
	public boolean deletePhoto(long photoId);
	
	// Comment operations
	
	public List<Comment> getCommentsByPostId(long postId, int pageNumber) throws BlogEntityNotFoundException;
	
	public List<Comment> getCommentsByPhotoId(long photoId, int pageNumber) throws BlogEntityNotFoundException;
	
	public List<Comment> getCommentsByParentCommentId(long parentCommentId, int pageNumber) throws BlogEntityNotFoundException;
	
	public Comment createCommentForPost(long postId, CommentDTO commentRequest) throws BlogEntityNotFoundException;
	
	public Comment createCommentForPhoto(long photoId, CommentDTO commentRequest) throws BlogEntityNotFoundException;
	
	public Comment createCommentForParentComment(long parentCommentId, CommentDTO commentRequest) throws BlogEntityNotFoundException;
	
	public Comment updateComment(long commentId, CommentDTO commentRequest) throws BlogEntityNotFoundException;
	
	public boolean deleteComment(long commentId);
	
}
