package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

import java.util.List;
import java.util.Objects;

public class PassedTransactionsTest extends LightningTest {

    private static final String EXPECTED_RESULT_MESSAGE = "Number of failed transactions <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Number of failed transactions = %s";

    private final long allowedNumberOfFailedTransactions;

    public PassedTransactionsTest(String name, String description, String transactionName, long allowedNumberOfFailedTransactions) {
        super(name, description, transactionName);
        this.allowedNumberOfFailedTransactions = allowedNumberOfFailedTransactions;
        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, allowedNumberOfFailedTransactions);
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        try {
            JMeterTransactions transactions = null;
            if (transactionName != null) {
                transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);
            } else {
                transactions = originalJMeterTransactions;
            }

            int failureCount = 0;
            for (List<String> transaction : transactions) {
                String success = transaction.get(2);
                if (!Boolean.parseBoolean(success)) failureCount++;
            }

            actualResult = String.format(ACTUAL_RESULT_MESSAGE, failureCount);
            passed = !(failureCount > allowedNumberOfFailedTransactions);
            failed = (failureCount > allowedNumberOfFailedTransactions);
        } catch (Exception e) {
            error = true;
            actualResult = e.getMessage();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof PassedTransactionsTest) {
            PassedTransactionsTest test = (PassedTransactionsTest) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    Objects.equals(transactionName, test.transactionName) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    failed == test.failed &&
                    allowedNumberOfFailedTransactions == test.allowedNumberOfFailedTransactions;
        } else {
            return false;
        }
    }
}
