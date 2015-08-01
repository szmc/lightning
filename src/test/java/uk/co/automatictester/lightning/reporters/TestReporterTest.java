package uk.co.automatictester.lightning.reporters;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.ConsoleOutputTest;
import uk.co.automatictester.lightning.TestResult;
import uk.co.automatictester.lightning.tests.PassedTransactionsTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestReporterTest extends ConsoleOutputTest {

    @Test
    public void testPrintTestExecutionReportPass() {
        String expectedOutput = String.format("Test name:        my name%n" +
                "Test type:        my type%n" +
                "Test description: my description%n" +
                "Transaction name: my transaction%n" +
                "Expected result:  my expectations%n" +
                "Actual result:    my reality%n" +
                "Test result:      Pass");

        PassedTransactionsTest test = mock(PassedTransactionsTest.class);
        when(test.getDescription()).thenReturn("my description");
        when(test.getTransactionName()).thenReturn("my transaction");
        when(test.getName()).thenReturn("my name");
        when(test.getType()).thenReturn("my type");
        when(test.getExpectedResult()).thenReturn("my expectations");
        when(test.getActualResult()).thenReturn("my reality");
        when(test.getResult()).thenReturn(TestResult.PASS);

        configureStream();
        new TestReporter(test).printTestExecutionReport();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }

    @Test
    public void testPrintTestExecutionReportFail() {
        String expectedOutput = String.format("Test name:        my name%n" +
                "Test type:        my type%n" +
                "Test description: my description%n" +
                "Transaction name: my transaction%n" +
                "Expected result:  my expectations%n" +
                "Actual result:    my reality%n" +
                "Test result:      FAIL");

        PassedTransactionsTest test = mock(PassedTransactionsTest.class);
        when(test.getDescription()).thenReturn("my description");
        when(test.getTransactionName()).thenReturn("my transaction");
        when(test.getName()).thenReturn("my name");
        when(test.getType()).thenReturn("my type");
        when(test.getExpectedResult()).thenReturn("my expectations");
        when(test.getActualResult()).thenReturn("my reality");
        when(test.getResult()).thenReturn(TestResult.FAIL);

        configureStream();
        new TestReporter(test).printTestExecutionReport();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }

    @Test
    public void testPrintTestExecutionReportIgnored() {
        String expectedOutput = String.format("Test name:        my name%n" +
                "Test type:        my type%n" +
                "Test description: my description%n" +
                "Transaction name: my transaction%n" +
                "Expected result:  my expectations%n" +
                "Actual result:    my reality%n" +
                "Test result:      IGNORED");

        PassedTransactionsTest test = mock(PassedTransactionsTest.class);
        when(test.getDescription()).thenReturn("my description");
        when(test.getTransactionName()).thenReturn("my transaction");
        when(test.getName()).thenReturn("my name");
        when(test.getType()).thenReturn("my type");
        when(test.getExpectedResult()).thenReturn("my expectations");
        when(test.getActualResult()).thenReturn("my reality");
        when(test.getResult()).thenReturn(TestResult.IGNORED);

        configureStream();
        new TestReporter(test).printTestExecutionReport();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }

    @Test
    public void testPrintTestExecutionReportPassNoDescription() {
        String expectedOutput = String.format("Test name:        my name%n" +
                "Test type:        my type%n" +
                "Transaction name: my transaction%n" +
                "Expected result:  my expectations%n" +
                "Actual result:    my reality%n" +
                "Test result:      Pass");

        PassedTransactionsTest test = mock(PassedTransactionsTest.class);
        when(test.getDescription()).thenReturn("");
        when(test.getTransactionName()).thenReturn("my transaction");
        when(test.getName()).thenReturn("my name");
        when(test.getType()).thenReturn("my type");
        when(test.getExpectedResult()).thenReturn("my expectations");
        when(test.getActualResult()).thenReturn("my reality");
        when(test.getResult()).thenReturn(TestResult.PASS);

        configureStream();
        new TestReporter(test).printTestExecutionReport();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }

    @Test
    public void testPrintTestExecutionReportPassNoTransactionName() {
        String expectedOutput = String.format("Test name:        my name%n" +
                "Test type:        my type%n" +
                "Test description: my description%n" +
                "Expected result:  my expectations%n" +
                "Actual result:    my reality%n" +
                "Test result:      Pass");

        PassedTransactionsTest test = mock(PassedTransactionsTest.class);
        when(test.getDescription()).thenReturn("my description");
        when(test.getName()).thenReturn("my name");
        when(test.getType()).thenReturn("my type");
        when(test.getExpectedResult()).thenReturn("my expectations");
        when(test.getActualResult()).thenReturn("my reality");
        when(test.getResult()).thenReturn(TestResult.PASS);

        configureStream();
        new TestReporter(test).printTestExecutionReport();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }
}