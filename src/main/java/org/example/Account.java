package org.example;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private String accountNumber;
    private BigDecimal accountBalance;
    private List<Client> associatedClients;
    public Account(String accountNumber, BigDecimal accountBalance, List<Client> associatedClient) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.associatedClients = associatedClient;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public BigDecimal getAccountBalance() {
        return BankService.getAccounts().get(this.accountNumber).accountBalance;
    }
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
    public List<Client>  getAssociatedClient() {
        return associatedClients;
    }
    public void setAssociatedClient(Client associatedClient) {
        this.associatedClients = associatedClients;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                ", associatedClient=" + associatedClients +
                '}';
    }
    public void deposit(BigDecimal amount) {
        accountBalance = accountBalance.add(amount);
    }
    public void withdraw(BigDecimal amount) {
        accountBalance = accountBalance.subtract(amount);
    }
}