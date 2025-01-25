package org.example;

import org.example.bank.AccountActionHandler;
import org.example.customer.CustomerFileHandler;
import org.example.customer.CustomerHandler;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        CustomerFileHandler handler = new CustomerFileHandler();
        try {
            handler.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printMenu();
        handler.finalizeFile();
    }

    static void printMenu() {
        System.out.println("Please select an option: ");
        System.out.println("1. Create User:");
        System.out.println("2. Deposit:");
        System.out.println("3. Withdraw:");
        System.out.println("4. Fund transfer:");

        try {
            Scanner in = new Scanner(System.in);
            int option = in.nextInt();
            CustomerHandler handler1 = new CustomerHandler();
            switch (option) {
                case 1:
                    handler1.addCustomer();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withDraw();
                    break;
                case 4:
                    transfer();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }

    }

    private static void transfer() {
        Scanner in = new Scanner(System.in);
        CustomerHandler handler1 = new CustomerHandler();
        AccountActionHandler accountHandler = new AccountActionHandler();
        System.out.println("Enter customer id: ");
        int customerId = in.nextInt();
        System.out.println("Enter password: ");
        String password = in.next();
        if (handler1.authenticateCustomer(customerId, password)) {
            System.out.println("Enter customer id to transfer: ");
            int toCustomerId = in.nextInt();
            System.out.println("Enter transfer amount: ");
            double amount = in.nextDouble();
            accountHandler.transfer(customerId, toCustomerId, amount);
        }
    }

    private static void withDraw() {
        Scanner in = new Scanner(System.in);
        CustomerHandler handler1 = new CustomerHandler();
        AccountActionHandler accountHandler = new AccountActionHandler();
        System.out.println("Enter customer id: ");
        int customerId = in.nextInt();
        System.out.println("Enter password: ");
        String password = in.next();
        if (handler1.authenticateCustomer(customerId, password)) {
            System.out.println("Enter withdraw amount: ");
            double amount = in.nextDouble();
            accountHandler.withDraw(customerId, amount);
        }
    }

    private static void deposit() {
        Scanner in = new Scanner(System.in);
        CustomerHandler handler1 = new CustomerHandler();
        AccountActionHandler accountHandler = new AccountActionHandler();
        System.out.println("Enter customer id: ");
        int customerId = in.nextInt();
        System.out.println("Enter password: ");
        String password = in.next();
        if (handler1.authenticateCustomer(customerId, password)) {
            System.out.println("Enter deposit amount: ");
            double amount = in.nextDouble();
            accountHandler.deposit(customerId, amount);
        }
    }
}