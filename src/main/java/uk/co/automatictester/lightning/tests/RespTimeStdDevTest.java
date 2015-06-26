package uk.co.automatictester.lightning.tests;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import uk.co.automatictester.lightning.JMeterTransactions;

import java.text.DecimalFormat;
import java.util.List;

public class RespTimeStdDevTest extends Test {

    private static final String EXPECTED_RESULT_MESSAGE = "Average standard deviance time <= %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Average standard deviance time = %s";

    private final long maxRespTimeStdDev;

    public RespTimeStdDevTest(String name, String description, String transactionName, long maxRespTimeStdDev) {
        super(name, description, transactionName);
        this.maxRespTimeStdDev = maxRespTimeStdDev;
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        JMeterTransactions transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);

        DescriptiveStatistics ds = new DescriptiveStatistics();
        for (List<String> transaction : transactions) {
            String elapsed = transaction.get(1);
            ds.addValue(Double.parseDouble(elapsed));
        }
        double actualRespTimeStdDev = ds.getStandardDeviation();
        DecimalFormat df = new DecimalFormat("#.##");
        double roundedActualRespTimeStdDev = Double.valueOf(df.format(actualRespTimeStdDev));

        expectedResult = String.format(EXPECTED_RESULT_MESSAGE, maxRespTimeStdDev);
        actualResult = String.format(ACTUAL_RESULT_MESSAGE, roundedActualRespTimeStdDev);
        failed = (roundedActualRespTimeStdDev > maxRespTimeStdDev);
    }

    public boolean equals(Object obj) {
        if (obj instanceof RespTimeStdDevTest) {
            RespTimeStdDevTest test = (RespTimeStdDevTest) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    transactionName.equals(test.transactionName) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    failed == test.failed &&
                    maxRespTimeStdDev == test.maxRespTimeStdDev;
        } else {
            return false;
        }
    }
}
