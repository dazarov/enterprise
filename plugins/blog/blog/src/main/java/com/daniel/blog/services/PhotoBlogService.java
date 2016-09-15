package com.daniel.blog.services;

import java.util.List;

import com.daniel.blog.dto.BlogDTO;
import com.daniel.blog.dto.CommentDTO;
import com.daniel.blog.dto.PhotoDTO;
import com.daniel.blog.dto.PostDTO;
import com.daniel.blog.dto.UserDTO;
import com.daniel.blog.errors.PhotoBlogException;
import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.Photo;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.User;

public interface PhotoBlogService {
	// Blog operations
	
	public List<Blog> getAllBlogs() throws PhotoBlogException;
	
	public List<Blog> getBlogs(int pageNumber) throws PhotoBlogException;
	
	public Blog getBlogByName(String blogName) throws PhotoBlogException;
	
	public Blog createBlog(BlogDTO blogRequest) throws PhotoBlogException;
	
	public Blog updateBlog(String blogName, BlogDTO blogRequest) throws PhotoBlogException;
	
	public boolean deleteBlog(String blogName) throws PhotoBlogException;
	
	// User operations
	
	public List<User> getAllUsers() throws PhotoBlogException;
	
	public List<User> getUsers(int pageNumber) throws PhotoBlogException;
	
	public User getUserById(long userId) throws PhotoBlogException;
	
	public User createUser(UserDTO userRequest);
	
	public User updateUser(long userId, UserDTO userRequest) throws PhotoBlogException;
	
	public boolean deleteUser(long userId);
	
	// Post operations
	
	public List<Post> getPostsByBlogName(String blogName, int pageNumber) throws PhotoBlogException;
	
	public Post getPost(long postId) throws PhotoBlogException;
	
	public Post createPost(String blogName, PostDTO postRequest) throws PhotoBlogException;
	
	public Post updatePost(long postId, PostDTO postRequest) throws PhotoBlogException;
	
	public boolean deletePost(long postId);
	
	// Photo operations

	public List<Photo> getPhotosByBlogName(String blogName, int pageNumber) throws PhotoBlogException;
	
	public Photo getPhoto(long photoId) throws PhotoBlogException;
	
	public Photo createPhoto(String blogName, PhotoDTO photoRequest) throws PhotoBlogException;
	
	public Photo updatePhoto(long photoId, PhotoDTO photoRequest) throws PhotoBlogException;
	
	public boolean deletePhoto(long photoId);
	
	// Comment operations
	
	public List<Comment> getCommentsByPostId(long postId, int pageNumber) throws PhotoBlogException;
	
	public List<Comment> getCommentsByPhotoId(long photoId, int pageNumber) throws PhotoBlogException;
	
	public List<Comment> getCommentsByParentCommentId(long parentCommentId, int pageNumber) throws PhotoBlogException;
	
	public Comment createCommentForPost(long postId, CommentDTO commentRequest) throws PhotoBlogException;
	
	public Comment createCommentForPhoto(long photoId, CommentDTO commentRequest) throws PhotoBlogException;
	
	public Comment createCommentForParentComment(long parentCommentId, CommentDTO commentRequest) throws PhotoBlogException;
	
	public Comment updateComment(long commentId, CommentDTO commentRequest) throws PhotoBlogException;
	
	public boolean deleteComment(long commentId);
	
}
