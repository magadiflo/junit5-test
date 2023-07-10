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

}