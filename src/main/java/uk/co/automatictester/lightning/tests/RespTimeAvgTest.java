package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

import java.text.DecimalFormat;
import java.util.List;

public class RespTimeAvgTest extends LightningTest {

    private static final String EXPECTED_RESULT_MESSAGE = "Average response time <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Average response time = %s";

    private final long maxAvgRespTime;

    public RespTimeAvgTest(String name, String description, String transactionName, long maxAvgRespTime) {
        super(name, description, transactionName);
        this.maxAvgRespTime = maxAvgRespTime;
        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, maxAvgRespTime);
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        try {
            JMeterTransactions transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);

            double totalRespTime = 0;
            for (List<String> transaction : transactions) {
                String elapsed = transaction.get(1);
                totalRespTime += Long.parseLong(elapsed);
            }
            double avgRespTime = totalRespTime / transactions.size();
            DecimalFormat df = new DecimalFormat("#.##");
            double roundedAvgRespTime = Double.valueOf(df.format(avgRespTime));

            actualResult = String.format(ACTUAL_RESULT_MESSAGE, roundedAvgRespTime);
            passed = !(roundedAvgRespTime > maxAvgRespTime);
            failed = (roundedAvgRespTime > maxAvgRespTime);
        } catch (Exception e) {
            error = true;
            actualResult = e.getMessage();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof RespTimeAvgTest) {
            RespTimeAvgTest test = (RespTimeAvgTest) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    transactionName.equals(test.transactionName) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    failed == test.failed &&
                    maxAvgRespTime == test.maxAvgRespTime;
        } else {
            return false;
        }
    }
}
