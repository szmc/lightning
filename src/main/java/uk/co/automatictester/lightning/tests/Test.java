package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

public abstract class Test {

    protected final String name;
    protected final String description;
    protected final String transactionName;
    protected String expectedResult;
    protected String actualResult;
    protected boolean passed;
    protected boolean failed;
    protected boolean error;

    protected Test(String name, String description, String transactionName) {
        this.name = name;
        this.description = description;
        this.transactionName = transactionName;
        this.expectedResult = "";
        this.actualResult = "";
        this.passed = false;
        this.failed = false;
        this.error = false;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public String getReport() {
        String desc = (!description.isEmpty()) ? (String.format("Test description: %s%n", description)) : "";
        String testResult;

        if (error) {
            testResult = "ERROR";
        } else if (failed) {
            testResult = "FAIL";
        } else {
            testResult = "Pass";
        }

        return String.format("Test name:        %s%n" +
                        "%s" +
                        "Transaction name: %s%n" +
                        "Expected result:  %s%n" +
                        "Actual result:    %s%n" +
                        "Test result:      %s%n%n",
                name,
                desc,
                transactionName,
                expectedResult,
                actualResult,
                testResult);
    }

    public boolean isPassed() {
        return passed;
    }

    public boolean isFailed() {
        return failed;
    }

    public boolean isError() {
        return error;
    }
}
