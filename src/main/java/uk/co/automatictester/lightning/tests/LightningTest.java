package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

public abstract class LightningTest {

    protected final String name;
    protected final String description;
    protected final String transactionName;
    protected String expectedResult;
    protected String actualResult;
    protected TestResult result;

    protected LightningTest(String name, String description, String transactionName) {
        this.name = name;
        this.description = description;
        this.transactionName = transactionName;
        this.expectedResult = "";
        this.actualResult = "";
        this.result = null;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public void printExecutionReport() {
        String desc = (!description.isEmpty()) ? (String.format("Test description: %s%n", description)) : "";
        String transName = (transactionName != null) ? (String.format("Transaction name: %s%n", transactionName)) : "";
        String testResult;

        if (result == TestResult.IGNORED) {
            testResult = "IGNORED";
        } else if (result == TestResult.FAIL) {
            testResult = "FAIL";
        } else {
            testResult = "Pass";
        }

        String executionReport = String.format("Test name:        %s%n" +
                        "%s" +
                        "%s" +
                        "Expected result:  %s%n" +
                        "Actual result:    %s%n" +
                        "Test result:      %s%n",
                name,
                desc,
                transName,
                expectedResult,
                actualResult,
                testResult);

        System.out.println(executionReport);
    }

    public TestResult getResult() {
        return result;
    }
}
