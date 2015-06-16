package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

public abstract class Test {

    protected String name;
    protected String description;
    protected String expectedResult;
    protected String actualResult;
    protected boolean failed;

    protected Test(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public abstract int reportResults();
}
