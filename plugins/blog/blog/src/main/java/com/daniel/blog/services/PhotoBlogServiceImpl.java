package com.daniel.blog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.annotations.Loggable;
import com.daniel.blog.dto.BlogDTO;
import com.daniel.blog.dto.CommentDTO;
import com.daniel.blog.dto.DTOConverter;
import com.daniel.blog.dto.PhotoDTO;
import com.daniel.blog.dto.PostDTO;
import com.daniel.blog.dto.UserDTO;
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.Photo;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.User;
import com.daniel.blog.repositories.BlogRepository;
import com.daniel.blog.repositories.CommentRepository;
import com.daniel.blog.repositories.PhotoRepository;
import com.daniel.blog.repositories.PostRepository;
import com.daniel.blog.repositories.UserRepository;
import com.daniel.blog.repositories.UserRoleRepository;

@Service
public class PhotoBlogServiceImpl implements PhotoBlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Loggable
	@Override
	public void init() {
//		// Comments init
//		Comment comment1 = new Comment();
//		comment1.setBody("Comment 1");
//		commentRepository.save(comment1);
//
//		Comment comment2 = new Comment();
//		comment2.setBody("Comment 2");
//		commentRepository.save(comment2);
//
//		// UserRoles init
//		UserRole commentatorRole = new UserRole();
//		commentatorRole.setRole(Role.COMMENTATOR);
//		userRoleRepository.save(commentatorRole);
//
//		UserRole blogOwnerRole = new UserRole();
//		blogOwnerRole.setRole(Role.BLOG_OWNER);
//		userRoleRepository.save(blogOwnerRole);
//		
//		UserRole moderatorRole = new UserRole();
//		moderatorRole.setRole(Role.MODERATOR);
//		userRoleRepository.save(moderatorRole);
//
//		UserRole adminRole = new UserRole();
//		adminRole.setRole(Role.ADMIN);
//		userRoleRepository.save(adminRole);
//		
//		// Users init
//		User admin = new User();
//		admin.setName("Admin");
//		admin.setEmail("admin@domain.com");
//		admin.setPassword("password");
//		admin.getRoles().add(commentatorRole);
//		admin.getRoles().add(blogOwnerRole);
//		admin.getRoles().add(moderatorRole);
//		admin.getRoles().add(adminRole);
//		userRepository.save(admin);
//		
//		User owner1 = new User();
//		owner1.setName("Owner 1");
//		owner1.setEmail("owner1@domain.com");
//		owner1.setPassword("password");
//		owner1.getRoles().add(blogOwnerRole);
//		userRepository.save(owner1);
//
//		User owner2 = new User();
//		owner2.setName("Owner 2");
//		owner2.setEmail("owner2@domain.com");
//		owner2.setPassword("password");
//		owner2.getRoles().add(blogOwnerRole);
//		userRepository.save(owner2);
//
//		User moderator = new User();
//		moderator.setName("Moderator");
//		moderator.setEmail("moderator@domain.com");
//		moderator.setPassword("password");
//		moderator.getRoles().add(moderatorRole);
//		userRepository.save(moderator);
//
//		User commentator1 = new User();
//		commentator1.setName("Commentator 1");
//		commentator1.setEmail("commentator1@domain.com");
//		commentator1.setPassword("password");
//		commentator1.getRoles().add(commentatorRole);
//		userRepository.save(commentator1);
//
//		User commentator2 = new User();
//		commentator2.setName("Commentator 2");
//		commentator2.setEmail("commentator2@domain.com");
//		commentator2.setPassword("password");
//		commentator2.getRoles().add(commentatorRole);
//		userRepository.save(commentator2);
//		
//		// Blogs init
//		Blog blog1 = new Blog();
//		blog1.setName("Blog1");
//		blog1.getUsers().add(owner1);
//		blog1.getUsers().add(moderator);
//		blog1.setModerated(true);
//		blogRepository.save(blog1);
//		
//		Blog blog2 = new Blog();
//		blog2.setName("Blog2");
//		blog2.getUsers().add(owner2);
//		blog2.setModerated(false);
//		blogRepository.save(blog2);
//		
//		// Posts init
//		Post post1 = new Post();
//		post1.setSubject("Blog 1 Post 1 Subject");
//		post1.setDescription("Blog 1 Post 1 Description");
//		post1.setBody("Blog 1 Post 1 Body");
//		post1.setBlog(blog1);
//		postRepository.save(post1);
//		
//		Post post2 = new Post();
//		post2.setSubject("Blog 1 Post 2 Subject");
//		post2.setDescription("Blog 1 Post 2 Description");
//		post2.setBody("Blog 1 Post 2 Body");
//		post2.setBlog(blog1);
//		//post2.getComments().add(comment1);
//		//post2.getComments().add(comment2);
//		comment1.setBlogEntry(post2);
//		comment2.setBlogEntry(post2);
//		postRepository.save(post2);
//		commentRepository.save(comment1);
//		commentRepository.save(comment2);
//
//		Post post3 = new Post();
//		post3.setSubject("Blog 2 Post 1 Subject");
//		post3.setDescription("Blog 2 Post 1 Description");
//		post3.setBody("Blog 2 Post 1 Body");
//		post3.setBlog(blog2);
//		postRepository.save(post3);
//
//		Post post4 = new Post();
//		post4.setSubject("Blog 2 Post 2 Subject");
//		post4.setDescription("Blog 2 Post 2 Description");
//		post4.setBody("Blog 2 Post 2 Body");
//		post4.setBlog(blog2);
//		postRepository.save(post4);
		
	}
	
	// Blog operations
	
	@Loggable
	@Override
	public List<Blog> getAllBlogs() {
		Iterable<Blog> blogs = blogRepository.findAll();
		List<Blog> blogList = new ArrayList<>();
		blogs.forEach(blogList::add);
		return blogList;
	}

	@Loggable
	@Override
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
	public Blog getBlogById(long blogId) throws BlogEntityNotFoundException {
		Blog blog = blogRepository.findOne(blogId);
		if(blog == null){
			throw new BlogEntityNotFoundException("Blog with id "+blogId+" not found!");
		}
		return blog;
	}

	@Loggable
	@Override
	public Blog getBlogByName(String blogName) throws BlogEntityNotFoundException {
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
			throw new BlogEntityNotFoundException("Blog with name "+blogName+" not found!");
		}
		return blog;
	}

	@Loggable
	@Override
	public Blog createBlog(BlogDTO blogRequest) {
		Blog blog = new Blog();
		
		DTOConverter.update(blog, blogRequest);
		
		return blogRepository.save(blog);
	}

	@Loggable
	@Override
	public Blog updateBlog(long blogId, BlogDTO blogRequest) throws BlogEntityNotFoundException {
		Blog blog = blogRepository.findOne(blogId);
		if(blog == null){
			throw new BlogEntityNotFoundException("Blog with id "+blogId+" not found!");
		}
		
		DTOConverter.update(blog, blogRequest);
		
		return blogRepository.save(blog);
	}

	@Loggable
	@Override
	public boolean deleteBlog(long blogId) {
		Blog blog = blogRepository.findOne(blogId);
		if(blog != null){
			blogRepository.delete(blog);
			return true;
		}
		return false;
	}
	
	// User operations

	@Loggable
	@Override
	public List<User> getAllUsers() {
		Iterable<User> users = userRepository.findAll();
		List<User> userList = new ArrayList<>();
		users.forEach(userList::add);
		return userList;
	}

	@Loggable
	@Override
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
	public User getUserById(long userId) throws BlogEntityNotFoundException {
		User user = userRepository.findOne(userId);
		if(user == null){
			throw new BlogEntityNotFoundException("User with id "+userId+" not found!");
		}
		return user;
	}

	@Loggable
	@Override
	public User createUser(UserDTO userRequest) {
		User user = new User();
		
		DTOConverter.update(user, userRequest);
		
		return userRepository.save(user);
	}

	@Loggable
	@Override
	public User updateUser(long userId, UserDTO userRequest) throws BlogEntityNotFoundException {
		User user = userRepository.findOne(userId);
		if(user == null){
			throw new BlogEntityNotFoundException("User with id "+userId+" not found!");
		}
		
		DTOConverter.update(user, userRequest);
		
		return userRepository.save(user);
	}

	@Loggable
	@Override
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
	public List<Post> getPostsByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		
		return postRepository.findByBlog(blog, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_POSTS_ON_PAGE));
	}

	
	@Loggable
	@Override
	public Post getPost(long postId) throws BlogEntityNotFoundException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new BlogEntityNotFoundException("Post with id "+postId+" not found!");
		}
		return post;
	}
	
	@Loggable
	@Override
	public Post createPost(String blogName, PostDTO postRequest) throws BlogEntityNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		Post post = new Post();
		
		post.setBlog(blog);

		DTOConverter.update(post, postRequest);
		
		return postRepository.save(post);
	}

	@Loggable
	@Override
	public Post updatePost(long postId, PostDTO postRequest) throws BlogEntityNotFoundException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new BlogEntityNotFoundException("Post with id "+postId+" not found!");
		}
		
		DTOConverter.update(post, postRequest);
		
		return postRepository.save(post);
	}

	@Loggable
	@Override
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
	public List<Photo> getPhotosByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return photoRepository.findByBlog(blog, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_PHOTOS_ON_PAGE));
	}

	@Loggable
	@Override
	public Photo getPhoto(long photoId) throws BlogEntityNotFoundException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new BlogEntityNotFoundException("Photo with id "+photoId+" not found!");
		}
		return photo;
	}

	@Loggable
	@Override
	public Photo createPhoto(String blogName, PhotoDTO photoRequest) throws BlogEntityNotFoundException {
		Blog blog = getBlogByName(blogName);
		
		Photo photo = new Photo();
		
		photo.setBlog(blog);

		DTOConverter.update(photo, photoRequest);
		
		return photoRepository.save(photo);
	}

	@Loggable
	@Override
	public Photo updatePhoto(long photoId, PhotoDTO photoRequest) throws BlogEntityNotFoundException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new BlogEntityNotFoundException("Photo with id "+photoId+" not found!");
		}
		
		DTOConverter.update(photo, photoRequest);
		
		return photoRepository.save(photo);
	}

	@Loggable
	@Override
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
	public List<Comment> getCommentsByPostId(long postId, int pageNumber) throws BlogEntityNotFoundException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new BlogEntityNotFoundException("Post with id "+postId+" not found!");
		}
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return commentRepository.findByBlogEntry(post, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_COMMENTS_ON_PAGE));
	}

	@Loggable
	@Override
	public List<Comment> getCommentsByPhotoId(long photoId, int pageNumber) throws BlogEntityNotFoundException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new BlogEntityNotFoundException("Photo with id "+photoId+" not found!");
		}
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return commentRepository.findByBlogEntry(photo, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_COMMENTS_ON_PAGE));
	}

	@Loggable
	@Override
	public List<Comment> getCommentsByParentCommentId(long parentCommentId, int pageNumber) throws BlogEntityNotFoundException {
		Comment parent = commentRepository.findOne(parentCommentId);
		if(parent == null){
			throw new BlogEntityNotFoundException("Comment with id "+parentCommentId+" not found!");
		}
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return commentRepository.findByBlogEntry(parent, new PhotoBlogPageable(pageNumber, PhotoBlogConstants.NUMBER_OF_COMMENTS_ON_PAGE));
	}

	@Loggable
	@Override
	public Comment createCommentForPost(long postId, CommentDTO commentRequest) throws BlogEntityNotFoundException {
		Post post = postRepository.findOne(postId);
		if(post == null){
			throw new BlogEntityNotFoundException("Post with id "+postId+" not found!");
		}
		
		Comment comment = new Comment();
		comment.setBlogEntry(post);
		
		DTOConverter.update(comment, commentRequest);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	public Comment createCommentForPhoto(long photoId, CommentDTO commentRequest) throws BlogEntityNotFoundException {
		Photo photo = photoRepository.findOne(photoId);
		if(photo == null){
			throw new BlogEntityNotFoundException("Photo with id "+photoId+" not found!");
		}
		
		Comment comment = new Comment();
		comment.setBlogEntry(photo);
		
		DTOConverter.update(comment, commentRequest);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	public Comment createCommentForParentComment(long parentCommentId, CommentDTO commentRequest) throws BlogEntityNotFoundException {
		Comment parent = commentRepository.findOne(parentCommentId);
		if(parent == null){
			throw new BlogEntityNotFoundException("Comment with id "+parentCommentId+" not found!");
		}
		
		Comment comment = new Comment();
		comment.setBlogEntry(parent);
		
		DTOConverter.update(comment, commentRequest);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	public Comment updateComment(long commentId, CommentDTO commentRequest) throws BlogEntityNotFoundException {
		Comment comment = commentRepository.findOne(commentId);
		if(comment == null){
			throw new BlogEntityNotFoundException("Comment with id "+commentId+" not found!");
		}
		
		DTOConverter.update(comment, commentRequest);
		
		return commentRepository.save(comment);
	}

	@Loggable
	@Override
	public boolean deleteComment(long commentId) {
		Comment comment = commentRepository.findOne(commentId);
		if(comment != null){
			commentRepository.delete(comment);
			return true;
		}
		return false;
	}
	
	
}
