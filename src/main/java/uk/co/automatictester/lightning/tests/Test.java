package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

public abstract class Test {

    protected final String name;
    protected final String description;
    protected final String transactionName;
    protected String expectedResult;
    protected String actualResult;
    protected boolean failed;

    protected Test(String name, String description, String transactionName) {
        this.name = name;
        this.description = description;
        this.transactionName = transactionName;
        this.expectedResult = "";
        this.actualResult = "";
        this.failed = false;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public String getReport() {
        String ls = System.lineSeparator();
        String desc = (!description.isEmpty()) ? ("Test description: " + description + ls) : "";
        return "Test name:        " + name + ls
                + desc
                + "Transaction name: " + transactionName + ls
                + "Expected result:  " + expectedResult + ls
                + "Actual result:    " + actualResult + ls
                + "Test result:      " + ((failed) ? "FAIL" : "Pass") + ls + ls;
    }

    public boolean isFailed() {
        return failed;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Test) {
            Test test = (Test) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    transactionName.equals(test.transactionName) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    failed == test.failed;
        } else {
            return false;
        }
    }
}
