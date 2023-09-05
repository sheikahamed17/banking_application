package org.example.bank;

import org.example.customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    public static final double INITIAL_BALANCE = 10000;
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static HashMap<Integer, Customer> customerMap = new HashMap<Integer, Customer>();
    public static int refCustomerId;
    public static long refAccountNumber;
}
