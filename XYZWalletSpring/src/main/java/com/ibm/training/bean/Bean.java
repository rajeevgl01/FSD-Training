package com.ibm.training.bean;

import org.springframework.stereotype.Component;

@Component("be")
public class Bean {
	private long PhoneNumber;
	private long Balance;
	private int AccountNumber;
	private int transactionNumber;
	
	public int getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	private String transactionTime;
	private long transactionAmount;
	private String name;
	private long amount;
	
	public long getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public long getBalance() {
		return Balance;
	}
	public void setBalance(long balance) {
		Balance = balance;
	}
	public int getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public long getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return("Transaction Number : " + getTransactionNumber() + " ,Account Number : " + getAccountNumber() + " ,Transaction Amount : " + getTransactionAmount() + " ,Time : " + getTransactionTime());
	}
}


