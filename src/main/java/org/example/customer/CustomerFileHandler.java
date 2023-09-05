package org.example.customer;

import org.example.bank.Bank;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class CustomerFileHandler {

    private static final String filename = "bank_db.txt";
    static CustomerFileHandler handler;
    ArrayList<Customer> customers = new ArrayList<Customer>();

    public static CustomerFileHandler getInstance() {
        if (handler == null) {
            handler = new CustomerFileHandler();
        }
        return handler;
    }

    public void initialize() throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(
                new FileReader(file)
        );
        String customerInfo = reader.readLine();
        do {
            Customer customerClass = castStringToCustomer(customerInfo);
            Bank.customers.add(customerClass);
            Bank.customerMap.put(customerClass.customerId, customerClass);
            customerInfo = reader.readLine();
        } while (customerInfo != null);
        reader.close();

        int refPosition = Bank.customers.size() - 1;
        Bank.refCustomerId = Bank.customers.get(refPosition).customerId;
        Bank.refAccountNumber = Bank.customers.get(refPosition).accountId;
    }

    private Customer castStringToCustomer(String customerInfo) {
        String[] trimmedInfo = customerInfo.split(" ");
        Customer customer = new Customer(
                Integer.parseInt(trimmedInfo[0]),
                trimmedInfo[2],
                Double.parseDouble(trimmedInfo[3]),
                trimmedInfo[4],
                Long.parseLong(trimmedInfo[1])
        );
        return customer;
    }

    public void addCustomerToFile(Customer customer) {
        File file = new File(filename);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new FileWriter(file, true));
            writer.write("\n" + customer.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void finalizeFile() {
        File file = new File(filename);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new FileWriter(file));
            Set keySet = Bank.customerMap.keySet();
            Iterator iterator = keySet.iterator();
            while (iterator.hasNext()) {
                int customerId = (int) iterator.next();
                Customer customer = Bank.customerMap.get(customerId);
                writer.write(customer.toString() + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}