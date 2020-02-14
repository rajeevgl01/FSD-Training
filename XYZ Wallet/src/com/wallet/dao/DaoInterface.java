package com.wallet.dao;

import java.sql.Connection;

public interface DaoInterface {
	
	public Connection con(Connection dbCon);
	
	public void addAccountDetails(Connection dbCon);
	public void updateAccountDetails(Connection dbCon);
	public void getBalance(Connection dbCon);
	public void depositMoney(Connection dbCon);
	public void withdrawMoney(Connection dbCon);
	public void transactionDetails(Connection dbCon);
	public void fundTransfer(Connection dbCon);
	
	public String getCurrentTime();
	
}
