package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

   
    //made this private final for more security
    private final List<Transaction> transactions;
    protected static final int yearDays=365;

    //removed accountType => used inheritance
    public Account() {
        this.transactions = new ArrayList<Transaction>();
    }

   //now that transaction list is private we can access it through a getter
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    protected void deposit(double amount, String from) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount,from));
        }
    }

    protected void withdraw(double amount, String from) {
     if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
     } 
     else 
     {  
     double currentBalance = getBalance();
      if (currentBalance>0)
        transactions.add(new Transaction(-amount,from));
      else
        throw new IllegalArgumentException("you have insufficient funds");
     }
    }


    //to be implemented from derived classes
    abstract protected double annualInterestEarned();
    
    
    protected double dailyInterestEarned()
    {
        //daily interest earned is the annual interest earned devided by 365
    return round((annualInterestEarned()/yearDays),6);

    }
    

    //used to round doubles with too many decimals - makes testing easier as well
    protected static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
    }
    
    
    //copied code from checkIfTransactionExist here since the only statement is a call to it, which makes the call unnecessary
    protected double getBalance() {
       
        double amount = 0.0;
        //checks if there are any elements in transactions
        if(!transactions.isEmpty())
        {         
        for (Transaction t: transactions)
            amount += t.getAmount();
        }
        
        return amount;
    }

}
