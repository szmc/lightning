package uk.co.automatictester.lightning;

import java.util.ArrayList;

public class JMeterTransactions extends ArrayList<ArrayList<String>> {

    public JMeterTransactions excludeLabelsOtherThan(String label) {
        JMeterTransactions transactions = new JMeterTransactions();
        for (ArrayList<String> transaction : this) {
            if (transaction.get(0).equals(label)) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

}
