package uk.co.automatictester.lightning.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;

public class AvgRespTimeTestTest {

    @Test
    public void testExecutePass() {
        ArrayList<String> txn1 = new ArrayList<>();
        txn1.add(0, "transaction x");
        txn1.add(1, "1200");
        txn1.add(2, "true");

        ArrayList<String> txn2 = new ArrayList<>();
        txn2.add(0, "transaction x");
        txn2.add(1, "800");
        txn2.add(2, "true");

        ArrayList<String> txn3 = new ArrayList<>();
        txn3.add(0, "transaction y");
        txn3.add(1, "1000");
        txn3.add(2, "true");

        JMeterTransactions txns = new JMeterTransactions();
        txns.add(txn1);
        txns.add(txn2);
        txns.add(txn3);

        AvgRespTimeTest test = new AvgRespTimeTest("test a", "description", "transaction x", 1000);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, Matchers.containsString("Test name:        test a"));
        assertThat(testReport, Matchers.containsString("Transaction name: transaction x"));
        assertThat(testReport, Matchers.containsString("Expected result:  Average response time <= 1000"));
        assertThat(testReport, Matchers.containsString("Actual result:    Average response time = 1000.0"));
        assertThat(testReport, Matchers.containsString("Test result:      Pass"));

    }

    public void testExecuteFail() {
        ArrayList<String> txn1 = new ArrayList<>();
        txn1.add(0, "transaction x");
        txn1.add(1, "1200");
        txn1.add(2, "true");

        ArrayList<String> txn2 = new ArrayList<>();
        txn2.add(0, "transaction y");
        txn2.add(1, "800");
        txn2.add(2, "true");

        ArrayList<String> txn3 = new ArrayList<>();
        txn3.add(0, "transaction x");
        txn3.add(1, "1000");
        txn3.add(2, "true");

        JMeterTransactions txns = new JMeterTransactions();
        txns.add(txn1);
        txns.add(txn2);
        txns.add(txn3);

        AvgRespTimeTest test = new AvgRespTimeTest("test a", "description", "transaction x", 1000);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, Matchers.containsString("Test name:        test a"));
        assertThat(testReport, Matchers.containsString("Transaction name: transaction x"));
        assertThat(testReport, Matchers.containsString("Expected result:  Average response time <= 1000"));
        assertThat(testReport, Matchers.containsString("Actual result:    Average response time = 1100.0"));
        assertThat(testReport, Matchers.containsString("Test result:      FAIL"));

    }
}