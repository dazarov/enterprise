package com.daniel.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.daniel.blog.PhotoBlogConstants;
import com.daniel.blog.annotations.Loggable;
import com.daniel.blog.errors.BlogEntityNotFoundException;
import com.daniel.blog.model.Blog;
import com.daniel.blog.model.Comment;
import com.daniel.blog.model.Post;
import com.daniel.blog.model.Status;
import com.daniel.blog.repositories.BlogRepository;
import com.daniel.blog.repositories.PostRepository;
import com.daniel.blog.requests.PostRequest;

@Service
public class PhotoBlogServiceImpl implements PhotoBlogService {
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BlogRepository blogRepository;
	
	@Loggable
	@Override
	public List<Post> getPosts(int offset, int number){
		Page<Post> page = postRepository.findAll(new PageRequest(offset, number));
		return page.getContent();
	}
	
	@Loggable
	@Override
	public Post getPost(long id){
		return postRepository.findOne(id);
	}
	
	@Loggable
	@Override
	public Post createPost(PostRequest postRequest){
		Post post = new Post();
		post.setStatus(Status.ENTRY_NOTPUBLISHED);
		post.setBody(postRequest.getBody());
		post.setDescription(postRequest.getDescription());
		post.setSubject(postRequest.getSubject());
		postRepository.save(post);
		return post;
	}
	
	@Loggable
	@Override
	public Post updatePost(PostRequest postRequest, long id){
		Post post = postRepository.findOne(id);
		if(post != null){
			post.setStatus(Status.ENTRY_NOTPUBLISHED);
			post.setBody(postRequest.getBody());
			post.setDescription(postRequest.getDescription());
			post.setSubject(postRequest.getSubject());
			postRepository.save(post);
		}
		return post;
	}
	
	@Loggable
	@Override
	public boolean deletePost(long id){
		Post post = postRepository.findOne(id);
		if(post != null){
			postRepository.delete(id);
			return true;
		}
		return false;
	}

	@Loggable
	@Override
	public Post createPost(Post post) {
		postRepository.save(post);
		return post;
	}

	@Loggable
	@Override
	public Post updatePost(Post post, long id) {
		Post newPost = postRepository.findOne(id);
		if(post != null){
			newPost.setStatus(post.getStatus());
			newPost.setBody(post.getBody());
			newPost.setDescription(post.getDescription());
			newPost.setSubject(post.getSubject());
			postRepository.save(post);
		}
		return post;
	}

	@Loggable
	@Override
	public List<Post> getPostsByBlogName(String blogName, int pageNumber) throws BlogEntityNotFoundException{
		Blog blog = blogRepository.findOneByName(blogName);
		if(blog == null){
			throw new BlogEntityNotFoundException("Blog with name "+blogName+" not found!");
		}
		pageNumber--;
		if(pageNumber < 0){
			pageNumber = 0;
		}
		return postRepository.findByBlog(blog, new PageRequest(pageNumber*PhotoBlogConstants.NUMBER_OF_POSTS_ON_PAGE, PhotoBlogConstants.NUMBER_OF_POSTS_ON_PAGE));
	}

	@Loggable
	@Override
	public List<Comment> getCommentsByPostId(Long postId, int pageNUmber) {
		return null;
	}

	@Loggable
	@Override
	public Post createPost(String blogName, PostRequest postRequest) {
		return null;
	}
	
}
