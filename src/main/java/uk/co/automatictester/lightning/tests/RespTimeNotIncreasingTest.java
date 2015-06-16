package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

public abstract class RespTimeNotIncreasingTest extends Test {

    private String transactionName;

    public RespTimeNotIncreasingTest(String name, String description, String transactionName) {
        super(name, description);
        this.transactionName = transactionName;
    }

    // TODO implement
    public void execute(JMeterTransactions originalJMeterTransactions) {
        System.out.println("name : " + name);
        System.out.println("description : " + description);
        System.out.println("transactionName : " + transactionName);
    }
}
