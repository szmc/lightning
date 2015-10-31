package uk.co.automatictester.lightning.reporters;

import uk.co.automatictester.lightning.tests.LightningTest;

public class RespTimeBasedTestReporter extends TestReporter {

    public RespTimeBasedTestReporter(LightningTest test) {
        super(test);
    }

    public void printTestExecutionReport() {
        String executionReport = String.format("Test name:            %s%n" +
                        "Test type:            %s%n" +
                        "%s" +
                        "%s" +
                        "Expected result:      %s%n" +
                        "Actual result:        %s%n" +
                        "Transaction count:    %s%n" +
                        "Longest transactions: %s%n" +
                        "Test result:          %s%n",
                test.getName(),
                test.getType(),
                getDescription(),
                getTransactionName(),
                test.getExpectedResult(),
                test.getActualResult(),
                test.getTransactionCount(),
                test.getLongestTransactions(),
                getResult());

        System.out.println(executionReport);
    }
}
