package com.daniel.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.daniel.blog.model.Post;
import com.daniel.blog.model.Status;
import com.daniel.blog.repositories.PostRepository;
import com.daniel.blog.requests.PostRequest;

@Service
public class PhotoBlogServiceImpl implements PhotoBlogService {
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<Post> getPosts(int offset, int number){
		Page<Post> page = postRepository.findAll(new PageRequest(offset, number));
		return page.getContent();
	}
	
	@Override
	public Post getPost(long id){
		return postRepository.findOne(id);
	}
	
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
	
	@Override
	public boolean deletePost(long id){
		Post post = postRepository.findOne(id);
		if(post != null){
			postRepository.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public Post createPost(Post post) {
		postRepository.save(post);
		return post;
	}

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
	
}
