package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class PassedTransactionsTestTest {

    @Test
    public void testExecutePass() {
        ArrayList<String> txn1 = new ArrayList<>();
        txn1.add(0, "transaction x");
        txn1.add(1, "1200");
        txn1.add(2, "true");

        JMeterTransactions txns = new JMeterTransactions();
        txns.add(txn1);

        PassedTransactionsTest test = new PassedTransactionsTest("test a", "description", "transaction x", 0);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, containsString("Test name:        test a"));
        assertThat(testReport, containsString("Transaction name: transaction x"));
        assertThat(testReport, containsString("Expected result:  Number of failed transactions <= 0"));
        assertThat(testReport, containsString("Actual result:    Number of failed transactions = 0"));
        assertThat(testReport, containsString("Test result:      Pass"));
    }

    @Test
    public void testExecuteFail() {
        ArrayList<String> txn1 = new ArrayList<>();
        txn1.add(0, "transaction x");
        txn1.add(1, "1200");
        txn1.add(2, "false");

        JMeterTransactions txns = new JMeterTransactions();
        txns.add(txn1);

        PassedTransactionsTest test = new PassedTransactionsTest("test a", "description", "transaction x", 0);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, containsString("Test name:        test a"));
        assertThat(testReport, containsString("Transaction name: transaction x"));
        assertThat(testReport, containsString("Expected result:  Number of failed transactions <= 0"));
        assertThat(testReport, containsString("Actual result:    Number of failed transactions = 1"));
        assertThat(testReport, containsString("Test result:      FAIL"));
    }

    @Test
    public void testIsEqual() {
        PassedTransactionsTest test1 = new PassedTransactionsTest("test a", "description", "transaction x", 1);
        PassedTransactionsTest test2 = new PassedTransactionsTest("test a", "description", "transaction x", 1);
        assertThat(test1, is(equalTo(test2)));
    }

    @Test
    public void testIsNotEqual() {
        PassedTransactionsTest test1 = new PassedTransactionsTest("test a", "description", "transaction x", 1);
        PassedTransactionsTest test2 = new PassedTransactionsTest("test a", "description", "transaction x", 0);
        assertThat(test1, is(not(equalTo(test2))));
    }
}