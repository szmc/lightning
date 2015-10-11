package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

public abstract class LightningTest {

    protected final String name;
    protected final String description;
    protected final String transactionName;
    protected final String type;
    protected String expectedResult;
    protected String actualResult;
    protected TestResult result;
    protected int transactionCount;

    protected LightningTest(String name, String type, String description, String transactionName) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.transactionName = transactionName;
        this.expectedResult = "";
        this.actualResult = "";
        this.result = null;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public JMeterTransactions filterTransactions(JMeterTransactions originalJMeterTransactions) {
        if (getTransactionName() != null) {
            return originalJMeterTransactions.excludeLabelsOtherThan(getTransactionName());
        } else {
            return originalJMeterTransactions;
        }
    }

    public String getName() {
        return name;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public String getType() {
        return type;
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
