package org.magadiflo.junit5.app.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void transferTest() {
        Account origen = new Account("Martín", new BigDecimal("2000.50"));
        Account destinate = new Account("Alicia", new BigDecimal("1500.50"));

        Bank bank = new Bank();
        bank.setName("Banco BBVA");

        bank.transfer(origen, destinate, new BigDecimal("500.50"));

        assertEquals(1500D, origen.getBalance().doubleValue());
        assertEquals(2001D, destinate.getBalance().doubleValue());
    }

    @Test
    void relationshipBetweenBankAndAccounts() {
        Account origen = new Account("Martín", new BigDecimal("2000.50"));
        Account destinate = new Account("Alicia", new BigDecimal("1500.50"));

        Bank bank = new Bank();
        bank.setName("Banco BBVA");
        bank.addAccount(origen);
        bank.addAccount(destinate);

        assertEquals(2, bank.getAccounts().size());
        assertEquals("Banco BBVA", origen.getBank().getName());
        assertEquals("Banco BBVA", destinate.getBank().getName());
        assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getPerson().equals("Martín")));
    }

    @Test
    @DisplayName("Usando AssertAll para ver qué asserts fallan")
    void usingAssertAllMethod() {
        Account account1 = new Account("Martín", new BigDecimal("2000.50"));
        Account account2 = new Account("Alicia", new BigDecimal("1500.50"));
        Account account3 = new Account("Alex", new BigDecimal("1500.50"));

        Bank bank = new Bank();
        bank.setName("Banco BBVA");
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);

        assertAll(() -> assertEquals(3, bank.getAccounts().size()),
                () -> assertEquals("Banco BBVA", account1.getBank().getName()),
                () -> {
                    /* Si tenemos más de una línea de código sí habría que colocar las llaves */
                    assertEquals("Banco BBVA", account2.getBank().getName());
                },
                () -> assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getPerson().equals("Alex"))));
    }

}