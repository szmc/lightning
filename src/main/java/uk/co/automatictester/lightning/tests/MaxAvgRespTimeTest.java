package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

import java.util.List;

public class MaxAvgRespTimeTest extends Test {

    private String transactionName;
    private long maxAvgRespTime;

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

        String expectedResult = "Average response time <= " + maxAvgRespTime;
        String actualResult = "Average response time  = " + avgRespTime;
        boolean failed = (avgRespTime <= maxAvgRespTime) ? false : true;
        TestResult result = new TestResult(name, description, expectedResult, actualResult, failed);
        result.report();
    }
}
