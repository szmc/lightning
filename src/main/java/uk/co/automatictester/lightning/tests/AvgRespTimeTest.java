package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

import java.text.DecimalFormat;
import java.util.List;

public class AvgRespTimeTest extends Test {

    private static final String EXPECTED_RESULT_MESSAGE = "Average response time <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Average response time = %s";

    private final long maxAvgRespTime;

    public AvgRespTimeTest(String name, String description, String transactionName, long maxAvgRespTime) {
        super(name, description, transactionName);
        this.maxAvgRespTime = maxAvgRespTime;
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        JMeterTransactions transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);

        double totalRespTime = 0;
        for (List<String> transaction : transactions) {
            String elapsed = transaction.get(1);
            totalRespTime += Long.parseLong(elapsed);
        }
        double avgRespTime = totalRespTime / transactions.size();
        DecimalFormat df = new DecimalFormat("#.##");
        double roundedAvgRespTime = Double.valueOf(df.format(avgRespTime));

        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, maxAvgRespTime);
        actualResult = String.format(ACTUAL_RESULT_MESSAGE, roundedAvgRespTime);
        failed = (roundedAvgRespTime > maxAvgRespTime);
    }
}
