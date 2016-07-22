package com.daniel.blog.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.daniel.blog.model.Status;

@Converter
public class StatusConverter implements AttributeConverter<Integer, Status>{

	@Override
	public Status convertToDatabaseColumn(Integer id) {
		if(id == null){
			return null;
		}
		return Status.byId(id);
	}

	@Override
	public Integer convertToEntityAttribute(Status status) {
		if(status == null){
			return null;
		}
		return status.getId();
	}
	
}