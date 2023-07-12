package org.magadiflo.junit5.app.models;

import java.math.BigDecimal;

public class Bank {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void transfer(Account origen, Account destinate, BigDecimal amount) {
        origen.debit(amount);
        destinate.credit(amount);
    }
}
