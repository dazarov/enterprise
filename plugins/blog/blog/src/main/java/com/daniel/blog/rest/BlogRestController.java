package com.daniel.blog.rest;

// ------------------------ BlogRestController:
//GET /blogs/init														- Creates some test entities

//GET /blogs															- Retrieves a list of blogs
//GET /blogs?page={page_number}											- Retrieves a page of blogs
//GET /blogs/{blog_id}													- Retrieves a specific blog

//POST /blogs															- Creates a new blog
//UPDATE /blogs/{blog_id}												- Updates a specific blog
//DELETE /blogs/{blog_id}												- Deletes a specific blog

//------------------------ UserRestController:
//GET /users															- Retrieves a list of users
//GET /users?page={page_number}											- Retrieves a page of users
//GET /users/{user_id}													- Retrieves a specific user

//POST /users															- Creates a new user
//PUT /users/{user_id}													- Updates a specific user (more then one property)
//----PATCH /users/{user_id}												- Partially updates a specific user (one property)
//DELETE /users/{user_id}												- Deletes a specific user

//------------------------ PostsRestController:
//GET	/{blog_name}/posts       										- Retrieves a list of posts
//GET	/{blog_name}/posts?page={page_number}							- Retrieves a page of posts
//GET	/posts/{post_id}			    								- Retrieves a specific post

//POST	/{blog_name}/posts      										- Creates a new post in the blog
//PUT	/posts/{post_id}    											- Updates a specific post (more then one property)
//----PATCH	/posts/{post_id}			  									- Partially updates a specific post (one property)
//DELETE /posts/{post_id} 												- Deletes a specific post

//------------------------ PhotosRestController:
//GET	/{blog_name}/photos       										- Retrieves a list of photos
//GET	/{blog_name}/photos?page={page_number}							- Retrieves a page of photos
//GET	/photos/{photo_id} 				   								- Retrieves a specific photo

//POST	/{blog_name}/photos      										- Creates a new photo in the blog
//PUT	/photos/{photo_id}			    								- Updates a specific photo (more then one property)
//----PATCH	/photos/{photo_id}  											- Partially updates a specific photo (one property)
//DELETE /photos/{photo_id}												- Deletes a specific photo

//------------------------ CommentsRestController:
//GET	/posts/{post_id}/comments?page={page_number}   					- Retrieves a page of comments for specific post
//GET	/photos/{photo_id}/comments?page={page_number} 					- Retrieves a page of comments for specific photo
//GET	/comments/{parent_comment_id}?page={page_number} 				- Retrieves a page of child comments for specific comment

//POST /posts/{post_id}/comments										- Creates a new comment for specific post
//POST /photos/{photo_id}/comments										- Creates a new comment for specific photo
//POST /comments/{parent_comment_id}									- Creates a new child comment for specific comment

//PUT /comments/{comment_id}											- Updates a specific comment
//DELETE /comments/{comment_id}											- Deletes a specific comment

public class BlogRestController {

}
