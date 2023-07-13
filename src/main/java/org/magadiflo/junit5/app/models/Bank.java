package org.magadiflo.junit5.app.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        account.setBank(this);
        this.accounts.add(account);
    }

    public void transfer(Account origen, Account destinate, BigDecimal amount) {
        origen.debit(amount);
        destinate.credit(amount);
    }
}
