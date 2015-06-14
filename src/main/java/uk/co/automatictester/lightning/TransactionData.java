package uk.co.automatictester.lightning;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionData {

    public static Map<String, Integer> columns = new HashMap<>();
    private static List<List<String>> transactions = new ArrayList<List<String>>();
    private static int labelIndex;
    private static int elapsedIndex;

    static {
        columns.put("label", 0);
        columns.put("elapsed", 1);
    }

    public static void load(String csv) {

        try {
            CSVReader reader = new CSVReader(new FileReader(csv));

            String[] header = reader.readNext();
            parseHeaders(header);

            String[] transaction;
            while ((transaction = reader.readNext()) != null) {
                String label = transaction[labelIndex];
                String elapsed = transaction[elapsedIndex];
                ArrayList<String> currentTransaction = new ArrayList();
                currentTransaction.add(columns.get("label"), label);
                currentTransaction.add(columns.get("elapsed"), elapsed);
                transactions.add(currentTransaction);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseHeaders(String[] header) {
        labelIndex = getIndex(header, "label");
        elapsedIndex = getIndex(header, "elapsed");
    }

    public static List<List<String>> getTransactions() {
        return transactions;
    }

    private static int getIndex(String[] CSVLine, String column) {
        for (int i = 0; i < CSVLine.length; i++) {
            if (CSVLine[i].equals(column)) {
                return i;
            }
        }
        throw new RuntimeException("Column not found: " + column);
    }
}
