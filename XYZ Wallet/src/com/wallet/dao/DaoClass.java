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
	Bean b = new Bean();
	
	public DaoClass() {
		try {
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/wallet?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			System.out.println("Issue with Connection" + e.getMessage());
		}
	}

	@Override
	public int addAccountDetails(Bean b) {
		String nameQry = "INSERT INTO account_details VALUES (?,?,?,?)";
		int n = 0;
		try {
			pstmt = dbCon.prepareStatement(nameQry);
			
			pstmt.setLong(1, b.getAccountNumber());
			pstmt.setString(2, b.getName());
			pstmt.setLong(3, b.getBalance());
			pstmt.setLong(4, b.getPhoneNumber());
			
			if(pstmt.executeUpdate() > 0)
				n++;
		} catch (SQLException e) {
			System.out.println("issue with prepare statement : " + e.getMessage() );
		}
		return n;
	}

	@Override
	public int updatePhoneNumber(Bean b) {
		String updateNumber = "UPDATE account_details SET PhoneNumber = ? WHERE AccountNumber = ?";
		int n = 0;
		try {
			pstmt = dbCon.prepareStatement(updateNumber);
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
	public ResultSet getBalance(Bean b) {
		String fetchBalance = "SELECT Balance FROM account_details WHERE AccountNumber = ?";
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		ResultSet rs2 = null;
		try {
			pstmt = dbCon.prepareStatement(getAccountNumber);
			
			pstmt.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int accNum = rs.getInt("AccountNumber");
			
			pstmt = dbCon.prepareStatement(fetchBalance);
			pstmt.setInt(1, accNum);
			
			ResultSet rs1 = pstmt.executeQuery();
			if(rs1.next())
				rs2 = rs1;
		} catch (SQLException e) {
			System.out.println("issue with balance retrieval " + e.getMessage() );
		}	
		return rs2;
	}

	@Override
	public int depositMoney(Bean b) {		
		String depositQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		
		String UpdateAccBalanceQry = "UPDATE account_details"
				+ " SET account_details.Balance = account_details.Balance + ? WHERE AccountNumber = ?";
		
		int n = 0;
		
		try {
			pstmt = dbCon.prepareStatement(depositQry);
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt1.executeQuery();
			rs.next();
			int accNum = rs.getInt("AccountNumber");
			
			String time = getCurrentTime();
			
			pstmt.setInt(1, accNum);
			pstmt.setLong(2, b.getAmount());
			pstmt.setString(3, time);
			
			if(pstmt.executeUpdate() > 0)
				n++;
			
			pstmt = dbCon.prepareStatement(UpdateAccBalanceQry);
			pstmt.setLong(1, b.getAmount());
			pstmt.setInt(2, accNum);
			
			if(pstmt.executeUpdate() > 0)
				n++;
				
		} catch (SQLException e) {
			System.out.println("issue with deposit " + e.getMessage());
		}
		return n;
	}

	@Override
	public int withdrawMoney(Bean b) {
		String withdrawQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (?,?,?)";
		
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		
		String UpdateAccBalanceQry = "UPDATE account_details "
				+ "SET account_details.Balance = account_details.Balance - ? WHERE AccountNumber = ?";
		
		int n = 0;
		
		try {
			pstmt = dbCon.prepareStatement(withdrawQry);
			pstmt1 = dbCon.prepareStatement(getAccountNumber);
			
			pstmt1.setLong(1, b.getPhoneNumber());
			ResultSet rs = pstmt1.executeQuery();
			rs.next();
			int accNum = rs.getInt("AccountNumber");
			
			long bal = (new DaoClass().getBalance(b)).getInt("Balance");
			
			if(bal >= b.getAmount()) {
			
				String time = getCurrentTime();
				
				pstmt.setInt(1, accNum);
				pstmt.setLong(2, - b.getAmount());
				pstmt.setString(3, time);
				
				if(pstmt.executeUpdate() > 0)
					n++;
				
				pstmt = dbCon.prepareStatement(UpdateAccBalanceQry);
				pstmt.setLong(1, b.getAmount());
				pstmt.setInt(2, accNum);
				
				if(pstmt.executeUpdate() > 0)
					n++;
			}
			else
				n--;
				
		} catch (SQLException e) {
			System.out.println("issue with withdrawl " + e.getMessage());
		}
		return n;
	}

	@Override
	public List<Bean> transactionDetails(Bean b) {
		List<Bean> bilers = new ArrayList<Bean>();
		String queryGet = "SELECT * FROM transaction_details WHERE AccountNumber = ?";
		
		String getAccNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = ?";
		
		try {
			pstmt = dbCon.prepareStatement(getAccNumber);
			pstmt.setLong(1, b.getPhoneNumber());
			ResultSet rs1 = pstmt.executeQuery();
			rs1.next();
			int accNum = rs1.getInt("AccountNumber");
			pstmt = dbCon.prepareStatement(queryGet);
			pstmt.setInt(1, accNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Bean b1 = new Bean();
				b1.setTransactionNumber(rs.getInt("Transaction Number"));
				b1.setAccountNumber(rs.getInt("accountNumber"));
				b1.setTransactionAmount(rs.getInt("transactionAmount"));
				b1.setTransactionTime(rs.getString("transactionTime"));
				bilers.add(b1);
			}
		} catch (SQLException e) {
			System.out.println("Issue with transaction details " + e.getMessage() );
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
