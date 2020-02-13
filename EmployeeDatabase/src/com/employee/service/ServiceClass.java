package com.employee.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ServiceClass implements Service {
	PreparedStatement pstmt;
	Scanner scan = new Scanner(System.in);
		
	@Override
	public void getAllData(Connection dbCon) {
		String fetchQry = "SELECT * FROM employee_details";
		
		try {
			pstmt = dbCon.prepareStatement(fetchQry);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.print("Employee name : " + rs.getString("userName"));
				System.out.print(", Employee ID : " + rs.getInt("userId"));
				System.out.print(", Designation : " + rs.getString("Designation"));
				System.out.println(", Salary : " + rs.getInt("Salary"));
			}
			
		} catch (SQLException e) {
			System.out.println("issue with pstmt " + e.getMessage() );	
		}
	}
	
	@Override
	public void insertData(Connection dbCon) {
		String insertQry = "INSERT INTO employee_details VALUES (?,?,?,?)";
		
		try {
			pstmt = dbCon.prepareStatement(insertQry);
		} catch (SQLException e1) {
			System.out.println("Issue with prepare " + e1.getMessage());
		}
		
		System.out.println("Enter Name of the employee");
		String userName = scan.nextLine();
		
		System.out.println("Enter Designation of the employee");
		String designation = scan.nextLine();
		
		System.out.println("Enter userId of the employee");
		int userId = scan.nextInt();
		
		scan.nextLine();
		
		System.out.println("Enter salary of the employee");
		int salary = scan.nextInt();
		
		try {
			pstmt.setString(1, userName);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, salary);
			pstmt.setString(4, designation);
		} catch (SQLException e) {
			System.out.println("issue with insert " + e.getMessage());
		}
		
		try {
			if(pstmt.executeUpdate() > 0)
				System.out.println("Inserted successfully");
		} catch (SQLException e) {
			System.out.println("issue with execute " + e.getMessage());
		}	
	}
	
	@Override
	public void deleteData(Connection dbCon) {
		String deleteqry = "DELETE FROM employee_details WHERE userID = ?";
		try {
			pstmt = dbCon.prepareStatement(deleteqry);
			
			System.out.println("Enter userId of the employee");
			int userId = scan.nextInt();
			
			pstmt.setInt(1, userId);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("Deleted successfully..");
			
		} catch (SQLException e) {
			System.out.println("Issue with delete " + e.getMessage());
		}		
	}
	
	@Override
	public void updateData(Connection dbCon) {
		try {
			System.out.println("Enter userId of the employee");
			int userId = scan.nextInt();
			
			System.out.println("Enter the new details of the empployee");
			System.out.println("Press 1 for name update: press 2 for userID update: Press 3 for Salary update: press 4 for designation update");
			
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:
				scan.nextLine();
				String updateQry1 = "UPDATE employee_details SET userName = ? WHERE userID = " + userId;
				pstmt = dbCon.prepareStatement(updateQry1);
				System.out.println("Enter new username");
				String userName = scan.nextLine();
				pstmt.setString(1, userName);
				if(pstmt.executeUpdate() > 0)
					System.out.println("Username updated successfully...");
				break;
				
			case 2:
				scan.nextLine();
				String updateQry2 = "UPDATE employee_details SET userID = ? WHERE userID = " + userId;
				pstmt = dbCon.prepareStatement(updateQry2);
				System.out.println("enter new userid");
				int userID = scan.nextInt();
				pstmt.setInt(1, userID);
				if(pstmt.executeUpdate() > 0)
					System.out.println("UserID updated successfully...");
				break;
				
			case 3:
				scan.nextLine();
				String updateQry3 = "UPDATE employee_details SET Salary = ? WHERE userID = " + userId;
				pstmt = dbCon.prepareStatement(updateQry3);
				System.out.println("enter updated salary");
				int Salary = scan.nextInt();
				pstmt.setInt(1, Salary);
				if(pstmt.executeUpdate() > 0)
					System.out.println("Salary updated successfully...");
				break;
				
			case 4:
				scan.nextLine();
				String updateQry4 = "UPDATE employee_details SET Designation = ? WHERE userID = " + userId;
				pstmt = dbCon.prepareStatement(updateQry4);
				System.out.println("enter new designation");
				String Designation = scan.nextLine();
				pstmt.setString(1, Designation);
				if(pstmt.executeUpdate() > 0)
					System.out.println("Designation updated successfully...");
				break;
				
			default:
				break;
			}
		} catch (SQLException e) {
			System.out.println("Issue with update" + e.getMessage());
		}
		
	}
}
