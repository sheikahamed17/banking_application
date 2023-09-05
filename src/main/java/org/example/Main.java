package org.example;

import org.example.bank.Bank;
import org.example.customer.CustomerFileHandler;
import org.example.customer.CustomerHandler;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        CustomerFileHandler handler = new CustomerFileHandler();
        try {
            handler.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerHandler handler1 = new CustomerHandler();
        handler1.addCustomer();

        System.out.println(Bank.customers.get(2).name);
    }
}