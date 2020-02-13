package com.employee.service;

import java.sql.Connection;

public interface Service {
	public void getAllData(Connection dbCon);
	
	public void insertData(Connection dbCon);
	
	public void deleteData(Connection dbCon);
	
	public void updateData(Connection dbCon);
}