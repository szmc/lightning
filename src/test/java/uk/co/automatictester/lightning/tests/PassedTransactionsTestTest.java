package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class PassedTransactionsTestTest {

    @Test
    public void verifyExecuteMethodPass() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(LOGIN_1000_SUCCESS);

        test.execute(jmeterTranactions);
        assertThat(test.isPassed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteMethodAllTransactionsPass() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", null, 0);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(LOGIN_1000_SUCCESS);
        jmeterTranactions.add(SEARCH_800_SUCCESS);

        test.execute(jmeterTranactions);
        assertThat(test.isPassed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteMethodFail() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(LOGIN_1200_FAILURE);

        test.execute(jmeterTranactions);
        assertThat(test.isFailed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteMethodAllTransactionsFail() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", null, 0);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(LOGIN_1200_SUCCESS);
        jmeterTranactions.add(SEARCH_800_FAILURE);

        test.execute(jmeterTranactions);
        assertThat(test.isFailed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteMethodError() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "Verify number of passed tests", NONEXISTENT_LABEL, 0);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(LOGIN_1200_FAILURE);

        test.execute(jmeterTranactions);
        assertThat(test.isError(), is(equalTo(true)));
    }

    @Test
    public void verifyIsEqual() {
        assertThat(PASSED_TRANSACTIONS_TEST_A, is(equalTo(PASSED_TRANSACTIONS_TEST_A)));
    }

    @Test
    public void verifyIsEqualNoTransactionName() {
        assertThat(PASSED_TRANSACTIONS_TEST_NO_TRANS_NAME, is(equalTo(PASSED_TRANSACTIONS_TEST_NO_TRANS_NAME)));
    }

    @Test
    public void verifyIsNotEqual() {
        assertThat(PASSED_TRANSACTIONS_TEST_A, is(not(equalTo(PASSED_TRANSACTIONS_TEST_B))));
    }

    @Test
    public void verifyIsNotEqualOtherTestType() {
        assertThat((LightningTest) PASSED_TRANSACTIONS_TEST_A, is(not(equalTo((LightningTest) RESP_TIME_PERC_TEST_A))));
    }

    @Test
    public void verifyIsNotEqualNoTransactionName() {
        assertThat(PASSED_TRANSACTIONS_TEST_B, is(not(equalTo(PASSED_TRANSACTIONS_TEST_NO_TRANS_NAME))));
    }
}