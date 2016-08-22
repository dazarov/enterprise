package com.daniel.blog.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daniel.blog.model.converters.RoleConverter;

@Entity
@Table(name="ROLE")
public class UserRole {
	// Fields
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name="ROLE")
	@Basic
	@Convert(converter = RoleConverter.class)
	private Role role;
	
	// Methods
	
	public Long getId(){
		return id;
	}
	
	public void setRole(Role role){
		this.role = role;
	}
	
	public Role getRole(){
		return role;
	}
}
