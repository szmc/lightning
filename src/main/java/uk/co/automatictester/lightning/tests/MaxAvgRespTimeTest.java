package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.Test;

public class MaxAvgRespTimeTest extends Test {

    private String transactionName;
    private long maxAvgRespTime;

    public MaxAvgRespTimeTest(String name, String description, String transactionName, long maxAvgRespTime) {
        super(name, description);
        this.transactionName = transactionName;
        this.maxAvgRespTime = maxAvgRespTime;
    }

    // TODO implement
    public void execute() {
        System.out.println("name : " + name);
        System.out.println("description : " + description);
        System.out.println("transactionName : " + transactionName);
        System.out.println("maxAvgRespTime : " + maxAvgRespTime);
    }
}
