package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.exceptions.CSVFileNonexistentLabelException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JMeterTransactionsTest {

    @Test
    public void testExcludeLabelsOtherThanMethod() {
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1200", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1000", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Search", "800", "true")));

        assertThat(jmeterTransactions.excludeLabelsOtherThan("Login").size(), is(2));
    }

    @Test(expectedExceptions = CSVFileNonexistentLabelException.class)
    public void testExcludeLabelsOtherThanMethodException() {
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1200", "true")));

        jmeterTransactions.excludeLabelsOtherThan("nonexistent");
    }

    @Test
    public void testGetFailCount() {
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1200", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1000", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Search", "800", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1200", "false")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Search", "800", "false")));

        assertThat(jmeterTransactions.getFailCount(), is(equalTo(2)));
    }

    @Test
    public void testTransactionCount() {
        JMeterTransactions jmeterTransactions = new JMeterTransactions();
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1200", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1000", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Search", "800", "true")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Login", "1200", "false")));
        jmeterTransactions.add(new ArrayList<>(Arrays.asList("Search", "800", "false")));

        assertThat(jmeterTransactions.getTransactionCount(), is(equalTo(5)));
    }
}