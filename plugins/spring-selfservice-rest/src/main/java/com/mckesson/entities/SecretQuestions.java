package com.mckesson.entities;

import java.util.ArrayList;
import java.util.List;

public class SecretQuestions {
	private static List<String>questions = new ArrayList<String>();
	
	static{
		questions.add("What was your childhood nickname?");
		questions.add("In what city did you meet your spuse/significant other?");
		questions.add("What is the name of your favorite childhood friend?");
		questions.add("What street did you live on in elementary school?");
		questions.add("What is the middle name of your oldest child?");
		questions.add("What is your oldest sibling's birthday month and year? (e.g., January 1900)");
		questions.add("What is your oldest sibling's middle name?");
		questions.add("What school did you attend for middle school?");
		questions.add("What was your childhood phone number including area code? (e.g., 000-000-0000)");
		questions.add("What is your cousin's first and last name?");
		questions.add("What was the name of your first stuffed animal?");
		questions.add("In what city or town your mother and father meet?");
		questions.add("What was the last name of your elementary school teacher?");
		questions.add("In what city does your nearest sibling live?");
		questions.add("What is your oldest brother's birthday month and year? (e.g., January 1900)");
		questions.add("What is your maternal grandmother's maiden name?");
		questions.add("In what city or town was your first job?");
		questions.add("What is the name of the place your wedding reception was held?");
		questions.add("What is the name of a college you applied to but didn't attend?");
		questions.add("Waht is the name of your first pet?");
	}
	
	public static List<String> getSecretAllQuestions(){
		return questions;
	}
	
	public static List<String> getThreeQuestions(int index1, int index2, int index3){
		if(index1 > questions.size() || index2 > questions.size() || index3 > questions.size()){
			return null;
		}
		List<String> threeQuestions = new ArrayList<String>(3);
		threeQuestions.add(questions.get(index1));
		threeQuestions.add(questions.get(index2));
		threeQuestions.add(questions.get(index3));
		return threeQuestions;
	}
}
