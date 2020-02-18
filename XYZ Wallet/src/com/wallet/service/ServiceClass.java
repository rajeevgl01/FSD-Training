package com.wallet.service;

import java.sql.ResultSet;
import java.util.List;
import com.wallet.bean.Bean;
import com.wallet.dao.DaoClass;

public class ServiceClass implements ServiceInterface {
	DaoClass dc = new DaoClass();
	
	@Override
	public int InsertServe(Bean b) {
		return dc.addAccountDetails(b);
	}

	@Override
	public int UpdatePhoneServe(Bean b) {
		return dc.updatePhoneNumber(b);
	}

	@Override
	public int UpdateNameServe(Bean b) {
		return dc.updateName(b);
	}

	@Override
	public ResultSet BalanceServe(Bean b) {
		return dc.getBalance(b);
	}

	@Override
	public int DepositServe(Bean b) {
		return dc.depositMoney(b);
	}

	@Override
	public int WithdrawServe(Bean b) {
		return dc.withdrawMoney(b);
	}
	
	@Override
	public List<Bean> TransactServe(Bean b) {
		return dc.transactionDetails(b);
	}
	
	
	

}
