package com.wallet.dao;

import java.sql.ResultSet;
import java.util.List;

import com.wallet.bean.Bean;

public interface DaoInterface {

	
	public int addAccountDetails(Bean b);
	public int updatePhoneNumber(Bean b);
	public int updateName(Bean b);
	public ResultSet getBalance(Bean b);
	public int depositMoney(Bean b);
	public int withdrawMoney(Bean b);
	public List<Bean> transactionDetails(Bean b);
	
	public String getCurrentTime();
}
