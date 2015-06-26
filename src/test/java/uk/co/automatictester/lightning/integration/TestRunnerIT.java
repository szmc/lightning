package uk.co.automatictester.lightning.integration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uk.co.automatictester.lightning.TestRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_1_1_1;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_CSV_FILE;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_XML_FILE;

public class TestRunnerIT {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public void configureStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test(groups = "INTEGRATION")
    public void verifyRunTests_300() {
        String expectedOutput = String.format("Test name:        Test #1%n"
                + "Test description: Verify average login times%n"
                + "Transaction name: Login%n"
                + "Expected result:  Average response time <= 4000%n"
                + "Actual result:    Average response time = 3583.2%n"
                + "Test result:      Pass%n%n"
                + "Test name:        Test #2%n"
                + "Test description: Verify standard deviation%n"
                + "Transaction name: Search%n"
                + "Expected result:  Average standard deviance time <= 500%n"
                + "Actual result:    Average standard deviance time = 279.07%n"
                + "Test result:      Pass%n%n"
                + "Test name:        Test #3%n"
                + "Test description: Verify number of passed tests%n"
                + "Transaction name: Login%n"
                + "Expected result:  Number of failed transactions <= 0%n"
                + "Actual result:    Number of failed transactions = 0%n"
                + "Test result:      Pass%n%n%n"
                + "============= EXECUTION SUMMARY =============%n"
                + "Tests executed:    3%n"
                + "Tests passed:      3%n"
                + "Tests failed:      0%n"
                + "Tests with errors: 0%n"
                + "Test set status:   Pass");


        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_XML_FILE, "-csv=" + TEST_SET_CSV_FILE};
        TestRunner.runTests(cmdLineParams);

        assertThat(outContent.toString(), containsString(expectedOutput));
    }

    @Test(groups = "INTEGRATION")
    public void verifyRunTests_111() {
        String expectedOutput = String.format("Test name:        Test #1%n"
                + "Test description: Verify average login times%n"
                + "Transaction name: Login%n"
                + "Expected result:  Average response time <= 1%n"
                + "Actual result:    Average response time = 3583.2%n"
                + "Test result:      FAIL%n%n"
                + "Test name:        Test #2%n"
                + "Test description: Verify standard deviation%n"
                + "Transaction name: Search%n"
                + "Expected result:  Average standard deviance time <= 500%n"
                + "Actual result:    Average standard deviance time = 279.07%n"
                + "Test result:      Pass%n%n"
                + "Test name:        Test #3%n"
                + "Test description: Verify number of passed tests%n"
                + "Transaction name: Logi%n"
                + "Expected result:  Number of failed transactions <= 0%n"
                + "Actual result:    No transactions with label equal to 'Logi' found in CSV file%n"
                + "Test result:      ERROR%n%n%n"
                + "============= EXECUTION SUMMARY =============%n"
                + "Tests executed:    3%n"
                + "Tests passed:      1%n"
                + "Tests failed:      1%n"
                + "Tests with errors: 1%n"
                + "Test set status:   FAIL%n%n");


        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_1_1_1, "-csv=" + TEST_SET_CSV_FILE};
        TestRunner.runTests(cmdLineParams);

        assertThat(outContent.toString(), containsString(expectedOutput));
    }

    @AfterClass
    public void revertStreams() {
        System.setOut(null);
    }
}