package com.myApp.bank;

public class AccountTransaction {

	
	private String datetime;
	private String transactionType;
	private double amount;
	
	
	public AccountTransaction(String date, String transactionType, double amount) {
		this.datetime = date;
		this.transactionType = transactionType;
		this.amount = amount;
	}
	public String getDatetime() {
		return datetime;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public double getAmount() {
		return amount;
	}
	
	
	
}
