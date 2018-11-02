package com.abc;

//changed Date to LocalDateTime
import java.time.LocalDateTime;


public class Transaction {
    
    //made porperties private for safety and final since it only needs to be initialised once-slightly more efficient efficiency
    //added encapsulation getters - readonly access- safety
    private final double amount;
    private final LocalDateTime transactionDate;
    
    private final String transactionType ; // Customer deposited or withdrew money

    //used to make customer statement clearer
    public static final String WITHDRAWAL_FROM_BANK = "WITHDRAWAL FROM BANK";
    public static final String WITHDRAWAL_FROM_CUSTOMER = "WITHDRAWAL FROM ACCOUNT HOLDER";
    public static final String DEPOSIT_FROM_BANK= "DEPOSIT FROM BANK";
    public static final String DEPOSIT_FROM_CUSTOMER= "DEPOSIT FROM ACCOUNT HOLDER";
    public static final String TRANSFER_OUT= "TRANSFERALL SENT";
    public static final String TRANSFER_IN= "TRANSFERALL RECEIVED";

    

    public Transaction(double amount,String transactionType) {
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
        this.transactionType=transactionType;
    }

    public double getAmount() {
        return amount;
    }
     public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
     
     public String getTransactionType()
     {
         return transactionType;
     }
     
    
}
