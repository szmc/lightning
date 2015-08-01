package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

import java.util.List;
import java.util.Objects;

public class PassedTransactionsTest extends LightningTest {

    private static final String EXPECTED_RESULT_MESSAGE = "Number of failed transactions <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Number of failed transactions = %s";

    private final long allowedNumberOfFailedTransactions;

    public PassedTransactionsTest(String name, String type, String description, String transactionName, long allowedNumberOfFailedTransactions) {
        super(name, type, description, transactionName);
        this.allowedNumberOfFailedTransactions = allowedNumberOfFailedTransactions;
        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, allowedNumberOfFailedTransactions);
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        try {
            JMeterTransactions transactions;
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

            if (failureCount > allowedNumberOfFailedTransactions) {
                result = TestResult.FAIL;
            } else {
                result = TestResult.PASS;
            }
        } catch (Exception e) {
            result = TestResult.IGNORED;
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
                    result == test.result &&
                    allowedNumberOfFailedTransactions == test.allowedNumberOfFailedTransactions &&
                    type.equals(test.type);
        } else {
            return false;
        }
    }
}
