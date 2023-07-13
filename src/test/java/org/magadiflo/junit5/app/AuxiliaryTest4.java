package org.magadiflo.junit5.app;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.magadiflo.junit5.app.models.Account;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AuxiliaryTest4 {
    @ParameterizedTest(name = "número {index} ejecutando con valor {argumentsWithNames}")
    @ValueSource(strings = {"100", "200", "300", "500", "700", "1000", "2000"})
    void accountDebitTest(String amount) {
        Account account = new Account("Martín", new BigDecimal("2000"));
        account.debit(new BigDecimal(amount));

        assertNotNull(account.getBalance());
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }
}
