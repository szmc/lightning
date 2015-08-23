package uk.co.automatictester.lightning.ci;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestSet;

public abstract class CIReporter {

    public static String getVerifySummary(TestSet testSet) {
        int executed = testSet.getTestCount();
        int failed = testSet.getFailCount();
        int ignored = testSet.getIgnoreCount();
        return String.format("Tests executed: %s, failed: %s, ignored: %s", executed, failed, ignored);
    }

    public static String getReportSummary(JMeterTransactions jmeterTransactions) {
        int executed = jmeterTransactions.getTransactionCount();
        int failed = jmeterTransactions.getFailCount();
        return String.format("Transactions executed: %s, failed: %s", executed, failed);
    }

}
