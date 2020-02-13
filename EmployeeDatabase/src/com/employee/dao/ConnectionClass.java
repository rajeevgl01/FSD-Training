package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass implements ConnectionInterface{
	
	@Override
	public Connection con(Connection dbCon) {
		try {
			//Connect to Database..
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedatabase?serverTimezone=UTC", "root", "");
			
		} catch (SQLException e) {
			System.out.println("issue with connection " + e.getMessage());
		}
		return dbCon;
	}
}

