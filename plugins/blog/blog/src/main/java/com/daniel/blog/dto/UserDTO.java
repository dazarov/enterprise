package com.daniel.blog.dto;

public class UserDTO extends PhotoBlogDTO{
	private String name;
	private String email;
	private String password;
	
	public UserDTO(String name, String email, String password){
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public UserDTO(){
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "UserDTO name: "+name;
	}
}
