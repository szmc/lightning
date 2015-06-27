package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.exceptions.CSVFileIOException;
import uk.co.automatictester.lightning.exceptions.CSVFileMissingColumnNameException;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.automatictester.lightning.data.TestData.*;

public class JMeterCSVFileReaderTest {

    @Test
    public void verifyReadMethod() {
        JMeterTransactions jmeterTransactions = new JMeterCSVFileReader().read(CSV_2_TRANSACTIONS);
        assertThat(jmeterTransactions, hasItem(LOGIN_3514_SUCCESS));
        assertThat(jmeterTransactions, hasItem(SEARCH_11221_SUCCESS));
    }

    @Test(expectedExceptions = CSVFileMissingColumnNameException.class)
    public void verifyReadMethodMissingColumnNameException() {
        new JMeterCSVFileReader().read(CSV_MISSING_LABEL_COLUMN);
    }

    @Test(expectedExceptions = CSVFileIOException.class)
    public void verifyReadMethodIOException() {
        new JMeterCSVFileReader().read(CSV_NONEXISTENT);
    }
}