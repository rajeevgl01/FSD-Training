package com.ibm.training.service;

import java.util.List;

import com.ibm.training.bean.Bean;

public interface ServiceInterface {
	public boolean InsertServe(Bean b);
	public boolean UpdatePhoneServe(Bean b);
	public boolean UpdateNameServe(Bean b);
	public long BalanceServe(Bean b);
	public boolean DepositServe(Bean b);
	public int WithdrawServe(Bean b);
	public List<Bean> TransactServe(Bean b);
	public boolean checkNumberServe(Bean b);
	public String CurrentTime(Bean b);
}
