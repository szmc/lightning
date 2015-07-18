package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class RespTimeStdDevTestTest {

    @Test
    public void verifyExecutePass() {
        RespTimeStdDevTest test = new RespTimeStdDevTest("Test #1", "Verify standard deviance", "Search", 1);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_1_SUCCESS);
        jmeterTransactions.add(SEARCH_2_SUCCESS);
        jmeterTransactions.add(SEARCH_3_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.isPassed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteFail() {
        RespTimeStdDevTest test = new RespTimeStdDevTest("Test #1", "Verify standard deviance", "Search", 0);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_1_SUCCESS);
        jmeterTransactions.add(SEARCH_2_SUCCESS);
        jmeterTransactions.add(SEARCH_3_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.isFailed(), is(equalTo(true)));
    }

    @Test
    public void verifyExecuteError() {
        RespTimeStdDevTest test = new RespTimeStdDevTest("Test #1", "Verify standard deviance", NONEXISTENT_LABEL, 8);
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTransactions);
        assertThat(test.isError(), is(equalTo(true)));
    }

    @Test
    public void verifyIsEqual() {
        assertThat(RESP_TIME_STD_DEV_TEST_A, is(equalTo(RESP_TIME_STD_DEV_TEST_A)));
    }

    @Test
    public void verifyIsNotEqualOtherTestType() {
        assertThat((LightningTest) RESP_TIME_STD_DEV_TEST_A, is(not(equalTo((LightningTest) AVG_RESP_TIME_TEST_A))));
    }

    @Test
    public void verifyIsNotEqual() {
        assertThat(RESP_TIME_STD_DEV_TEST_A, is(not(equalTo(RESP_TIME_STD_DEV_TEST_B))));
    }
}