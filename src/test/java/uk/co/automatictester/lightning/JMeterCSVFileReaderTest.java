package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JMeterCSVFileReaderTest {

    private static final String FILE_WITH_MISSING_COLUMN = "src/test/resources/csv/JMeterCSVFileReaderTestWithMissingColumn.csv";

    @Test
    public void testReadCheckTransactionData() {
        JMeterTransactions txns = JMeterCSVFileReader.read("src/test/resources/csv/ValidJMeterCSVFile.csv");

        List<String> txn1 = txns.get(0);
        assertThat(txn1.get(0), is("Login"));
        assertThat(txn1.get(1), is("3514"));
        assertThat(txn1.get(2), is("true"));

        List<String> txn2 = txns.get(1);
        assertThat(txn2.get(0), is("Search"));
        assertThat(txn2.get(1), is("11221"));
        assertThat(txn2.get(2), is("true"));

    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Column name 'label' not found in first row of CSV file")
    public void testReadFileWithMissingColumn() {
        JMeterCSVFileReader.read(FILE_WITH_MISSING_COLUMN);
    }
}