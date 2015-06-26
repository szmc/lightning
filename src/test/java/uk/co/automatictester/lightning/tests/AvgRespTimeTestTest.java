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
        AvgRespTimeTest avgRespTimeBelow1000 = new AvgRespTimeTest("Test #1", "Verify response times", "Search", 1000);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_800_SUCCESS);

        avgRespTimeBelow1000.execute(jmeterTxns);
        String testReport = avgRespTimeBelow1000.getReport();
        assertThat(testReport, containsString("Test result:      Pass"));
    }

    @Test
    public void testExecuteFail() {
        AvgRespTimeTest avgRespTimeBelow800 = new AvgRespTimeTest("Test #1", "Verify response times", "Search", 800);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_11221_SUCCESS);

        avgRespTimeBelow800.execute(jmeterTxns);
        String testReport = avgRespTimeBelow800.getReport();
        assertThat(testReport, containsString("Test result:      FAIL"));
    }

    @Test
    public void testExecuteError() {
        AvgRespTimeTest avgRespTimeBelow800 = new AvgRespTimeTest("Test #1", "Verify response times", NONEXISTENT_LABEL, 800);
        JMeterTransactions jmeterTxns = new JMeterTransactions();
        jmeterTxns.add(SEARCH_11221_SUCCESS);

        avgRespTimeBelow800.execute(jmeterTxns);
        String testReport = avgRespTimeBelow800.getReport();
        assertThat(testReport, containsString("Test result:      ERROR"));
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