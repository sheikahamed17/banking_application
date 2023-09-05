package org.example.customer;

import org.example.bank.Bank;

import java.util.Scanner;

public class CustomerHandler {

    public void addCustomer() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String name = in.nextLine();

        System.out.println("Enter password: ");
        String password = in.nextLine();

        System.out.println("Re-type password: ");
        String tempPassword = in.nextLine();

        if (!password.equals(tempPassword)) {
            System.out.println("Add customer failed: Mismatch password");
            return;
        }
        Bank.refCustomerId++;
        Bank.refAccountNumber++;

        Customer c = new Customer(
                Bank.refCustomerId,
                name,
                Bank.INITIAL_BALANCE,
                password,
                Bank.refAccountNumber
        );
        Bank.customers.add(c);
        CustomerFileHandler.getInstance().addCustomerToFile(c);
    }

    private String 
}
