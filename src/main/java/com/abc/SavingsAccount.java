/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc;

/**
 *
 * @author and-i
 */
public class SavingsAccount extends Account{
     public SavingsAccount(){
       // super(transactions);
    }
     
     public double annualInterestEarned(){
        double amount = super.getBalance();

        //after the first 1000 dollars the interest rate increases to 0.002
         if (amount <= 1000)
            return amount * 0.001;
         else
            return 1 + (amount-1000) * 0.002;
        
        
     }
     
   
     
     
}
