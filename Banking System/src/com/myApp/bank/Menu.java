package com.myApp.bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	int choice;
	String input;
	int pin=0;
	int accountNum=0;
	String accountType="";
	AccountService accountService;
	Scanner scan = new Scanner(System.in);
	
	/*	Menu Function to show main menu */
	public void showMainMenu()  
	{
		try {
				 
				do {
					
					System.out.println();
					System.out.println("\t1. Login into your account");
					System.out.println("\t2. Create Account");
					System.out.println("\t3. View  All Accounts");
					System.out.println("\t4. Exit");
					System.out.print("\n\tEnter your Choice:");
					choice = scan.nextInt();
					
					}while(!validateChoice(4));
		
				switch(choice)
				{
				case 1 : getLogin();break;
				case 2 : createAccount();showMainMenu(); break;
				case 3 : displayAllAccount();break;
				case 4 : System.out.print("\n\tThank You for using ATM Service..!!");scan.close();System.exit(0); 
				}
			}catch(InputMismatchException e)
			{
				System.err.println("\tInvalid Character(s). Only Numbers.");
				scan.nextLine(); //clear the invalid input from the scanner if exception occurs 
				showMainMenu();	//call the method again to continue the menu
			}
	}
	
	/*	Function to display all account	*/
	private void displayAllAccount()  
	{
		
		if(accountService==null)
			System.err.print("\n\tNo Account Exist..!!");
		else
			accountService.displayAllAccount();
			showMainMenu();
			
	}

	/*	Function to login into account	*/
	public void getLogin() 
	{
		try
		{
		if(accountService == null)
		{
			System.err.println("\n\tNo Account Exist..!! Create a single account first..!!");
			showMainMenu();
		}
		else
		{
			//scan.nextLine();//using to clear the input buffer as variable 'choice' is still there
			System.out.print("\nEnter your Account Number:");
			
				accountNum = scan.nextInt();
				do
				{
					System.out.print("\nEnter your pin :");
					pin = scan.nextInt();
				} while(!validatePin(pin));
				
			if(accountService.login(accountNum,pin))
				displayToDo();
			else
				showMainMenu();
		}
		}catch(InputMismatchException e)
		{
			System.err.println("\tInvalid Character(s). Only Numbers.");
		}
	}

	/*	Function to display withdraw and deposit menu	*/
	public void displayToDo() 
	{
		try
		{
			do {
				System.out.print("\n");
				System.out.println("\t1. Withdraw amount");
				System.out.println("\t2. Deposit amount");
				System.out.println("\t3. Check Balance");
				System.out.println("\t4. Back to Main Menu");
				System.out.println("\t5. Exit");
				System.out.print("\n\tEnter your Choice:");
				choice = scan.nextInt();
				}
			while(!validateChoice(4));
			
			switch(choice)
			{
			case 1 : withdrawFromAccount(); break;
			case 2 : depositIntoAccount();break;
			case 3 : checkBalance();break;
			case 4 : showMainMenu();break;
			case 5 : System.out.print("\n\tThank You for using ATM Service..!!"); scan.close(); System.exit(0); 
			}
		}catch(InputMismatchException e)
		{
			System.err.println("\tInvalid Character(s). Only Numbers.");
			scan.nextLine(); //clear the invalid input from the scanner if exception occurs 
			displayToDo();	//call the method again to continue the menus
		}
		
	}

	/*	Function to validate the deposit amount	*/
	public void depositIntoAccount()  
	{
		double amount = 0; 
		boolean validAmount = false; 
		//if exception occurs then again we will take the amount
		do {
			try
			{
				System.out.println("Enter the amount you want to deposit: ");
				amount = scan.nextDouble();
				
			if(amount<=0)
				System.err.println("Deposit Amount cannot be negative or zero..!!");
			else
			{
				validAmount = true;
				accountService.depositAmount(amount);	
			}
			
			}catch(InputMismatchException e)
			{
				System.err.println("\tInvalid Character(s).Only Numbers.");
				scan.nextLine();	
				validAmount = false;
			}
		}while(!validAmount);
			displayToDo();
	}
	
	/*	Function to check balance	*/
	public void checkBalance() 
	{
		
		System.out.println("Your Account Balance is: "+accountService.getAccountBalance());
		displayToDo();
	}

	/*	Function to validate the withdraw amount	*/
	public void withdrawFromAccount() 
	{
		double amount = 0;
		boolean validAmount = false;
		if(accountService.getAccountBalance() == 0)
		{
			System.err.println("Your Account Balance is 0.00");
			displayToDo();
		}
		else 
		{
		do {
			try
			{
				System.out.print("\n\tEnter the amount you want to withdraw: ");
				 amount = scan.nextDouble();
				 validAmount = true;
			}catch(InputMismatchException e)
			{
				System.err.println("\tInvalid Character(s). Only Numbers.");
				scan.nextLine();
				validAmount = false;
			}
		}while(!validAmount);
		
			if(amount<=0)
			{
				System.err.println("\tDeposit Amount cannot be negative or zero..!!");
				displayToDo();
			}
			else if(amount> accountService.getAccountBalance())
			{
				System.err.println("\tInsufficient Balance cannot withdraw above present account balance..!!");
				displayToDo();
			}
			else
			{
			accountService.withdrawAmount(amount);
			displayToDo();
			}
		}
		
	}

	/*	Menu function to create Account 	*/
	public void createAccount() 	
	{
		try
		{
			do {
				System.out.print("\n");
				System.out.println("\t1. Saving Account");
				System.out.println("\t2. Current Account");
				System.out.println("\t3. Back to Main Menu");
				System.out.println("\t4. Exit");
				System.out.print("\n\tEnter your Choice:");
				choice = scan.nextInt();
				}while(!validateChoice(4));
		
			
			switch(choice)
			{
			case 1 : 
			case 2 : createUser(choice);break;
			case 3 : showMainMenu();break;
			case 4 : System.out.print("\n\tThank You for using BANKING Service..!!");scan.close();System.exit(0); 
			}
		}catch(InputMismatchException e)
			{
				System.err.println("\tInvalid Character(s). Only Numbers.");
				scan.nextLine();
				createAccount();
			}
		
	}
	
	/*	Function to create user for account	*/
	public void createUser(int choice)
	{
		int pinNum=0;
		boolean validPin = false;
		if(choice==1)
			accountType="Saving";
		if(choice==2)
			accountType="Current";
		scan.nextLine();//using to clear the input buffer as variable 'choice' is still there
		System.out.print("\n\tEnter customer Name : ");
		String customerName = scan.nextLine();
		
		do {
				try
				{
					System.out.print("\n\tEnter Your four digit Pin Number : ");
					pinNum = scan.nextInt();
					validPin = validatePin(pinNum);
				}
				catch(InputMismatchException e)
				{
					System.err.print("\n\tInvalid Character(s). Only Numbers.");
					scan.nextLine();
					validPin = false;
				}
			}while(!validPin);
		
		accountService = AccountService.getInstance();
		accountService.setCustomerName(customerName);
		accountService.setPinNum(pinNum);
		accountService.createAccount(accountType);
		
		
	}
	
	/*	Function to validate pin number	*/
	public boolean validatePin(int pin)
	{
		
		if(pin<0)
		{
			System.err.print("\n\tInvalid Pin ..!! Pin cannot be negative\n");
			return false;
		}
		else if(!(String.valueOf(pin).length()==4 ))
		{
		
			System.err.print("\n\tInvalid pin ..!! Pin should be of 4 digit.\n");
			return false;
		}
		else
			return true;
	}
	
	
	/*	Function to validate entered choice	*/
	public boolean validateChoice(int choiceCount)
	{
		boolean flag=false;
		try
		{
		if(!(choice<=choiceCount && choice>0))
		{
			System.err.print("\n\tInvalid choice enter a digit within range of: "+choiceCount+"\n");
			 flag=false;
		}
		else
			flag= true;
		}catch(InputMismatchException e)
		{
			
			System.err.println("\tInvalid Character(s). Only Numbers.");
		}
		return flag;
	}
	
}
