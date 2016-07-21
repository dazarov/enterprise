package com.daniel.blog.model;

public enum Language {
	RUSSIAN(0),
	ENGLISH(1)
	;

	private int id;
	
	Language(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public static Language byId(int id){
		for(Language s: Language.values()){
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
}
