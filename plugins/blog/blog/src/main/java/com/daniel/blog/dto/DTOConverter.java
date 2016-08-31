package com.daniel.blog.dto;

import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.Photo;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.User;

public class DTOConverter {
	public static BlogDTO convert(Blog blog){
		BlogDTO blogDTO = new BlogDTO();
		
		blogDTO.setId(blog.getId());
		blogDTO.setName(blog.getName());
		
		return blogDTO;
	}
	
	public static UserDTO convert(User user){
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		
		return userDTO;
	}
	
	public static PostDTO convert(Post post){
		PostDTO postDTO = new PostDTO();
		
		postDTO.setId(post.getId());
		postDTO.setSubject(post.getSubject());
		postDTO.setDescription(post.getDescription());
		postDTO.setBody(post.getBody());
		
		return postDTO;
	}
	
	public static PhotoDTO convert(Photo photo){
		PhotoDTO photoDTO = new PhotoDTO();
		
		photoDTO.setId(photo.getId());
		photoDTO.setLocation(photo.getLocation());
		photoDTO.setDescription(photo.getDescription());
		
		return photoDTO;
	}
	
	public static CommentDTO convert(Comment comment){
		CommentDTO commentDTO = new CommentDTO();
		
		commentDTO.setId(comment.getId());
		commentDTO.setBody(comment.getBody());
		
		return commentDTO;
	}
	
	public static Blog convert(BlogDTO blogDTO){
		Blog blog = new Blog();
		update(blog, blogDTO);
		return blog;
	}

	public static User convert(UserDTO userDTO){
		User user = new User();
		update(user, userDTO);
		return user;
	}

	public static Post convert(PostDTO postDTO){
		Post post = new Post();
		update(post, postDTO);
		return post;
	}

	public static Photo convert(PhotoDTO photoDTO){
		Photo photo = new Photo();
		update(photo, photoDTO);
		return photo;
	}

	public static Comment convert(CommentDTO commentDTO){
		Comment comment = new Comment();
		update(comment, commentDTO);
		return comment;
	}
	
	public static void update(Blog blog, BlogDTO blogRequest){
		blog.setName(blogRequest.getName());
	}
	
	public static void update(User user, UserDTO userRequest){
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
	}
	
	public static void update(Post post, PostDTO postRequest){
		post.setBody(postRequest.getBody());
		post.setSubject(postRequest.getSubject());
		post.setDescription(postRequest.getDescription());
	}

	public static void update(Photo photo, PhotoDTO photoRequest){
		photo.setDescription(photoRequest.getDescription());
		photo.setLocation(photoRequest.getLocation());
	}
	
	public static void update(Comment comment, CommentDTO commentRequest){
		comment.setBody(commentRequest.getBody());
	}

}
