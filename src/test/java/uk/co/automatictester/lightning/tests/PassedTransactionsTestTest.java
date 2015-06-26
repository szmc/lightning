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
        PassedTransactionsTest passedTxnTestWithNoFailedTxnAllowed = new PassedTransactionsTest("Test #1", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTxnsWithoutFailedTxn = new JMeterTransactions();
        jmeterTxnsWithoutFailedTxn.add(LOGIN_1000_SUCCESS);

        passedTxnTestWithNoFailedTxnAllowed.execute(jmeterTxnsWithoutFailedTxn);
        String testReport = passedTxnTestWithNoFailedTxnAllowed.getReport();
        assertThat(testReport, containsString("Test result:      Pass"));
    }

    @Test
    public void verifyExecuteMethodFail() {
        PassedTransactionsTest passedTxnTestWithNoFailedTxnAllowed = new PassedTransactionsTest("Test #1", "Verify number of passed tests", "Login", 0);
        JMeterTransactions jmeterTxnsWithFailedTxn = new JMeterTransactions();
        jmeterTxnsWithFailedTxn.add(LOGIN_1200_FAILURE);

        passedTxnTestWithNoFailedTxnAllowed.execute(jmeterTxnsWithFailedTxn);
        String testReport = passedTxnTestWithNoFailedTxnAllowed.getReport();
        assertThat(testReport, containsString("Test result:      FAIL"));
    }

    @Test
    public void verifyExecuteMethodError() {
        PassedTransactionsTest passedTxnTestWithNoFailedTxnAllowed = new PassedTransactionsTest("Test #1", "Verify number of passed tests", NONEXISTENT_LABEL, 0);
        JMeterTransactions jmeterTxnsWithFailedTxn = new JMeterTransactions();
        jmeterTxnsWithFailedTxn.add(LOGIN_1200_FAILURE);

        passedTxnTestWithNoFailedTxnAllowed.execute(jmeterTxnsWithFailedTxn);
        String testReport = passedTxnTestWithNoFailedTxnAllowed.getReport();
        assertThat(testReport, containsString("Test result:      ERROR"));
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