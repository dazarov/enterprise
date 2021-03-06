package com.daniel.blog.model;

public enum Language {
	UNKNOWN(0),
	RUSSIAN(1),
	ENGLISH(2),
	SPANISH(3),
	FRENCH(4)
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
