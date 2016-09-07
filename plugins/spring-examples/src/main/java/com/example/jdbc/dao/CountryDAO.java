package com.example.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbc.model.Country;


public class CountryDAO {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/CountryDB";

	static final String USER = "username";
	static final String PASS = "password";
	   
	public CountryDAO() throws ClassNotFoundException{
		Class.forName(JDBC_DRIVER);
	}

	public void addCountry(Country country){
		try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
			String sql = "INSERT INTO Country (name) VALUES (?)";
			 
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, country.getName());
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new country was inserted successfully!");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	public void updateCountry(Country country) {
		try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
			String sql = "UPDATE Country SET name = ?";
			 
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, country.getName());
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new country was inserted successfully!");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	public List<Country> listCountries() {
		List<Country> countryList = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
			String sql = "SELECT id, name FROM Country";
			 
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			 
			while (result.next()){
				Long id = result.getLong(1);
			    String name = result.getString(2);
			    
				countryList.add(new Country(id, name));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return countryList;
	}

	public Country getCountryById(long countryId) {
		try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
			String sql = "SELECT name FROM Country WHERE id = ?";
			 
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, countryId);
			
			ResultSet result = statement.executeQuery(sql);
			 
			while (result.next()){
			    String name = result.getString(1);
			    
				return new Country(countryId, name);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public void removeCountry(long countryId) {
		try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
			String sql = "DELETE FROM Country WHERE id = ?";
			 
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, countryId);
			 
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

}
