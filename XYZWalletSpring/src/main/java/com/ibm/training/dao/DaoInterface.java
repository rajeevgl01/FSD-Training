package com.ibm.training.dao;

import java.util.List;

import com.ibm.training.bean.Bean;


public interface DaoInterface {

	public boolean addAccountDetails(Bean b);
	public boolean updatePhoneNumber(Bean b);
	public boolean updateName(Bean b);
	public Long Balance(Bean b);
	public boolean depositMoney(Bean b);
	public int withdrawMoney(Bean b);
	public List<Bean> transactionDetails(Bean b);
	public boolean checkNumber(Bean b);
	public String getCurrentTime();
}
