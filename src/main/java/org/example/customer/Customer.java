package org.example.customer;

public class Customer {
    private static final String SPACE = " ";
    public int customerId;
    public String name;
    public double balance;
    public long accountId;
    public String password;

    public Customer(int customerId, String name, double balance, String password, long accountId) {
        this.customerId = customerId;
        this.name = name;
        this.balance = balance;
        this.password = password;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return customerId + SPACE +
                accountId + SPACE +
                name + SPACE +
                balance + SPACE +
                password;
    }
}
