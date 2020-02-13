package com.employee.ui;

import java.sql.Connection;
import java.util.Scanner;

import com.employee.dao.ConnectionClass;

public class Main {
	static Connection dbCon;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ConnectionClass cc = new ConnectionClass();
		dbCon = cc.con(dbCon);
		
		boolean flag = true;
		
		while(flag) {
			System.out.println("Press 1 for Insert: 2 for delete data: 3 for update entry: 4 to show the data: 5 to terminate.. ");
			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					cc.insertData(dbCon);
					break;
				case 2:
					cc.deleteData(dbCon);
					break;
				case 3:
					cc.updateData(dbCon);
					break;
				case 4:
					cc.getAllData(dbCon);
					break;
				case 5:
					flag = false;
					break;
				default:
					System.out.println("Enter choice again");
					break;
			}
		}
		scan.close();
	}
}
