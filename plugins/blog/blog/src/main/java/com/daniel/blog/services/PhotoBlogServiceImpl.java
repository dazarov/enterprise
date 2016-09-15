package com.daniel.blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.annotations.Loggable;
import com.daniel.blog.dto.BlogDTO;
import com.daniel.blog.dto.CommentDTO;
import com.daniel.blog.dto.DTOConverter;
import com.daniel.blog.dto.PhotoDTO;
import com.daniel.blog.dto.PostDTO;
import com.daniel.blog.dto.UserDTO;
import com.daniel.blog.errors.BlogNameNotUniqueException;
import com.daniel.blog.errors.BlogNotFoundException;
import com.daniel.blog.errors.CommentNotFoundException;
import com.daniel.blog.errors.CommentsNotAllowedException;
import com.daniel.blog.errors.PhotoBlogException;
import com.daniel.blog.errors.PhotoNotFoundException;
import com.daniel.blog.errors.PostNotFoundException;
import com.daniel.blog.errors.UserNotFoundException;
import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.CommentAllowance;
import com.daniel.blog.model.Photo;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.Status;
import com.daniel.blog.model.User;
import com.daniel.blog.repositories.BlogRepository;
import com.daniel.blog.repositories.CommentRepository;
import com.daniel.blog.repositories.PhotoRepository;
import com.daniel.blog.repositories.PostRepository;
import com.daniel.blog.repositories.UserRepository;

@Service
public class PhotoBlogServiceImpl implements PhotoBlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;

	//@Autowired
	//private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	
	// Blog operations
	
	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<Blog> getAllBlogs() {
		Iterable<Blog> blogs = blogRepository.findAll();
		List<Blog> blogList = new ArrayList<>();
		blogs.forEach(blogList::add);
		return blogList;
	}

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<Blog> getBlogs(int pageNumber) {
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		Page<Blog> page = blogRepository.findAll(new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_BLOGS_ON_PAGE));
		return page.getContent();
	}
	
	@Loggable
	@Override
	@Transactional(readOnly = true)
	public Blog getBlogByName(String blogName) throws BlogNotFoundException {
		long blogId = -1;
		try{
			blogId = Long.parseLong(blogName);
		}catch(NumberFormatException e){
			// do nothing
		}
		Blog blog = null;
		
		if(blogId >= 0){
			blog = blogRepository.findOne(blogId);
		}
		
		if(blog == null){
			blog = blogRepository.findOneByName(blogName);
		}
		
		if(blog == null){
			throw new BlogNotFoundException(blogName);
		}
		return blog;
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Blog createBlog(BlogDTO blogDTO) throws BlogNameNotUniqueException {
		Blog blog = new Blog();
		
		List<Blog> blogs = getAllBlogs();
		List<String> names = blogs.stream().map(b -> b.getName()).collect(Collectors.toList());
		
		if(names.contains(blogDTO.getName())){
			throw new BlogNameNotUniqueException("Blog <"+blogDTO.getName()+"> already exists!");
		}
		
		DTOConverter.update(blog, blogDTO);
		
		return blogRepository.save(blog);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Blog updateBlog(String blogName, BlogDTO blogRequest) throws BlogNotFoundException {
		Blog blog = getBlogByName(blogName);
		if(blog == null){
			throw new BlogNotFoundException(blogName);
		}
		
		DTOConverter.update(blog, blogRequest);
		
		return blogRepository.save(blog);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public boolean deleteBlog(String blogName)  throws BlogNotFoundException {
		Blog blog = getBlogByName(blogName);
		if(blog != null){
			blogRepository.delete(blog);
			return true;
		}
		return false;
	}
	
	// User operations

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		Iterable<User> users = userRepository.findAll();
		List<User> userList = new ArrayList<>();
		users.forEach(userList::add);
		return userList;
	}

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<User> getUsers(int pageNumber) {
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		Page<User> page = userRepository.findAll(new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_USERS_ON_PAGE));
		return page.getContent();
	}

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public User getUserById(long userId) throws UserNotFoundException {
		User user = userRepository.findOne(userId);
		if(user == null){
			throw new UserNotFoundException(userId);
		}
		return user;
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public User createUser(UserDTO userRequest) {
		User user = new User();
		
		DTOConverter.update(user, userRequest);
		
		return userRepository.save(user);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public User updateUser(long userId, UserDTO userRequest) throws UserNotFoundException {
		User user = userRepository.findOne(userId);
		if(user == null){
			throw new UserNotFoundException(userId);
		}
		
		DTOConverter.update(user, userRequest);
		
		return userRepository.save(user);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public boolean deleteUser(long userId) {
		User user = userRepository.findOne(userId);
		if(user != null){
			userRepository.delete(user);
			return true;
		}
		return false;
	}
	
	// Post operations
	
	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<Post> getPostsByBlogName(String blogName, int pageNumber) throws BlogNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		
		return postRepository.findByBlog(blog, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_POSTS_ON_PAGE));
	}

	
	@Loggable
	@Override
	@Transactional(readOnly = true)
	public Post getPost(long postId) throws PostNotFoundException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new PostNotFoundException(postId);
		}
		return post;
	}
	
	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Post createPost(String blogName, PostDTO postRequest) throws BlogNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		Post post = new Post();
		
		post.setBlog(blog);

		DTOConverter.update(post, postRequest);
		
		return postRepository.save(post);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Post updatePost(long postId, PostDTO postRequest) throws PostNotFoundException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new PostNotFoundException(postId);
		}
		
		DTOConverter.update(post, postRequest);
		
		return postRepository.save(post);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public boolean deletePost(long postId){
		Post post = postRepository.findOne(postId);
		if(post != null){
			postRepository.delete(post);
			return true;
		}
		return false;
	}
	
	// Photo operations

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<Photo> getPhotosByBlogName(String blogName, int pageNumber) throws BlogNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return photoRepository.findByBlog(blog, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_PHOTOS_ON_PAGE));
	}

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public Photo getPhoto(long photoId) throws PhotoNotFoundException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new PhotoNotFoundException(photoId);
		}
		return photo;
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Photo createPhoto(String blogName, PhotoDTO photoRequest) throws BlogNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		Photo photo = new Photo();
		
		photo.setBlog(blog);

		DTOConverter.update(photo, photoRequest);
		
		return photoRepository.save(photo);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Photo updatePhoto(long photoId, PhotoDTO photoRequest) throws PhotoNotFoundException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new PhotoNotFoundException(photoId);
		}
		
		DTOConverter.update(photo, photoRequest);
		
		return photoRepository.save(photo);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public boolean deletePhoto(long photoId) {
		Photo photo = photoRepository.findOne(photoId);
		if(photo != null){
			photoRepository.delete(photo);
			return true;
		}
		return false;
	}
	
	// Comment operations
	
	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByPostId(long postId, int pageNumber) throws PostNotFoundException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new PostNotFoundException(postId);
		}
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return commentRepository.findByBlogEntry(post, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_COMMENTS_ON_PAGE));
	}

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByPhotoId(long photoId, int pageNumber) throws PhotoNotFoundException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new PhotoNotFoundException(photoId);
		}
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return commentRepository.findByBlogEntry(photo, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_COMMENTS_ON_PAGE));
	}

	@Loggable
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByParentCommentId(long parentCommentId, int pageNumber) throws CommentNotFoundException {
		Comment parent = commentRepository.findOne(parentCommentId);
		if(parent == null){
			throw new CommentNotFoundException(parentCommentId);
		}
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return commentRepository.findByBlogEntry(parent, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_COMMENTS_ON_PAGE));
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Comment createCommentForPost(long postId, CommentDTO commentDAO) throws PhotoBlogException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new PostNotFoundException(postId);
		}
		if(post.getCommentAllowance() == CommentAllowance.COMMENTS_NOT_ALLOWED){
			throw new CommentsNotAllowedException("Comments in post with id "+postId+" are not allowed!");
		}
		
		if(post.getCommentAllowance() == CommentAllowance.COMMENTS_MODERATED){
			commentDAO.setStatus(Status.ENTRY_NOTPUBLISHED.toString());
		}
		
		Comment comment = new Comment();
		comment.setBlogEntry(post);
		
		DTOConverter.update(comment, commentDAO);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Comment createCommentForPhoto(long photoId, CommentDTO commentDAO) throws PhotoBlogException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new PhotoNotFoundException(photoId);
		}
		
		if(photo.getCommentAllowance() == CommentAllowance.COMMENTS_NOT_ALLOWED){
			throw new CommentsNotAllowedException("Comments in photo with id "+photoId+" are not allowed!");
		}
		
		if(photo.getCommentAllowance() == CommentAllowance.COMMENTS_MODERATED){
			commentDAO.setStatus(Status.ENTRY_NOTPUBLISHED.toString());
		}
		
		Comment comment = new Comment();
		comment.setBlogEntry(photo);
		
		DTOConverter.update(comment, commentDAO);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Comment createCommentForParentComment(long parentCommentId, CommentDTO commentDAO) throws PhotoBlogException {
		Comment parent = commentRepository.findOne(parentCommentId);
		if(parent == null){
			throw new CommentNotFoundException(parentCommentId);
		}
		
		if(parent.getCommentAllowance() == CommentAllowance.COMMENTS_NOT_ALLOWED){
			throw new CommentsNotAllowedException("Comments in comment with id "+parentCommentId+" are not allowed!");
		}
		
		if(parent.getCommentAllowance() == CommentAllowance.COMMENTS_MODERATED){
			commentDAO.setStatus(Status.ENTRY_NOTPUBLISHED.toString());
		}
		
		Comment comment = new Comment();
		comment.setBlogEntry(parent);
		
		DTOConverter.update(comment, commentDAO);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public Comment updateComment(long commentId, CommentDTO commentRequest) throws CommentNotFoundException {
		Comment comment = commentRepository.findOne(commentId);
		if(comment == null){
			throw new CommentNotFoundException(commentId);
		}
		
		DTOConverter.update(comment, commentRequest);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	@Transactional(readOnly = false)
	public boolean deleteComment(long commentId) {
		Comment comment = commentRepository.findOne(commentId);
		if(comment != null){
			commentRepository.delete(comment);
			return true;
		}
		return false;
	}
	
	
}
