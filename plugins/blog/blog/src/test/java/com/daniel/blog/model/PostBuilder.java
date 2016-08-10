package com.daniel.blog.model;

import org.springframework.test.util.ReflectionTestUtils;

public class PostBuilder {

    private Post post;

    public PostBuilder() {
        post = new Post();
    }

    public PostBuilder id(Long id) {
        ReflectionTestUtils.setField(post, "id", id);
        return this;
    }

    public PostBuilder description(String description) {
    	post.setDescription(description);
        return this;
    }

    public PostBuilder subject(String subject) {
    	post.setSubject(subject);
        return this;
    }

    public PostBuilder body(String body) {
    	post.setBody(body);
        return this;
    }

    public Post build() {
        return post;
    }
}