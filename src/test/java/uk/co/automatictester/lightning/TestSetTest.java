package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.data.JMeterTransactions;
import uk.co.automatictester.lightning.tests.LightningTest;
import uk.co.automatictester.lightning.tests.PassedTransactionsTest;
import uk.co.automatictester.lightning.tests.RespTimeAvgTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.co.automatictester.lightning.shared.TestData.LOGIN_3514_SUCCESS;
import static uk.co.automatictester.lightning.shared.TestData.SEARCH_11221_SUCCESS;

public class TestSetTest extends ConsoleOutputTest {

    @Test
    public void verifyExecuteMethod_2_0_0() {
        PassedTransactionsTest passedTransactionsTestA = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", "Login", 0);
        PassedTransactionsTest passedTransactionsTestB = new PassedTransactionsTest("Test #2", "passedTransactionsTest", "Verify number of passed tests", null, 0);

        JMeterTransactions transactions = new JMeterTransactions();
        transactions.add(LOGIN_3514_SUCCESS);
        transactions.add(SEARCH_11221_SUCCESS);

        List<LightningTest> tests = new ArrayList<>();
        tests.add(passedTransactionsTestA);
        tests.add(passedTransactionsTestB);

        TestSet testSet = new TestSet(tests);
        configureStream();
        testSet.execute(transactions);
        revertStream();

        assertThat(testSet.getTestCount(), is(2));
        assertThat(testSet.getPassCount(), is(2));
        assertThat(testSet.getFailCount(), is(0));
        assertThat(testSet.getIgnoreCount(), is(0));
    }

    @Test
    public void verifyExecuteMethod_1_1_1() {
        RespTimeAvgTest respTimeAvgTestA = new RespTimeAvgTest("Test #1", "avgRespTimeTest", "", "Login", 4000);
        RespTimeAvgTest respTimeAvgTestB = new RespTimeAvgTest("Test #2", "avgRespTimeTest", "", "Search", 5000);
        RespTimeAvgTest respTimeAvgTestC = new RespTimeAvgTest("Test #3", "avgRespTimeTest", "", "Sear", 1000);

        JMeterTransactions transactions = new JMeterTransactions();
        transactions.add(LOGIN_3514_SUCCESS);
        transactions.add(SEARCH_11221_SUCCESS);

        List<LightningTest> tests = new ArrayList<>();
        tests.add(respTimeAvgTestA);
        tests.add(respTimeAvgTestB);
        tests.add(respTimeAvgTestC);

        TestSet testSet = new TestSet(tests);
        configureStream();
        testSet.execute(transactions);
        revertStream();

        assertThat(testSet.getTestCount(), is(3));
        assertThat(testSet.getPassCount(), is(1));
        assertThat(testSet.getFailCount(), is(1));
        assertThat(testSet.getIgnoreCount(), is(1));
    }
}