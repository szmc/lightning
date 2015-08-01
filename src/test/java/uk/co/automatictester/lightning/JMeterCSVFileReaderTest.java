package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.exceptions.CSVFileIOException;
import uk.co.automatictester.lightning.exceptions.CSVFileMissingColumnNameException;
import uk.co.automatictester.lightning.readers.JMeterCSVFileReader;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.automatictester.lightning.data.TestData.*;

public class JMeterCSVFileReaderTest {

    @Test
    public void verifyReadMethod() {
        JMeterTransactions jmeterTransactions = new JMeterCSVFileReader().getTransactions(CSV_2_TRANSACTIONS);
        assertThat(jmeterTransactions, hasItem(LOGIN_3514_SUCCESS));
        assertThat(jmeterTransactions, hasItem(SEARCH_11221_SUCCESS));
    }

    @Test(expectedExceptions = CSVFileMissingColumnNameException.class)
    public void verifyReadMethodMissingColumnNameException() {
        new JMeterCSVFileReader().getTransactions(CSV_MISSING_LABEL_COLUMN);
    }

    @Test(expectedExceptions = CSVFileIOException.class)
    public void verifyReadMethodIOException() {
        new JMeterCSVFileReader().getTransactions(CSV_NONEXISTENT);
    }
}