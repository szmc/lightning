package uk.co.automatictester.lightning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.co.automatictester.lightning.exceptions.CSVFileNonexistentLabelException;

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
    public void verifyExcludeLabelsOtherThanMethod() {
        assertThat(JMETER_TRANSACTIONS.excludeLabelsOtherThan(LOGIN_LABEL).size(), is(2));
    }

    @Test(expectedExceptions = CSVFileNonexistentLabelException.class)
    public void verifyExcludeLabelsOtherThanMethodException() {
        JMETER_TRANSACTIONS.excludeLabelsOtherThan(NONEXISTENT_LABEL);
    }
}