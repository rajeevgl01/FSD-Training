package com.wallet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.wallet.bean.Bean;

public class DaoClass implements DaoInterface {
	PreparedStatement pstmt, pstmt1;
	Connection dbCon;
	public Bean b = new Bean();
	
	public DaoClass() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/wallet?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			System.out.println("Issue with Connection" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found " + e.getMessage());
		}
	}
	
	@Override
	public boolean getPhone(Bean b){
		String getQry = "SELECT * FROM user_credentials WHERE PhoneNumber = ?";
		
		try {
			pstmt = dbCon.prepareStatement(getQry);
			pstmt.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getLong("PhoneNumber") == b.getPhoneNumber()) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Issue with getting PhoneNumber " + e.getMessage());
		}
		
		return false;
	}
	
	@Override
	public boolean getPassword(Bean b){
		String getPassQry = "SELECT password FROM user_credentials WHERE PhoneNumber = ?";

		try {
			pstmt = dbCon.prepareStatement(getPassQry);
			pstmt.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			String pass = rs.getString("password");
			
			if(pass.equals(b.getPassword()))
				return true;
			
		} catch (SQLException e) {
			System.out.println("Issue with getting password " + e.getMessage());
		}
		
		return false;
	}
	
	@Override
	public ResultSet getUserName(Bean b) {
		String getNameQry = "SELECT Name FROM account_details WHERE PhoneNumber = ?";
		ResultSet rs1 = null;
		
		try {
			pstmt = dbCon.prepareStatement(getNameQry);
			pstmt.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) 
				rs1 = rs;
			
		} catch (SQLException e) {
			System.out.println("Issue with getting userName " + e.getMessage());
		}
		return rs1;
	}
	
	@Override
	public int addAccountDetails(Bean b) {
		String nameQry = "INSERT INTO account_details VALUES (?,?,?,?)";
		String credQry = "INSERT INTO user_credentials VALUES(?,?,?)";
		int n = 0;
		try {
			pstmt = dbCon.prepareStatement(credQry);
			pstmt.setLong(1, b.getPhoneNumber());
			pstmt.setString(2, b.getPassword());
			pstmt.setInt(3, b.getAccountNumber());
			
			if(pstmt.executeUpdate() > 0) {
				n++;
				pstmt = dbCon.prepareStatement(nameQry);
				pstmt.setInt(1, b.getAccountNumber());
				pstmt.setString(2, b.getName());
				pstmt.setLong(3, b.getBalance());
				pstmt.setLong(4, b.getPhoneNumber());
				
				if(pstmt.executeUpdate() > 0)
					n++;
			}
		} catch (SQLException e) {
			System.out.println("issue with add account : " + e.getMessage() );
		}
		return n;
	}

	@Override
	public int updatePhoneNumber(Bean b) {
		String updateNumber = "UPDATE account_details SET PhoneNumber = ? WHERE AccountNumber = ?";
		String updateNumber1 = "UPDATE user_credentials SET PhoneNumber = ? WHERE AccountNumber = ?";
		int n = 0;
		try {
			pstmt = dbCon.prepareStatement(updateNumber);
			pstmt.setLong(1, b.getPhoneNumber());
			pstmt.setInt(2, b.getAccountNumber());
			
			if(pstmt.executeUpdate() > 0)
				pstmt = dbCon.prepareStatement(updateNumber1);
				pstmt.setLong(1, b.getPhoneNumber());
				pstmt.setInt(2, b.getAccountNumber());
				if(pstmt.executeUpdate() > 0)
					n++;

		} catch (SQLException e) {
			System.out.println("issue with phone number update " + e.getMessage());
		}
		return n;
	}

	@Override
	public int updateName(Bean b) {
		String updateName = "UPDATE account_details SET Name = ? WHERE AccountNumber = ?";
		int n = 0;
		
		try {
			pstmt = dbCon.prepareStatement(updateName);
			pstmt.setString(1, b.getName());
			pstmt.setInt(2, b.getAccountNumber());
			
			if(pstmt.executeUpdate() > 0)
				n++;
			
		} catch (SQLException e) {
			System.out.println("issue with name update " + e.getMessage());
		}
		return n;
	}

	@Override
	public Bean getBalance(Bean b) {
		String fetchBalance = "SELECT Balance FROM account_details WHERE PhoneNumber = ?";
		
		try {
			pstmt = dbCon.prepareStatement(fetchBalance);
			
			pstmt.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				b.setBalance(rs.getLong("Balance"));
		} catch (SQLException e) {
			System.out.println("issue with balance retrieval " + e.getMessage() );
		}	
		return b;
	}

	@Override
	public int depositMoney(Bean b) {		
		String depositQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		String getAccountNumber = "SELECT AccountNumber FROM user_credentials WHERE PhoneNumber = ?";
		
		String UpdateAccBalanceQry = "UPDATE account_details"
				+ " SET account_details.Balance = account_details.Balance + ? WHERE AccountNumber = ?";
		
		int n = 0;
		
		try {
			pstmt = dbCon.prepareStatement(depositQry);
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt1.executeQuery();
			
			int accNum = 0;
			
			while(rs.next()) {
				accNum = rs.getInt("AccountNumber");
			}
			String time = getCurrentTime();
			
			pstmt.setInt(1, accNum);
			pstmt.setLong(2, b.getAmount());
			pstmt.setString(3, time);
			
			if(pstmt.executeUpdate() > 0) {
				n++;
			
				pstmt = dbCon.prepareStatement(UpdateAccBalanceQry);
				pstmt.setLong(1, b.getAmount());
				pstmt.setInt(2, accNum);
				
				if(pstmt.executeUpdate() > 0)
					n++;
			}
				
		} catch (SQLException e) {
			System.out.println("issue with deposit " + e.getMessage());
		}
		return n;
	}

	@Override
	public int withdrawMoney(Bean b) {
		String withdrawQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		String getAccountNumber = "SELECT AccountNumber FROM user_credentials WHERE PhoneNumber = ?";
		
		String UpdateAccBalanceQry = "UPDATE account_details "
				+ "SET account_details.Balance = account_details.Balance - ? WHERE AccountNumber = ?";
		
		int n = 0;
		
		try {
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, b.getPhoneNumber());
			
			ResultSet rs = pstmt1.executeQuery();
			
			int accNum = 0;
			while(rs.next()) {
				accNum = rs.getInt("AccountNumber");
			}
			b.setAccountNumber(accNum);
			
			long bal = getBalance(b).getBalance();
			
			if(bal >= b.getAmount()) {
			
				pstmt = dbCon.prepareStatement(withdrawQry);
				String time = getCurrentTime();
				
				pstmt.setInt(1, b.getAccountNumber());
				pstmt.setLong(2, -b.getAmount());
				pstmt.setString(3, time);
				
				if(pstmt.executeUpdate() > 0) {
					n++;
					System.out.println(n);
				}
				pstmt = dbCon.prepareStatement(UpdateAccBalanceQry);
				pstmt.setLong(1, b.getAmount());
				pstmt.setInt(2, accNum);
				
				if(pstmt.executeUpdate() > 0)
					n++;
					System.out.println(n);
			}
			else
				n--;
				
		} catch (SQLException e) {
			System.out.println("issue with withdrawl " + e.getMessage());
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public List<Bean> transactionDetails(Bean b) {
		List<Bean> bilers = new ArrayList<Bean>();
		String queryGet = "SELECT * FROM transaction_details WHERE AccountNumber = ?";
		
		String getAccNumber = "SELECT AccountNumber FROM user_credentials WHERE PhoneNumber = ?";
		int accNum = 0;
		try {
			pstmt1 = dbCon.prepareStatement(getAccNumber);
			
			pstmt1.setLong(1, b.getPhoneNumber());
			
			ResultSet rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				accNum = rs1.getInt("AccountNumber");
			}
			b.setAccountNumber(accNum); 
			
			pstmt = dbCon.prepareStatement(queryGet);
			pstmt.setInt(1, b.getAccountNumber());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Bean b1 = new Bean();
				b1.setTransactionNumber(rs.getInt("Transaction Number"));
				b1.setAccountNumber(rs.getInt("AccountNumber"));
				b1.setTransactionAmount(rs.getInt("transactionAmount"));
				b1.setTransactionTime(rs.getString("transactionTime"));
				bilers.add(b1);
			}
			
		} catch (SQLException e) {
			System.out.println("Issue with transaction details " + e.getMessage() );
			e.printStackTrace();
		}
		return bilers ;
	}
	
	@Override
	public String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Calendar calobj = Calendar.getInstance();
	    return(df.format(calobj.getTime()));
	}

}
