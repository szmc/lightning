package uk.co.automatictester.lightning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class JMeterTransactionsTest {

    private static JMeterTransactions JMETER_TRANSACTIONS = new JMeterTransactions();

    @BeforeMethod
    public void setupData() {
        JMETER_TRANSACTIONS.clear();
        JMETER_TRANSACTIONS.add(LOGIN_1200_SUCCESS);
        JMETER_TRANSACTIONS.add(LOGIN_1000_SUCCESS);
        JMETER_TRANSACTIONS.add(SEARCH_800_SUCCESS);
    }

    @Test
    public void verifyNumberOfTransactions() {
        assertThat(JMETER_TRANSACTIONS.size(), is(3));
    }

    @Test
    public void verifyExcludeLabelsOtherThanMethod() {
        assertThat(JMETER_TRANSACTIONS.excludeLabelsOtherThan(EXISTING_LABEL).size(), is(2));
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "No transactions with label equal to '" + NONEXISTENT_LABEL + "' found in CSV file")
    public void verifyExcludeLabelsOtherThanMethodRuntimeException() {
        JMETER_TRANSACTIONS.excludeLabelsOtherThan(NONEXISTENT_LABEL);
    }
}