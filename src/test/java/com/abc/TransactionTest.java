package com.abc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    
        private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void transaction() {
        Transaction t = new Transaction(20,Transaction.DEPOSIT_FROM_BANK);
        
        t.getAmount();
        
        assertTrue(t instanceof Transaction);
        System.out.println(t.getTransactionDate());
        assertEquals(20, t.getAmount(),DOUBLE_DELTA);
        assertEquals("DEPOSIT FROM BANK", t.getTransactionType());
        assertTrue(t instanceof Transaction);
        
    }
}
