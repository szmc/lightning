package uk.co.automatictester.lightning;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JMeterCSVFileReader {

    private static int labelIndex;
    private static int elapsedIndex;
    private static int successIndex;

    public static JMeterTransactions read(String csvFile) {
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile));

            String[] columnNames = reader.readNext();
            getColumnIndexes(columnNames);

            String[] jmeterTransaction;
            while ((jmeterTransaction = reader.readNext()) != null) {
                ArrayList<String> currentTransaction = new ArrayList<>();
                String labelValue = jmeterTransaction[labelIndex];
                String elapsedValue = jmeterTransaction[elapsedIndex];
                String successValue = jmeterTransaction[successIndex];
                currentTransaction.add(labelValue);     // 0
                currentTransaction.add(elapsedValue);   // 1
                currentTransaction.add(successValue);   // 2
                jmeterTransactions.add(currentTransaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jmeterTransactions;
    }

    private static void getColumnIndexes(String[] columnNames) {
        labelIndex = getColumnIndexFor(columnNames, "label");
        elapsedIndex = getColumnIndexFor(columnNames, "elapsed");
        successIndex = getColumnIndexFor(columnNames, "success");
    }

    private static int getColumnIndexFor(String[] columnNames, String searchedColumnName) {
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(searchedColumnName)) {
                return i;
            }
        }
        throw new RuntimeException("Column not found: " + searchedColumnName);
    }
}
