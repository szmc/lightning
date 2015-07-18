package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

public abstract class LightningTest {

    protected final String name;
    protected final String description;
    protected final String transactionName;
    protected String expectedResult;
    protected String actualResult;
    protected TestResult result;

    protected LightningTest(String name, String description, String transactionName) {
        this.name = name;
        this.description = description;
        this.transactionName = transactionName;
        this.expectedResult = "";
        this.actualResult = "";
        this.result = null;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public TestResult getResult() {
        return result;
    }
}
