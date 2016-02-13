package uk.co.automatictester.lightning.tests;

import org.apache.commons.lang3.NotImplementedException;
import uk.co.automatictester.lightning.enums.TestResult;
import uk.co.automatictester.lightning.data.JMeterTransactions;

import java.util.List;

public abstract class ClientSideTest extends LightningTest {

    protected final String transactionName;
    protected int transactionCount;

    protected ClientSideTest(String name, String type, String description, String transactionName) {
        super(name, type, description);
        this.transactionName = transactionName;
    }

    public abstract void execute(JMeterTransactions originalJMeterTransactions);

    public JMeterTransactions filterTransactions(JMeterTransactions originalJMeterTransactions) {
        if (getTransactionName() != null) {
            return originalJMeterTransactions.excludeLabelsOtherThan(getTransactionName());
        } else {
            return originalJMeterTransactions;
        }
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public List<Integer> getLongestTransactions() {
        throw new NotImplementedException("Method not implemented for LightningTest which is not RespTimeBasedTest");
    }
}
