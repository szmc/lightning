package uk.co.automatictester.lightning.tests;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import uk.co.automatictester.lightning.TestResult;
import uk.co.automatictester.lightning.data.JMeterTransactions;

import java.util.List;

public class RespTimeMaxTest extends RespTimeBasedTest {

    private static final String EXPECTED_RESULT_MESSAGE = "Max response time <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Max response time = %s";

    private final long maxRespTime;

    public RespTimeMaxTest(String name, String type, String description, String transactionName, long maxRespTime) {
        super(name, type, description, transactionName);
        this.maxRespTime = maxRespTime;
        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, maxRespTime);
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {

        try {
            JMeterTransactions transactions = filterTransactions(originalJMeterTransactions);
            transactionCount = transactions.getTransactionCount();

            DescriptiveStatistics ds = new DescriptiveStatistics();
            for (List<String> transaction : transactions) {
                String elapsed = transaction.get(1);
                ds.addValue(Double.parseDouble(elapsed));
            }
            longestTransactions = transactions.getLongestTransactions();
            long maxRespTime = (long) ds.getMax();

            actualResult = String.format(ACTUAL_RESULT_MESSAGE, maxRespTime);

            if (maxRespTime > this.maxRespTime) {
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
        if (obj instanceof RespTimeMaxTest) {
            RespTimeMaxTest test = (RespTimeMaxTest) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    transactionName.equals(test.transactionName) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    result == test.result &&
                    maxRespTime == test.maxRespTime &&
                    transactionCount == test.transactionCount &&
                    type.equals(test.type);
        } else {
            return false;
        }
    }
}
