package uk.co.automatictester.lightning.tests;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.JMeterTransactions;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class RespTimeStdDevTestTest {

    @Test
    public void testExecutePass() {
        ArrayList<String> txn1 = new ArrayList<>();
        txn1.add(0, "transaction x");
        txn1.add(1, "2");
        txn1.add(2, "true");

        ArrayList<String> txn2 = new ArrayList<>();
        txn2.add(0, "transaction x");
        txn2.add(1, "3");
        txn2.add(2, "true");

        ArrayList<String> txn3 = new ArrayList<>();
        txn3.add(0, "transaction x");
        txn3.add(1, "4");
        txn3.add(2, "true");

        JMeterTransactions txns = new JMeterTransactions();
        txns.add(txn1);
        txns.add(txn2);
        txns.add(txn3);

        RespTimeStdDevTest test = new RespTimeStdDevTest("test a", "description", "transaction x", 1);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, containsString("Test name:        test a"));
        assertThat(testReport, containsString("Transaction name: transaction x"));
        assertThat(testReport, containsString("Expected result:  Average standard deviance time <= 1"));
        assertThat(testReport, containsString("Actual result:    Average standard deviance time = 1.0"));
        assertThat(testReport, containsString("Test result:      Pass"));
    }

    @Test
    public void testExecuteFail() {
        ArrayList<String> txn1 = new ArrayList<>();
        txn1.add(0, "transaction x");
        txn1.add(1, "2");
        txn1.add(2, "true");

        ArrayList<String> txn2 = new ArrayList<>();
        txn2.add(0, "transaction x");
        txn2.add(1, "3");
        txn2.add(2, "true");

        ArrayList<String> txn3 = new ArrayList<>();
        txn3.add(0, "transaction x");
        txn3.add(1, "4");
        txn3.add(2, "true");

        JMeterTransactions txns = new JMeterTransactions();
        txns.add(txn1);
        txns.add(txn2);
        txns.add(txn3);

        RespTimeStdDevTest test = new RespTimeStdDevTest("test a", "description", "transaction x", 0);
        test.execute(txns);
        String testReport = test.getReport();

        assertThat(testReport, containsString("Test name:        test a"));
        assertThat(testReport, containsString("Transaction name: transaction x"));
        assertThat(testReport, containsString("Expected result:  Average standard deviance time <= 0"));
        assertThat(testReport, containsString("Actual result:    Average standard deviance time = 1.0"));
        assertThat(testReport, containsString("Test result:      FAIL"));
    }

    @Test
    public void testIsEqual() {
        RespTimeStdDevTest test1 = new RespTimeStdDevTest("test a", "description", "transaction x", 1);
        RespTimeStdDevTest test2 = new RespTimeStdDevTest("test a", "description", "transaction x", 1);
        assertThat(test1, is(equalTo(test2)));
    }

    @Test
    public void testIsNotEqual() {
        RespTimeStdDevTest test1 = new RespTimeStdDevTest("test a", "description", "transaction x", 1);
        RespTimeStdDevTest test2 = new RespTimeStdDevTest("test a", "description", "transaction x", 0);
        assertThat(test1, is(not(equalTo(test2))));
    }
}