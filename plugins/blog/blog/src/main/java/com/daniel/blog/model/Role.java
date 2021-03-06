package com.daniel.blog.model;

public enum Role {
	
	COMMENTATOR(0), 		// can leave/edit/delete own comments
	
	BLOG_OWNER(1),  // can create/edit/delete blogs  
					// can create/update/delete posts, photos and photo_images in a blog
					// can block/unblock users for particular blog
	
	MODERATOR(2),	// can moderate (publish) commetns for moderated blogs
	
	ADMIN(3) 		// can block/unblock users 
					// can block/unblock blogs
					// can edit adverts
	
	
	;

	private int id;
	
	Role(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public static Role byId(int id){
		for(Role s: Role.values()){
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
}
