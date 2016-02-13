package uk.co.automatictester.lightning.reporters;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.ConsoleOutputTest;
import uk.co.automatictester.lightning.enums.TestResult;
import uk.co.automatictester.lightning.tests.RespTimeAvgTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RespTimeBasedTestReporterTest extends ConsoleOutputTest {

    @Test
    public void testPrintTestExecutionReport() {
        String expectedOutput = String.format("Test name:            my name%n" +
                "Test type:            my type%n" +
                "Test description:     my description%n" +
                "Transaction name:     my transaction%n" +
                "Expected result:      my expectations%n" +
                "Actual result:        my reality%n" +
                "Transaction count:    10%n" +
                "Longest transactions: [3, 2, 1]%n" +
                "Test result:          Pass");

        RespTimeAvgTest test = mock(RespTimeAvgTest.class);
        when(test.getDescription()).thenReturn("my description");
        when(test.getTransactionName()).thenReturn("my transaction");
        when(test.getName()).thenReturn("my name");
        when(test.getType()).thenReturn("my type");
        when(test.getExpectedResult()).thenReturn("my expectations");
        when(test.getActualResult()).thenReturn("my reality");
        when(test.getTransactionCount()).thenReturn(10);
        when(test.getLongestTransactions()).thenReturn(new ArrayList<>(Arrays.asList(3, 2, 1)));
        when(test.getResult()).thenReturn(TestResult.PASS);

        configureStream();
        new RespTimeBasedTestReporter(test).printTestExecutionReport();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }
}