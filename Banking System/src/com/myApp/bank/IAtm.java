package com.myApp.bank;

public interface IAtm {
	
	void withdrawAmount(double amount);	
	void depositAmount(double amount);
	double getAccountBalance();
	void getTransactionHistory();


}
