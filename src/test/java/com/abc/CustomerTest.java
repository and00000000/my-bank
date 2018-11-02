package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

       
        CheckingAccount checkingAccount=new CheckingAccount();
        SavingsAccount savingsAccount=new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0,Transaction.DEPOSIT_FROM_CUSTOMER);
        savingsAccount.deposit(4000.0,Transaction.DEPOSIT_FROM_CUSTOMER);
        savingsAccount.withdraw(200.0,Transaction.WITHDRAWAL_FROM_CUSTOMER);
        
        henry.transfer(500, savingsAccount, checkingAccount);
                
                
        System.out.println(henry.getStatement());
        
        
        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                (checkingAccount.getTransactions().get(0).getTransactionDate())+"   DEPOSIT FROM ACCOUNT HOLDER $100.00\n" +
                (checkingAccount.getTransactions().get(1).getTransactionDate())+"   TRANSFERALL RECEIVED $500.00\n"+
                "\t\tTotal $600.00\n" +
                "\n" +
                "Savings Account\n" +
                (savingsAccount.getTransactions().get(0).getTransactionDate())+"   DEPOSIT FROM ACCOUNT HOLDER $4,000.00\n" +
                (savingsAccount.getTransactions().get(1).getTransactionDate())+"   WITHDRAWAL FROM ACCOUNT HOLDER $200.00\n" +
                (savingsAccount.getTransactions().get(2).getTransactionDate())+"   TRANSFERALL SENT $500.00\n"+
                "\t\tTotal $3,300.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
