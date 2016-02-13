package uk.co.automatictester.lightning.reporters;

import uk.co.automatictester.lightning.data.JMeterTransactions;

public class JMeterReporter {

    private JMeterTransactions jmeterTransactions;

    public JMeterReporter(JMeterTransactions jmeterTransactions) {
        this.jmeterTransactions = jmeterTransactions;
    }

    public void printJMeterReport() {
        int transactionCount = jmeterTransactions.getTransactionCount();
        int failCount = jmeterTransactions.getFailCount();
        String summaryReport = String.format("Transactions executed: %d, failed: %d", transactionCount, failCount);
        System.out.println(summaryReport);
    }
}
