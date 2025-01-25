package org.example.transaction;

public class Transaction {
    public int transactionId;
    public String type;
    double amount;
    double balance;

    public Transaction(int transactionId, String type, double amount, double balance) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return transactionId +
                "\t" + type +
                "\t" + amount +
                "\t" + balance + "\n";
    }
}
