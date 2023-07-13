package org.magadiflo.junit5.app.models;

import org.junit.jupiter.api.*;
import org.magadiflo.junit5.app.exceptions.InsufficientMoneyException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando las pruebas");
        System.out.println("=========================");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("=========================");
        System.out.println("Finalizando las pruebas");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Iniciando método");
        this.account = new Account("Martín", new BigDecimal("2000"));
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando método\n");
    }

    @Test
    @Tag("account")
    @DisplayName("Probando nombre de la cuenta")
    void accountNameTest() {
        String expected = "Martín";
        String real = this.account.getPerson();

        assertEquals(expected, real);
    }

    @Test
    @Tag("account")
    @DisplayName("Probando el saldo de la cuenta")
    void balanceAccountTest() {
        assertEquals(2000D, this.account.getBalance().doubleValue());
        assertFalse(this.account.getBalance().compareTo(BigDecimal.ZERO) == -1);
        assertTrue(this.account.getBalance().compareTo(BigDecimal.ZERO) == 1);
    }

    @Test
    @DisplayName("Probando que dos objetos sean iguales")
    void valueAccountTest() {
        Account account1 = new Account("Liz Gonzales", new BigDecimal("2500.00"));
        Account account2 = new Account("Liz Gonzales", new BigDecimal("2500.00"));

        assertEquals(account2, account1);
    }

    @Test
    void accountDebitTest() {
        this.account.debit(new BigDecimal("100"));

        assertNotNull(this.account.getBalance());
        assertEquals(1900D, this.account.getBalance().doubleValue());
        assertEquals("1900", this.account.getBalance().toPlainString());
    }

    @Test
    void accountCreditTest() {
        this.account.credit(new BigDecimal("100"));

        assertNotNull(this.account.getBalance(), () -> "La cuenta no puede ser nula");
        assertEquals(2100D, this.account.getBalance().doubleValue(), () -> "El valor obtenido no es igual al valor que se espera");
        assertEquals("2100", this.account.getBalance().toPlainString(), () -> "El valor obtenido no es igual al valor que se espera");
    }

    @Test
    @Disabled
    void insufficientMoneyException() {
        InsufficientMoneyException exception = assertThrows(InsufficientMoneyException.class, () -> {
            this.account.debit(new BigDecimal("5000"));
        }, "Se esperaba que InsufficientMoneyException fuera lanzado");

        assertEquals(InsufficientMoneyException.class, exception.getClass());
        assertEquals("Dinero insuficiente", exception.getMessage());
    }
}