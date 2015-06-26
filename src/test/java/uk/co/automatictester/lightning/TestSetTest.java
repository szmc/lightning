package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.tests.AvgRespTimeTest;
import uk.co.automatictester.lightning.tests.PassedTransactionsTest;
import uk.co.automatictester.lightning.tests.RespTimeStdDevTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_XML_FILE;
import static uk.co.automatictester.lightning.data.TestData.VALID_CSV_FILE;

public class TestSetTest {

    @Test
    public void verifyLoadMethod() {
        AvgRespTimeTest avgRespTimeTestTest = new AvgRespTimeTest("Test #1", "Verify average login times", "Login", 4000);
        RespTimeStdDevTest respTimeStdDevTest = new RespTimeStdDevTest("Test #2", "Verify standard deviation", "Search", 500);
        PassedTransactionsTest passedTransactionsTest = new PassedTransactionsTest("Test #3", "Verify number of passed tests", "Login", 0);

        TestSet testSet = new TestSet();
        testSet.load(TEST_SET_XML_FILE);

        assertThat(testSet.getTests(), hasSize(3));
        assertThat(testSet.getTests(), hasItem(avgRespTimeTestTest));
        assertThat(testSet.getTests(), hasItem(passedTransactionsTest));
        assertThat(testSet.getTests(), hasItem(respTimeStdDevTest));
    }

    @Test
    public void verifyExecuteMethod() {
        JMeterTransactions jmeterTranactions = JMeterCSVFileReader.read(VALID_CSV_FILE);

        TestSet testSet = new TestSet();
        testSet.load(TEST_SET_XML_FILE);
        testSet.execute(jmeterTranactions);

        assertThat(testSet.getPassCount(), is(3));
        assertThat(testSet.getFailCount(), is(0));
        assertThat(testSet.getErrorCount(), is(0));
    }
}