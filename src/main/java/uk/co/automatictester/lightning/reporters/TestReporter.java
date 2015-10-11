package uk.co.automatictester.lightning.reporters;

import uk.co.automatictester.lightning.TestResult;
import uk.co.automatictester.lightning.tests.LightningTest;

public class TestReporter {

    private LightningTest test;

    public TestReporter(LightningTest test) {
        this.test = test;
    }

    public void printTestExecutionReport() {
        String executionReport = String.format("Test name:         %s%n" +
                        "Test type:         %s%n" +
                        "%s" +
                        "%s" +
                        "Expected result:   %s%n" +
                        "Actual result:     %s%n" +
                        "Transaction count: %s%n"+
                        "Test result:       %s%n",
                test.getName(),
                test.getType(),
                getDescription(),
                getTransactionName(),
                test.getExpectedResult(),
                test.getActualResult(),
                test.getTransactionCount(),
                getResult());

        System.out.println(executionReport);
    }

    private String getDescription() {
        return (!test.getDescription().isEmpty()) ? (String.format("Test description:  %s%n", test.getDescription())) : "";
    }

    private String getTransactionName() {
        return (test.getTransactionName() != null) ? (String.format("Transaction name:  %s%n", test.getTransactionName())) : "";
    }

    private String getResult() {
        if (test.getResult() == TestResult.IGNORED) {
            return "IGNORED";
        } else if (test.getResult() == TestResult.FAIL) {
            return "FAIL";
        } else {
            return "Pass";
        }
    }
}
