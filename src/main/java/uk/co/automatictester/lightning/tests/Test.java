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

    public int reportResults() {
        System.out.println("Test name:        " + name);
        if (!description.isEmpty()) System.out.println("Test description: " + description);
        System.out.println("Transaction name: " + transactionName);
        System.out.println("Expected result:  " + expectedResult);
        System.out.println("Actual result:    " + actualResult);
        System.out.println("Test result:      " + ((failed) ? "FAIL" : "Pass") + System.lineSeparator());
        return (failed) ? 1 : 0;
    }
}
