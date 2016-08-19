package com.daniel.blog.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.daniel.blog.model.Role;

@Converter
public class RoleConverter implements AttributeConverter<Role, Integer>{

	@Override
	public Role convertToEntityAttribute(Integer id) {
		if(id == null){
			return null;
		}
		return Role.byId(id);
	}

	@Override
	public Integer convertToDatabaseColumn(Role role) {
		if(role == null){
			return null;
		}
		return role.getId();
	}
	
}