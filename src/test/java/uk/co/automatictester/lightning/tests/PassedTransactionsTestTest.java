package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.LOGIN_1200_FAILURE;
import static uk.co.automatictester.lightning.data.TestData.LOGIN_1200_SUCCESS;

public class PassedTransactionsTestTest {

    @Test
    public void testExecutePass() {
        JMeterTransactions txns = new JMeterTransactions();
        txns.add(LOGIN_1200_SUCCESS);

        PassedTransactionsTest test = new PassedTransactionsTest("test a", "description", "Login", 0);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, containsString("Test name:        test a"));
        assertThat(testReport, containsString("Transaction name: Login"));
        assertThat(testReport, containsString("Expected result:  Number of failed transactions <= 0"));
        assertThat(testReport, containsString("Actual result:    Number of failed transactions = 0"));
        assertThat(testReport, containsString("Test result:      Pass"));
    }

    @Test
    public void testExecuteFail() {
        JMeterTransactions txns = new JMeterTransactions();
        txns.add(LOGIN_1200_FAILURE);

        PassedTransactionsTest test = new PassedTransactionsTest("test a", "description", "Login", 0);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, containsString("Test name:        test a"));
        assertThat(testReport, containsString("Transaction name: Login"));
        assertThat(testReport, containsString("Expected result:  Number of failed transactions <= 0"));
        assertThat(testReport, containsString("Actual result:    Number of failed transactions = 1"));
        assertThat(testReport, containsString("Test result:      FAIL"));
    }

    @Test
    public void testIsEqual() {
        PassedTransactionsTest test1 = new PassedTransactionsTest("test a", "description", "transaction x", 1);
        PassedTransactionsTest test2 = new PassedTransactionsTest("test a", "description", "transaction x", 1);
        assertThat(test1, is(equalTo(test2)));
    }

    @Test
    public void testIsNotEqual() {
        PassedTransactionsTest test1 = new PassedTransactionsTest("test a", "description", "transaction x", 1);
        PassedTransactionsTest test2 = new PassedTransactionsTest("test a", "description", "transaction x", 0);
        assertThat(test1, is(not(equalTo(test2))));
    }
}