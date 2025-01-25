package org.example.bank;

import org.example.customer.Customer;
import org.example.transaction.Transaction;
import org.example.transaction.TransactionHandler;

public class AccountActionHandler {

    public void deposit(int customerId, double amount) {
        if (amount < 0) {
            return;
        }
        Customer customer = Bank.customerMap.get(customerId);
        customer.balance += amount;

        Bank.customerMap.put(customerId, customer);

        TransactionHandler handler = new TransactionHandler();
        int lastTransactionId = handler.getLastTransactionId(customerId);

        Transaction transaction = new Transaction(lastTransactionId++, "Deposit", amount, customer.balance);
        handler.writeTransaction(customerId, transaction);
    }

    public boolean withDraw(int customerId, double amount) {
        Customer customer = Bank.customerMap.get(customerId);

        double balance = customer.balance - amount;
        if (balance >= 1000) {
            customer.balance = balance;
            Bank.customerMap.put(customerId, customer);

            TransactionHandler handler = new TransactionHandler();
            int lastTransactionId = handler.getLastTransactionId(customerId);

            Transaction transaction = new Transaction(lastTransactionId++, "Withdraw", amount, customer.balance);
            handler.writeTransaction(customerId, transaction);

            return true;
        }
        System.out.println("Insufficient balance");
        return false;
    }

    public void transfer(int fromCustomerId, int toCustomerId, double amount) {
        Customer toCustomer = Bank.customerMap.get(toCustomerId);
        if (toCustomer == null) {
            System.out.println("The customer to transfer fund doesn't exist");
            return;
        }

        Customer fromCustomer = Bank.customerMap.get(fromCustomerId);
        boolean isSuccess = withDraw(fromCustomerId, amount);
        if (isSuccess) {
            deposit(toCustomerId, amount);
        }
    }
}
