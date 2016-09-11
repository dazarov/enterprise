package com.daniel.blog.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.daniel.blog.model.CommentAllowance;

@Converter
public class CommentAllowanceConverter implements AttributeConverter<CommentAllowance, Integer>{

	@Override
	public CommentAllowance convertToEntityAttribute(Integer id) {
		if(id == null){
			return null;
		}
		return CommentAllowance.byId(id);
	}

	@Override
	public Integer convertToDatabaseColumn(CommentAllowance allowance) {
		if(allowance == null){
			return null;
		}
		return allowance.getId();
	}
	
}