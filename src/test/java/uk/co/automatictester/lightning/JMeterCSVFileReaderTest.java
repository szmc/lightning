package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.automatictester.lightning.data.TestData.*;

public class JMeterCSVFileReaderTest {

    @Test
    public void verifyReadMethod() {
        JMeterTransactions jmeterTransactions = new JMeterCSVFileReader().read(VALID_CSV_FILE);
        assertThat(jmeterTransactions, hasItem(LOGIN_3514_SUCCESS));
        assertThat(jmeterTransactions, hasItem(SEARCH_11221_SUCCESS));
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Column name '" + MISSING_COLUMN_NAME + "' not found in first row of CSV file")
    public void verifyReadMethodRuntimeException() {
        new JMeterCSVFileReader().read(CSV_FILE_WITH_MISSING_LABEL_COLUMN);
    }
}