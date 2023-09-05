package org.example;

import org.example.customer.CustomerFileHandler;
import org.example.customer.CustomerHandler;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        CustomerFileHandler handler = new CustomerFileHandler();
        try {
            handler.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printMenu();
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

                case 3:
                    
                case 4:
                    System.out.println("Enter customer id: ");
                    int customerId = in.nextInt();
                    System.out.println("Enter password: ");
                    String password = in.next();
                    handler1.authenticateCustomer(customerId, password);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }

    }
}