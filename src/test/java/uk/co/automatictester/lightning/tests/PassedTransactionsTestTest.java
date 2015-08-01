package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class PassedTransactionsTestTest {

    @Test
    public void verifyExecuteMethodPass() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(LOGIN_1000_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.PASS)));
    }

    @Test
    public void verifyExecuteMethodAllTransactionsPass() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", null, 0);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(LOGIN_1000_SUCCESS);
        jmeterTransactions.add(SEARCH_800_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.PASS)));
    }

    @Test
    public void verifyExecuteMethodFail() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(LOGIN_1200_FAILURE);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.FAIL)));
    }

    @Test
    public void verifyExecuteMethodAllTransactionsFail() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", null, 0);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(LOGIN_1200_SUCCESS);
        jmeterTransactions.add(SEARCH_800_FAILURE);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.FAIL)));
    }

    @Test
    public void verifyExecuteMethodError() {
        PassedTransactionsTest test = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", "nonexistent", 0);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(LOGIN_1200_FAILURE);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.IGNORED)));
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