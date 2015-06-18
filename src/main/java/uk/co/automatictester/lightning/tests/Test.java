package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

public abstract class Test {

    protected String name;
    protected String description;
    protected String transactionName;
    protected String expectedResult;
    protected String actualResult;
    protected boolean failed;

    protected Test(String name, String description, String transactionName) {
        this.name = name;
        this.description = description;
        this.transactionName = transactionName;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public String getReport() {
        String ls = System.lineSeparator();
        String report = "Test name:        " + name + ls;
        if (!description.isEmpty()) report += "Test description: " + description + ls;
        report += "Transaction name: " + transactionName + ls;
        report += "Expected result:  " + expectedResult + ls;
        report += "Actual result:    " + actualResult + ls;
        report += "Test result:      " + ((failed) ? "FAIL" : "Pass") + ls;
        return report;
    }

    public boolean isFailed() {
        return failed;
    }
}
