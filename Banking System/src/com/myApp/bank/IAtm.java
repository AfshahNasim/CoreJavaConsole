package com.myApp.bank;

public interface IAtm {
	
	double getAccountBalance();
	void withdrawAmount(double amount);
	void depositAmount(double amount);


}
