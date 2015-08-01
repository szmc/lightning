package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class RespTimeAvgTestTest {

    @Test
    public void verifyExecutePass() {
        RespTimeAvgTest test = new RespTimeAvgTest("Test #1", "avgRespTimeTest", "Verify response times", "Search", 1000);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_800_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.PASS)));
    }

    @Test
    public void verifyExecuteFail() {
        RespTimeAvgTest test = new RespTimeAvgTest("Test #1", "avgRespTimeTest", "Verify response times", "Search", 800);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.FAIL)));
    }

    @Test
    public void verifyExecuteError() {
        RespTimeAvgTest test = new RespTimeAvgTest("Test #1", "avgRespTimeTest", "Verify response times", "nonexistent", 800);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.getResult(), is(equalTo(TestResult.IGNORED)));
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