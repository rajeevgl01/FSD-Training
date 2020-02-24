package com.wallet.bean;

public class Bean {
	private long PhoneNumber;
	private long Balance;
	private int AccountNumber;
	private int transactionNumber;
	private String exception;
	private String transactionTime;
	private int transactionAmount;
	private String name;
	private long amount;
	private String password;
	
	public int getTransactionNumber() {
		return transactionNumber;
	}
	
	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
		
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
	
	public int getTransactionAmount() {
		return transactionAmount;
	}
	
	public void setTransactionAmount(int transactionAmount) {
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
	
	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String printData() {
		return    " Transaction Number : " + this.transactionNumber + " ,"
				+ "  Account Number : " + this.AccountNumber + " ,"
				+ "  Transaction Amount : " + this.transactionAmount + " ,"
				+ "  Transaction time : " + this.transactionTime;
	}
}
