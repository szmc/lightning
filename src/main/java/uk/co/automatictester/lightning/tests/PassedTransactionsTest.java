package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

import java.util.List;

public class PassedTransactionsTest extends Test {

    private static final String EXPECTED_RESULT_MESSAGE = "Number of failed transactions <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Number of failed transactions = %s";

    private final long allowedNumberOfFailedTransactions;

    public PassedTransactionsTest(String name, String description, String transactionName, long allowedNumberOfFailedTransactions) {
        super(name, description, transactionName);
        this.allowedNumberOfFailedTransactions = allowedNumberOfFailedTransactions;
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        JMeterTransactions transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);

        int failureCount = 0;
        for (List<String> transaction : transactions) {
            String success = transaction.get(2);
            if (!Boolean.parseBoolean(success)) failureCount++;
        }

        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, allowedNumberOfFailedTransactions);
        actualResult = String.format(ACTUAL_RESULT_MESSAGE, failureCount);
        failed = (failureCount > allowedNumberOfFailedTransactions);
    }

    public boolean equals(Object obj) {
        if (obj instanceof PassedTransactionsTest) {
            PassedTransactionsTest test = (PassedTransactionsTest) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    transactionName.equals(test.transactionName) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    failed == test.failed &&
                    allowedNumberOfFailedTransactions == test.allowedNumberOfFailedTransactions;
        } else {
            return false;
        }
    }
}
