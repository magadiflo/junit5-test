package org.magadiflo.junit5.app;

import org.junit.jupiter.api.*;
import org.magadiflo.junit5.app.models.Account;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuxiliaryTest6 {
    Account account;

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        this.account = new Account("Martín", new BigDecimal("2000"));

        System.out.println(testInfo.getDisplayName());
        System.out.println(testInfo.getTestMethod().orElseGet(null).getName());
        System.out.println(testInfo.getTestClass().orElseGet(null).getName());
        System.out.println(testInfo.getTags());

        testReporter.publishEntry("Ejecutando: " + testInfo.getDisplayName());
    }

    @Test
    @Tag("account")
    @DisplayName("Probando nombre de la cuenta")
    void accountNameTest() {

        String expected = "Martín";
        String real = this.account.getPerson();

        assertEquals(expected, real);
    }

}
