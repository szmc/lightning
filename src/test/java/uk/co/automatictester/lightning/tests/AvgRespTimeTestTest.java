package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class AvgRespTimeTestTest {

    @Test
    public void testExecutePass() {
        AvgRespTimeTest test = new AvgRespTimeTest("Test #1", "Verify response times", "Search", 1000);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_800_SUCCESS);

        test.execute(jmeterTxns);
        assertThat(test.isPassed(), is(equalTo(true)));
    }

    @Test
    public void testExecuteFail() {
        AvgRespTimeTest test = new AvgRespTimeTest("Test #1", "Verify response times", "Search", 800);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTxns);
        assertThat(test.isFailed(), is(equalTo(true)));
    }

    @Test
    public void testExecuteError() {
        AvgRespTimeTest test = new AvgRespTimeTest("Test #1", "Verify response times", NONEXISTENT_LABEL, 800);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTxns);
        assertThat(test.isError(), is(equalTo(true)));
    }

    @Test
    public void verifyIsEqual() {
        assertThat(AVG_RESP_TIME_TEST_A, is(equalTo(AVG_RESP_TIME_TEST_A)));
    }

    @Test
    public void verifyIsNotEqual() {
        assertThat(AVG_RESP_TIME_TEST_A, is(not(equalTo(AVG_RESP_TIME_TEST_B))));
    }
}