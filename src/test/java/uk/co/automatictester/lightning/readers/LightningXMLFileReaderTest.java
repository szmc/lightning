package uk.co.automatictester.lightning.readers;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.exceptions.*;
import uk.co.automatictester.lightning.tests.*;
import uk.co.automatictester.lightning.utils.Percent;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static uk.co.automatictester.lightning.shared.TestData.*;

public class LightningXMLFileReaderTest {

    @Test
    public void verifyGetTestsMethodPercentileTest() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_PERCENTILE);
        RespTimeNthPercentileTest test = new RespTimeNthPercentileTest("Test #4", "nthPercRespTimeTest", "Verify nth percentile", "Search", 80, 11245);

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodStdDevTest() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_STD_DEV);
        RespTimeStdDevTest test = new RespTimeStdDevTest("Test #2", "respTimeStdDevTest", "Verify standard deviation", "Search", 500);

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodPassedTest() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_PASSED);
        PassedTransactionsTest test = new PassedTransactionsTest("Test #3", "passedTransactionsTest", "Verify number of passed tests", "Login", 0);

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodPassedPercentTest() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_PASSED_PERCENT);
        PassedTransactionsTest test = new PassedTransactionsTest("Test #3", "passedTransactionsTest", "Verify percent of passed tests", "Login", new Percent(0));

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodAvgRespTime() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_AVG_RESP_TIME);
        RespTimeAvgTest test = new RespTimeAvgTest("Test #1", "avgRespTimeTest", "Verify average login times", "Login", 4000);

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodMaxRespTime() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_MAX_RESP_TIME);
        RespTimeMaxTest test = new RespTimeMaxTest("Test #1", "maxRespTimeTest", "Verify max login times", "Login", 4000);

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodMedianRespTime() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_MEDIAN);
        RespTimeMedianTest test = new RespTimeMedianTest("Test #4", "medianRespTimeTest", "Verify median response time", "Search", 11244);

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodThroughput() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_THROUGHPUT);
        ThroughputTest test = new ThroughputTest("Test #2", "throughputTest", "Verify throughput", null, 2);

        assertThat(tests, hasSize(1));
        assertThat(tests.contains(test), is(true));
    }

    @Test
    public void verifyGetTestsMethodThreeTestsOfTwoKinds() {
        List<LightningTest> tests = new LightningXMLFileReader().getTests(TEST_SET_3_0_0);

        assertThat(tests, hasSize(3));
        assertThat(tests.contains(PASSED_TRANSACTIONS_TEST_3_0_0_A), is(true));
        assertThat(tests.contains(PASSED_TRANSACTIONS_TEST_3_0_0_B), is(true));
        assertThat(tests.contains(RESP_TIME_PERC_TEST_3_0_0_C), is(true));
    }

    @Test(expectedExceptions = XMLFileException.class)
    public void verifyGetTestsMethodThrowsXMLFileLoadingException() {
        // suppress error output - coming NOT from own code
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        new LightningXMLFileReader().getTests(TEST_SET_NOT_WELL_FORMED);

        System.setErr(null);
    }

    @Test(expectedExceptions = XMLFileNumberFormatException.class)
    public void verifyGetTestsMethodThrowsXMLFileNumberFormatException() {
        new LightningXMLFileReader().getTests(TEST_SET_XML_FILE_NUMBER_FORMAT_EXCEPTION);
    }

    @Test(expectedExceptions = XMLFileMissingElementValueException.class)
    public void verifyGetTestsMethodThrowsXMLFileMissingElementValueException() {
        new LightningXMLFileReader().getTests(TEST_SET_XML_FILE_MISSING_ELEMENT_VALUE_EXCEPTION);
    }

    @Test(expectedExceptions = XMLFileMissingElementException.class)
    public void verifyGetTestsMethodThrowsXMLFileMissingElementException() {
        new LightningXMLFileReader().getTests(TEST_SET_XML_FILE_MISSING_ELEMENT_EXCEPTION);
    }

    @Test(expectedExceptions = XMLFilePercentileException.class)
    public void verifyGetTestsMethodThrowsXMLFilePercentileException() {
        new LightningXMLFileReader().getTests(TEST_SET_XML_FILE_PERCENTILE_EXCEPTION);
    }

    @Test(expectedExceptions = XMLFileNoTestsException.class)
    public void verifyGetTestsMethodThrowsXMLFileNoTestsException() {
        new LightningXMLFileReader().getTests(TEST_SET_0_0_0);
    }

}