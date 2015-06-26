package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class PassedTransactionsTestTest {

    @Test
    public void verifyExecuteMethodPass() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(LOGIN_1000_SUCCESS);

        test.execute(jmeterTxns);
        assertThat(test.isPassed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteMethodFail() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(LOGIN_1200_FAILURE);

        test.execute(jmeterTxns);
        assertThat(test.isFailed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteMethodError() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", NONEXISTENT_LABEL, 0);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(LOGIN_1200_FAILURE);

        test.execute(jmeterTxns);
        assertThat(test.isError(), is(equalTo(true)));
    }

    @Test
    public void verifyIsEqual() {
        assertThat(PASSED_TRANSACTIONS_TEST_A, is(equalTo(PASSED_TRANSACTIONS_TEST_A)));
    }

    @Test
    public void verifyIsNotEqual() {
        assertThat(PASSED_TRANSACTIONS_TEST_A, is(not(equalTo(PASSED_TRANSACTIONS_TEST_B))));
    }
}