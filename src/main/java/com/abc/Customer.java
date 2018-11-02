package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private final String name;
    private final List<Account> accounts;


    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }
    
    public List<Account> getAccounts()
    {
        return accounts;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.annualInterestEarned();
        return total;
    }
    
    public double dailyInterestEarned()
    {
        double total = 0;
        for (Account a : accounts)
            total += a.dailyInterestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.getBalance();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    
    //decided to make statement much more detailed by adding more information/properties about the transaction
    private String statementForAccount(Account a) {
        
        
        //used instanceof insead of accounttype
        String s = "";

        if (a instanceof CheckingAccount) 
        { 
         s += "Checking Account\n";
 
        }
        else if (a instanceof SavingsAccount)
        {
         s += "Savings Account\n";

        }
        else if (a instanceof MaxiSavingsAccount)
        { 
         s += "Maxi Savings Account\n";
        }
        
        //Now total up all the transactions
        //used the getters in transaction class
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s += t.getTransactionDate()+"   " + t.getTransactionType() + " " + toDollars(t.getAmount()) +"\n";
            total += t.getAmount();
        }
        s += "\t\tTotal " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    
    public void transfer(double amount , Account from, Account to){
        
        //make sure that from, to and amount are valid
        if (from == to) {
            throw new IllegalArgumentException("the sender and receiver account cannot be the same.");
        }
        if (accounts.contains(from)==false  || accounts.contains(to)==false) {
            throw new IllegalArgumentException("account(s) not found.");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("amount to be transferred must be greater than zero.");
        } 
          
        double currentBalance = from.getBalance();
         
        if (currentBalance>=amount){
            from.getTransactions().add(new Transaction(-amount,Transaction.TRANSFER_OUT));
            to.getTransactions().add(new Transaction (amount,Transaction.TRANSFER_IN));        
        }
        else
            throw new IllegalArgumentException("sender account has insufficient funds. transaction could not be made.");
             
     } 
}
