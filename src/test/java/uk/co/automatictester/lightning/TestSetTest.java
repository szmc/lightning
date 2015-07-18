package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.tests.LightningTest;
import uk.co.automatictester.lightning.tests.PassedTransactionsTest;
import uk.co.automatictester.lightning.tests.RespTimeNthPercentileTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.co.automatictester.lightning.data.TestData.LOGIN_3514_SUCCESS;
import static uk.co.automatictester.lightning.data.TestData.SEARCH_11221_SUCCESS;

public class TestSetTest {

    public static final PassedTransactionsTest PASSED_TRANSACTIONS_TEST_3_0_0_A = new PassedTransactionsTest("Test #1", "Verify number of passed tests", "Login", 0);
    public static final PassedTransactionsTest PASSED_TRANSACTIONS_TEST_3_0_0_B = new PassedTransactionsTest("Test #2", "Verify number of passed tests", null, 0);
    public static final RespTimeNthPercentileTest RESP_TIME_PERC_TEST_3_0_0_C = new RespTimeNthPercentileTest("Test #3", "Verify nth percentile", "Search", 80, 11245);

    @Test
    public void verifyExecuteMethod() {
        JMeterTransactions transactions = new JMeterTransactions();
        transactions.add(LOGIN_3514_SUCCESS);
        transactions.add(SEARCH_11221_SUCCESS);

        List<LightningTest> tests = new ArrayList<>();
        tests.add(PASSED_TRANSACTIONS_TEST_3_0_0_A);
        tests.add(PASSED_TRANSACTIONS_TEST_3_0_0_B);
        tests.add(RESP_TIME_PERC_TEST_3_0_0_C);

        TestSet testSet = new TestSet(tests);
        testSet.execute(transactions);

        assertThat(testSet.getPassCount(), is(3));
        assertThat(testSet.getFailCount(), is(0));
        assertThat(testSet.getIgnoreCount(), is(0));
    }
}