package org.example.transaction;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TransactionHandler {
    public void writeTransaction(int customerId, Transaction transaction) {
        String filename = customerId + ".txt";
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            writer.write(transaction.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public int getLastTransactionId(int customerId) {
        String filename = customerId + ".txt";
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return 0;
            }
            Scanner s = new Scanner(file);
            String transaction = "";
            while (s.hasNext()) {
                transaction = s.nextLine();
            }
            s.close();
            Transaction trans = castString(transaction);
            return trans.transactionId;
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return 0;
    }

    private Transaction castString(String transaction) {
        String[] arr = transaction.split("\t");
        return new Transaction(
                Integer.parseInt(arr[0]),
                arr[1],
                Double.parseDouble(arr[2]),
                Double.parseDouble(arr[3])
        );
    }
}
