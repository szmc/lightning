package uk.co.automatictester.lightning.integration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uk.co.automatictester.lightning.TestRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TestRunnerIT {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public void configureStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test(groups = "INTEGRATION")
    public void testRunTests() {
        String ls = System.lineSeparator();
        String expectedOutput = "Test name:        Test #1" + ls +
                "Test description: Verify average login times" + ls +
                "Transaction name: Login" + ls +
                "Expected result:  Average response time <= 4000" + ls +
                "Actual result:    Average response time = 3514.0" + ls +
                "Test result:      Pass" + ls +
                ls +
                "Test name:        Test #2" + ls +
                "Test description: Verify standard deviation" + ls +
                "Transaction name: Search" + ls +
                "Expected result:  Average standard deviance time <= 500" + ls +
                "Actual result:    Average standard deviance time = 0.0" + ls +
                "Test result:      Pass" + ls +
                ls +
                "Test name:        Test #3" + ls +
                "Test description: Verify number of passed tests" + ls +
                "Transaction name: Login" + ls +
                "Expected result:  Number of failed transactions <= 0" + ls +
                "Actual result:    Number of failed transactions = 0" + ls +
                "Test result:      Pass" + ls +
                ls +
                ls +
                "============= EXECUTION SUMMARY =============" + ls +
                "Tests executed:   3" + ls +
                "Tests passed:     3" + ls +
                "Tests failed:     0" + ls +
                "Test set status:  Pass";


        String[] cmdLineParams = new String[]{"-xml=src/test/resources/xml/TestSetTest.xml", "-csv=src/test/resources/csv/ValidJMeterCSVFile.csv"};
        TestRunner.runTests(cmdLineParams);

        assertThat(outContent.toString(), containsString(expectedOutput));
    }

    @AfterClass
    public void revertStreams() {
        System.setOut(null);
    }
}