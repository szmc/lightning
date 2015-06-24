package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.tests.AvgRespTimeTest;
import uk.co.automatictester.lightning.tests.PassedTransactionsTest;
import uk.co.automatictester.lightning.tests.RespTimeStdDevTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestSetTest {

    @Test
    public void testLoadValidFile() {
        AvgRespTimeTest avgRespTimeTestTest = new AvgRespTimeTest("Test #1", "Verify average login times", "Login", 4000);
        RespTimeStdDevTest respTimeStdDevTest = new RespTimeStdDevTest("Test #2", "Verify standard deviation", "Search", 500);
        PassedTransactionsTest passedTransactionsTest = new PassedTransactionsTest("Test #3", "Verify number of passed tests", "Login", 0);

        TestSet testSet = new TestSet();
        testSet.load("src/test/resources/xml/TestSetTest.xml");
        List<uk.co.automatictester.lightning.tests.Test> ts = testSet.getTests();

        assertThat(ts, hasSize(3));
        assertThat(ts, hasItem(avgRespTimeTestTest));
        assertThat(ts, hasItem(passedTransactionsTest));
        assertThat(ts, hasItem(respTimeStdDevTest));
    }

    @Test
    public void testGetTestSetExecutionSummaryReport() {
        JMeterTransactions originalJMeterTransactions = JMeterCSVFileReader.read("src/test/resources/csv/ValidJMeterCSVFile.csv");
        TestSet testSet = new TestSet();
        testSet.load("src/test/resources/xml/TestSetTest.xml");
        testSet.execute(originalJMeterTransactions);
        String testSetExecReport = testSet.getTestSetExecutionSummaryReport();

        String ls = System.lineSeparator();
        String summaryReport = "============= EXECUTION SUMMARY =============" + ls +
                "Tests executed:    3" + ls +
                "Tests passed:      3" + ls +
                "Tests failed:      0" + ls +
                "Tests with errors: 0" + ls +
                "Test set status:   Pass" + ls;

        assertThat(testSetExecReport, containsString(summaryReport));
    }

    @Test
    public void testGetTestSetExecutionReport() {
        JMeterTransactions originalJMeterTransactions = JMeterCSVFileReader.read("src/test/resources/csv/ValidJMeterCSVFile.csv");
        TestSet testSet = new TestSet();
        testSet.load("src/test/resources/xml/TestSetTest.xml");
        testSet.execute(originalJMeterTransactions);
        String testSetExecReport = testSet.getTestSetExecutionReport();

        String ls = System.lineSeparator();

        String test1 = "Test name:        Test #1" + ls +
                "Test description: Verify average login times" + ls +
                "Transaction name: Login" + ls +
                "Expected result:  Average response time <= 4000" + ls +
                "Actual result:    Average response time = 3514.0" + ls +
                "Test result:      Pass";

        String test2 = "Test name:        Test #2" + ls +
                "Test description: Verify standard deviation" + ls +
                "Transaction name: Search" + ls +
                "Expected result:  Average standard deviance time <= 500" + ls +
                "Actual result:    Average standard deviance time = 0.0" + ls +
                "Test result:      Pass";

        String test3 = "Test name:        Test #3" + ls +
                "Test description: Verify number of passed tests" + ls +
                "Transaction name: Login" + ls +
                "Expected result:  Number of failed transactions <= 0" + ls +
                "Actual result:    Number of failed transactions = 0" + ls +
                "Test result:      Pass";

        assertThat(testSetExecReport, containsString(test1));
        assertThat(testSetExecReport, containsString(test2));
        assertThat(testSetExecReport, containsString(test3));
    }
}