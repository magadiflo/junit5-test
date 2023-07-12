package org.magadiflo.junit5.app.models;

import org.junit.jupiter.api.Test;
import org.magadiflo.junit5.app.exceptions.InsufficientMoneyException;

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

    @Test
    void accountDebitTest() {
        Account account = new Account("Martín", new BigDecimal("2000"));
        account.debit(new BigDecimal("100"));

        assertNotNull(account.getBalance());
        assertEquals(1900D, account.getBalance().doubleValue());
        assertEquals("1900", account.getBalance().toPlainString());
    }

    @Test
    void accountCreditTest() {
        Account account = new Account("Martín", new BigDecimal("2000"));
        account.credit(new BigDecimal("100"));

        assertNotNull(account.getBalance(), () -> "La cuenta no puede ser nula");
        assertEquals(2100D, account.getBalance().doubleValue(), () -> "El valor obtenido no es igual al valor que se espera");
        assertEquals("2100", account.getBalance().toPlainString(), () -> "El valor obtenido no es igual al valor que se espera");
    }

    @Test
    void insufficientMoneyException() {
        Account account = new Account("Martín", new BigDecimal("2000"));

        InsufficientMoneyException exception = assertThrows(InsufficientMoneyException.class, () -> {
            account.debit(new BigDecimal("5000"));
        }, "Se esperaba que InsufficientMoneyException fuera lanzado");

        assertEquals(InsufficientMoneyException.class, exception.getClass());
        assertEquals("Dinero insuficiente", exception.getMessage());
    }
}