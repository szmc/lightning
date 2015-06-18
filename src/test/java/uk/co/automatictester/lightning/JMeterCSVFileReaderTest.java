package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JMeterCSVFileReaderTest {

    private static final String NONEXISTENT_FILE = "src/test/resources/nonexistent.csv";

    @Test
    public void testReadCheckTransactionData() {
        JMeterTransactions txns = JMeterCSVFileReader.read("src/test/resources/JMeterCSVFileReaderTest.csv");

        List<String> txn1 = txns.get(0);
        assertThat(txn1.get(0), is("Login"));
        assertThat(txn1.get(1), is("3514"));
        assertThat(txn1.get(2), is("true"));

        List<String> txn2 = txns.get(1);
        assertThat(txn2.get(0), is("Search"));
        assertThat(txn2.get(1), is("11221"));
        assertThat(txn2.get(2), is("true"));

    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Error reading CSV file: " + NONEXISTENT_FILE + ".*")
    public void testReadNonexistentFile() {
        JMeterCSVFileReader.read(NONEXISTENT_FILE);
    }
}