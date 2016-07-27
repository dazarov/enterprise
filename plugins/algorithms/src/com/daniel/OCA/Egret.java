package com.daniel.OCA;

public class Egret {
	private String color;
	public Egret(String color){
		color = color;
	}
	public static void main(String[] args){
		//Egret e = new Egret();
		
		String year = "Senior";
		switch(year){
		case "Freshman" : default : case "Sophomore" :
		case "Junior" : System.out.println("See you next year"); break;
		case "Senior" : System.out.println("Congratulations");
		}
	}
}
