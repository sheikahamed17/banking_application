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
        if (!isValidPassword(password)) {
            System.out.println("Add customer failed: Invalid password");
            return;
        }

        password = getEncryptedPassword(password);

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

    private boolean isValidPassword(String password) {
        char[] passChar = password.toCharArray();
        for (char temp : passChar) {
            if ((temp >= 97 && temp <= 122) || (temp >= 65 && temp <= 90) || (temp >= 48 && temp <= 57)) {
                continue;
            } else
                return false;
        }
        return true;
    }

    private String getEncryptedPassword(String password) {
        char[] passChar = password.toCharArray();
        for (int i = 0; i < passChar.length; i++) {
            if (passChar[i] == 'Z' || passChar[i] == 'z' || passChar[i] == '9') {
                switch (passChar[i]) {
                    case 'z':
                        passChar[i] = 'a';
                        break;
                    case 'Z':
                        passChar[i] = 'A';
                        break;
                    case '9':
                        passChar[i] = '0';
                        break;
                }

            } else {
                passChar[i] = (char) (passChar[i] + 1);
            }
        }
        return String.valueOf(passChar);
    }

    public boolean authenticateCustomer(int customerId, String password) {
        String encryptedPass = getEncryptedPassword(password);

        Customer c = Bank.customerMap.get(customerId);
        if (c == null) {
            System.out.println("Invalid customer Id");
            return false;
        }
        if (encryptedPass.equals(c.password)) {
            System.out.println("Valid User");
            return true;
        } else {
            System.out.println("Invalid User");
        }
        return false;
    }
}
