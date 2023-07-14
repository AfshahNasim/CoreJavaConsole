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

public Account()
{
	this.transactions = new ArrayList<>();
}
public List<AccountTransaction> getTransactions() {
	return transactions;
}

public void setTransactions( AccountTransaction transaction) {
	transactions.add(transaction);
}

public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}
	
/*public double calulateCurrentBalanceWithraw(double amount) {
	checkingBalance = currentBalance-amount;
	return checkingBalance;
}

public double calulateCheckingBalanceDeposit(double amount) {
	checkingBalance = checkingBalance+amount;
	return checkingBalance;
}
*/

public void setBalance(double balance) {
	this.balance =balance;
}

@Override
public String toString() {
	return "Account [accountNumber=" + accountNumber + ", pinNumber=" + pinNumber + ", customerName=" + customerName
			+ ", customerID=" + customerID + "]";
}
	
	

}
