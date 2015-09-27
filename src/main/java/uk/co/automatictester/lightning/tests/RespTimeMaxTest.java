package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

import java.util.List;

public class RespTimeMaxTest extends LightningTest {

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
            JMeterTransactions transactions;
            if (transactionName != null) {
                transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);
            } else {
                transactions = originalJMeterTransactions;
            }

            long maxRespTime = 0;
            for (List<String> transaction : transactions) {
                long elapsed = Long.parseLong(transaction.get(1));
                if (elapsed > maxRespTime) {
                    maxRespTime = elapsed;
                }
            }

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
                    type.equals(test.type);
        } else {
            return false;
        }
    }
}
