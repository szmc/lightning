package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class RespTimeAvgTestTest {

    @Test
    public void verifyExecutePass() {
        RespTimeAvgTest test = new RespTimeAvgTest("Test #1", "Verify response times", "Search", 1000);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(SEARCH_800_SUCCESS);

        test.execute(jmeterTranactions);
        assertThat(test.isPassed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteFail() {
        RespTimeAvgTest test = new RespTimeAvgTest("Test #1", "Verify response times", "Search", 800);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTranactions);
        assertThat(test.isFailed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteError() {
        RespTimeAvgTest test = new RespTimeAvgTest("Test #1", "Verify response times", NONEXISTENT_LABEL, 800);
        JMeterTransactions jmeterTranactions = new JMeterTransactions();
        jmeterTranactions.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTranactions);
        assertThat(test.isError(), is(equalTo(true)));
    }

    @Test
    public void verifyIsEqual() {
        assertThat(AVG_RESP_TIME_TEST_A, is(equalTo(AVG_RESP_TIME_TEST_A)));
    }

    @Test
    public void verifyIsNotEqualOtherTestType() {
        assertThat((LightningTest) AVG_RESP_TIME_TEST_A, is(not(equalTo((LightningTest) RESP_TIME_PERC_TEST_A))));
    }

    @Test
    public void verifyIsNotEqual() {
        assertThat(AVG_RESP_TIME_TEST_A, is(not(equalTo(AVG_RESP_TIME_TEST_B))));
    }
}