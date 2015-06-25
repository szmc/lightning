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
        String ls = System.lineSeparator();
        String desc = (!description.isEmpty()) ? ("Test description: " + description + ls) : "";
        String testResult = "";

        if (error) {
            testResult = "ERROR";
        } else if (failed) {
            testResult = "FAIL";
        } else {
            testResult = "Pass";
        }

        return "Test name:        " + name + ls
                + desc
                + "Transaction name: " + transactionName + ls
                + "Expected result:  " + expectedResult + ls
                + "Actual result:    " + actualResult + ls
                + "Test result:      " + testResult + ls + ls;
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
