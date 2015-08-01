package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

import java.util.Objects;

public class ThroughputTest extends LightningTest {

    private static final String EXPECTED_RESULT_MESSAGE = "Throughput >= %s / second";
    private static final String ACTUAL_RESULT_MESSAGE = "Throughput = %s / second";

    private final double minThroughput;

    public ThroughputTest(String name, String type, String description, String transactionName, double minThroughput) {
        super(name, type, description, transactionName);
        this.minThroughput = minThroughput;
        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, minThroughput);
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        try {
            JMeterTransactions transactions;
            if (transactionName != null) {
                transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);
            } else {
                transactions = originalJMeterTransactions;
            }

            double actualThroughput = transactions.getThroughput();

            actualResult = String.format(ACTUAL_RESULT_MESSAGE, actualThroughput);

            if (actualThroughput < minThroughput) {
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
        if (obj instanceof ThroughputTest) {
            ThroughputTest test = (ThroughputTest) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    Objects.equals(transactionName, test.transactionName) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    result == test.result &&
                    minThroughput == test.minThroughput &&
                    type == test.type;
        } else {
            return false;
        }
    }
}
