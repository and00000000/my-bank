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


public class CheckingAccount extends Account{
    
    private static final double interestRate = 0.001;

     public CheckingAccount(){
    }
     
     public double annualInterestEarned(){
        return super.getBalance() * interestRate;

     }
     
     
}
