package uk.co.automatictester.lightning.tests;

import java.util.List;

public abstract class RespTimeBasedTest extends ClientSideTest {

    protected List<Integer> longestTransactions;

    public RespTimeBasedTest(String name, String type, String description, String transactionName) {
        super(name, type, description, transactionName);
    }

    public List<Integer> getLongestTransactions() {
        return longestTransactions;
    }

}
