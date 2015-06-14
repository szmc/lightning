package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.Test;
import uk.co.automatictester.lightning.TransactionData;

import java.util.List;

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

        List<List<String>> transactions = TransactionData.getTransactions();
        long totalRespTime = 0;

        // TODO: implement label filtering
        for (List<String> transaction : transactions) {
            String elapsed = transaction.get(TransactionData.columns.get("elapsed"));
            totalRespTime += Long.parseLong(elapsed);
        }

        System.out.println("avgRespTime : " + totalRespTime / transactions.size());
    }
}
