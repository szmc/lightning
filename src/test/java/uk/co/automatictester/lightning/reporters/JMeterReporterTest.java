package uk.co.automatictester.lightning.reporters;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uk.co.automatictester.lightning.ConsoleOutputTest;
import uk.co.automatictester.lightning.JMeterTransactions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JMeterReporterTest extends ConsoleOutputTest {

    @DataProvider(name = "counts")
    private Integer[][] counts() {
        return new Integer[][]{
                {10, 0},
                {10, 3}
        };
    }

    @Test(dataProvider = "counts")
    public void testPrintJMeterReport(int total, int failures) {
        String expectedOutput = String.format("Transactions executed: %d, failed: %d", total, failures);

        JMeterTransactions transactions = mock(JMeterTransactions.class);
        when(transactions.getTransactionCount()).thenReturn(total);
        when(transactions.getFailCount()).thenReturn(failures);

        configureStream();
        new JMeterReporter(transactions).printJMeterReport();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }
}