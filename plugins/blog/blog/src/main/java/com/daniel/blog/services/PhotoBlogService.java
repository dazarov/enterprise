package com.daniel.blog.services;

import java.util.List;

import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.Photo;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.User;
import com.daniel.blog.requests.BlogRequest;
import com.daniel.blog.requests.CommentRequest;
import com.daniel.blog.requests.PhotoRequest;
import com.daniel.blog.requests.PostRequest;
import com.daniel.blog.requests.UserRequest;

public interface PhotoBlogService {
	// test
	public void init();
	
	// Blog operations
	
	public List<Blog> getAllBlogs() throws BlogEntityNotFoundException;
	
	public List<Blog> getBlogs(int pageNumber) throws BlogEntityNotFoundException;
	
	public Blog getBlogById(long blogId) throws BlogEntityNotFoundException;
	
	public Blog getBlogByName(String blogName) throws BlogEntityNotFoundException;
	
	public Blog createBlog(BlogRequest blogRequest);
	
	public Blog updateBlog(long blogId, BlogRequest blogRequest) throws BlogEntityNotFoundException;
	
	public boolean deleteBlog(long blogId);
	
	// User operations
	
	public List<User> getAllUsers() throws BlogEntityNotFoundException;
	
	public List<User> getUsers(int pageNumber) throws BlogEntityNotFoundException;
	
	public User getUserById(long userId) throws BlogEntityNotFoundException;
	
	public User createUser(UserRequest userRequest);
	
	public User updateUser(long userId, UserRequest userRequest) throws BlogEntityNotFoundException;
	
	public boolean deleteUser(long userId);
	
	// Post operations
	
	public List<Post> getPostsByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException;
	
	public Post getPost(long postId) throws BlogEntityNotFoundException;
	
	public Post createPost(String blogName, PostRequest postRequest) throws BlogEntityNotFoundException;
	
	public Post updatePost(long postId, PostRequest postRequest) throws BlogEntityNotFoundException;
	
	public boolean deletePost(long postId);
	
	// Photo operations

	public List<Photo> getPhotosByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException;
	
	public Photo getPhoto(long photoId) throws BlogEntityNotFoundException;
	
	public Photo createPhoto(String blogName, PhotoRequest photoRequest) throws BlogEntityNotFoundException;
	
	public Photo updatePhoto(long photoId, PhotoRequest photoRequest) throws BlogEntityNotFoundException;
	
	public boolean deletePhoto(long photoId);
	
	// Comment operations
	
	public List<Comment> getCommentsByPostId(long postId, int pageNUmber) throws BlogEntityNotFoundException;
	
	public List<Comment> getCommentsByPhotoId(long photoId, int pageNUmber) throws BlogEntityNotFoundException;
	
	public List<Comment> getCommentsByParentCommentId(long parentCommentId, int pageNUmber) throws BlogEntityNotFoundException;
	
	public Comment createCommentForPost(long postId, CommentRequest commentRequest) throws BlogEntityNotFoundException;
	
	public Comment createCommentForPhoto(long photoId, CommentRequest commentRequest) throws BlogEntityNotFoundException;
	
	public Comment createCommentForParentComment(long parentCommentId, CommentRequest commentRequest) throws BlogEntityNotFoundException;
	
	public Comment updateComment(long commentId, CommentRequest commentRequest) throws BlogEntityNotFoundException;
	
	public boolean deleteComment(long commentId);
	
}
