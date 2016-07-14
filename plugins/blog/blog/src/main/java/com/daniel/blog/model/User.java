package com.daniel.blog.model;

import java.time.LocalDateTime;

public class User {
	private int id;
	
	private String nick;
	
	private String name;
	
	private String passwordHash;
	
	private LocalDateTime registrationDateTime;
	
	private boolean active;
}
