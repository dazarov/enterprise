package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLService {
	private String dbURL;
	
	public MySQLService(String dbName, String dbUser, String dbPassword){
		dbURL = "jdbc:mysql://localhost/"+dbName+"?user="+dbUser+"&password="+dbPassword;
	}
	
	protected List<Map<String, String>> runQuery(String query){
		return runQuery(query, null, null);
	}
	
	protected List<Map<String, String>> runQuery(String query, List<String> queryParameters, List<String> fields){
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
//		if(tableName != null){
//			query = query.replace(TABLE_NAME_SIGNATURE, tableName);
//		}
		
		try{
		  Class.forName("com.mysql.jdbc.Driver");
  
		  connect = DriverManager.getConnection(dbURL);

		  if(queryParameters == null){
			  statement = connect.createStatement();
			  resultSet = statement.executeQuery(query);
		  }else{
			  preparedStatement = connect.prepareStatement(query);
			  int index = 1;
			  for(String parameter : queryParameters){
				  preparedStatement.setString(index++, parameter);
			  }
			  resultSet = preparedStatement.executeQuery();
		  }
		  if(fields != null){
			  List<Map<String, String>> results = new ArrayList<Map<String, String>>();
			  while (resultSet.next()) {
				  Map<String, String> map = new HashMap<String, String>();
				  for(String field : fields){
					  String value = resultSet.getString(field);
					  if(value != null){
						  map.put(field, value);
					  }
				  }
				  results.add(map);
			  }
			  return results;
		  }else{
			  return null;
		  }
		  
		}catch(Exception e){
		  e.printStackTrace();
		}finally{
		  try {
		      if (resultSet != null) {
		        resultSet.close();
		      }

		      if (statement != null) {
		        statement.close();
		      }
		      
		      if (preparedStatement != null) {
		    	  preparedStatement.close();
			  }

		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		return null;
	}

}
