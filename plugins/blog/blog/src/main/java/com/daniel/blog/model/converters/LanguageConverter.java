package com.daniel.blog.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.daniel.blog.model.Language;

@Converter
public class LanguageConverter implements AttributeConverter<Language, Integer>{

	@Override
	public Language convertToEntityAttribute(Integer id) {
		if(id == null){
			return null;
		}
		return Language.byId(id);
	}

	@Override
	public Integer convertToDatabaseColumn(Language language) {
		if(language == null){
			return null;
		}
		return language.getId();
	}
	
}