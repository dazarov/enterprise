package com.mckesson.selfservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.utils.MySQLService;

public class SecurityQuestionsAccess extends MySQLService{
	private static final String CREATE_TABLE_QUERY = "CREATE TABLE SecurityAnswers (uid varchar(255), givenname varchar(255), email varchar(255), "+
			"question1 varchar(255), question2 varchar(255), question3 varchar(255), answer1 varchar(255), answer2 varchar(255), answer3 varchar(255), PRIMARY KEY (uid));";
	
	private static final String GET_QUESTIONS_QUERY = "SELECT * from SecurityAnswers WHERE uid=? AND email=?;";
	
	private static final String CHECK_ANSWERS_QUERY = "SELECT * from SecurityAnswers WHERE uid=? AND email=? AND VERIFY_SSHA(answer1, ?) AND VERIFY_SSHA(answer2, ?) AND VERIFY_SSHA(answer3, ?)";
	
	private static final String CHECK_USER_QUERY = "SELECT * from SecurityAnswers WHERE email=? AND givenname=?;";
	
	public SecurityQuestionsAccess(String dbName, String dbUser, String dbPassword){
		super(dbName, dbUser, dbPassword);
	}
	
	public void initDatabase(){
		ArrayList<String> parameters = new ArrayList<String>();
		runQuery(CREATE_TABLE_QUERY, parameters, null);
	}
	
	public List<String> getSecurityQuestions(String userId, String email){
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add(userId);
		parameters.add(email);

		ArrayList<String> fields = new ArrayList<String>();
		fields.add("question1");
		fields.add("question2");
		fields.add("question3");
		
		List<Map<String, String>> result = runQuery(GET_QUESTIONS_QUERY, parameters, fields);
		
		if(result != null && result.size() == 1){
			ArrayList<String> questions = new ArrayList<String>();
			questions.addAll(result.get(0).values());
			return questions;
		}
		
		return null;
	}
	  
	public boolean checkSecurityAnswers(String userId, String email, List<String> answers){
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add(userId);
		parameters.add(email);
		parameters.addAll(answers);
		
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("uid");
		
		List<Map<String, String>> result = runQuery(CHECK_ANSWERS_QUERY, parameters, fields);
		
		if(result != null && result.size() == 1){
			return true;
		}
		
		return false;
	}

	public String getUserId(String email, String givenName){
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add(email);
		parameters.add(givenName);
		
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("uid");
		
		List<Map<String, String>> result = runQuery(CHECK_USER_QUERY, parameters, fields);
		
		if(result != null && result.size() == 1){
			return result.get(0).get("uid");
		}
		
		return null;
	}
}
