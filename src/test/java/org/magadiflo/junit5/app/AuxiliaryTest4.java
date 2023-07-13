package org.magadiflo.junit5.app;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.magadiflo.junit5.app.models.Account;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuxiliaryTest4 {
    @ParameterizedTest(name = "número {index} ejecutando con valor {argumentsWithNames}")
    @ValueSource(strings = {"100", "200", "300", "500", "700", "1000", "2000"})
    void accountDebitValueSourceTest(String amount) {
        Account account = new Account("Martín", new BigDecimal("2000"));
        account.debit(new BigDecimal(amount));

        assertNotNull(account.getBalance());
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @ParameterizedTest(name = "número {index} ejecutando con valor {argumentsWithNames}")
    @CsvSource({"1,100", "2,200", "3,300", "4,500", "5,700", "6,1000", "7,2000"})
    void accountDebitCsvSourceTest(String index, String amount) {
        System.out.printf("%s -> %s\n", index, amount);
        Account account = new Account("Martín", new BigDecimal("2000"));
        account.debit(new BigDecimal(amount));

        assertNotNull(account.getBalance());
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @ParameterizedTest(name = "número {index} ejecutando con valor {argumentsWithNames}")
    @CsvFileSource(resources = "/data.csv")
    void accountDebitCsvFileSourceTest(String amount) {
        Account account = new Account("Martín", new BigDecimal("2000"));
        account.debit(new BigDecimal(amount));

        assertNotNull(account.getBalance());
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @ParameterizedTest(name = "número {index} ejecutando con valor {argumentsWithNames}")
    @MethodSource("amountList")
    void accountDebitMethodSourceTest(String amount) {
        Account account = new Account("Martín", new BigDecimal("2000"));
        account.debit(new BigDecimal(amount));

        assertNotNull(account.getBalance());
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    private static List<String> amountList() {
        return List.of("100", "200", "300", "500", "700", "1000", "2000");
    }
}
