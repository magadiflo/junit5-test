package org.magadiflo.junit5.app.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void accountNameTest() {
        Account account = new Account("Martín", new BigDecimal("2000"));

        String expected = "Martín";
        String real = account.getPerson();

        assertEquals(expected, real);
    }

    @Test
    void balanceAccountTest() {
        Account account = new Account("Martín", new BigDecimal("2000"));

        assertEquals(2000D, account.getBalance().doubleValue());
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) == -1);
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) == 1);
    }

    @Test
    void valueAccountTest() {
        Account account1 = new Account("Liz Gonzales", new BigDecimal("2500.00"));
        Account account2 = new Account("Liz Gonzales", new BigDecimal("2500.00"));

        assertEquals(account2, account1);
    }

}