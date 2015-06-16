package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

import java.util.List;

public class MaxAvgRespTimeTest extends Test {

    private static final String EXPECTED_RESULT_MESSAGE = "Average response time <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Average response time = %s";

    private final String transactionName;
    private final long maxAvgRespTime;

    public MaxAvgRespTimeTest(String name, String description, String transactionName, long maxAvgRespTime) {
        super(name, description);
        this.transactionName = transactionName;
        this.maxAvgRespTime = maxAvgRespTime;
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        JMeterTransactions transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);

        long totalRespTime = 0;
        for (List<String> transaction : transactions) {
            String elapsed = transaction.get(1);
            totalRespTime += Long.parseLong(elapsed);
        }
        long avgRespTime = totalRespTime / transactions.size();

        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, maxAvgRespTime);
        actualResult = String.format(ACTUAL_RESULT_MESSAGE, avgRespTime);
        failed = (avgRespTime > maxAvgRespTime);
    }

    public int reportResults() {
        System.out.println("Test name:        " + name);
        if (!description.isEmpty()) System.out.println("Test description: " + description);
        System.out.println("Expected result:  " + expectedResult);
        System.out.println("Actual result:    " + actualResult);
        System.out.println("Test result:      " + ((failed) ? "FAIL" : "Pass") + System.lineSeparator());
        return (failed) ? 1 : 0;
    }
}
