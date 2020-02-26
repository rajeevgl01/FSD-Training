package com.ibm.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.ibm.training.bean.Bean;
import com.ibm.training.dao.DaoClass;

@Service("sc")
public class ServiceClass implements ServiceInterface {
	@Autowired
	DaoClass dc;
	
	public DaoClass ServiceClass() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		dc = context.getBean("dao", DaoClass.class);
		return dc;
	}
	
	public boolean InsertServe(Bean b) {
		return dc.addAccountDetails(b);
	}

	public boolean UpdatePhoneServe(Bean b) {
		return dc.updatePhoneNumber(b);
	}

	public boolean UpdateNameServe(Bean b) {
		return dc.updateName(b);
	}

	public long BalanceServe(Bean b) {
		return dc.Balance(b);
	}

	public boolean DepositServe(Bean b) {
		return dc.depositMoney(b);
	}

	public int WithdrawServe(Bean b) {
		return dc.withdrawMoney(b);
	}
	
	public List<Bean> TransactServe(Bean b) {
		return dc.transactionDetails(b);
	}
	
	public boolean checkNumberServe(Bean b) {
		return dc.checkNumber(b);
	}
	
	public String CurrentTime(Bean b) {
		return dc.getCurrentTime();
	}
}
