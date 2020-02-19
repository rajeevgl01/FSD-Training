package com.ibm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoClass {
	PreparedStatement pstmt;
	Connection dbCon;
	
	public DaoClass() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_logout?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			System.out.println("Issue with connection " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not found " + e.getMessage());
		}
	}
	
	public int addNewUser(String userId, String password) {
		String newUser = "INSERT INTO login_details VALUES (?,?)";
		
		int n = 0;
		
		try {
			pstmt = dbCon.prepareStatement(newUser);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			if(pstmt.executeUpdate() > 0)
				n++;
			
		} catch (SQLException e) {
			System.out.println("Issue with new user insertion " + e.getMessage());
		}
		return n;
	}
	
	public boolean getUserId(String userId){
		String getQry = "SELECT * FROM login_details WHERE userName = ?";
		
		try {
			pstmt = dbCon.prepareStatement(getQry);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			String user = rs.getString("userName");
			
			if(user.equals(userId))
				return true;
			
		} catch (SQLException e) {
			System.out.println("Issue with getting username " + e.getMessage());
		}
		
		return false;
	}
	
	public boolean getPassword(String userId, String password){
		String getPassQry = "SELECT password FROM `login_details` WHERE userName = ?";
		
		try {
			pstmt = dbCon.prepareStatement(getPassQry);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			String pass = rs.getString("password");
			
			if(pass.equals(password))
				return true;
			
		} catch (SQLException e) {
			System.out.println("Issue with getting password " + e.getMessage());
		}
		
		return false;
	}
	
}
