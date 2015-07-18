package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class RespTimeNthPercentileTestTest {

    @Test
    public void testExecutePass() {
        RespTimeNthPercentileTest test = new RespTimeNthPercentileTest("Test #1", "Verify 90th percentile", "Search", 90, 9.9);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_1_SUCCESS);
        jmeterTransactions.add(SEARCH_2_SUCCESS);
        jmeterTransactions.add(SEARCH_3_SUCCESS);
        jmeterTransactions.add(SEARCH_4_SUCCESS);
        jmeterTransactions.add(SEARCH_5_SUCCESS);
        jmeterTransactions.add(SEARCH_6_SUCCESS);
        jmeterTransactions.add(SEARCH_7_SUCCESS);
        jmeterTransactions.add(SEARCH_8_SUCCESS);
        jmeterTransactions.add(SEARCH_9_SUCCESS);
        jmeterTransactions.add(SEARCH_10_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.isPassed(), is(equalTo(true)));
    }

    @Test
    public void testExecuteFail() {
        RespTimeNthPercentileTest test = new RespTimeNthPercentileTest("Test #1", "Verify 90th percentile", "Search", 90, 9.8);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_1_SUCCESS);
        jmeterTransactions.add(SEARCH_2_SUCCESS);
        jmeterTransactions.add(SEARCH_3_SUCCESS);
        jmeterTransactions.add(SEARCH_4_SUCCESS);
        jmeterTransactions.add(SEARCH_5_SUCCESS);
        jmeterTransactions.add(SEARCH_6_SUCCESS);
        jmeterTransactions.add(SEARCH_7_SUCCESS);
        jmeterTransactions.add(SEARCH_8_SUCCESS);
        jmeterTransactions.add(SEARCH_9_SUCCESS);
        jmeterTransactions.add(SEARCH_10_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.isFailed(), is(equalTo(true)));
    }

    @Test
    public void testExecuteError() {
        RespTimeNthPercentileTest test = new RespTimeNthPercentileTest("Test #1", "Verify 90th percentile", "Search", -90, 9);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_5_SUCCESS);
        test.execute(jmeterTransactions);
        assertThat(test.isError(), is(equalTo(true)));
    }

    @Test
    public void verifyIsEqual() {
        assertThat(RESP_TIME_PERC_TEST_A, is(equalTo(RESP_TIME_PERC_TEST_A)));
    }

    @Test
    public void verifyIsNotEqualOtherTestType() {
        assertThat((LightningTest) RESP_TIME_PERC_TEST_A, is(not(equalTo((LightningTest) AVG_RESP_TIME_TEST_A))));
    }

    @Test
    public void verifyIsNotEqual() {
        assertThat(RESP_TIME_PERC_TEST_A, is(not(equalTo(RESP_TIME_PERC_TEST_B))));
    }
}