package org.magadiflo.junit5.app.models;

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

}