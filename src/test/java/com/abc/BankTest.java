package com.abc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

     @Test
    public void accountWithNoTransactions() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        CheckingAccount checkingAccount=new CheckingAccount();

        john.openAccount(checkingAccount);
        assertEquals(0.00, checkingAccount.getBalance(),DOUBLE_DELTA);

    }
    
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());

        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();

        CheckingAccount checkingAccount=new CheckingAccount();

        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0, Transaction.DEPOSIT_FROM_CUSTOMER);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        SavingsAccount savings=new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savings));

        savings.deposit(1500.0, Transaction.DEPOSIT_FROM_CUSTOMER);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        MaxiSavingsAccount maxisavings=new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxisavings));

        maxisavings.deposit(3000.0, Transaction.DEPOSIT_FROM_CUSTOMER);
        maxisavings.withdraw(450, Transaction.WITHDRAWAL_FROM_CUSTOMER);
        
        
        assertEquals(2550.0, maxisavings.getBalance(), DOUBLE_DELTA);
        assertEquals(2.55, bank.totalInterestPaid(), DOUBLE_DELTA);
        assertEquals(0.006986, bank.dailyInterestPaid(),DOUBLE_DELTA);
        
    }
    
      @Test
    public void maxi_savings_account2() {
        Bank bank = new Bank();
        MaxiSavingsAccount maxisavings=new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxisavings));

        maxisavings.deposit(3000.0, Transaction.DEPOSIT_FROM_CUSTOMER);
              
        assertEquals(3000.0, maxisavings.getBalance(), DOUBLE_DELTA);
        assertEquals(150, bank.totalInterestPaid(), DOUBLE_DELTA);
        assertEquals(0.410959, bank.dailyInterestPaid(),DOUBLE_DELTA);
    }
    
    
    @Test
    public void tranferring() {
        Bank bank = new Bank();
        MaxiSavingsAccount maxi=new MaxiSavingsAccount();
        SavingsAccount savings=new SavingsAccount();

        Customer customer=new Customer("Bill");
        bank.addCustomer(customer);
        
        customer.openAccount(savings);
        customer.openAccount(maxi);

               

        maxi.deposit(3000.0, Transaction.DEPOSIT_FROM_CUSTOMER);
        savings.deposit(1500.0,Transaction.DEPOSIT_FROM_CUSTOMER);
        customer.transfer(1000.0, maxi, savings);


        assertEquals(2000, maxi.getBalance(), DOUBLE_DELTA);
        assertEquals(2500, savings.getBalance(), DOUBLE_DELTA);

    }
    
     @Test
    public void tranferring_insufficientfunds() {
        Bank bank = new Bank();
        MaxiSavingsAccount maxi=new MaxiSavingsAccount();
        SavingsAccount savings=new SavingsAccount();

        Customer customer=new Customer("Bill");
        bank.addCustomer(customer);
        
        customer.openAccount(savings);
        customer.openAccount(maxi);

        maxi.deposit(1000.0, Transaction.DEPOSIT_FROM_CUSTOMER);
        savings.deposit(2000.0, Transaction.DEPOSIT_FROM_CUSTOMER);

        
        try {
            customer.transfer(1500.0, maxi, savings);
             fail("Expected an insufficient funds to be thrown");
             // if we got here, no exception was thrown which is bad
        } 
        catch (Exception e) 
        {
             final String expected = "sender account has insufficient funds. transaction could not be made.";
            assertEquals( expected, e.getMessage());
        }        

    }
    
    @Test
    public void tranferring_fromtosameaccount() {
        Bank bank = new Bank();
        MaxiSavingsAccount maxi=new MaxiSavingsAccount();
        SavingsAccount savings=new SavingsAccount();

        Customer customer=new Customer("Bill");
        bank.addCustomer(customer);
        
        customer.openAccount(savings);
        customer.openAccount(maxi);

        maxi.deposit(1000.0, Transaction.DEPOSIT_FROM_CUSTOMER);
        savings.deposit(2000.0, Transaction.DEPOSIT_FROM_CUSTOMER);

        
        try {
            customer.transfer(1500.0, savings, savings);
             fail("Expected excpetion about same account choice twice to be thrown");
        } 
        catch (Exception e) 
        {
             final String expected = "the sender and receiver account cannot be the same.";
            assertEquals( expected, e.getMessage());
        }        

    }
    
     @Test
    public void tranferring_amountlessthanzero() {
        Bank bank = new Bank();
        MaxiSavingsAccount maxi=new MaxiSavingsAccount();
        SavingsAccount savings=new SavingsAccount();

        Customer customer=new Customer("Bill");
        bank.addCustomer(customer);
        
        customer.openAccount(savings);
        customer.openAccount(maxi);

        maxi.deposit(1000.0, Transaction.DEPOSIT_FROM_CUSTOMER);
        savings.deposit(2000.0, Transaction.DEPOSIT_FROM_CUSTOMER);

        
        try {
            customer.transfer(-45, maxi, savings);
             fail("Expected excpetion about amount<0 to be thrown");
        } 
        catch (Exception e) 
        {
             final String expected = "amount to be transferred must be greater than zero.";
            assertEquals( expected, e.getMessage());
        }        

    }
    
    
    
  
}
