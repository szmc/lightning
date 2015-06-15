package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

public abstract class Test {

    protected String name;
    protected String description;

    protected Test(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);
}
