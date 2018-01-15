/*Project 3
Fang Yao
UFL ID: 37961919
Section: 11H6
Brief description: A currency conversion program that allows the user to deposit, withdraw and
view the balance in different currency types*/

import java.util.Scanner;

public class CurrencyExchange {
	private static double balance = 0;//balance could be used in any methods

	//Get the current balance of the account
    public static double getBalance() {
        return balance;
    }

     //Update the balance of current account, will do a roundup to 2 significant digits
    private static boolean updateBalance(double newBalance) {

        balance = Math.round(newBalance * 100) / 100.0;
        if (balance >= 0) {
            return true;
        } else
            return false;
    }


    //Main method for the logic
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Currency Exchange 2.0\n");
        printConversionTable();
        int currencyType;
        int choiceMenu;
        
        while (true){
        	choiceMenu = mainMenuOptionSelector(input);
        	if (choiceMenu == 1){
        		System.out.println("Your current balance is: " + getBalance());
        		continue;
        	}
        	else if (choiceMenu == 2){
        		currencyType = currencyMenuOptionSelector(input);
        		System.out.println("Please enter the deposit amount:");
        		double depositAmount = input.nextDouble();
        		deposit(depositAmount, currencyType);//call deposit method
        		System.out.println(logTransaction(depositAmount, currencyType, true));//call and print logTransaction message
        		continue;
        	}
        	else if (choiceMenu == 3){
        		currencyType = currencyMenuOptionSelector(input);
        		System.out.println("Please enter the withdraw amount:");
        		double withdrawAmount = input.nextDouble();
        		//call withdraw and logTransaction method, if true print successful message, if false print logging error
        		if (withdraw(withdrawAmount, currencyType) == true){
        			System.out.println(logTransaction(withdrawAmount, currencyType, false));
        		}
        		else {
        			System.out.println(logTransaction(-1, currencyType, false));
        		}
        		continue;
        	}
        	else {
        		//withdraw all the money in USD or show 0 balance
        		if (getBalance() != 0){
        			System.out.println(logTransaction(getBalance(), 1, false));
        			withdraw(getBalance(), 1);
        		}
        		else{
        			System.out.println("Your remaining balance is " + getBalance() + " U.S. dollars");
        		}
        		System.out.println("Goodbye");
        		System.exit(0);
        	}
        }
    }

    
    /*deposit the amount of a specific currency to the account
     if the amount is valid, update the balance, return true,
     otherwise doesn't update the balance, return false*/
    public static boolean deposit(double amount, int currencyType) {    	
    	if (amount > 0){
    		balance = balance + convertCurrency(amount, currencyType, true);//call convertCurrency method
    		updateBalance(balance);
    		balance = getBalance();
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    
    /*withdraw the value amount with a specific currency from the account
    if the amount is valid, update the balance, return true,
    if the amount is valid but more than balance, print insufficient funds, don't update the balance and return false
    if the amount is invalid, don't update the balance, return false*/
    public static boolean withdraw(double amount, int currencyType) {
    	double testBalance;
    	if (currencyType == 1){
			testBalance = balance - convertCurrency(amount, currencyType, true);//call covertCurrency method
		}
		else {
			testBalance = balance - convertCurrency(amount, currencyType, true) * (1+0.005);//convenience fee 0.5%
		}
    	if (amount > 0 && testBalance >= 0){
    		updateBalance(testBalance);
    		balance = getBalance();
    		return true;
    	}
    	else if (amount > 0 && testBalance < 0){
    		System.out.println("Error: Insufficient funds.");
    		return false;
    	}
    	else {
    		return false;
    	}
    }

    
    //Convert the value amount between U.S. dollars and a specific currency
    public static double convertCurrency(double amount, int currencyType, boolean isConvertToUSD) {
    	double [] currencyRate = {0, 1.00, 0.89, 0.78, 66.53, 1.31, 1.31, 1.37, 0.97, 4.12, 101.64, 6.67};
    	double convertAmount;
    	if (isConvertToUSD == true){
    		convertAmount = amount / currencyRate[currencyType];
    	}
    	else {
    		convertAmount = amount * currencyRate[currencyType];
    	}
    	return 	convertAmount;
    }

    
    //Displays message at the end of the withdraw, deposit, and endTransaction stating
    private static String logTransaction(double amount, int currencyType, boolean isDeposit) {
    	String [] currencyList = {"U.S. Dollars","Euros","British Pounds","Indian Rupees", "Australian Dollars",
           		"Canadian Dollars","Singapore Dollars","Swiss Francs","Malaysian Ringgits","Japanese Yen",
           		"Chinese Yuan Renminbi"};
    	String message = "";
    	if (amount > 0){
    		if (isDeposit == true){
    			message ="You successfully deposited " + amount + " " + currencyList[currencyType-1];
    		}
    		else {
    			message ="You successfully withdrew " + amount + " " + currencyList[currencyType-1];
    		}
    	}
    	else {
    		message = "Logging Error";
    	}
    	return message;
    }

 
    //Prints the currency menu, allows the user to make a selection from available currencies, only return integer from 1-11
    private static int currencyMenuOptionSelector(Scanner input) {
    	System.out.println("Please select the currency type:");
    	String [] currencyList = {"U.S. Dollars","Euros","British Pounds","Indian Rupees", "Australian Dollars",
       		"Canadian Dollars","Singapore Dollars","Swiss Francs","Malaysian Ringgits","Japanese Yen",
       		"Chinese Yuan Renminbi"};
    	for (int i = 1; i <= 11; i++){
    		System.out.println(i + ". " + currencyList[i-1]);
    	}
    	int currencyChoice = input.nextInt();
    	while (currencyChoice > 11 || currencyChoice < 1) {
    		System.out.println("Input failed validation. Please try again.");
    		System.out.println("Please select the currency type:");
    		for (int i = 1; i <= 11; i++){
        		System.out.println(i + ". " + currencyList[i-1]);
        	}
    		currencyChoice = input.nextInt();
    	}
    	return currencyChoice;
    }

    
    //Prints the main menu, allows the user to make a selection from available operations, only return integer from 1-4
    private static int mainMenuOptionSelector(Scanner input) {
    	System.out.println("Please select an option from the list below:");
    	System.out.println("1. Check the balance of your account\n" + "2. Make a deposit\n" +
    	"3. Withdraw an amount in a specific currency\n" + "4. End your session " +
    	"(and withdraw all remaining currency in U.S. Dollars)");
    	int optionChoice = input.nextInt();
    	while (optionChoice > 4 || optionChoice < 1) {
    		System.out.println("Input failed validation. Please try again.");
    		System.out.println("Please select an option from the list below:");
        	System.out.println("1. Check the balance of your account\n" + "2. Make a deposit\n" +
        	"3. Withdraw an amount in a specific currency\n" + "4. End your session " +
        	"(and withdraw all remaining currency in U.S. Dollars)");
        	optionChoice = input.nextInt();
    	}
    	return optionChoice;
    }

    
    //Prints the conversion table at the start of the program
    private static void printConversionTable() {
    	System.out.println("Current rates are as follows:");
        String [] currencySelection = {"U.S. Dollar","Euro","British Pound","Indian Rupee", "Australian Dollar",
        		"Canadian Dollar","Singapore Dollar","Swiss Franc","Malaysian Ringgit","Japanese Yen",
        		"Chinese Yuan Renminbi"};
        String [] currencyRate = {"1.00", "0.89", "0.78", "66.53", "1.31", "1.31", "1.37", "0.97", "4.12", "101.64", "6.67"};
        for (int i = 1; i <= 11; i++){
        	System.out.println(i + " - " + currencySelection[i-1] + " - " + currencyRate[i-1]);
        }
        System.out.println();
    }
}
