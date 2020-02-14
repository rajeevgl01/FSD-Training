package com.wallet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class ConnectionClass implements DaoInterface {
	
	Scanner scan = new Scanner(System.in);
	PreparedStatement pstmt, pstmt1;
	
	@Override
	public String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Calendar calobj = Calendar.getInstance();
	    return(df.format(calobj.getTime()));
	}
	
	public String inputString() {
		return scan.nextLine();
	}
	
	public int inputInt(){
		return scan.nextInt();
	}
	
	public long inputLong() {
		return scan.nextLong();
	}
	
	public static int generateRandomDigits() {
	    int m = (int) Math.pow(10, 9);
	    return m + new Random().nextInt(9 * m);
	}
		
	@Override
	public Connection con(Connection dbCon) {
		try {
			//Connect to Database..
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/wallet?serverTimezone=UTC", "root", "");
			
		} catch (SQLException e) {
			System.out.println("issue with connection " + e.getMessage());
		}
		return dbCon;
	}

	@Override
	public void addAccountDetails(Connection dbCon) {
		String nameQry = "INSERT INTO account_details VALUES (?,?,?,?)";
		
		try {
			pstmt = dbCon.prepareStatement(nameQry);
			
			System.out.println("Enter the name of the Account Holder");
			String name = inputString();
			int accountNumber = generateRandomDigits();
			System.out.println("Enter Phone Number");
			long phoneNumber = inputLong();
			System.out.println("Enter the money you want to start account with...");
			long money = inputLong();
			
			pstmt.setLong(1, accountNumber);
			pstmt.setString(2, name);
			pstmt.setLong(3, money);
			pstmt.setLong(4, phoneNumber);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("New Account created successfully...");
			
		} catch (SQLException e) {
			System.out.println("issue with prepare statement : " + e.getMessage() );
		}
	}

	@Override
	public void updateAccountDetails(Connection dbCon) {
		System.out.println("Enter account Number");
		int accNumber = inputInt();
		
		System.out.println("Press 1 for Name update: 2 for PhoneNumber Update");
		int choice = inputInt();
		inputString();
		
		switch(choice) {
		case 1:
			String updateName = "UPDATE account_details SET Name = ? WHERE AccountNumber = ?";
			try {
				pstmt = dbCon.prepareStatement(updateName);
				System.out.println("Enter name");
				String name = inputString();
				pstmt.setString(1, name);
				pstmt.setInt(2, accNumber);
				
				if(pstmt.executeUpdate() > 0)
					System.out.println("Name updated successfully..");

			} catch (SQLException e) {
				System.out.println("issue with name update " + e.getMessage());
			}
			break;
		case 2:
			String updateNumber = "UPDATE account_details SET PhoneNumber = ? WHERE AccountNumber = ?";
			try {
				pstmt = dbCon.prepareStatement(updateNumber);
				System.out.println("Enter phone number");
				long number = inputLong();
				pstmt.setLong(1, number);
				pstmt.setInt(2, accNumber);
				
				if(pstmt.executeUpdate() > 0)
					System.out.println("Phone Number updated successfully..");

			} catch (SQLException e) {
				System.out.println("issue with phone number update " + e.getMessage());
			}
			break;
		default:
			break;
			
		}	
	}

	@Override
	public void getBalance(Connection dbCon) {
		String fetchBalance = "SELECT Balance FROM account_details WHERE AccountNumber = ?";
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		System.out.println("Enter phone number");
		long phoneNumber = inputLong();
		
		try {
			pstmt = dbCon.prepareStatement(fetchBalance);
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, phoneNumber);
			ResultSet rs = pstmt1.executeQuery();
			rs.next();
			int accNum = rs.getInt("AccountNumber");
						
			pstmt.setInt(1, accNum);
			
			ResultSet rs1 = pstmt.executeQuery();
			if(rs1.next())
				System.out.println("Balance : " + rs1.getInt("Balance"));
		} catch (SQLException e) {
			System.out.println("issue with balance retrieval " + e.getMessage() );
		}		
	}
			
	@Override
	public void depositMoney(Connection dbCon) {
		System.out.println("Enter the amount to be deposited");
		long amount = inputLong();
		
		inputString();
		
		System.out.println("Enter phone number");
		long phoneNumber = inputLong();
		
		String depositQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		
		String UpdateAccBalanceQry = "UPDATE account_details"
				+ " SET account_details.Balance = account_details.Balance + ? WHERE AccountNumber = ?";
		
		try {
			pstmt = dbCon.prepareStatement(depositQry);
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, phoneNumber);
			ResultSet rs = pstmt1.executeQuery();
			rs.next();
			int accNum = rs.getInt("AccountNumber");
			
			String time = getCurrentTime();
			
			pstmt.setInt(1, accNum);
			pstmt.setLong(2, amount);
			pstmt.setString(3, time);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("Transaction added..");
			else
				System.out.println("Transaction Not added");
			
			pstmt = dbCon.prepareStatement(UpdateAccBalanceQry);
			pstmt.setLong(1, amount);
			pstmt.setInt(2, accNum);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("Balance updated");
			else
				System.out.println("Balance not updated");
				
		} catch (SQLException e) {
			System.out.println("issue with deposit " + e.getMessage());
		}		
	}

	@Override
	public void withdrawMoney(Connection dbCon) {
		System.out.println("Enter the amount to be withdrawn");
		long amount = inputLong();
		
		inputString();
		
		System.out.println("Enter phone number");
		long phoneNumber = inputLong();
				
		String depositQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		
		String UpdateAccBalanceQry = "UPDATE account_details "
				+ "SET account_details.Balance = account_details.Balance - ? WHERE AccountNumber = ?";
		
		String balanceShow = "SELECT Balance FROM account_details WHERE AccountNumber = ?";
		
		try {
			pstmt = dbCon.prepareStatement(depositQry);
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, phoneNumber);
			ResultSet rs = pstmt1.executeQuery();
			rs.next();
			int accNum = rs.getInt("AccountNumber");
			
			pstmt1 = dbCon.prepareStatement(balanceShow);
			pstmt1.setInt(1, accNum);
			
			ResultSet rs1 = pstmt1.executeQuery();
			rs1.next();
			long bal = rs1.getInt("Balance");
			
			if(bal >= amount) {
			
				String time = getCurrentTime();
				
				pstmt.setInt(1, accNum);
				pstmt.setLong(2, -amount);
				pstmt.setString(3, time);
				
				if(pstmt.executeUpdate() > 0)
					System.out.println("Transaction added..");
				else
					System.out.println("Transaction Not added");
				
				pstmt = dbCon.prepareStatement(UpdateAccBalanceQry);
				pstmt.setLong(1, amount);
				pstmt.setInt(2, accNum);
				
				if(pstmt.executeUpdate() > 0)
					System.out.println("Balance updated");
				else
					System.out.println("Balance not updated");
			}
			else
				System.out.println("Insufficient balance..");
				
		} catch (SQLException e) {
			System.out.println("issue with withdrawl " + e.getMessage());
		}		
	}

	@Override
	public void transactionDetails(Connection dbCon) {
		String fetchTransactionQry = "SELECT * FROM transaction_details WHERE AccountNumber = ?";
		System.out.println("Enter phone number");
		long phoneNumber = inputLong();
		
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		
		
		try {
			pstmt = dbCon.prepareStatement(fetchTransactionQry);
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, phoneNumber);
			ResultSet rs1 = pstmt1.executeQuery();
			rs1.next();
			int accNum = rs1.getInt("AccountNumber");
			pstmt.setInt(1, accNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.print("Transaction Number : " + rs.getInt("Transaction Number"));
				System.out.print(", Account Number : " + rs.getInt("AccountNumber"));
				System.out.print(", transaction Amount : " + rs.getInt("transactionAmount"));
				System.out.println(", transaction time : " + rs.getString("transactionTime"));
			}
			
		} catch (SQLException e) {
			System.out.println("issue with transaction detail output " + e.getMessage() );	
		}
		
	}

	@Override
	public void fundTransfer(Connection dbCon) {
		String senderQry = "UPDATE account_details SET account_details.Balance = account_details.Balance - ? WHERE AccountNumber = ?";
		
		String receiveQry = "UPDATE account_details SET account_details.Balance = account_details.Balance + ? WHERE AccountNumber = ?";
		
		String sendTransactQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		String receiveTransactQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		System.out.println("Enter account number for sender");
		int senderAccNum = inputInt();
		
		System.out.println("Enter account number for reciever");
		int receiveAccNum = inputInt();
		
		System.out.println("Enter the amount to be transfered");
		long amount = inputLong();
		
		try {
			String time = getCurrentTime();
			pstmt = dbCon.prepareStatement(sendTransactQry);
			
			pstmt.setInt(1, senderAccNum);
			pstmt.setLong(2, -amount);
			pstmt.setString(3, time);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("Send Transaction Complete");
			else
				System.out.println("Send transaction error..");
			
			pstmt = dbCon.prepareStatement(senderQry);
			
			pstmt.setLong(1, amount);
			pstmt.setInt(2, senderAccNum);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("Sender Balance Updated");
			else
				System.out.println("sender balance update error");
			
			pstmt = dbCon.prepareStatement(receiveTransactQry);
			pstmt.setInt(1, receiveAccNum);
			pstmt.setLong(2, amount);
			pstmt.setString(3, time);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("Receive Transaction Complete");
			else
				System.out.println("Receive Transaction Complete");
			
			pstmt = dbCon.prepareStatement(receiveQry);
			pstmt.setLong(1, amount);
			pstmt.setInt(2, receiveAccNum);
			
			if(pstmt.executeUpdate() > 0)
				System.out.println("Receiver balance updated");
			else
				System.out.println("Receiver balance update error..");
			
		} catch (SQLException e) {
			System.out.println("issue with fund transfer " + e.getMessage());
		}		
		
	}
	
}
