package com.daniel.blog;

public interface PhotoBlogConstants {
	int NUMBER_OF_BLOGS_ON_PAGE = 30;
	
	int NUMBER_OF_USERS_ON_PAGE = 30;
	
	int NUMBER_OF_POSTS_ON_PAGE = 10;
	
	int NUMBER_OF_COMMENTS_ON_PAGE = 10;
	
	int NUMBER_OF_PHOTOS_ON_PAGE = 15;
	
	// Validation constants
	
	// User
	
	int MAX_USER_NAME_LENGTH = 100;
	
	int MAX_USER_EMAIL_LENGTH = 100;
	
	int MAX_USER_PASSWORD_LENGTH = 100;
	
	// Blog
	
	int MAX_BLOG_NAME_LENGTH = 100;
	
	// Post
	
	int MAX_POST_SUBJECT_LENGTH = 100;
	
	int MAX_POST_DESCRIPTION_LENGTH = 200;
	
	int MAX_POST_BODY_LENGTH = 3000;
	
	// Photo
	
	int MAX_PHOTO_LOCATION_LENGTH = 255;
	
	int MAX_PHOTO_DESCRIPTION_LENGTH = 255;
	
	// Comment
	
	int MAX_COMMENT_BODY_LENGTH = 3000;
}
