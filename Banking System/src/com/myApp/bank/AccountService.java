package com.myApp.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AccountService implements IAtm {
	
	String customerID="";
	int accountnum;
	String customerName="";
	int pinNum;
	private HashMap<Integer,Account> currentMap;
	private static AccountService instance;
	Account account;
	
	/*	Private non-Parameterized constructor for creating only one Map	*/
	private AccountService() 
	{
		this.currentMap = new HashMap<Integer,Account>();	
	}
	
	/*	Private constructor	 for creating only one account service (i.e singleton)	*/
	public static AccountService getInstance()
	{
		if(instance == null)
		instance= new AccountService();
		return instance;
	}
	
	/* Function to set customer name	*/
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/*	Function to set Pin	*/
	public void setPinNum(int pinNum) {
		this.pinNum = pinNum;
	}
	
	/*	Function to get transaction history of account	*/
	public void getTransactionHistory()
	{
		List<AccountTransaction> translist = new ArrayList<AccountTransaction>();
		translist = account.getTransactions();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|\tTRANSACTION_DATE\t|\tTRANSACTION_TYPE\t|\tAMOUNT\t|\tAVAILABLE_BALANCE\t|");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		for(AccountTransaction list	: translist)
		{
			System.out.print("\t"+list.getDatetime()+"\t\t"+list.getTransactionType()+"\t\t"+list.getAmount());
			System.out.println();
		}
		System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t"+account.getBalance());
	}
	
	/*	Function to withdraw Amount	*/
	public void withdrawAmount(double amount) 
	{
		AccountTransaction trans = new AccountTransaction(LocalDateTime.now().format(DateTimeFormatter.ofPattern("DD-MM-YYYY HH:mm:ss")), "Debit", amount);
		double finalamount = getAccountBalance()- amount;
		account.setBalance(finalamount);
		account.setTransactions(trans);
		System.out.println("Your account is debited with : "+amount);
	}

	/*	Function to deposit Amount	*/
	public void depositAmount(double amount)
	{
		AccountTransaction trans = new AccountTransaction(LocalDateTime.now().format(DateTimeFormatter.ofPattern("DD-MM-YYYY HH:mm:ss")), "Credit", amount);
		double finalamount = getAccountBalance()+ amount;
		account.setBalance(finalamount);
		account.setTransactions(trans);
		System.out.println("Your account is credited with : "+amount);
	}
	
	/*	Function to get the balance */
	public double getAccountBalance() 
	{
		
		return account.getBalance();
		
	}
	
	/* Function to Traverse HashMap and find desired Account on basis of account number	*/
	public Account getAccount(int accnum)
	{
		for(Integer accountNum: currentMap.keySet())
		{
			if(accountNum == accnum)
			{
				account = currentMap.get(accountNum);
				break;
			}
		}
		return account;
	}

	/*	Function to create account on basis of type*/
	public void createAccount(String accountType)
	{
		/*	Creating new Account	*/
		account = new Account();
		
		/*	Generating random number between 1 and 50 to create unique customer ID	*/
		if(accountType.equals("Saving"))
			customerID = "SA-"+ ThreadLocalRandom.current().nextInt(1,50)+ customerName.substring(0, 3).toUpperCase()+ "0000" + ThreadLocalRandom.current().nextInt(50,100);
		else
			customerID = "CU-"+ ThreadLocalRandom.current().nextInt(1,50)+ customerName.substring(0, 3).toUpperCase()+ "0000" + ThreadLocalRandom.current().nextInt(50,100);
		
		accountnum = ThreadLocalRandom.current().nextInt(100000,999999);
		account.setCustomerID(customerID);
		account.setCustomerName(customerName);
		account.setAccountNumber(accountnum);
		account.setPinNumber(pinNum);
		account.setAccountType(accountType);
		currentMap.put(accountnum,account);
		currentMap.put(accountnum,account);
		displayCreatedAccountDetails();
	}

	/*	Function to login into Account	*/
	public boolean login(int accnum, int pin) 
	{
		 
		if(validateAccountLogin(accnum,pin))
		{
			System.out.println("Login successfull..!!");
			displayLoggedInAccountDetails();
			return true;
		}
		else
		{
			System.err.println("Invalid account number or PIN. Please try again.");
			return false;
		}
			
	}

	/* Function to display details of login account */
	public void displayLoggedInAccountDetails() 
	{
		
		System.out.println("\tYour account created successfully details are below ");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|\tCUSTOMER_ID\t|\tCUSTOMER_NAME\t|\tACCOUNT_NUMBER\t|\tACCOUNT_TYPE\t|\tBALANCE\t\t|");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("    "+account.getCustomerID()+"\t\t"+account.getCustomerName()+"\t\t"+account.getAccountNumber()+"\t\t\t"+account.getAccountType()+"\t\t\t\t"+account.getBalance());
			
	}

	/*	Function to validate Account Login	*/
	public boolean validateAccountLogin(int accnum,int pin)
	{
			Account acc = getAccount(accnum);
		    if (acc.getAccountNumber() == accnum && acc.getPinNumber() == pin) 
		        return true;
		     else 
		        return false;
	}	
	
	/*	Function to display the created account details	*/
	public void displayCreatedAccountDetails()
	{
		
		System.out.println("\tYour account created successfully details are below ");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|\tCUSTOMER_ID\t|\tCUSTOMER_NAME\t|\tACCOUNT_NUMBER\t|\tACCOUNT_TYPE\t|\tBALANCE\t\t|");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("    "+account.getCustomerID()+"\t\t"+account.getCustomerName()+"\t\t"+account.getAccountNumber()+"\t\t\t"+account.getAccountType()+"\t\t\t"+account.getBalance());//+balance);
	
	}
	
	/* Function to display all account details	*/
	public void displayAllAccount()
	{
		if(account == null)
			System.err.println("NO Account Exist..!!");
		else
		{
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|\tCUSTOMER_ID\t|\tCUSTOMER_NAME\t|\tACCOUNT_NUMBER\t|\tACCOUNT_TYPE\t|\tBALANCE\t\t|");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		for(Integer entrykey:currentMap.keySet())
		{
		System.out.println("    "+currentMap.get(entrykey).getCustomerID()+"\t\t"+currentMap.get(entrykey).getCustomerName()+"\t\t"+currentMap.get(entrykey).getAccountNumber()+"\t\t\t"+currentMap.get(entrykey).getAccountType()+"\t\t\t\t"+currentMap.get(entrykey).getBalance());
		}
		}
	}

}
