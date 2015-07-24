package uk.co.automatictester.lightning;

import uk.co.automatictester.lightning.exceptions.CSVFileNonexistentLabelException;

import java.util.ArrayList;

public class JMeterTransactions extends ArrayList<ArrayList<String>> {

    public JMeterTransactions excludeLabelsOtherThan(String label) {
        JMeterTransactions transactions = new JMeterTransactions();
        for (ArrayList<String> transaction : this) {
            if (transaction.get(0).equals(label)) {
                transactions.add(transaction);
            }
        }
        if (transactions.size() == 0)
            throw new CSVFileNonexistentLabelException(label);
        return transactions;
    }

    public int getFailCount() {
        int failCount = 0;
        for (ArrayList<String> transaction : this) {
            if (transaction.get(2).equals("false")) {
                failCount++;
            }
        }

        return failCount;
    }

    public int getTransactionCount() {
        return this.size();
    }

}
