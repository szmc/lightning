package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static uk.co.automatictester.lightning.data.TestData.*;

public class RespTimeStdDevTestTest {

    @Test
    public void testExecutePass() {
        RespTimeStdDevTest test = new RespTimeStdDevTest("Test #1", "Verify response times", "Search", 1);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_1_SUCCESS);
        jmeterTxns.add(SEARCH_2_SUCCESS);
        jmeterTxns.add(SEARCH_3_SUCCESS);

        test.execute(jmeterTxns);
        String testReport = test.getReport();
        assertThat(testReport, containsString("Test result:      Pass"));
    }

    @Test
    public void testExecuteFail() {
        RespTimeStdDevTest test = new RespTimeStdDevTest("Test #1", "Verify response times", "Search", 0);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_1_SUCCESS);
        jmeterTxns.add(SEARCH_2_SUCCESS);
        jmeterTxns.add(SEARCH_3_SUCCESS);

        test.execute(jmeterTxns);
        String testReport = test.getReport();
        assertThat(testReport, containsString("Test result:      FAIL"));
    }

    @Test
    public void testExecuteError() {
        RespTimeStdDevTest test = new RespTimeStdDevTest("Test #1", "Verify standard deviance", NONEXISTENT_LABEL, 8);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_11221_SUCCESS);

        test.execute(jmeterTxns);
        String testReport = test.getReport();
        assertThat(testReport, containsString("Test result:      ERROR"));
    }

    @Test
    public void testIsEqual() {
        assertThat(RESP_TIME_STD_DEV_TEST_A, is(equalTo(RESP_TIME_STD_DEV_TEST_A)));
    }

    @Test
    public void testIsNotEqual() {
        assertThat(RESP_TIME_STD_DEV_TEST_A, is(not(equalTo(RESP_TIME_STD_DEV_TEST_B))));
    }
}