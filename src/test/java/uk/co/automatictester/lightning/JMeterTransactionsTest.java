package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JMeterTransactionsTest {

    @Test
    public void testExcludeLabelsOtherThan() {
        JMeterTransactions txns = new JMeterTransactions();

        ArrayList<String> txn1 = new ArrayList<>();
        ArrayList<String> txn2 = new ArrayList<>();
        ArrayList<String> txn3 = new ArrayList<>();

        txn1.add("Login");
        txn1.add("1200");

        txn2.add("Login");
        txn2.add("1000");

        txn3.add("Search");
        txn3.add("800");

        txns.add(txn1);
        txns.add(txn2);
        txns.add(txn3);
        assertThat(txns.size(), is(3));

        JMeterTransactions filteredTxns = txns.excludeLabelsOtherThan("Login");
        assertThat(filteredTxns.size(), is(2));
    }
}