package com.daniel.blog.dto;

import java.time.Instant;

import com.daniel.blog.model.AbstractEntity;
import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.CommentAllowance;
import com.daniel.blog.model.CommentableBlogEntry;
import com.daniel.blog.model.Photo;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.Status;
import com.daniel.blog.model.User;

public class DTOConverter {
	private static void basicConvert(PhotoBlogDTO dto, AbstractEntity entity){
		dto.setId(entity.getId());
		if(entity.getCreationTime() != null){
			dto.setDateTime(entity.getCreationTime().toEpochMilli());
		}
		if(entity.getStatus() != null){
			dto.setStatus(entity.getStatus().toString());
		}
	}
	
	private static void commentableConvert(CommentableDTO commentableDTO, CommentableBlogEntry commentableEntry){
		basicConvert(commentableDTO, commentableEntry);
		
		if(commentableEntry.getCommentAllowance() != null){
			commentableDTO.setCommentAllowance(commentableEntry.getCommentAllowance().toString());
		}else if(commentableEntry.getBlog() != null){
			commentableDTO.setCommentAllowance(commentableEntry.getBlog().getCommentAllowance().toString());
		}
	}
	
	public static BlogDTO convert(Blog blog){
		BlogDTO blogDTO = new BlogDTO();
		
		basicConvert(blogDTO, blog);
		
		blogDTO.setName(blog.getName());
		
		if(blog.getCommentAllowance() != null){
			blogDTO.setCommentAllowance(blog.getCommentAllowance().toString());
		}else{
			blogDTO.setCommentAllowance(CommentAllowance.COMMENTS_ALLOWED.toString());
		}
		
		return blogDTO;
	}
	
	public static UserDTO convert(User user){
		UserDTO userDTO = new UserDTO();
		
		basicConvert(userDTO, user);
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		
		return userDTO;
	}
	
	public static PostDTO convert(Post post){
		PostDTO postDTO = new PostDTO();
		
		commentableConvert(postDTO, post);
		
		postDTO.setSubject(post.getSubject());
		postDTO.setDescription(post.getDescription());
		postDTO.setBody(post.getBody());
		
		return postDTO;
	}
	
	public static PhotoDTO convert(Photo photo){
		PhotoDTO photoDTO = new PhotoDTO();
		
		commentableConvert(photoDTO, photo);
		
		photoDTO.setLocation(photo.getLocation());
		photoDTO.setDescription(photo.getDescription());
		
		return photoDTO;
	}
	
	public static CommentDTO convert(Comment comment){
		CommentDTO commentDTO = new CommentDTO();
		
		commentableConvert(commentDTO, comment);
		
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
	
	private static void basicUpdate(AbstractEntity entity, PhotoBlogDTO dto){
		if(dto.getDateTime() != null){
			entity.setCreationTime(Instant.ofEpochMilli(dto.getDateTime()));
		}
		if(dto.getStatus() != null){
			entity.setStatus(Status.valueOf(dto.getStatus()));
		}
	}
	
	private static void commentableUpdate(CommentableBlogEntry commentableEntry, CommentableDTO commentableDTO){
		basicUpdate(commentableEntry, commentableDTO);
		
		commentableEntry.setCommentAllowance(CommentAllowance.valueOf(commentableDTO.getCommentAllowance()));
	}
	
	public static void update(Blog blog, BlogDTO blogDTO){
		basicUpdate(blog, blogDTO);
		
		blog.setName(blogDTO.getName());
		blog.setCommentAllowance(CommentAllowance.valueOf(blogDTO.getCommentAllowance()));
	}
	
	public static void update(User user, UserDTO userDTO){
		basicUpdate(user, userDTO);
		
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
	}
	
	public static void update(Post post, PostDTO postDTO){
		commentableUpdate(post, postDTO);
		
		post.setBody(postDTO.getBody());
		post.setSubject(postDTO.getSubject());
		post.setDescription(postDTO.getDescription());
	}

	public static void update(Photo photo, PhotoDTO photoDTO){
		commentableUpdate(photo, photoDTO);
		
		photo.setDescription(photoDTO.getDescription());
		photo.setLocation(photoDTO.getLocation());
	}
	
	public static void update(Comment comment, CommentDTO commentDTO){
		commentableUpdate(comment, commentDTO);
		
		comment.setBody(commentDTO.getBody());
	}

}
