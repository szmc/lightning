package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;

import java.util.List;

public class MaxAvgRespTimeTest extends Test {

    private String transactionName;
    private long maxAvgRespTime;

    public MaxAvgRespTimeTest(String name, String description, String transactionName, long maxAvgRespTime) {
        super(name, description);
        this.transactionName = transactionName;
        this.maxAvgRespTime = maxAvgRespTime;
    }

    // TODO: implement verification, reporting etc
    public void execute(JMeterTransactions originalJMeterTransactions) {
        System.out.println("name : " + name);
        System.out.println("description : " + description);
        System.out.println("transactionName : " + transactionName);
        System.out.println("maxAvgRespTime : " + maxAvgRespTime);

        JMeterTransactions transactions = originalJMeterTransactions.excludeLabelsOtherThan(transactionName);
        long totalRespTime = 0;

        for (List<String> transaction : transactions) {
            String elapsed = transaction.get(1);
            totalRespTime += Long.parseLong(elapsed);
        }

        System.out.println("avgRespTime : " + totalRespTime / transactions.size());
    }
}
