package com.wallet.service;

import java.sql.Connection;
import java.util.Scanner;

import com.wallet.dao.ConnectionClass;

public class ServiceClass implements ServiceInterface{
	
	static Connection dbCon;
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void serviceMethod() {
		ConnectionClass cc = new ConnectionClass();
		dbCon = cc.con(dbCon);
		
		boolean flag = true;
		while(flag) {
			System.out.println("Enter Choice");
			System.out.println("1 : Add New Account; 2 : Update Account Details; "
					+ "3 : Show Balance; 4 : Deposit Money; 5 : Withdraw Money; 6 : Account Transactions; "
					+ "7 : Fund Transfer; 8 : Close the wallet");
			
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			case 1:
				cc.addAccountDetails(dbCon);
				break;
				
			case 2:
				cc.updateAccountDetails(dbCon);
				break;
				
			case 3:
				cc.getBalance(dbCon);
				break;
				
			case 4:
				cc.depositMoney(dbCon);
				break;
				
			case 5:
				cc.withdrawMoney(dbCon);
				break;
				
			case 6:
				cc.transactionDetails(dbCon);
				break;
				
			case 7:
				cc.fundTransfer(dbCon);
				break;
			
			case 8:
				flag = false;
				break;
				
			default:
				break;
			}
		}
		
	}

}
