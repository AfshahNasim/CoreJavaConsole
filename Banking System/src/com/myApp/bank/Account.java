package com.myApp.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private int accountNumber;
	private int pinNumber;
	private double balance=0;
	private String accountType="";
	private String customerName="";
	private String customerID="";
	private List<AccountTransaction> transactions;

	/* Non- Parameterized constructor	*/
	public Account()
	{
		this.transactions = new ArrayList<>(); //	Array-list for holding the transactions
	}
	
	/*	Function to return list of transactions of a specific account */
	public List<AccountTransaction> getTransactions() 
	{
		return transactions;
	}
	
	/*	Function to add transaction to the list of a specific account */
	public void setTransactions( AccountTransaction transaction)
	{
		transactions.add(transaction);
	}
	
	/*	Getter-Setters */
	public int getAccountNumber() 
	{
			return accountNumber;
	}

	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public int getPinNumber() 
	{
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) 
	{
		this.pinNumber = pinNumber;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getCustomerID() 
	{
		return customerID;
	}

	public void setCustomerID(String customerID)
	{
		this.customerID = customerID;
	}
	
	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public double getBalance() 
	{
		return balance;
	}
	
	public void setBalance(double balance) 
	{
	this.balance =balance;
	}

}
